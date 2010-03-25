package org.uncommons.watchmaker.impl.gp.node.math;

import day.peter.watchmaker.gp.node.UnaryNode;

public class SqrtNode extends UnaryNode<Double, Double> {

	@Override
	public Double evaluate(Double arg) {
		
		return	Math.sqrt(Math.abs(arg));
		
	}

	@Override
	public String getOperatorString() {
		
		return "sqrt";
	}

}
