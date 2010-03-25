package org.uncommons.watchmaker.impl.gp.inputParameter;


/**
 * 
 * A parameter definition.
 * In the simplest case this is used to hold the name of a parameter.
 * Further implementations may apply validations / transformations on the 
 * input data.
 * @author Peter Day
 *
 * @param <T> The parameter type
 */
public interface Parameter<T> {
	public String getName();
	public T getValue(Object value);

}
