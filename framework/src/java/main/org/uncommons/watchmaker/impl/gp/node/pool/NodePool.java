package org.uncommons.watchmaker.impl.gp.node.pool;

import java.util.Random;

import day.peter.watchmaker.gp.node.Node;
import day.peter.watchmaker.gp.node.factory.NodeFactory;

public interface NodePool<T> {
	public Node<T> createRandomNode(Random rng);
	public void addNodeFactory(NodeFactory<?> node, double probability);
	public void removeNodeFactory(NodeFactory<?> node);
	public double getProbabilitySum();
}
