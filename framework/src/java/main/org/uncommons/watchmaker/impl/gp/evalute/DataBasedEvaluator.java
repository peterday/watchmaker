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
package org.uncommons.watchmaker.impl.gp.evalute;

import org.uncommons.watchmaker.framework.FitnessEvaluator;
import org.uncommons.watchmaker.impl.gp.inputParameter.ParameterValues;
import org.uncommons.watchmaker.impl.gp.node.Node;

import java.util.List;
import java.util.Map;


public abstract class DataBasedEvaluator<T> implements FitnessEvaluator<Node<T>>{

	
	private Map<ParameterValues, T> data;
	

	public DataBasedEvaluator(Map<ParameterValues, T> data) {
		this.data = data;
	}
	
	
	public double getFitness(Node<T> candidate, List<? extends Node<T>> population) {
		double error = 0;
        for (Map.Entry<ParameterValues, T> entry : data.entrySet())
        {
            T actualValue = candidate.evaluate(entry.getKey());
            double diff = diff(entry.getValue(), actualValue);
            error += (diff * diff);
        }
        return error;
	}
	

	public abstract double diff(T expected, T given);
	
	
	public boolean isNatural() {
		return false;
	}

}
