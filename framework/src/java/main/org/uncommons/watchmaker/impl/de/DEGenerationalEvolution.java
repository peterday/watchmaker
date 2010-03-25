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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.AbstractEvolutionEngine;
import org.uncommons.watchmaker.framework.CandidateFactory;
import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.FitnessEvaluator;
import org.uncommons.watchmaker.framework.SelectionStrategy;


public class DEGenerationalEvolution extends AbstractEvolutionEngine<Double[]> {
	public DEGenerationalEvolution(
			CandidateFactory<Double[]> candidateFactory,
			FitnessEvaluator<? super Double[]> fitnessEvaluator, DEMutation mutator, SelectionStrategy<Double[]> selection, Random rng) {
		super(candidateFactory, fitnessEvaluator, rng);
		this.mutation = mutator;
		this.selector = selection;
		this.fitnessEvaluator = fitnessEvaluator;
		
	}


	DEMutation mutation;
	SelectionStrategy<Double[]> selector;
	private FitnessEvaluator<? super Double[]> fitnessEvaluator;
	
	
	@Override
	protected List<EvaluatedCandidate<Double[]>> nextEvolutionStep(
			List<EvaluatedCandidate<Double[]>> evaluatedPopulation, int eliteCount,
			Random rng) {
		ArrayList<EvaluatedCandidate<Double[]>> newPop = new ArrayList<EvaluatedCandidate<Double[]>>(evaluatedPopulation.size());
		newPop.addAll(evaluatedPopulation);
		
		//iterate through old population
		for (int i = 0; i < evaluatedPopulation.size(); i++) {
			EvaluatedCandidate<Double[]> baseCandidate = evaluatedPopulation.get(i);
			//remove this from the new pop
			newPop.remove(baseCandidate);
			List<Double[]> otherInds = selector.select(newPop, fitnessEvaluator.isNatural(), 3, rng);
			Double[] newInd = mutation.apply(baseCandidate.getCandidate(), otherInds, rng);
			EvaluatedCandidate<Double[]> newEvaluated = new EvaluatedCandidate<Double[]>(newInd, fitnessEvaluator.getFitness(newInd, null));
			if (fitnessEvaluator.isNatural()) {
				if (newEvaluated.getFitness() > baseCandidate.getFitness()) {
					newPop.add(newEvaluated);
				}
				else {
					newPop.add(baseCandidate);
				}
			}
			else {
				//fitness not natural
				if (newEvaluated.getFitness() < baseCandidate.getFitness()) {
					newPop.add(newEvaluated);
				}
				else {
					newPop.add(baseCandidate);
				}
			}
			
		}
		
		//ignore elite count 

		return newPop;
	}

}
