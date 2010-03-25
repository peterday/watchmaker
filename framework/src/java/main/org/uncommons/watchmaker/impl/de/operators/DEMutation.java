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
package org.uncommons.watchmaker.impl.de.operators;

import org.uncommons.maths.number.ConstantGenerator;
import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.impl.de.factory.ComparableLimit;

import java.util.List;
import java.util.Random;

/**
 * <p>
 * Provides *standard* (aka type1) DE mutation / crossover.
 * Note - this DOES NOT implement EvolutionaryOperator as it cannot be used as a standard
 * evolutionary operator - it must act on four candidate solutions and produces only one
 * resultant candidate.
 * </p>
 * <p>
 * If you wish to create an EvolutionaryOperator which performs Differential evolution it must also
 * have responsibility for selection and candidate evaluation (fitness).
 * </p>
 * 
 * @author: Peter Day
 */
public class DEMutation {

	private NumberGenerator<Double> mutationConstant;
	private Probability crossoverProbability;
	private ComparableLimit<Double>[] limits;

    /**
     *
     * @param limits
     * @param mutationConstant   the mutation constant
     * @param crossoverProb  the probability of crossover
     */
	public DEMutation(ComparableLimit<Double>[] limits, double mutationConstant, double crossoverProb) {
		this(limits, new ConstantGenerator<Double>(mutationConstant),  new Probability(crossoverProb));
	}

    public DEMutation(ComparableLimit<Double>[] limits, NumberGenerator<Double> mutationConstant, Probability crossoverProbability)
    {
        this.crossoverProbability = crossoverProbability;
        this.mutationConstant = mutationConstant;
        this.limits = limits;

    }


//    /**
//     * Not intended for use - please note that this EvolutionaryOperator does
//     * NOT behave as expected. Given a set of 4 candidates only one candidate is returned.
//     *
//     * @param selectedCandidates The individuals to evolve.
//     * @param rng A source of randomness for stochastic operators (most
//     * operators will be stochastic).
//     * @return
//     */
//	public List<Double[]> apply(List<Double[]> selectedCandidates, Random rng) {
//		//assume size is correct
//
//
//		Double[] newInd = this.apply(selectedCandidates.get(0), selectedCandidates.subList(1,3), rng);
//
//        List<Double[]> newInds = new ArrayList<Double[]>(1);
//        newInds.add(newInd);
//        return newInds;
//	}
	
	public Double[] apply(Double[] baseInd, List<Double[]> otherInds, Random rng) {
		//random point in the params
		int rnd = rng.nextInt(limits.length);
		Double[] cand1 = otherInds.get(0);
		Double[] cand2= otherInds.get(1);
		Double[] cand3 = otherInds.get(2);
		
		Double[] candidate = new Double[limits.length];

		for (int i=0; i<limits.length; i++)
		{
			if ( (crossoverProbability.nextEvent(rng)) || (rnd == i) ) {
				candidate[i] = cand3[i] +
				mutationConstant.nextValue() * (cand1[i] - cand2[i]) ;
			}
			else {
				candidate[i] = baseInd[i] ;
			}
			

			
			if (!limits[i].isValid(candidate[i])) {
				//value outside limit
//				should be returned stochastically to a point inside the search space
				candidate[i]= limits[i].getMinimumValue() + ((limits[i].getMaximumValue() - limits[i].getMinimumValue()) * rng.nextDouble());
			}
			

		}
		
		return candidate;
	}




}
