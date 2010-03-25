package org.uncommons.watchmaker.impl.gp.parsimony;

import java.util.List;

/**
 * A parsimony evaluator is used to calculate the effect on the fitness value of
 * a candidate
 * @author Peter Day
 *
 * @param <T>
 */
public interface ParsimonyEvaluator<T> {
	public double getParsimonyFitness(T candidate, List<? extends T> population);
}
