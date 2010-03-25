package org.uncommons.watchmaker.impl.gp.treeBuilder;

import java.util.Random;

import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import day.peter.watchmaker.gp.node.Node;
import day.peter.watchmaker.gp.node.pool.NodePool;

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
	
	
	
	
	@Override
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




	@Override
	public Node<T> generateRandomCandidate(Random rng, int minDepth,
			int maxDepth) {
		
		return createNode(null, rng, minDepth, maxDepth);
	}

}
