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
package org.uncommons.watchmaker.impl.gp.evalute;

import org.uncommons.watchmaker.impl.gp.inputParameter.ParameterValues;

import java.util.Map;


public class DoubleDataEvaluator extends DataBasedEvaluator<Double> {

	public DoubleDataEvaluator(Map<ParameterValues, Double> data) {
		super(data);
	}

	@Override
	public double diff(Double expected, Double given) {
		return expected - given;
	}

}
