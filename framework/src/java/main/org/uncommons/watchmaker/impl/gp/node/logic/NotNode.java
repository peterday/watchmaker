package org.uncommons.watchmaker.impl.gp.node.logic;

import day.peter.watchmaker.gp.node.UnaryNode;

public class NotNode extends UnaryNode<Boolean, Boolean> {

	@Override
	public Boolean evaluate(Boolean arg1) {
		return !arg1;
	}

	@Override
	public String getOperatorString() {
		
		return "!";
	}

}
