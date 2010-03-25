//=============================================================================
// Copyright 2006-2010 Daniel W. Dyer
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//=============================================================================
package org.uncommons.watchmaker.impl.gp.node;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;



public abstract class BaseNode<T> implements Node<T> {

	
	private List<Node<?>> children = new ArrayList<Node<?>>(this.getArity());
	private Node<?> parent;


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




	public Node<?> getChild(int i) {
		if (i > getArity()) {
			throw new RuntimeException("Cannot retrieve child node with index " + i + " as this node only has an arity of " + this.getArity());
		}
		return children.get(i);
	}


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




	public Node<?> getParent() {

		return parent;
	}


	public int getWidth() {
		int width = 0;
		for (Iterator<Node<?>> iterator = children.iterator(); iterator.hasNext();) {
			Node<?> child =iterator.next();
			width = width + child.getWidth();
		}
		return width;
	}


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


	public void setParent(Node<?> parent) {
		this.parent = parent;

	}


	@SuppressWarnings("unchecked")
	@Override
	public Node<T> clone()  {

		BaseNode<T> clone;
		try {
			clone = this.getClass().newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException("error cloning: " + e.getMessage());
		} catch (IllegalAccessException e) {
			throw new RuntimeException("error cloning: " + e.getMessage());
		}

		int i = 0;
		for (Iterator<Node<?>> iterator = children.iterator(); iterator.hasNext();) {
			Node<?> child = iterator.next();
			clone.setChild(i, child.clone());
			i++;
		}

		return clone;



	}


	public int getHeight() {
		if (this.getParent() == null) {
			return 0;
		}
		return parent.getHeight() + 1;
	}
	
	@SuppressWarnings("unchecked")
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
