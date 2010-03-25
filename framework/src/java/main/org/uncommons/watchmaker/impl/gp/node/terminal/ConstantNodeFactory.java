package org.uncommons.watchmaker.impl.gp.node.terminal;

import day.peter.watchmaker.gp.node.factory.NodeFactory;

public class ConstantNodeFactory implements NodeFactory<ConstantNode> {

	private double value;
	
	public ConstantNodeFactory(double value) {
		this.value = value;
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
		
		return new ConstantNode(value);
	}

}
