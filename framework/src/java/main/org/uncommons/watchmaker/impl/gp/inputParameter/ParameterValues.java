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
