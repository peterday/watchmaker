package org.uncommons.watchmaker.impl.gp.inputParameter;

public class DoubleParameter extends SimpleParameter<Double>{

	
	public DoubleParameter(String name) {
		super(name);	
	}
	
	@Override
	public Double getValue(Object value) {
		if (value instanceof Number) {
			return ((Number)value).doubleValue();
		}
		//may throw exception
		return Double.valueOf(value.toString());
	}

}
