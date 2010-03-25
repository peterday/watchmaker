package org.uncommons.watchmaker.examples.de;

import day.peter.watchmaker.de.evaluator.DEFunction;

public class RosenbrockFunction implements DEFunction {

	public static final int NO_ARGS = 2;
	@Override
	public double evaluate(Double[] args) {
	
		// function f(x,y) = 100. (x^2 - y)^2 + (1-x)^2
		double x= args[0];
		double y = args[1];
		
		
		
		double result = 100 * Math.pow((x*x - y),2d) + Math.pow(1-x, 2d);
		return result;
	}

	@Override
	public int getNoArgs() {
		
		return NO_ARGS;
	}

}
