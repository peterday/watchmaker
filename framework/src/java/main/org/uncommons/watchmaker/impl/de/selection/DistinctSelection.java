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
package org.uncommons.watchmaker.impl.de.selection;


import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.SelectionStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Selection strategy to return distinct individuals regardless of
 * any other property (such as fitness).
 * @param <T>
 *
 * @author Peter Day
 */
public class DistinctSelection<T> implements SelectionStrategy<T> {

    /**
     *
     * @param population The population from which to select.
     * @param naturalFitnessScores Whether higher fitness values represent fitter
     * individuals or not.
     * @param selectionSize The number of individual selections to make (not necessarily
     * the number of distinct candidates to select, since the same individual may
     * potentially be selected more than once).
     * @param rng Source of randomness for stochastic selection strategies.
     * @param <S> the type of candidate on which to act
     * @return
     */
	public <S extends T> List<S> select(List<EvaluatedCandidate<S>> population,
			boolean naturalFitnessScores, int selectionSize, Random rng) {

        //ensure there are enough candidates to select the required number of distinct individuals
        if (population.size() < selectionSize) {
            throw new IndexOutOfBoundsException("Cannot return a distinct selection of candidates larger " +
                    "than population size");
        }

        //shuffle the population
		Collections.shuffle(population);
		
		List<S> selection = new ArrayList<S>(selectionSize);
		for (int i = 0; i<selectionSize; i++) {
			selection.add(population.get(i).getCandidate());
		}
		return selection;
	}

}
