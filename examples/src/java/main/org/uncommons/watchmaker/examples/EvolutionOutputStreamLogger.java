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
package org.uncommons.watchmaker.examples;

import org.uncommons.watchmaker.framework.EvolutionObserver;
import org.uncommons.watchmaker.framework.PopulationData;

import java.io.PrintStream;

/**
 * Evolution observer to log generational information to a specified output stream
 * @param <T>  the type of candidate solution being 
 */
public class EvolutionOutputStreamLogger<T> implements EvolutionObserver<T> {
	private final PrintStream output;
	public EvolutionOutputStreamLogger(final PrintStream outStream) {
		this.output = outStream;
	}
	
	
	public void populationUpdate(PopulationData<? extends T> data) {
       

		 output.println("Generation " + data.getGenerationNumber() + ": " + data.getBestCandidateFitness());
		 
	}

}
