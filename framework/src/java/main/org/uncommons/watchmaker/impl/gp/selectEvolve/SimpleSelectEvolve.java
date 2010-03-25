package org.uncommons.watchmaker.impl.gp.selectEvolve;

import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.SelectionStrategy;

/**
 * Provides the simplest form of select evolve pipeline.
 * 
 * @author Peter Day
 *
 * @param <T>
 */
public class SimpleSelectEvolve<T> implements SelectEvolvePipeline<T> {

	private final SelectionStrategy<T> selectionStrategy;
	private final EvolutionaryOperator<T> evolutionaryOperator;
	
	public SimpleSelectEvolve(SelectionStrategy<T> selectionStrategy, EvolutionaryOperator<T> evolutionaryOperator) {
		this.selectionStrategy = selectionStrategy;
		this.evolutionaryOperator = evolutionaryOperator;
	}
	
	@Override
	public List<T> selectEvolve(List<EvaluatedCandidate<T>> population, 
			boolean naturalFitnessScores, int requiredChildren, Random rng) {
		List<T> selectedCandidates = this.selectionStrategy.select(population, naturalFitnessScores, requiredChildren, rng);
		return this.evolutionaryOperator.apply(selectedCandidates, rng);
	}

}
