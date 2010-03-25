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
	public double getFitnessWithoutParsominy(T candidate, List<? extends T> population);
}
