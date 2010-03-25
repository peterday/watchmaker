package org.uncommons.watchmaker.impl.gp.node.pool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import day.peter.watchmaker.gp.node.Node;
import day.peter.watchmaker.gp.node.factory.NodeFactory;

public class SimpleNodePool<T> implements NodePool<T> {

	private Map<NodeFactory<?>, Double> nodes = new HashMap<NodeFactory<?>, Double>();
	private Double probabilitySum = null;
	
	@Override
	public void addNodeFactory(NodeFactory<?> node, double probability) {
		nodes.put(node, probability);
		probabilitySum = null;
	}

	@Override
	public double getProbabilitySum() {
		if (probabilitySum == null) {
			probabilitySum = 0d;
			Iterator<Double> iter = nodes.values().iterator();
			while (iter.hasNext()) {
				probabilitySum = probabilitySum + iter.next();
			}
		}
		return probabilitySum;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Node<T> createRandomNode(Random rng) {
		double value = rng.nextDouble() * getProbabilitySum();
		Iterator<Entry<NodeFactory<?>, Double>> iter = nodes.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<NodeFactory<?>, Double> entry = iter.next();
			if (value <= entry.getValue()) {
				return (Node<T>) entry.getKey().newInstance();
			}
			value = value - entry.getValue();
		}
		
		//should never happen
		throw new RuntimeException("Could not find node");
	}

	@Override
	public void removeNodeFactory(NodeFactory<?> node) {
		if (nodes.containsKey(node)) {
			nodes.remove(node);
			//force prob sum to be re-calculated
			probabilitySum = null;
		}
	}

}
