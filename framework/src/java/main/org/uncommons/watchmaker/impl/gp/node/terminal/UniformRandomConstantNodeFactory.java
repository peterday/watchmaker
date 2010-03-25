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
package org.uncommons.watchmaker.impl.gp.node.terminal;

import org.uncommons.watchmaker.impl.gp.node.factory.NodeFactory;

import java.util.Random;



public class UniformRandomConstantNodeFactory implements NodeFactory<ConstantNode> {

	private Random rng;
	private double minValue = 0;
	private double range = 1;
	
	public UniformRandomConstantNodeFactory(Random rng, double minValue, double range) {
		this.minValue = minValue;
		this.range = range;
		this.rng = rng;
	}
	

	public int getArity() {
		
		return TerminalNode.ARITY;
	}


	public Class<ConstantNode> getNodeClass() {
		
		return ConstantNode.class;
	}


	public ConstantNode newInstance() {
		Double value = (rng.nextDouble() * range) + minValue;
		
		return new ConstantNode(value);
	}

}
