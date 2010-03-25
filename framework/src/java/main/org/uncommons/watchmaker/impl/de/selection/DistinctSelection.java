package org.uncommons.watchmaker.impl.de.selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.SelectionStrategy;

public class DistinctSelection<T> implements SelectionStrategy<T> {

	@Override
	public <S extends T> List<S> select(List<EvaluatedCandidate<S>> population,
			boolean naturalFitnessScores, int selectionSize, Random rng) {
		//assure population is shuffled
		Collections.shuffle(population);
		
		 List<S> selection = new ArrayList<S>(selectionSize);
		for (int i = 0; i<selectionSize; i++) {
			selection.add(population.get(i).getCandidate());
		}
		return selection;
	}

}
