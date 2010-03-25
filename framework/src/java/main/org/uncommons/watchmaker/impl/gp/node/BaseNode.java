package org.uncommons.watchmaker.impl.gp.node;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;



public abstract class BaseNode<T> implements Node<T> {

	
	private List<Node<?>> children = new ArrayList<Node<?>>(this.getArity());
	private Node<?> parent;

	@Override
	public int countNodes() {
		int count = 1;
		for (int i = 0; i < this.getArity(); i++) {
			Node<?> child = this.getChild(i);
			if (child != null) {
				count = count + child.countNodes();
			}

		}
		return count;
	}



	@Override
	public Node<?> getChild(int i) {
		if (i > getArity()) {
			throw new RuntimeException("Cannot retrieve child node with index " + i + " as this node only has an arity of " + this.getArity());
		}
		return children.get(i);
	}

	@Override
	public int getDepth() {
		int maxdepth = 0;
		for (Iterator<Node<?>> iterator = children.iterator(); iterator.hasNext();) {
			Node<?> child = iterator.next();
			if (child != null) {
				maxdepth = Math.max(maxdepth, child.getDepth());
			}
		}
		return maxdepth + 1;
	}



	@Override
	public Node<?> replaceNode(int index, Node<?> node) {
		if (index == 0) {
			return node;
		}
		//		int i = 0;
		int nodeCount = 1;
		for (int j = 0; j<this.getArity(); j++) {

			Node<?> child = children.get(j);
			if (child != null) {
				int childNodes = child.countNodes();

				if (index < (childNodes + nodeCount)) {
					this.setChild(j, (child.replaceNode(index - nodeCount, node)));
					break;
				}
				nodeCount = nodeCount + childNodes;
			}
		}
		//		for (Iterator<Node<?>> iterator = children.iterator(); iterator.hasNext();) {
		//			Node<?> child = iterator.next();
		//			
		//			int childNodes = child.countNodes();
		//			if (index < (childNodes + nodeCount)) {
		//				this.setChild(i, (child.replaceNode(index - nodeCount, node)));
		//			}
		//			nodeCount = nodeCount + childNodes;
		//			i++;
		//		}
		return this;
	}

	@Override
	public Node<?> getNode(int index) {

		if (index == 0)
		{
			return this;
		}
		int nodeCount = 1;

		for (Iterator<Node<?>> iterator = children.iterator(); iterator.hasNext();) {
			Node<?> child = iterator.next();
			int childNodes = child.countNodes();
			if (index < (childNodes + nodeCount)) {
				return (child.getNode(index - nodeCount));
			}
			nodeCount = nodeCount + childNodes;

		}

		throw new IndexOutOfBoundsException("Cannot find node of index " + index);

	}



	@Override
	public Node<?> getParent() {

		return parent;
	}

	@Override
	public int getWidth() {
		int width = 0;
		for (Iterator<Node<?>> iterator = children.iterator(); iterator.hasNext();) {
			Node<?> child =iterator.next();
			width = width + child.getWidth();
		}
		return width;
	}

	@Override
	public void setChild(int i, Node<?> child) {

		if (i > this.getArity()) {

			throw new IndexOutOfBoundsException("Cannot set node with index " + i + " for a node with arity " + getArity());
		}
		child.setParent(this);
		if (this.children.size() > i) {
			this.children.set(i, child);
			
			return;
		}
		this.children.add(i, child);
	}

	@Override
	public void setParent(Node<?> parent) {
		this.parent = parent;

	}


	@SuppressWarnings("unchecked")
	@Override
	public Node<T> clone() throws CloneNotSupportedException {

		BaseNode<T> clone;
		try {
			clone = this.getClass().newInstance();
		} catch (InstantiationException e) {
			throw new CloneNotSupportedException("error cloning: " + e.getMessage());
		} catch (IllegalAccessException e) {
			throw new CloneNotSupportedException("error cloning: " + e.getMessage());
		}

		int i = 0;
		for (Iterator<Node<?>> iterator = children.iterator(); iterator.hasNext();) {
			Node<?> child = iterator.next();
			clone.setChild(i, child.clone());
			i++;
		}

		return clone;



	}

	@Override
	public int getHeight() {
		if (this.getParent() == null) {
			return 0;
		}
		return parent.getHeight() + 1;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <U extends Node<?>> Collection<U> getNodesOfType(Class<U> clazz) {
		//not happy about this - should not be instantiated here
		Collection<U> nodes = new ArrayList<U>();
		if (this.getClass().equals(clazz)) {
			nodes.add((U)this);
		}
		
		for (int i = 0; i< this.getArity(); i++) {
			nodes.addAll(this.getChild(i).getNodesOfType(clazz));
		}
		return nodes;
		
		
		
	}
}
