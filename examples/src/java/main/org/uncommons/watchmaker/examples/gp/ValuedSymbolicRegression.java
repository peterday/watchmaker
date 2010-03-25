package org.uncommons.watchmaker.examples.gp;

import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.examples.EvolutionOutputStreamLogger;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.FitnessEvaluator;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.operators.SplitEvolution;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.GenerationCount;
import org.uncommons.watchmaker.impl.gp.evalute.DoubleDataEvaluator;
import org.uncommons.watchmaker.impl.gp.inputParameter.DoubleParameter;
import org.uncommons.watchmaker.impl.gp.inputParameter.MapParameterValues;
import org.uncommons.watchmaker.impl.gp.inputParameter.Parameter;
import org.uncommons.watchmaker.impl.gp.inputParameter.ParameterValues;
import org.uncommons.watchmaker.impl.gp.node.Node;
import org.uncommons.watchmaker.impl.gp.node.factory.DefaultNodeFactory;
import org.uncommons.watchmaker.impl.gp.node.math.*;
import org.uncommons.watchmaker.impl.gp.node.pool.NodePool;
import org.uncommons.watchmaker.impl.gp.node.pool.SimpleNodePool;
import org.uncommons.watchmaker.impl.gp.node.terminal.ConstantNode;
import org.uncommons.watchmaker.impl.gp.node.terminal.ConstantNodeFactory;
import org.uncommons.watchmaker.impl.gp.node.terminal.InputParameterNodeFactory;
import org.uncommons.watchmaker.impl.gp.node.terminal.UniformRandomConstantNodeFactory;
import org.uncommons.watchmaker.impl.gp.operators.NumberPerterber;
import org.uncommons.watchmaker.impl.gp.operators.TreeCrossover;
import org.uncommons.watchmaker.impl.gp.operators.TreeMutation;
import org.uncommons.watchmaker.impl.gp.parsimony.ParsimonyEvaluator;
import org.uncommons.watchmaker.impl.gp.parsimony.ParsimonyFitnessEvaluatorWrapper;
import org.uncommons.watchmaker.impl.gp.parsimony.ParsimonyPressureFitnessEvaluator;
import org.uncommons.watchmaker.impl.gp.parsimony.SimpleAdditiveParsimonyPressure;
import org.uncommons.watchmaker.impl.gp.parsimony.termination.ParsimonyFitnessTerminationCondition;
import org.uncommons.watchmaker.impl.gp.selectEvolve.SelectEvolveGenerationalEvolution;
import org.uncommons.watchmaker.impl.gp.selectEvolve.SelectEvolvePipeline;
import org.uncommons.watchmaker.impl.gp.selectEvolve.SimpleSelectEvolve;
import org.uncommons.watchmaker.impl.gp.selectEvolve.SplitSelectEvolve;
import org.uncommons.watchmaker.impl.gp.selection.SelectNodeWithChildOfTypeDet;
import org.uncommons.watchmaker.impl.gp.treeBuilder.SimpleTreeBuilder;
import org.uncommons.watchmaker.impl.gp.treeBuilder.TreeBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Peter Day
 */
public class ValuedSymbolicRegression {

    public static void main(String[] args) {
        //set up rng
        Random rng = new Random();

        //create the parameters
        Parameter<Double> xParam = new DoubleParameter("x");
        Parameter<Double> yParam = new DoubleParameter("y");
        Map<ParameterValues, Double> trainingData = createTrainingData(xParam,yParam);

        NodePool<Double> terminalPool = new SimpleNodePool<Double>();
        NodePool<Double> functionPool = new SimpleNodePool<Double>();


        //add the parameter inputs to the terminal pool
        terminalPool.addNodeFactory(new InputParameterNodeFactory(xParam), 1);
        terminalPool.addNodeFactory(new InputParameterNodeFactory(yParam), 1);

        //add some useful constants to the terminal pool
         terminalPool.addNodeFactory(new ConstantNodeFactory(1), 0.25);
         terminalPool.addNodeFactory(new ConstantNodeFactory(10), 0.25);
         terminalPool.addNodeFactory(new ConstantNodeFactory(-1), 0.25);
        
        //add some random constants in the range -10 -> 10
         terminalPool.addNodeFactory(new UniformRandomConstantNodeFactory(rng,-10,20), 1);       

        //add basic math operators to the function pool - equal probabilities
        functionPool.addNodeFactory(DefaultNodeFactory.createNodeFactory(new AddNode()), 1);
        functionPool.addNodeFactory(DefaultNodeFactory.createNodeFactory(new SafeDivideNode()), 1);
        functionPool.addNodeFactory(DefaultNodeFactory.createNodeFactory(new SqrtNode()), 1);
        functionPool.addNodeFactory(DefaultNodeFactory.createNodeFactory(new SubtractNode()), 1);
        functionPool.addNodeFactory(DefaultNodeFactory.createNodeFactory(new MultiplyNode()), 1);

        //create the tree builder
        TreeBuilder<Double> treeBuilder = new SimpleTreeBuilder(0, 10, functionPool, terminalPool, Probability.EVENS);

        //create the complex evolution pipeline

        //main operator acts on any individuals and performs crossover mutation
        EvolutionaryOperator<Node<Double>> mainOperator = new SplitEvolution(new TreeCrossover(1), new TreeMutation(treeBuilder,0,10), 0.9);
        SelectEvolvePipeline<Node<Double>> mainPipeline = new SimpleSelectEvolve(new RouletteWheelSelection(), mainOperator);

        //number perterber acts only on individuals with numeric constants
        SelectionStrategy numberSelectStrategy = new SelectNodeWithChildOfTypeDet(new RouletteWheelSelection(),
                ConstantNode.class);
        SelectEvolvePipeline<Node<Double>> numberPipeline = new SimpleSelectEvolve(numberSelectStrategy,
                new NumberPerterber(1));

        SelectEvolvePipeline<Node<Double>> combinedPipeline = new SplitSelectEvolve(numberPipeline, mainPipeline, 0.2);

        //set up fitness evaluator
        FitnessEvaluator<Node<Double>> fitnessEvaluator = new DoubleDataEvaluator(trainingData);
        ParsimonyEvaluator parsimony = new SimpleAdditiveParsimonyPressure(0.1);
        ParsimonyPressureFitnessEvaluator<Node<Double>> parsimonyFitness =
                new ParsimonyFitnessEvaluatorWrapper<Node<Double>>(fitnessEvaluator,parsimony);


        SelectEvolveGenerationalEvolution<Node<Double>> engine = new SelectEvolveGenerationalEvolution(treeBuilder, combinedPipeline,
               parsimonyFitness, rng);

        engine.addEvolutionObserver(new EvolutionOutputStreamLogger(System.out));

       Node<Double> result = engine.evolve(1000,5,new ParsimonyFitnessTerminationCondition(parsimonyFitness,0d,fitnessEvaluator.isNatural()), new GenerationCount(100));
       System.out.println(result); 

    }


    public static Map<ParameterValues, Double> createTrainingData(Parameter<Double> x, Parameter<Double> y) {
        Map<ParameterValues, Double> trainingData = new HashMap<ParameterValues, Double>();
        for (double i = -10; i<11; i++) {
            for (double j = -10; i<11; i++) {
                ParameterValues pv = new MapParameterValues();
                pv.setParameterValue(x, i);
                pv.setParameterValue(y, j);
                trainingData.put(pv, func(i,j));
            }
        }

        return trainingData;
    }


    public static final double func(double x, double y) {

        return x*x*y*3.2 + x*y*10.33 + 0.005*y*y + Math.sqrt(Math.abs(x*2.5));
    }

}
