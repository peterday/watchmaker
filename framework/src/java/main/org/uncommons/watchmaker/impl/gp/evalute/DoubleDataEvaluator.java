package org.uncommons.watchmaker.impl.gp.evalute;

import java.util.Map;

import day.peter.watchmaker.gp.inputParameter.ParameterValues;

public class DoubleDataEvaluator extends DataBasedEvaluator<Double> {

	public DoubleDataEvaluator(Map<ParameterValues, Double> data) {
		super(data);
	}

	@Override
	public double diff(Double expected, Double given) {
		return expected - given;
	}

}
