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
package org.uncommons.watchmaker.impl.gp.parsimony;

import org.uncommons.maths.number.ConstantGenerator;
import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.watchmaker.impl.gp.node.Node;

import java.util.List;




public class SimpleAdditiveParsimonyPressure implements ParsimonyEvaluator<Node<?>> {

	private final NumberGenerator<Double> nodeMultiplier;
	
	public SimpleAdditiveParsimonyPressure(double multiplier) {
		this.nodeMultiplier = new ConstantGenerator<Double>(multiplier);
	}

    public SimpleAdditiveParsimonyPressure(NumberGenerator<Double> valueProvider) {
            this.nodeMultiplier = valueProvider;
    }


    
	public double getParsimonyFitness(Node<?> candidate,
			List<? extends Node<?>> population) {
		return candidate.countNodes() * nodeMultiplier.nextValue();
	}

}
