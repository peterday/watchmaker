package org.uncommons.watchmaker.impl.gp.node.factory;

import day.peter.watchmaker.gp.node.Node;





public class DefaultNodeFactory<T extends Node<?>> implements NodeFactory<T> {

	private final Class<T> nodeClass;
	private final int arity;
	public DefaultNodeFactory(Class<T> clazz, int arity) {
		this.arity = arity;
		this.nodeClass = clazz;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Node<?>> DefaultNodeFactory<T> createNodeFactory (T node) {
		return new DefaultNodeFactory<T>((Class<T>) node.getClass(), node.getArity());
	}
	
	@Override
	public int getArity() {
		
		return arity;
	}

	@Override
	public Class<T> getNodeClass() {
		
		return nodeClass;
	}

	@Override
	public T newInstance() {
		try {
			return nodeClass.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}
