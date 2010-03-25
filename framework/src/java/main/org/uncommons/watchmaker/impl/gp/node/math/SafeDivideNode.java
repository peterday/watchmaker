package org.uncommons.watchmaker.impl.gp.node.math;

import day.peter.watchmaker.gp.node.BinaryNode;

public class SafeDivideNode extends BinaryNode<Double, Double, Double> {

	@Override
	public Double evaluate(Double arg1, Double arg2) {
		if (arg2 == 0) {
			return 0d;
		}
		return arg1 / arg2;
	}

	@Override
	public String getOperatorString() {
		
		return "/";
	}


}
