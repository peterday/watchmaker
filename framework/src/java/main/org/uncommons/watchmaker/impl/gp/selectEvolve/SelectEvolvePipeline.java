package org.uncommons.watchmaker.impl.gp.selectEvolve;

import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvaluatedCandidate;



/**
 * A pipeline which performs both selection and evolutionary operators
 * on a population of evaluated candidates.
 * Intended to allow different genetic operators to apply different selection strategies.
 * Example use cases:
 * 
 * Comparative Partner Selection
 * Perterbable Numerical Values
 * 
 * @author Peter Day
 *
 */
public interface SelectEvolvePipeline<T> {

	
	List<T> selectEvolve(List<EvaluatedCandidate<T>> population, boolean isFitnessNatural,
			int requiredChildren, Random rng);
	
}
