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
package org.uncommons.watchmaker.impl.gp.operators;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.impl.gp.node.Node;
import org.uncommons.watchmaker.impl.gp.treeBuilder.TreeBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TreeMutation<T> implements EvolutionaryOperator<Node<T>> {
	TreeBuilder<?> treeBuilder;
	int maxDepth;
	int minDepth;
	
	
	public TreeMutation(TreeBuilder<T> tb, int minDepth, int maxDepth) {
		super();
		this.maxDepth = maxDepth;
		this.minDepth = minDepth;
		this.treeBuilder = tb;
	}
	

	public List<Node<T>> apply(List<Node<T>> selectedCandidates, Random rng) {
		
		List<Node<T>> children = new ArrayList<Node<T>>();
		for (Iterator<Node<T>> iterator = selectedCandidates.iterator(); iterator
				.hasNext();) {
			Node<T> node = iterator.next();
			children.add(mutate(node, rng));
		}
		
		
		
		return children;
	}
	
	public Node<T> mutate(Node<T> parent, Random rng) {
		Node<T> childTree;

		childTree = parent.clone();

		
//		int initialDepth = childTree.getDepth();
		int initialCount = childTree.countNodes();
		
		int mutationpoint = rng.nextInt(initialCount);
//		Node<?> mutationNode = childTree.getNode(mutationpoint);
		
		//set null
//		childTree = (Node<T>) childTree.replaceNode(mutationpoint, null);
		int newDepth = 0;
		if (childTree != null) {
			newDepth = childTree.getDepth();
		}
		
		
		
		//grow tree
		Node<?> newChild = treeBuilder.generateRandomCandidate(rng, minDepth - newDepth, maxDepth - newDepth);
		
		
		if (childTree == null) {
			return (Node<T>)newChild;
		}
		return (Node<T>) childTree.replaceNode(mutationpoint, newChild);
		
	}
	

}
