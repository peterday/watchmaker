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
package org.uncommons.watchmaker.impl.gp.selection;

import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.impl.gp.node.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SelectNodeWithChildOfTypeStoc implements SelectionStrategy<Node<?>>{

	private final SelectionStrategy<Node<?>> delegateSelectionStrategy;
	private final Class<? extends Node<?>> nodeClass;
	private final int maxTries;
	
	public SelectNodeWithChildOfTypeStoc(SelectionStrategy<Node<?>> delegateSelectionStrategy, Class<? extends Node<?>> nodeClass, int maxTries) {
		this.maxTries = maxTries;
		this.nodeClass = nodeClass;
		this.delegateSelectionStrategy = delegateSelectionStrategy;
	}
	
	
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
