package org.uncommons.watchmaker.impl.gp.node.terminal;

import day.peter.watchmaker.gp.inputParameter.Parameter;
import day.peter.watchmaker.gp.node.factory.NodeFactory;

@SuppressWarnings("unchecked")
public class InputParameterNodeFactory implements NodeFactory<InputParameterNode> {

	private final Parameter<?> parameter;
	
	public InputParameterNodeFactory(Parameter<?> parameter) {
		this.parameter = parameter;
	}
	
	@Override
	public int getArity() {
		return TerminalNode.ARITY;
	}

	@Override
	public Class<InputParameterNode> getNodeClass() {
		
		return (Class<InputParameterNode>) InputParameterNode.class;
	}

	@Override
	public InputParameterNode newInstance() {
		
		return new InputParameterNode(parameter);
	}

}
