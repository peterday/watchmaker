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
import org.uncommons.watchmaker.impl.gp.node.factory.NodeFactory;


@SuppressWarnings("unchecked")
public class InputParameterNodeFactory implements NodeFactory<InputParameterNode<?>> {

	private final Parameter<?> parameter;
	
	public InputParameterNodeFactory(Parameter<?> parameter) {
		this.parameter = parameter;
	}
	

	public int getArity() {
		return TerminalNode.ARITY;
	}


//	public Class<InputParameterNode> getNodeClass() {
//		return  InputParameterNode.class;
////		return (Class<InputParameterNode<T>>) InputParameterNode.class;
//	}

	
	public InputParameterNode newInstance() {
		
		return new InputParameterNode(parameter);
	}

}
