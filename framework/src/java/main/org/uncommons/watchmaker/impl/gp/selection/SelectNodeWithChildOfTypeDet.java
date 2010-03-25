package org.uncommons.watchmaker.impl.gp.selection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.SelectionStrategy;

import day.peter.watchmaker.gp.node.Node;

public class SelectNodeWithChildOfTypeDet implements SelectionStrategy<Node<?>>{
	private final SelectionStrategy<Node<?>> delegateSelectionStrategy;
	private final Class<? extends Node<?>> nodeClass;
	private final int maxTries;
	
	public SelectNodeWithChildOfTypeDet(SelectionStrategy<Node<?>> delegateSelectionStrategy, Class<? extends Node<?>> nodeClass, int maxTries) {
		this.maxTries = maxTries;
		this.nodeClass = nodeClass;
		this.delegateSelectionStrategy = delegateSelectionStrategy;
	}
	
	@Override
	public <S extends Node<?>> List<S> select(
			List<EvaluatedCandidate<S>> population,
			boolean naturalFitnessScores, int selectionSize, Random rng) {
		
		List<EvaluatedCandidate<S>> newPop = new ArrayList<EvaluatedCandidate<S>>(population.size());
		
		Iterator<EvaluatedCandidate<S>> iter = population.iterator();
		while (iter.hasNext()) {
			EvaluatedCandidate<S> candidate = iter.next();
			if (candidate.getCandidate().getNodesOfType(nodeClass).size() > 0) {
				newPop.add(candidate);
			}
		}
		
		return delegateSelectionStrategy.select(newPop, naturalFitnessScores, selectionSize, rng);
		
	}
	
	
}
