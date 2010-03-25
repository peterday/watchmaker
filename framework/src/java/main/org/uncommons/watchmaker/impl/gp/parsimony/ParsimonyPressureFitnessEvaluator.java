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

import java.util.List;

import org.uncommons.watchmaker.framework.FitnessEvaluator;
/**
 * A {@link org.uncommons.watchmaker.framework.FitnessEvaluator} which alters the fitness measure of a candidate solution
 * to promote parsimony should implement this interface.
 * 
 * @author Peter Day
 *
 * @param <T>
 */
public interface ParsimonyPressureFitnessEvaluator<T> extends FitnessEvaluator<T> {
	public double getFitnessWithoutParsimony(T candidate, List<? extends T> population);
}
