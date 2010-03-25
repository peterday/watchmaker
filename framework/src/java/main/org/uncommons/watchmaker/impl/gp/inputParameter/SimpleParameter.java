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
package org.uncommons.watchmaker.impl.gp.inputParameter;

public class SimpleParameter<T> implements Parameter<T> {

	private final String name;
	
	public SimpleParameter(String name) {
		this.name = name;
	}
	

	public String getName() {
		
		return name;
	}

	@SuppressWarnings("unchecked")
	public T getValue(Object value) {
		//try a class cast
		return (T) value;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Parameter<?>) {
			Parameter<?> otherParam = (Parameter<?>) obj;
			this.getName().equals(otherParam.getName());
		}
		return false;
	}

}
