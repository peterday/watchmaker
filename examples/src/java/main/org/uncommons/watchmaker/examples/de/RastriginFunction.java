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

import org.uncommons.watchmaker.impl.de.evaluator.DEFunction;


public class RastriginFunction implements DEFunction {

	
	private int noArgs;
	private double a;
	
	public RastriginFunction(int noArgs, double a) {
		this.noArgs = noArgs;
		this.a = a;
	}


    public double evaluate(Double[] args) {
		
		double result = this.getNoArgs() * a;
		
		for (int i = 0; i<this.getNoArgs(); i++) {
			double x = args[i];
			result = result + ((x*x) - (a * Math.cos(Math.PI * 2 * x)));
		}
//		double x = args[0];
//		double y = args[1];
//		double result = x * x + y * y - Math.cos(18 * x) - Math.cos(18 * y) + 2;
		return result;
	}

	
	public int getNoArgs() {
		
		return noArgs;
	}

}
