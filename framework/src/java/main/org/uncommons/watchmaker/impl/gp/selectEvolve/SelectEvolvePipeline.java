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



/**
 * A pipeline which performs both selection and evolutionary operators
 * on a population of evaluated candidates.
 * Intended to allow different genetic operators to apply different selection strategies.
 * Example use cases:
 * 
 * Comparative Partner Selection
 * Perterbable Numerical Values
 * 
 * @author Peter Day
 *
 */
public interface SelectEvolvePipeline<T> {

	
	List<T> selectEvolve(List<EvaluatedCandidate<T>> population, boolean isFitnessNatural,
			int requiredChildren, Random rng);
	
}
