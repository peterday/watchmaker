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
package org.uncommons.watchmaker.impl.gp.parsimony.termination;





import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.TerminationCondition;

import org.uncommons.watchmaker.impl.gp.parsimony.ParsimonyPressureFitnessEvaluator;


public class ParsimonyFitnessTerminationCondition<T> implements TerminationCondition {
	
	public ParsimonyFitnessTerminationCondition(ParsimonyPressureFitnessEvaluator<T> evaluator, double targetFitness,
			boolean natural) {
		
		this.natural = natural;
		this.targetFitness = targetFitness;
		this.evaluator = evaluator;
	}

	private boolean natural;
	private double targetFitness;
	private ParsimonyPressureFitnessEvaluator<T> evaluator;
	
	@SuppressWarnings("unchecked")
	public boolean shouldTerminate(PopulationData<?> populationData) {

        //get the fitness without parsimony
		double fitness = this.evaluator.getFitnessWithoutParsimony((T)populationData.getBestCandidate(), null);
		if (natural)
        {
            // If we're using "natural" fitness scores, higher values are better.
            return fitness >= targetFitness;
        }
        else
        {
            // If we're using "non-natural" fitness scores, lower values are better.
            return fitness <= targetFitness;
        }
		
	}

	
	
}
