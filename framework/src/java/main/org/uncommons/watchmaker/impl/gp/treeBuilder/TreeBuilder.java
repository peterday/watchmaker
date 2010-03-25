package org.uncommons.watchmaker.impl.gp.treeBuilder;

import java.util.Random;

import org.uncommons.watchmaker.framework.CandidateFactory;

import day.peter.watchmaker.gp.node.Node;

public interface TreeBuilder<T> extends CandidateFactory<Node<T>> {

	
	public Node<T> generateRandomCandidate(Random rng, int minDepth, int maxDepth);
}
