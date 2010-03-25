package org.uncommons.watchmaker.impl.gp.node.terminal;

import java.util.ArrayList;
import java.util.Collection;

import day.peter.watchmaker.gp.node.Node;




public abstract class TerminalNode<T> implements Node<T> {
	public static final int ARITY = 0;
	private Node<?> parent;
	
	
	
	@Override
	public int countNodes() {
		return 1;
	}
	

	@Override
	public int getArity() {
		return ARITY;
	}

	@Override
	public Node<?> getChild(int i) {
		return null;
	}

	@Override
	public int getDepth() {
		return 0;
	}

	@Override
	public Node<?> getNode(int index) {
		if (index == 0) {
			return this;
		}
		throw new IndexOutOfBoundsException();
	}

//	@Override
//	public Class<T> getOutputClass(Class<T> output) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Node<?> getParent() {
		
		return parent;
	}

	@Override
	public int getWidth() {
		
		return 1;
	}

	@Override
	public void setChild(int i, Node<?> child) {
		// TODO Auto-generated method stub
		throw new IndexOutOfBoundsException("Cannot set child for terminal (leaf) node");
	}

	@Override
	public void setParent(Node<?> parent) {
		this.parent = parent;
		
	}
	
	public abstract Node<T> clone() throws CloneNotSupportedException;
	
	@Override
	public Node<?> replaceNode(int index, Node<?> node) {
		if (index == 0) {
			return node;
		}
		throw new IndexOutOfBoundsException("Node index out of bounds");
	}
	
	@Override
	public int getHeight() {
		if (this.getParent() == null) {
			return 0;
		}
		return this.getParent().getHeight() + 1;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <U extends Node<?>> Collection<U> getNodesOfType(Class<U> clazz) {
		//shouldn't instantiate here
		Collection<U> nodes = new ArrayList<U>();
		if (this.getClass().equals(clazz)) {
			nodes.add((U)this);
		}
		return nodes;
	}

}
