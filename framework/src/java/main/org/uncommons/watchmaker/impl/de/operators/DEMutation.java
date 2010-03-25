package org.uncommons.watchmaker.impl.de.operators;

import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import day.peter.watchmaker.de.factory.Limit;

public class DEMutation implements EvolutionaryOperator<Double[]> {

	private double mutationConstant;
	private double crossoverProbability;
	private Limit<Double>[] limits;
	public DEMutation(Limit<Double>[] limits, double mutationConstant, double crossoverProb) {
		this.limits = limits;
		this.crossoverProbability = crossoverProb;
		this.mutationConstant = mutationConstant;
	}
	
	@Override
	public List<Double[]> apply(List<Double[]> selectedCandidates, Random rng) {
		//assume size is correct
		Double[] cand1 = selectedCandidates.get(1);
		Double[] cand2= selectedCandidates.get(2);
		
		Double[] cand3 = selectedCandidates.get(3);

		Double[] baseInd = selectedCandidates.get(0);
		
		
		Double[] candidate = new Double[limits.length];

		//random point in the params
		int rnd = rng.nextInt(limits.length);



		for (int i=0; i<limits.length; i++)
		{
			if ( (rng.nextDouble()<crossoverProbability) || (rnd == i) ) {
				candidate[i] = cand3[i] +
				mutationConstant * (cand1[i] - cand2[i]) ;
			}
			else {
				candidate[i] = baseInd[i] ;
			}
			
			//ensure value is inside the search space
//			candidate[i] = limits[i].apply(candidate[i]);
			
			if (!limits[i].isValid(candidate[i])) {
				//value outside limit
//				should be returned stochastically
				candidate[i]= limits[i].getMinimumValue() + ((limits[i].getMaximumValue() - limits[i].getMinimumValue()) * rng.nextDouble());
			}
		}

		//replace 
		return null;
	}
	
	public Double[] apply(Double[] baseInd, List<Double[]> otherInds, Random rng) {
		//random point in the params
		int rnd = rng.nextInt(limits.length);
		Double[] cand1 = otherInds.get(0);
		Double[] cand2= otherInds.get(1);
		Double[] cand3 = otherInds.get(2);
		
		Double[] candidate = new Double[limits.length];

		for (int i=0; i<limits.length; i++)
		{
			if ( (rng.nextDouble()<crossoverProbability) || (rnd == i) ) {
				candidate[i] = cand3[i] +
				mutationConstant * (cand1[i] - cand2[i]) ;
			}
			else {
				candidate[i] = baseInd[i] ;
			}
			
			//ensure value is inside the search space
//			candidate[i] = limits[i].apply(candidate[i]);
			
			if (!limits[i].isValid(candidate[i])) {
				//value outside limit
//				should be returned stochastically
				candidate[i]= limits[i].getMinimumValue() + ((limits[i].getMaximumValue() - limits[i].getMinimumValue()) * rng.nextDouble());
			}
			

		}
		
		return candidate;
	}




}
