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
package org.uncommons.watchmaker.impl.gp.node;

import org.uncommons.watchmaker.impl.gp.inputParameter.ParameterValues;


/**
 *
 * @author Peter Day
 *
 * @param <T>
 * @param <U>
 */
public abstract class UnaryNode<T, U> extends BaseNode<T> {
	
	public abstract T evaluate(U arg);
	public abstract String getOperatorString();
	
	public static final int ARITY = 1;
	
	@SuppressWarnings("unchecked")

	public T evaluate(ParameterValues values) {
		
		return evaluate((U)this.getChild(0).evaluate(values));
	}
	
	
	public int getArity() {
		return ARITY;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(getOperatorString());
		sb.append("(");
		sb.append(getChild(0).toString());
		sb.append("))");
		return sb.toString();
	}

}
