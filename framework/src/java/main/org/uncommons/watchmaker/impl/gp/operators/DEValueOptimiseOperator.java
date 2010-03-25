package org.uncommons.watchmaker.impl.gp.operators;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.FitnessEvaluator;
import org.uncommons.watchmaker.impl.gp.inputParameter.ParameterValues;
import org.uncommons.watchmaker.impl.gp.node.Node;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Peter Day
 */
public class DEValueOptimiseOperator implements EvolutionaryOperator<Node<Double>> {

    FitnessEvaluator<Node<Double>> evaluator;
    Map<ParameterValues, Double> data;
    

    public List<Node<Double>> apply(List<Node<Double>> selectedCandidates, Random rng) {
        return null;
    }
}
