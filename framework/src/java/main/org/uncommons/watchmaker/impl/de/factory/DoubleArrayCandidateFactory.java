package org.uncommons.watchmaker.impl.de.factory;

import java.util.Random;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

/**
 * {@link org.uncommons.watchmaker.framework.CandidateFactory} for generating arrays of {@link Double} type.
 * 
 * Specify the upper and lower bounds of each position in the generated array through an
 * array of {@link Limit} objects.
 * Each value generated as a uniform
 * 
 * 
 * @author Peter Day
 *
 */
public class DoubleArrayCandidateFactory extends AbstractCandidateFactory<Double[]> {

	private final Limit<Double>[] limits;
	
	
	public DoubleArrayCandidateFactory(Limit<Double>[] limits) {
		super();
		this.limits = limits;
	}
			
	@Override
	public Double[] generateRandomCandidate(Random rng) {
		Double[] candidate = new Double[limits.length];
		
		for (int i = 0; i < limits.length; i++) {
			double range = limits[i].getMaximumValue() - limits[i].getMinimumValue();
			double result = limits[i].getMinimumValue() + (rng.nextDouble() * range);
			candidate[i] = result;
		}
		return candidate;
	}
	
	

}
