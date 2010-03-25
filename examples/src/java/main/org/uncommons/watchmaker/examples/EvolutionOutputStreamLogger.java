package org.uncommons.watchmaker.examples;

import java.io.PrintStream;

import org.uncommons.watchmaker.framework.EvolutionObserver;
import org.uncommons.watchmaker.framework.PopulationData;

public class EvolutionOutputStreamLogger<T> implements EvolutionObserver<T> {
	private final PrintStream output;
	public EvolutionOutputStreamLogger(final PrintStream outStream) {
		this.output = outStream;
	}
	
	@Override
	public void populationUpdate(PopulationData<? extends T> data) {
		 output.println("Generation " + data.getGenerationNumber() + ": " + data.getBestCandidateFitness() + " ---- popsize: " + data.getPopulationSize());
		 
	}

}
