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
package org.uncommons.watchmaker.impl.gp.treeBuilder;

import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;
import org.uncommons.watchmaker.impl.gp.node.Node;
import org.uncommons.watchmaker.impl.gp.node.pool.NodePool;

import java.util.Random;

public class SimpleTreeBuilder<T> extends AbstractCandidateFactory<Node<T>> implements TreeBuilder<T> {

	private int maxDepth = -1;
	private int minDepth = -1;
	private NodePool<T> functionPool;
	private NodePool<T> terminalPool;
	private Probability functionProbability;
	
	public SimpleTreeBuilder(int minDepth, int maxDepth, NodePool<T> functionPool, NodePool<T> terminalPool, Probability functionProbability) {
		this.minDepth = minDepth;
		this.maxDepth = maxDepth;
		this.functionPool = functionPool;
		this.terminalPool = terminalPool;
		this.functionProbability = functionProbability;
	}
	
	
	
	

	public Node<T> generateRandomCandidate(Random rng) {
		return createNode(null, rng, minDepth, maxDepth);
	}
	
	
	
	public Node<T> createNode(Node<?> parent, Random rng, int minDepth, int maxDepth) {
		if (minDepth > 0 || (maxDepth > 1 && functionProbability.nextEvent(rng))) {
			  //generate a function
			Node<T> node = functionPool.createRandomNode(rng);
			node.setParent(parent);
			int arity = node.getArity();
			for (int i = 0; i < arity; i++) {
				node.setChild(i, createNode(node, rng, minDepth -1, maxDepth - 1));
			}
			return node;
		}
		
		//return a terminal
		Node<T> node = terminalPool.createRandomNode(rng);
		node.setParent(parent);
		return node;
	}





	public Node<T> generateRandomCandidate(Random rng, int minDepth,
			int maxDepth) {
		
		return createNode(null, rng, minDepth, maxDepth);
	}

}
