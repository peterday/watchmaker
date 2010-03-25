//=============================================================================
// Copyright 2006-2010 Daniel W. Dyer
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//=============================================================================
package org.uncommons.watchmaker.examples.de;

import org.uncommons.watchmaker.examples.EvolutionOutputStreamLogger;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.termination.GenerationCount;
import org.uncommons.watchmaker.framework.termination.TargetFitness;
import org.uncommons.watchmaker.impl.de.DEGenerationalEvolution;
import org.uncommons.watchmaker.impl.de.evaluator.DEFitnessEvaluator;
import org.uncommons.watchmaker.impl.de.evaluator.DEFunction;
import org.uncommons.watchmaker.impl.de.factory.DoubleArrayCandidateFactory;
import org.uncommons.watchmaker.impl.de.factory.Limit;
import org.uncommons.watchmaker.impl.de.operators.DEMutation;
import org.uncommons.watchmaker.impl.de.selection.DistinctSelection;

import java.text.NumberFormat;
import java.util.Random;


public class RastriginExample {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Random rng = new Random();
		boolean maximize = false;

//		DEFunction function = new RosenbrockFunction();
		DEFunction function = new RastriginFunction(2, 100d);
		DEFitnessEvaluator fitnessEvaluator = new DEFitnessEvaluator(function, maximize);

		int noParams = function.getNoArgs();

//		Limit<Double> limit = new Limit<Double>(-2.048, 2.048);
		Limit<Double> limit = new Limit<Double>(-5.12, 5.12);
		Limit<Double>[] limits = new Limit[noParams];
		for (int i = 0; i<noParams; i++) {
			limits[i] = limit;
		}
		DoubleArrayCandidateFactory factory = new DoubleArrayCandidateFactory(limits);
		DEMutation mutator = new DEMutation(limits, 0.9, 0.5);
		SelectionStrategy<Double[]> selection = new DistinctSelection<Double[]>();

		DEGenerationalEvolution evoEngine = new DEGenerationalEvolution(factory, fitnessEvaluator, mutator, selection, rng);

		evoEngine.addEvolutionObserver(new EvolutionOutputStreamLogger<Double[]>(System.out));

		Double[] result = evoEngine.evolve(60, 0, new GenerationCount(10000), new TargetFitness(0d, fitnessEvaluator.isNatural()));
		for (int i = 0; i<result.length; i++) {
			NumberFormat nf = NumberFormat.getInstance();
			System.out.println(nf.format(result[i]));
		}

	}

}