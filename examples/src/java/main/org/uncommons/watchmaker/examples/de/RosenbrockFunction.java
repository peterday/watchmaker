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


public class RosenbrockFunction implements DEFunction {

	public static final int NO_ARGS = 2;

	public double evaluate(Double[] args) {
	
		// function f(x,y) = 100. (x^2 - y)^2 + (1-x)^2
		double x= args[0];
		double y = args[1];
		
		
		
		double result = 100 * Math.pow((x*x - y),2d) + Math.pow(1-x, 2d);
		return result;
	}

	
	public int getNoArgs() {
		
		return NO_ARGS;
	}

}
