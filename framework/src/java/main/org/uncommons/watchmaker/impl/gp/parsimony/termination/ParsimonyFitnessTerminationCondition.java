package org.uncommons.watchmaker.impl.gp.parsimony.termination;





import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.TerminationCondition;

import day.peter.watchmaker.gp.parsimony.ParsimonyPressureFitnessEvaluator;


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
	@Override
	public boolean shouldTerminate(PopulationData<?> populationData) {
		double fitness = this.evaluator.getFitnessWithoutParsominy((T)populationData.getBestCandidate(), null);
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
