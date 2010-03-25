package org.uncommons.watchmaker.impl.gp.node;

import day.peter.watchmaker.gp.inputParameter.ParameterValues;

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
	@Override
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
	
	@Override
	public int getArity() {
		
		return ARITY;
	}
	
	public abstract String getOperatorString();
	

}
