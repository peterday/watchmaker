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

import org.uncommons.watchmaker.framework.operators.AbstractCrossover;
import org.uncommons.watchmaker.impl.gp.node.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TreeCrossover<T> extends AbstractCrossover<Node<T>>{

	public TreeCrossover(int crossoverPoints) {
		super(crossoverPoints);
	}
	
	public TreeCrossover() {
		this(1);
	}

	@Override
	public List<Node<T>> mate(Node<T> parent1, Node<T> parent2,
			int numberOfCrossoverPoints, Random rng) {
		
		 	List<Node<T>> offspring = new ArrayList<Node<T>>(2);
	        Node<T> offspring1;
	        Node<T> offspring2;

			offspring1 = parent1.clone();
			offspring2 = parent2.clone();

	        

	        for (int i = 0; i < numberOfCrossoverPoints; i++)
	        {
	            int crossoverPoint1 = rng.nextInt(offspring1.countNodes());
	            
	           
	            Node<T> subTree1 = (Node<T>) offspring1.getNode(crossoverPoint1);
	            int crossoverPoint2 = rng.nextInt(offspring2.countNodes());
	            Node<T> subTree2 = (Node<T>) offspring2.getNode(crossoverPoint2);
	            offspring1 = (Node<T>) offspring1.replaceNode(crossoverPoint1, subTree2);
	            offspring2 = (Node<T>) offspring2.replaceNode(crossoverPoint2, subTree1);
	        }

	        offspring.add(offspring1);
	        offspring.add(offspring2);
	        return offspring;
		
		
	}

}
