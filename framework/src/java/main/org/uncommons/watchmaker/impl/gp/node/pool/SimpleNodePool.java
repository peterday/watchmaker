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
package org.uncommons.watchmaker.impl.gp.node.pool;

import org.uncommons.maths.number.ConstantGenerator;
import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.watchmaker.impl.gp.node.Node;
import org.uncommons.watchmaker.impl.gp.node.factory.NodeFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;


public class SimpleNodePool<T> implements NodePool<T> {

	private Map<NodeFactory<?>, NumberGenerator<Double>> nodes = new HashMap<NodeFactory<?>, NumberGenerator<Double>>();
	private Double probabilitySum = null;
	

	public void addNodeFactory(NodeFactory<?> node, double probability) {
		nodes.put(node, new ConstantGenerator(probability));
		probabilitySum = null;
	}


	public double getProbabilitySum() {
		if (probabilitySum == null) {
			probabilitySum = 0d;
			Iterator<NumberGenerator<Double>> iter = nodes.values().iterator();
			while (iter.hasNext()) {
				probabilitySum = probabilitySum + iter.next().nextValue();
			}
		}
		return probabilitySum;
	}

	@SuppressWarnings("unchecked")
	public Node<T> createRandomNode(Random rng) {
		double value = rng.nextDouble() * getProbabilitySum();
		Iterator<Entry<NodeFactory<?>, NumberGenerator<Double>>> iter = nodes.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<NodeFactory<?>, NumberGenerator<Double>> entry = iter.next();
			if (value <= entry.getValue().nextValue()) {
				return (Node<T>) entry.getKey().newInstance();
			}
			value = value - entry.getValue().nextValue();
		}
		
		//should never happen
		throw new RuntimeException("Could not find node");
	}

    public void addNodeFactory(NodeFactory<?> node, NumberGenerator<Double> relativeProbability) {
        this.nodes.put(node, relativeProbability);
    }


    public void removeNodeFactory(NodeFactory<?> node) {
		if (nodes.containsKey(node)) {
			nodes.remove(node);
			//force prob sum to be re-calculated
			probabilitySum = null;
		}
	}

}
