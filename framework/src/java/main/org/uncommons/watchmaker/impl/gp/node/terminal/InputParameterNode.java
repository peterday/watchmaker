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

import org.uncommons.watchmaker.impl.gp.inputParameter.Parameter;
import org.uncommons.watchmaker.impl.gp.inputParameter.ParameterValues;
import org.uncommons.watchmaker.impl.gp.node.Node;


public class InputParameterNode<T> extends TerminalNode<T> {

	private Parameter<T> parameter;
	
	public InputParameterNode(Parameter<T> parameter) {
		this.parameter = parameter;
	}
	

	public T evaluate(ParameterValues values) {
		
		return values.getParameterValue(parameter);
	}
	
	@Override
	public String toString() {
		return parameter.getName();
	}
	
	@Override
	public Node<T> clone() {
		return new InputParameterNode<T>(parameter);
	}

}
