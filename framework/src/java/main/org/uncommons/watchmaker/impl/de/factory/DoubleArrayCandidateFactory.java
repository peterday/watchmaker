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
package org.uncommons.watchmaker.impl.de.factory;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.Random;

/** <p>
 * {@link org.uncommons.watchmaker.framework.CandidateFactory} for generating arrays of {@link Double} type.
 * </p>
 * <p>
 * Specify the upper and lower bounds of each position in the generated array through an
 * array of {@link ComparableLimit} objects.
 * </p>
 * Each value generated using provided random source from a uniform distribution over the relevant range
 * 
 * 
 * @author Peter Day
 *
 */
public class DoubleArrayCandidateFactory extends AbstractCandidateFactory<Double[]> {

	private final Limit<Double>[] limits;

    /**
     *
     * @param limits the limit to apply to each position in generated arrays
     */
	public DoubleArrayCandidateFactory(Limit<Double>[] limits) {
		this.limits = limits;
	}

    /**
     * Utility constructor to apply same limit constraints to all n positions
     * in an array of n size 
     * @param limit the limit to apply
     * @param noParams size of array
     */
    @SuppressWarnings("unchecked")
    public DoubleArrayCandidateFactory(Limit<Double> limit, int noParams) {
        limits =  new Limit[noParams];
        for (int i = 0; i<noParams; i++) {
              limits[i] = limit;
        }
    }


    /**
     * generates a random Double[], the value of each position in the array is guaranteed
     * to be in the range specified on instantiation of this instance.
     * @param rng The random number generator to use when creating the random
     * candidate.
     * @return the generated candidate
     */
	public Double[] generateRandomCandidate(Random rng) {
		Double[] candidate = new Double[limits.length];
		for (int i = 0; i < limits.length; i++) {
			double range = limits[i].getMaximumValue() - limits[i].getMinimumValue();
			candidate[i] = limits[i].getMinimumValue() + (rng.nextDouble() * range);
		}
		return candidate;
	}
	
	

}
