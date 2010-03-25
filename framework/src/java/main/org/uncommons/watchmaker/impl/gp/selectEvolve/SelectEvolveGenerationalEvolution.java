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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.AbstractEvolutionEngine;
import org.uncommons.watchmaker.framework.CandidateFactory;
import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

public class SelectEvolveGenerationalEvolution<T> extends AbstractEvolutionEngine<T>{
	
	
	private final SelectEvolvePipeline<T> pipeline;
	private final FitnessEvaluator<? super T> fitnessEvaluator;
	
	public SelectEvolveGenerationalEvolution(CandidateFactory<T> candidateFactory, SelectEvolvePipeline<T> selectEvolvePipeline,
			FitnessEvaluator<? super T> fitnessEvaluator, Random rng) {
		super(candidateFactory, fitnessEvaluator, rng);
		this.fitnessEvaluator = fitnessEvaluator;
		this.pipeline = selectEvolvePipeline;
	}

	

	@Override
	protected List<EvaluatedCandidate<T>> nextEvolutionStep(
			List<EvaluatedCandidate<T>> evaluatedPopulation, int eliteCount,
			Random rng) {
		 List<T> population = new ArrayList<T>(evaluatedPopulation.size());

	     // First perform any elitist selection.
	     List<T> elite = new ArrayList<T>(eliteCount);
	     Iterator<EvaluatedCandidate<T>> iterator = evaluatedPopulation.iterator();
	     while (elite.size() < eliteCount)
	     {
	         elite.add(iterator.next().getCandidate());
	     }
	     // Then select candidates that will be operated on to create the evolved
	     // portion of the next generation.
	     population.addAll(pipeline.selectEvolve(evaluatedPopulation,
	                                                fitnessEvaluator.isNatural(),
	                                                evaluatedPopulation.size() - eliteCount,
	                                                rng));
	     
	     // When the evolution is finished, add the elite to the population.
	     population.addAll(elite);
	     return evaluatePopulation(population);
	}
	
}
