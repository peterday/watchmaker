package org.uncommons.watchmaker.impl.gp.inputParameter;

public class SimpleParameter<T> implements Parameter<T> {

	private final String name;
	
	public SimpleParameter(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		
		return name;
	}

	@SuppressWarnings("unchecked")
	@Override
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
