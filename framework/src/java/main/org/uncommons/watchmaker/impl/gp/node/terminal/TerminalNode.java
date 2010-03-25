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
package org.uncommons.watchmaker.impl.gp.node.terminal;

import org.uncommons.watchmaker.impl.gp.node.Node;

import java.util.ArrayList;
import java.util.Collection;






public abstract class TerminalNode<T> implements Node<T> {
	public static final int ARITY = 0;
	private Node<?> parent;
	
	
	

	public int countNodes() {
		return 1;
	}
	


	public int getArity() {
		return ARITY;
	}


	public Node<?> getChild(int i) {
		return null;
	}


	public int getDepth() {
		return 0;
	}


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


	public Node<?> getParent() {
		
		return parent;
	}


	public int getWidth() {
		
		return 1;
	}


	public void setChild(int i, Node<?> child) {
		// TODO Auto-generated method stub
		throw new IndexOutOfBoundsException("Cannot set child for terminal (leaf) node");
	}


	public void setParent(Node<?> parent) {
		this.parent = parent;
		
	}
	
	public abstract Node<T> clone();
	

	public Node<?> replaceNode(int index, Node<?> node) {
		if (index == 0) {
			return node;
		}
		throw new IndexOutOfBoundsException("Node index out of bounds");
	}
	

	public int getHeight() {
		if (this.getParent() == null) {
			return 0;
		}
		return this.getParent().getHeight() + 1;
	}
	
	@SuppressWarnings("unchecked")
	public <U extends Node<?>> Collection<U> getNodesOfType(Class<U> clazz) {
		//shouldn't instantiate here
		Collection<U> nodes = new ArrayList<U>();
		if (this.getClass().equals(clazz)) {
			nodes.add((U)this);
		}
		return nodes;
	}

}
