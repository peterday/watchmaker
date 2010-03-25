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

import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.watchmaker.impl.gp.node.Node;
import org.uncommons.watchmaker.impl.gp.node.factory.NodeFactory;

import java.util.Random;



public interface NodePool<T> {
	public Node<T> createRandomNode(Random rng);
    public void addNodeFactory(NodeFactory<?> node, NumberGenerator<Double> relativeProbability);
	public void addNodeFactory(NodeFactory<?> node, double relativeProbability);
	public void removeNodeFactory(NodeFactory<?> node);
	public double getProbabilitySum();
}
