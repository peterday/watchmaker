package org.uncommons.watchmaker.impl.gp.node.math;

import day.peter.watchmaker.gp.node.BinaryNode;

public class AddNode extends BinaryNode<Double, Double, Double> {

	@Override
	public Double evaluate(Double arg1, Double arg2) {
		return arg1 + arg2;
	}

	@Override
	public String getOperatorString() {
		
		return "+";
	}

	
	

}
