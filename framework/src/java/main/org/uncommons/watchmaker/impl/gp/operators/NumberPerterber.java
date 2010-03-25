package org.uncommons.watchmaker.impl.gp.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import day.peter.watchmaker.gp.node.Node;
import day.peter.watchmaker.gp.node.terminal.ConstantNode;

public class NumberPerterber implements EvolutionaryOperator<Node<Double>>{

	@Override
	public List<Node<Double>> apply(List<Node<Double>> selectedCandidates,
			Random rng) {
		List<Node<Double>> children = new ArrayList<Node<Double>>();
		
		for (Iterator<Node<Double>> iterator = selectedCandidates.iterator(); iterator
				.hasNext();) {
			Node<Double> parent = (Node<Double>) iterator.next();
			try {
				Node<Double> child = apply(parent, rng);
				if (child != null) {
					children.add(child);
				}
			} catch (CloneNotSupportedException e) {
				throw new RuntimeException(e);
			}
		}
		
		return children;
	}
	
	
	public Node<Double> apply(Node<Double> parent, Random rng) throws CloneNotSupportedException {
//		System.out.println("applying perterber");
		Collection<ConstantNode> nodes = new ArrayList<ConstantNode>();
		Node<Double> child = parent.clone();
		nodes = child.getNodesOfType(ConstantNode.class);
		if (nodes.size() == 0) {
			System.out.println("null perterb");
			return child;
//			return null;
		}
		//pick a node at random
		double mutator = rng.nextGaussian();
		int nodePick = rng.nextInt(nodes.size());
		ConstantNode node = (ConstantNode)nodes.toArray()[nodePick];
//		System.out.println("mutation: " + mutator + " - oldval: " + node.getValue() + " - newval: " + node.getValue() * mutator);
		node.setValue(node.getValue() + mutator);
		
		return child;
	}

}
