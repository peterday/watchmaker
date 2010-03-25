package org.uncommons.watchmaker.impl.gp.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.w3c.dom.ranges.RangeException;

import day.peter.watchmaker.gp.node.Node;
import day.peter.watchmaker.gp.treeBuilder.TreeBuilder;

public class TreeMutation<T> implements EvolutionaryOperator<Node<T>> {
	TreeBuilder<?> treeBuilder;
	int maxDepth;
	int minDepth;
	
	
	public TreeMutation(TreeBuilder<T> tb, int minDepth, int maxDepth) {
		super();
		this.maxDepth = maxDepth;
		this.minDepth = minDepth;
		this.treeBuilder = tb;
	}
	
	@Override
	public List<Node<T>> apply(List<Node<T>> selectedCandidates, Random rng) {
		
		List<Node<T>> children = new ArrayList<Node<T>>();
		for (Iterator<Node<T>> iterator = selectedCandidates.iterator(); iterator
				.hasNext();) {
			Node<T> node = iterator.next();
			children.add(mutate(node, rng));
		}
		
		
		
		return children;
	}
	
	public Node<T> mutate(Node<T> parent, Random rng) {
		Node<T> childTree;
		try {
			childTree = parent.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("Could not clone node", e);
		}
		
//		int initialDepth = childTree.getDepth();
		int initialCount = childTree.countNodes();
		
		int mutationpoint = rng.nextInt(initialCount);
//		Node<?> mutationNode = childTree.getNode(mutationpoint);
		
		//set null
//		childTree = (Node<T>) childTree.replaceNode(mutationpoint, null);
		int newDepth = 0;
		if (childTree != null) {
			newDepth = childTree.getDepth();
		}
		
		
		
		//grow tree
		Node<?> newChild = treeBuilder.generateRandomCandidate(rng, minDepth - newDepth, maxDepth - newDepth);
		
		
		if (childTree == null) {
			return (Node<T>)newChild;
		}
		return (Node<T>) childTree.replaceNode(mutationpoint, newChild);
		
	}
	

}
