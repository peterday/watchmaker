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
package org.uncommons.watchmaker.impl.gp.selectEvolve;

import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.SelectionStrategy;

/**
 * Provides the simplest form of select evolve pipeline.
 * 
 * @author Peter Day
 *
 * @param <T>
 */
public class SimpleSelectEvolve<T> implements SelectEvolvePipeline<T> {

	private final SelectionStrategy<T> selectionStrategy;
	private final EvolutionaryOperator<T> evolutionaryOperator;
	
	public SimpleSelectEvolve(SelectionStrategy<T> selectionStrategy, EvolutionaryOperator<T> evolutionaryOperator) {
		this.selectionStrategy = selectionStrategy;
		this.evolutionaryOperator = evolutionaryOperator;
	}
	
	@Override
	public List<T> selectEvolve(List<EvaluatedCandidate<T>> population, 
			boolean naturalFitnessScores, int requiredChildren, Random rng) {
		List<T> selectedCandidates = this.selectionStrategy.select(population, naturalFitnessScores, requiredChildren, rng);
		return this.evolutionaryOperator.apply(selectedCandidates, rng);
	}

}
