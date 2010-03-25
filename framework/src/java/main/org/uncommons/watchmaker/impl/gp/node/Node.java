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

import org.uncommons.watchmaker.impl.gp.inputParameter.ParameterValues;

import java.util.Collection;


/**
 * <p>A node in a GP program tree.
 * </p>
 * <p>Used for tree based genetic programming.</p>
 * 
 * @author Peter Day
 *
 * @param <T> the return type of the {@link #evaluate(ParameterValues)} method
 */
public interface Node<T> extends Cloneable {
	public T evaluate(ParameterValues values);
	
//	public Class<T> getOutputClass(Class<T> output);
	
	public Node<?> getParent();
	public void setParent(Node<?> parent);
	
	public Node<?> getChild(int i);
	public void setChild(int i, Node<?> child);
	
	public <U extends Node<?>> Collection<U>  getNodesOfType(Class<U> clazz);
	
	int getHeight();
	
	  /**
     * @return The number of levels of nodes that make up this tree.
     * @see #getWidth()
     */
    int getDepth();

    /**
     * Work out how wide (in nodes) this tree is.  Used primarily for laying out a
     * visual representation.  A leaf node has a width of 1.  A binary node's width
     * is the sum of the widths of its sub-trees.
     * @return The maximum width of this tree.
     * @see #getDepth()
     * @see #getArity()
     */
    int getWidth();
    
    /**
     * @return The total number of nodes in this tree (recursively counts the nodes
     * for each sub-node of this node).
     * @see #getArity() 
     */
    int countNodes();

    int getArity();
    
    /**
     * Retrieves a sub-node from this tree. 
     * @param index The index of a node.  Index 0 is the root node.  Nodes are numbered
     * depth-first, right-to-left.
     * @return The node at the specified position.
     */
    Node<?> getNode(int index);
    
    /**
     * Replaces the node at specified index with the node
     * @param index
     * @param node
     * @return
     */
    Node<?> replaceNode(int index, Node<?> node);
    
    /**
     * Creates a clone of the node and its children
     * @return
     * @throws CloneNotSupportedException
     */
    public Node<T> clone();
}
