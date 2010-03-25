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
package org.uncommons.watchmaker.impl.gp.node.terminal;

import org.uncommons.watchmaker.impl.gp.inputParameter.ParameterValues;
import org.uncommons.watchmaker.impl.gp.node.Node;

import java.text.DecimalFormat;
import java.text.NumberFormat;



public class ConstantNode extends TerminalNode<Double> {

	private static final NumberFormat NUMBER_FORMAT = new DecimalFormat("######0.##");
	private double value;
	
	public ConstantNode(double value) {
		this.value = value;
	}
	

	public Double evaluate(ParameterValues values) {
		return value;
	}
	

	public String toString() {
		return NUMBER_FORMAT.format(value);
	}
	
	@Override
	public Node<Double> clone() {
		
		return new ConstantNode(value);
	}
	
	public double getValue() {
		return this.value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}



}
