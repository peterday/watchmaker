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
package org.uncommons.watchmaker.impl.de;

import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.impl.de.operators.DEMutation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * EvolutionEngine for differential evolution.
 * 
 */
public class DEGenerationalEvolution extends AbstractEvolutionEngine<Double[]> {
	DEMutation mutation;
	SelectionStrategy<Double[]> selector;
	private FitnessEvaluator<? super Double[]> fitnessEvaluator;

    public DEGenerationalEvolution(
			CandidateFactory<Double[]> candidateFactory, FitnessEvaluator<? super Double[]> fitnessEvaluator,
            DEMutation mutator, SelectionStrategy<Double[]> selector, Random rng) {
		super(candidateFactory, fitnessEvaluator, rng);
		this.mutation = mutator;
		this.selector = selector;
		this.fitnessEvaluator = fitnessEvaluator;
		
	}


    /**
     *
     * @param evaluatedPopulation The population at the beginning of the process.
     * @param eliteCount Ignored in this case - elitism is automatically provided by the evolution method.
     * @param rng A source of randomness.
     * @return the next generation of individuals
     */
	@Override
	protected List<EvaluatedCandidate<Double[]>> nextEvolutionStep(
			List<EvaluatedCandidate<Double[]>> evaluatedPopulation, int eliteCount,
			Random rng) {
		ArrayList<EvaluatedCandidate<Double[]>> newPop = new ArrayList<EvaluatedCandidate<Double[]>>(evaluatedPopulation.size());
		newPop.addAll(evaluatedPopulation);
		
		//iterate through old population
		for (int i = 0; i < evaluatedPopulation.size(); i++) {
			EvaluatedCandidate<Double[]> baseCandidate = evaluatedPopulation.get(i);
			//remove this from the new pop so it cannot be selected as a partner for mutation
			newPop.remove(baseCandidate);

            //select three other candidates for mutation / crossover
			List<Double[]> otherInds = selector.select(newPop, fitnessEvaluator.isNatural(), 3, rng);

//           apply the mutation
			Double[] newInd = mutation.apply(baseCandidate.getCandidate(), otherInds, rng);
            //evaluate this candidate
			EvaluatedCandidate<Double[]> newEvaluated = new EvaluatedCandidate<Double[]>(newInd, fitnessEvaluator.getFitness(newInd, null));
//          replace the base candidate in the population if the fitness is improved
            if (fitnessEvaluator.isNatural()) {
				if (newEvaluated.getFitness() > baseCandidate.getFitness()) {
					newPop.add(newEvaluated);
				}
				else {
					newPop.add(baseCandidate);
				}
			}
			else {
				//fitness not natural - lower is better
				if (newEvaluated.getFitness() < baseCandidate.getFitness()) {
					newPop.add(newEvaluated);
				}
				else {
					newPop.add(baseCandidate);
				}
			}
			
		}


		return newPop;
	}

}
