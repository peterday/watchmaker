package org.uncommons.watchmaker.impl.gp.parsimony;

import java.util.List;

import day.peter.watchmaker.gp.node.Node;


public class SimpleAdditiveParsimonyPressure implements ParsimonyEvaluator<Node<?>> {

	private final double nodeMultiplier;
	
	public SimpleAdditiveParsimonyPressure(double multiplier) {
		this.nodeMultiplier = multiplier;
	}
	@Override
	public double getParsimonyFitness(Node<?> candidate,
			List<? extends Node<?>> population) {
		return candidate.countNodes() * nodeMultiplier;
	}

}
