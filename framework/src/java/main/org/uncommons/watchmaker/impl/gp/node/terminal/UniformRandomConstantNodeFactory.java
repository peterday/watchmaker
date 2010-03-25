package org.uncommons.watchmaker.impl.gp.node.terminal;

import java.util.Random;

import day.peter.watchmaker.gp.node.factory.NodeFactory;

public class UniformRandomConstantNodeFactory implements NodeFactory<ConstantNode>{

	private Random rng;
	private double minValue = 0;
	private double range = 1;
	
	public UniformRandomConstantNodeFactory(Random rng, double minValue, double range) {
		this.minValue = minValue;
		this.range = range;
		this.rng = rng;
	}
	
	@Override
	public int getArity() {
		
		return TerminalNode.ARITY;
	}

	@Override
	public Class<ConstantNode> getNodeClass() {
		
		return ConstantNode.class;
	}

	@Override
	public ConstantNode newInstance() {
		Double value = (rng.nextDouble() * range) + minValue;
		
		return new ConstantNode(value);
	}

}
