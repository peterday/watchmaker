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

/**
 * {@link org.uncommons.watchmaker.impl.gp.inputParameter.ParameterValues} are used to store a set of input values for a
 * candidate solution.
 * Typically each training / test case will consist of an instance of {@link org.uncommons.watchmaker.impl.gp.inputParameter.ParameterValues}
 * and a desired output value.
 * @author Peter Day
 *
 */
public interface ParameterValues {
	/**
	 * 
	 * @param <T> parameter type - as defined by the parameter definition
	 * @param parameter
	 * @return the parameter value for the given parameter
	 */
	public <T> T getParameterValue(Parameter<T> parameter);
	
	/**
	 * 
	 * @param <T>
	 * @param parameter the parameter to set
	 * @param value the value of this parameter
	 */
	public <T> void setParameterValue(Parameter<T> parameter, T value);
}
