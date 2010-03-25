package org.uncommons.watchmaker.impl.gp.node.factory;

import day.peter.watchmaker.gp.node.Node;


public interface NodeFactory<T extends Node<?>> {
	Class<T> getNodeClass();
	
	public T newInstance();
	
	public int getArity();
}
