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
package org.uncommons.watchmaker.impl.de.evaluator;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.List;


/**
 * Fitness Evaluator for a standard DEFunction. Calculates fitness as the
 * result of the function - acts as a simple wrapper around a function ease
 * testing. 
 */
public class DEFitnessEvaluator implements FitnessEvaluator<Double[]> {

	private final boolean natural;
	private final DEFunction function;
	
	
	public DEFitnessEvaluator(DEFunction function, boolean isNatural) {
		this.function = function;
		this.natural = isNatural;
	}
	

	public double getFitness(Double[] candidate,
			List<? extends Double[]> population) {
		
		return function.evaluate(candidate);
	}

	
	public boolean isNatural() {
		return natural;
	}

}
