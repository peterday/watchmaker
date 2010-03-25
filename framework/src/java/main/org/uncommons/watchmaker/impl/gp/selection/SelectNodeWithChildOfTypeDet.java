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
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SelectNodeWithChildOfTypeDet implements SelectionStrategy<Node<?>>{
	private final SelectionStrategy<Object> delegateSelectionStrategy;
	private final Class<? extends Node<?>> nodeClass;

	
	public SelectNodeWithChildOfTypeDet(SelectionStrategy<Object> delegateSelectionStrategy, Class<? extends Node<?>> nodeClass) {
	
		this.nodeClass = nodeClass;
		this.delegateSelectionStrategy = delegateSelectionStrategy;
	}
	

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
		
		return (List<S>) delegateSelectionStrategy.select(newPop, naturalFitnessScores, selectionSize, rng);
		
	}
	
	
}
