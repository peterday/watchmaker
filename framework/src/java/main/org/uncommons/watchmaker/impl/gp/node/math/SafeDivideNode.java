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
package org.uncommons.watchmaker.impl.gp.node.math;

import org.uncommons.watchmaker.impl.gp.node.BinaryNode;


public class SafeDivideNode extends BinaryNode<Double, Double, Double> {

	@Override
	public Double evaluate(Double arg1, Double arg2) {
		if (arg2 == 0) {
			return 0d;
		}
		return arg1 / arg2;
	}

	@Override
	public String getOperatorString() {
		
		return "/";
	}


}
