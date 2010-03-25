package org.uncommons.watchmaker.impl.gp.node.terminal;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import day.peter.watchmaker.gp.inputParameter.ParameterValues;
import day.peter.watchmaker.gp.node.Node;

public class ConstantNode extends TerminalNode<Double> {

	private static final NumberFormat NUMBER_FORMAT = new DecimalFormat("######0.##");
	private double value;
	
	public ConstantNode(double value) {
		this.value = value;
	}
	
	@Override
	public Double evaluate(ParameterValues values) {
		return value;
	}
	
	@Override
	public String toString() {
		return NUMBER_FORMAT.format(value);
	}
	
	@Override
	public Node<Double> clone() throws CloneNotSupportedException {
		
		return new ConstantNode(value);
	}
	
	public double getValue() {
		return this.value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}



}
