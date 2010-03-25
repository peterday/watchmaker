package org.uncommons.watchmaker.impl.gp.evalute;

import java.util.List;
import java.util.Map;

import org.uncommons.watchmaker.framework.FitnessEvaluator;


import day.peter.watchmaker.gp.inputParameter.ParameterValues;
import day.peter.watchmaker.gp.node.Node;

public abstract class DataBasedEvaluator<T> implements FitnessEvaluator<Node<T>>{

	
	private Map<ParameterValues, T> data;
	

	public DataBasedEvaluator(Map<ParameterValues, T> data) {
		this.data = data;
	}
	
	
	@Override
	public double getFitness(Node<T> candidate, List<? extends Node<T>> population) {
		double error = 0;
        for (Map.Entry<ParameterValues, T> entry : data.entrySet())
        {
            T actualValue = candidate.evaluate(entry.getKey());
            double diff = diff(entry.getValue(), actualValue);
            error += (diff * diff);
        }
        return error;
	}
	

	public abstract double diff(T expected, T given);
	
	@Override
	public boolean isNatural() {
		return false;
	}

}
