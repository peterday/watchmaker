package org.uncommons.watchmaker.impl.gp.selection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.SelectionStrategy;

import day.peter.watchmaker.gp.node.Node;

public class SelectNodeWithChildOfTypeStoc implements SelectionStrategy<Node<?>>{

	private final SelectionStrategy<Node<?>> delegateSelectionStrategy;
	private final Class<? extends Node<?>> nodeClass;
	private final int maxTries;
	
	public SelectNodeWithChildOfTypeStoc(SelectionStrategy<Node<?>> delegateSelectionStrategy, Class<? extends Node<?>> nodeClass, int maxTries) {
		this.maxTries = maxTries;
		this.nodeClass = nodeClass;
		this.delegateSelectionStrategy = delegateSelectionStrategy;
	}
	
	@Override
	public <S extends Node<?>> List<S> select(
			List<EvaluatedCandidate<S>> population,
			boolean naturalFitnessScores, int selectionSize, Random rng) {
		
		
		
		List<S> result = new ArrayList<S>(selectionSize);
		for(int i = 0; i<selectionSize; i++) {
			S parent = findParent(population, naturalFitnessScores, selectionSize, rng);
			if (parent != null) {
				result.add(parent);
			}
			//what to do if missing - currently the returned list will be no be complete
		}
		
		
		return result;
	}
	
	public <S extends Node<?>> S findParent(
			List<EvaluatedCandidate<S>> population,
			boolean naturalFitnessScores, int selectionSize, Random rng) {
		for(int i = 0; i<maxTries; i++) {
			
			
			List<S> ind = delegateSelectionStrategy.select(population, naturalFitnessScores, 1, rng);
			if (ind.get(0).getNodesOfType(nodeClass).size() > 0) {
				return ind.get(0);
			}
		}
		
		//failed
		return null;
	}

}
