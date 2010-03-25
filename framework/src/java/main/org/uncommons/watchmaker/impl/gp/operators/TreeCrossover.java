package org.uncommons.watchmaker.impl.gp.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import day.peter.watchmaker.gp.node.Node;

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
			try {
				offspring1 = parent1.clone();
				offspring2 = parent2.clone();
			} catch (CloneNotSupportedException e) {
				throw new RuntimeException("Could not clone node", e);
			}
	        

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
