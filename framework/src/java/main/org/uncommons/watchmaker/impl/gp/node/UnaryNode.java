package org.uncommons.watchmaker.impl.gp.node;

import day.peter.watchmaker.gp.inputParameter.ParameterValues;

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
	@Override
	public T evaluate(ParameterValues values) {
		
		return evaluate((U)this.getChild(0).evaluate(values));
	}
	
	@Override
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
