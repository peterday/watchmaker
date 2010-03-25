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
 * A binary node operates on exactly two arguments and results 
 * 
 * @author Peter Day
 *
 * @param <T> The return type of the {@link #evaluate(ParameterValues)} method
 * @param <U> the type of the first parameter
 * @param <V> the type of the second parameter
 */
public abstract class BinaryNode<T, U, V> extends BaseNode<T> {

	public abstract T evaluate(U arg1, V arg2);
	public static final int ARITY = 2;
	
	@SuppressWarnings("unchecked")
	public T evaluate(ParameterValues values) {
		U arg1 = (U) this.getChild(0).evaluate(values);
		V arg2 = (V) this.getChild(1).evaluate(values);
		return evaluate(arg1, arg2);
	}

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(this.getChild(0).toString());
		sb.append(this.getOperatorString());
		sb.append(this.getChild(1).toString());
		sb.append(")");
		return sb.toString();
	}
	
	public int getArity() {
		
		return ARITY;
	}
	
	public abstract String getOperatorString();
	

}
