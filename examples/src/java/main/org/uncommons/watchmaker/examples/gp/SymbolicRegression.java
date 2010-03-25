//=============================================================================
// Copyright 2006-2010 Daniel W. Dyer
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//=============================================================================
package org.uncommons.watchmaker.examples.gp;

import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.examples.EvolutionOutputStreamLogger;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
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
import org.uncommons.watchmaker.impl.gp.node.math.AddNode;
import org.uncommons.watchmaker.impl.gp.node.math.MultiplyNode;
import org.uncommons.watchmaker.impl.gp.node.math.SafeDivideNode;
import org.uncommons.watchmaker.impl.gp.node.math.SubtractNode;
import org.uncommons.watchmaker.impl.gp.node.pool.NodePool;
import org.uncommons.watchmaker.impl.gp.node.pool.SimpleNodePool;
import org.uncommons.watchmaker.impl.gp.node.terminal.InputParameterNodeFactory;
import org.uncommons.watchmaker.impl.gp.operators.TreeCrossover;
import org.uncommons.watchmaker.impl.gp.operators.TreeMutation;
import org.uncommons.watchmaker.impl.gp.parsimony.ParsimonyFitnessEvaluatorWrapper;
import org.uncommons.watchmaker.impl.gp.parsimony.ParsimonyPressureFitnessEvaluator;
import org.uncommons.watchmaker.impl.gp.parsimony.SimpleAdditiveParsimonyPressure;
import org.uncommons.watchmaker.impl.gp.parsimony.termination.ParsimonyFitnessTerminationCondition;
import org.uncommons.watchmaker.impl.gp.treeBuilder.SimpleTreeBuilder;
import org.uncommons.watchmaker.impl.gp.treeBuilder.TreeBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Peter Day
 */
public class SymbolicRegression {

    public static void main(String[] args) {

        Random rng = new Random();
        //create input parameters
        Parameter<Double> xParam = new DoubleParameter("x");
        Parameter<Double> yParam = new DoubleParameter("y");

        NodePool<Double> functionPool = new SimpleNodePool<Double>();
        functionPool.addNodeFactory(new DefaultNodeFactory<AddNode>(AddNode.class, AddNode.ARITY), 1);
        functionPool.addNodeFactory(new DefaultNodeFactory<SubtractNode>(SubtractNode.class, SubtractNode.ARITY), 1);
        functionPool.addNodeFactory(DefaultNodeFactory.createNodeFactory(new SafeDivideNode()), 1);
        functionPool.addNodeFactory(DefaultNodeFactory.createNodeFactory(new MultiplyNode()), 1);

        NodePool<Double> terminalPool = new SimpleNodePool<Double>();
        terminalPool.addNodeFactory(new InputParameterNodeFactory(xParam), 1);
        terminalPool.addNodeFactory(new InputParameterNodeFactory(yParam), 1);
//        terminalPool.addNodeFactory(new UniformRandomConstantNodeFactory(rng, -10, 20), 0.5);
//        terminalPool.addNodeFactory(new UniformRandomConstantNodeFactory(rng, 0, 1), 0.5);
//        terminalPool.addNodeFactory(new ConstantNodeFactory(0), 0.1);
//        terminalPool.addNodeFactory(new ConstantNodeFactory(1), 0.1);
//        terminalPool.addNodeFactory(new ConstantNodeFactory(-1), 0.1);
//        terminalPool.addNodeFactory(new ConstantNodeFactory(10), 0.1);

        TreeBuilder<Double> treeBuilder = new SimpleTreeBuilder<Double>(0, 10, functionPool, terminalPool, Probability.EVENS);


        TreeCrossover<Double> crossoverOperator = new TreeCrossover<Double>();
        TreeMutation<Double> mutationOperator = new TreeMutation<Double>(treeBuilder, 0, 4);

        Map<ParameterValues, Double> expectedResults = new HashMap<ParameterValues, Double>();

        //generate training data
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                ParameterValues pv = new MapParameterValues();
                pv.setParameterValue(xParam, Double.valueOf(x));
                pv.setParameterValue(yParam, Double.valueOf(y));
                expectedResults.put(pv, function(Double.valueOf(x), Double.valueOf(y)));
            }
        }

        DoubleDataEvaluator evaluator = new DoubleDataEvaluator(expectedResults);
        //create parsimony pressure
        SimpleAdditiveParsimonyPressure parsimonyPressure = new SimpleAdditiveParsimonyPressure(0.1);

        
        ParsimonyPressureFitnessEvaluator<Node<Double>> parsimonyEvaluator = new ParsimonyFitnessEvaluatorWrapper(evaluator, parsimonyPressure);


        SplitEvolution<Node<Double>> pipeline = new SplitEvolution<Node<Double>>(crossoverOperator, mutationOperator, 0.8);
//        SplitEvolution<Node<Double>> pipeline2 = new SplitEvolution<Node<Double>>(pipeline, new NumberPerterber(), 0.8);

        RouletteWheelSelection selection = new RouletteWheelSelection();


        EvolutionEngine<Node<Double>> engine = new GenerationalEvolutionEngine<Node<Double>>(
                treeBuilder,
                pipeline,
                parsimonyEvaluator,
                selection,
                rng);

        engine.addEvolutionObserver(new EvolutionOutputStreamLogger<Node<Double>>(System.out));
        Node<Double> result = engine.evolve(1000,50, new ParsimonyFitnessTerminationCondition<Node<Double>>(parsimonyEvaluator, 0d, evaluator.isNatural()), new GenerationCount(100));

//		Node<Double> result = engine.evolve(500,25, new TargetFitness(0d, evaluator.isNatural()), new GenerationCount(100));
        System.out.println(result);




    }


    private static final double function(double x, double y) {
        return ((x + x)*y + x) * x;
    }



}
