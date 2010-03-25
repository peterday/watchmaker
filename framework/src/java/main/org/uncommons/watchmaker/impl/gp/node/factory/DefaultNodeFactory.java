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
package org.uncommons.watchmaker.impl.gp.node.factory;

import org.uncommons.watchmaker.impl.gp.node.Node;


public class DefaultNodeFactory<T extends Node<?>> implements NodeFactory<T> {

	private final Class<T> nodeClass;
	private final int arity;
	public DefaultNodeFactory(Class<T> clazz, int arity) {
		this.arity = arity;
		this.nodeClass = clazz;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Node<?>> DefaultNodeFactory<T> createNodeFactory (T node) {
		return new DefaultNodeFactory<T>((Class<T>) node.getClass(), node.getArity());
	}
	

	public int getArity() {
		
		return arity;
	}

	
	public Class<T> getNodeClass() {
		
		return nodeClass;
	}


	public T newInstance() {
		try {
			return nodeClass.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}
