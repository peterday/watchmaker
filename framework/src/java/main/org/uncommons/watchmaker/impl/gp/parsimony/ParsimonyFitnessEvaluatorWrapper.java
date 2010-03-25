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
package org.uncommons.watchmaker.impl.gp.parsimony;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.List;


/**
 * A wrapper around
 * @author Peter Day
 *
 * @param <T>
 */
public class ParsimonyFitnessEvaluatorWrapper<T> implements ParsimonyPressureFitnessEvaluator<T>{

	private final FitnessEvaluator<T> underlyingFitnessCalculator;
	private final ParsimonyEvaluator<T> parsimonyCalculator;

    public ParsimonyFitnessEvaluatorWrapper(FitnessEvaluator<T> underlyingFitnessCalculator,
                                            ParsimonyEvaluator<T> parsimonyCalculator) {
       this.underlyingFitnessCalculator = underlyingFitnessCalculator;
       this.parsimonyCalculator = parsimonyCalculator;
    }


	public double getFitnessWithoutParsimony(T candidate,
			List<? extends T> population) {
		
		return underlyingFitnessCalculator.getFitness(candidate, population);
	}


	public double getFitness(T candidate, List<? extends T> population) {
		
		return this.getFitnessWithoutParsimony(candidate, population) + parsimonyCalculator.getParsimonyFitness(candidate, population);
	}

	
	public boolean isNatural() {
		
		return underlyingFitnessCalculator.isNatural();
	}

}
