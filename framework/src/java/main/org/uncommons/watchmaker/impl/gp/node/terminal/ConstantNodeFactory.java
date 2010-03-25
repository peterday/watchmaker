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


public class ConstantNodeFactory implements NodeFactory<ConstantNode> {

	private double value;
	
	public ConstantNodeFactory(double value) {
		this.value = value;
	}
	

	public int getArity() {
		
		return TerminalNode.ARITY;
	}


	public Class<ConstantNode> getNodeClass() {
		return ConstantNode.class;
	}


	public ConstantNode newInstance() {
		
		return new ConstantNode(value);
	}

}
