package org.uncommons.watchmaker.impl.gp.node.logic;

public class OrNode extends BinaryLogicNode {

	@Override
	public Boolean evaluate(Boolean arg1, Boolean arg2) {
		return arg1 || arg2;
	}

	@Override
	public String getOperatorString() {
		
		return " || ";
	}

}
