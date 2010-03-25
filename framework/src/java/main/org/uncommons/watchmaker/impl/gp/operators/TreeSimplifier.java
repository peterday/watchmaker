package org.uncommons.watchmaker.impl.gp.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import day.peter.watchmaker.gp.node.Node;
import day.peter.watchmaker.gp.node.terminal.ConstantNode;

public class TreeSimplifier implements EvolutionaryOperator<Node<Double>>{

	@Override
	public List<Node<Double>> apply(List<Node<Double>> selectedCandidates, Random rng) {
		List<Node<Double>> newPop = new ArrayList<Node<Double>>();
		Iterator<Node<Double>> iter = selectedCandidates.iterator();
		while (iter.hasNext()) {
			try {
				newPop.add(simplify(iter.next().clone()));
			} catch (CloneNotSupportedException e) {
				throw new RuntimeException();
			}
		}
		return newPop;
	}


	@SuppressWarnings("unchecked")
	public Node<Double> simplify(Node<Double> parent) {

		if (parent instanceof ConstantNode) {
			return parent;
		}

		boolean allChildrenConstants = true;
		for (int i = 0; i < parent.getArity(); i++) {
			Node<Double> child = simplify((Node<Double>) parent.getChild(i));
			if (!(child instanceof ConstantNode)) {
				allChildrenConstants = false;
			}
			parent.setChild(i, child);
		}

		if (allChildrenConstants) {
			parent = (Node<Double>) new ConstantNode((Double)parent.evaluate(null));
		}

		return parent;

	}

}
