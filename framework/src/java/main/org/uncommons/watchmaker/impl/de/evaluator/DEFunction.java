package org.uncommons.watchmaker.impl.de.evaluator;

public interface DEFunction {
	public double evaluate(Double[] args);
	public int getNoArgs();
}
