package org.uncommons.watchmaker.impl.de.evaluator;

import java.util.List;

import org.uncommons.watchmaker.framework.FitnessEvaluator;


public class DEFitnessEvaluator implements FitnessEvaluator<Double[]> {

	private final boolean natural;
	private final DEFunction function;
	
	
	public DEFitnessEvaluator(DEFunction function, boolean isNatural) {
		this.function = function;
		this.natural = isNatural;
	}
	
	@Override
	public double getFitness(Double[] candidate,
			List<? extends Double[]> population) {
		
		return function.evaluate(candidate);
	}

	@Override
	public boolean isNatural() {
		return natural;
	}

}
