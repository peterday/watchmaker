package org.uncommons.watchmaker.impl.gp.inputParameter;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Simple implementation of {@link ParameterValues}. 
 * </p>
 * Stores a set of parameter values on a hash map.
 * @author Peter Day
 *
 */
public class MapParameterValues implements ParameterValues {

	private Map<Parameter<?>, Object> values = new HashMap<Parameter<?>, Object>();
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getParameterValue(Parameter<T> parameter) {
		//should be safe
		return (T) values.get(parameter);
	}

	@Override
	public <T> void setParameterValue(Parameter<T> parameter, T value) {
		values.put(parameter, value);
	}
	
}
