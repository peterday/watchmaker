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

import org.uncommons.maths.number.ConstantGenerator;
import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.impl.gp.node.Node;
import org.uncommons.watchmaker.impl.gp.node.terminal.ConstantNode;

import java.util.*;

public class NumberPerterber implements EvolutionaryOperator<Node<Double>>{

    private final NumberGenerator<Double> scale;


    public NumberPerterber(NumberGenerator<Double> scaler) {
        this.scale = scaler;
    }

    public NumberPerterber(double scaler) {
        this.scale = new ConstantGenerator(scaler);
    }
	
	public List<Node<Double>> apply(List<Node<Double>> selectedCandidates,
			Random rng) {
		List<Node<Double>> children = new ArrayList<Node<Double>>();
		
		for (Iterator<Node<Double>> iterator = selectedCandidates.iterator(); iterator
				.hasNext();) {
			Node<Double> parent = (Node<Double>) iterator.next();
			try {
				Node<Double> child = apply(parent, rng);
				if (child != null) {
					children.add(child);
				}
			} catch (CloneNotSupportedException e) {
				throw new RuntimeException(e);
			}
		}
		
		return children;
	}
	
	
	public Node<Double> apply(Node<Double> parent, Random rng) throws CloneNotSupportedException {
//		System.out.println("applying perterber");
		Collection<ConstantNode> nodes = new ArrayList<ConstantNode>();
		Node<Double> child = parent.clone();
		nodes = child.getNodesOfType(ConstantNode.class);
		if (nodes.size() == 0) {
			System.out.println("null perterb");
			return child;
//			return null;
		}
		//pick a node at random
		double mutator = rng.nextGaussian() * scale.nextValue();
		int nodePick = rng.nextInt(nodes.size());
		ConstantNode node = (ConstantNode)nodes.toArray()[nodePick];
//		System.out.println("mutation: " + mutator + " - oldval: " + node.getValue() + " - newval: " + node.getValue() * mutator);
		node.setValue(node.getValue() + mutator);
		
		return child;
	}

}
