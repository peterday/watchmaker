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
package org.uncommons.watchmaker.impl.gp.operators;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.impl.gp.node.Node;
import org.uncommons.watchmaker.impl.gp.node.terminal.ConstantNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TreeSimplifier implements EvolutionaryOperator<Node<Double>>{

	
	public List<Node<Double>> apply(List<Node<Double>> selectedCandidates, Random rng) {
		List<Node<Double>> newPop = new ArrayList<Node<Double>>();
		Iterator<Node<Double>> iter = selectedCandidates.iterator();
		while (iter.hasNext()) {

			newPop.add(simplify(iter.next().clone()));

		}
		return newPop;
	}


	@SuppressWarnings("unchecked")
	public Node<Double> simplify(Node<Double> parent) {

		if (parent instanceof ConstantNode) {
			return parent;
		}

		boolean allChildrenConstants = true;
		for (int i = 0; i < parent.getArity(); i++) {
			Node<Double> child = simplify((Node<Double>) parent.getChild(i));
			if (!(child instanceof ConstantNode)) {
				allChildrenConstants = false;
			}
			parent.setChild(i, child);
		}

		if (allChildrenConstants) {
			parent = (Node<Double>) new ConstantNode((Double)parent.evaluate(null));
		}

		return parent;

	}

}
