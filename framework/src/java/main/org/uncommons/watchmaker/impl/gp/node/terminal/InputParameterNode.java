package org.uncommons.watchmaker.impl.gp.node.terminal;

import day.peter.watchmaker.gp.inputParameter.Parameter;
import day.peter.watchmaker.gp.inputParameter.ParameterValues;
import day.peter.watchmaker.gp.node.Node;

public class InputParameterNode<T> extends TerminalNode<T> {

	private Parameter<T> parameter;
	
	public InputParameterNode(Parameter<T> parameter) {
		this.parameter = parameter;
	}
	
	@Override
	public T evaluate(ParameterValues values) {
		
		return values.getParameterValue(parameter);
	}
	
	@Override
	public String toString() {
		return parameter.getName();
	}
	
	@Override
	public Node<T> clone() throws CloneNotSupportedException {	
		return new InputParameterNode<T>(parameter);
	}

}
