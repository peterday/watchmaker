package org.uncommons.watchmaker.impl.gp.parsimony;

import java.util.List;

import org.uncommons.watchmaker.framework.FitnessEvaluator;


/**
 * A wrapper around
 * @author Peter Day
 *
 * @param <T>
 */
public class ParsimonyFitnessEvaluatorWrapper<T> implements ParsimonyPressureFitnessEvaluator<T>{

	private FitnessEvaluator<T> underlyingFitnessCalculator;
	private ParsimonyEvaluator<T> parsimonyCalculator;
	
	@Override
	public double getFitnessWithoutParsominy(T candidate,
			List<? extends T> population) {
		
		return underlyingFitnessCalculator.getFitness(candidate, population);
	}

	@Override
	public double getFitness(T candidate, List<? extends T> population) {
		
		return this.getFitnessWithoutParsominy(candidate, population) + parsimonyCalculator.getParsimonyFitness(candidate, population);
	}

	@Override
	public boolean isNatural() {
		
		return underlyingFitnessCalculator.isNatural();
	}

}
