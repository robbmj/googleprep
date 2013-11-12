

package com.github.robbmj.googleprep.datastructures.graphs;

import java.util.ArrayList;
import java.util.List;

import com.github.robbmj.googleprep.datastructures.Hashmap;
import com.github.robbmj.googleprep.datastructures.Linkedlist;
import com.github.robbmj.googleprep.datastructures.Queue;
import com.github.robbmj.googleprep.datastructures.Stack;

public class EdgeListGraph<T>  {

	private ArrayList<Vertex<T>> nodes;
	
	public EdgeListGraph() { 
		nodes = new ArrayList<>();
	}
	
	public EdgeListGraph(ArrayList<Vertex<T>> nodes) {
		initGraph(nodes);
		Math.min(1, 1);
	}
		
	public Vertex<T> createVertex(T label) {
		Vertex<T> v = new Vertex<>(label);
		nodes.add(v);
		return v;
	}
		
	public void initGraph(ArrayList<Vertex<T>> nodes) {
		this.nodes = nodes;
	}
	
	public Hashmap<T, Vertex<T>> getNodes() {
		Hashmap<T, Vertex<T>> map = new Hashmap<>();
		
		for (Vertex<T> node : nodes) {
			map.add(node.getLabel(), node);
		}
		
		return map;
	}
	
	// @Override
	public List<Vertex<T>> shortestPath(Vertex<T> v1, Vertex<T> v2) {
					
		ArrayList<Vertex<T>> path = new ArrayList<>();
		
		if (v1.equals(v2)) {
			path.add(v1);
			return path;
		}
		
		Queue<Vertex<T>> nodesToVisit = new Queue<>();
		Hashmap<Vertex<T>, Boolean> visitedNodes = new Hashmap<>();
		
		//visitedNodes.add(v1, true);
		nodesToVisit.queue(v1);
		
		Hashmap<Vertex<T>, Integer> distances = new Hashmap<>();
		Hashmap<Vertex<T>, Vertex<T>> previous = new Hashmap<>();
		
		//distance.add(v1, 0);
		
		while (nodesToVisit.size() > 0) {
			
			Vertex<T> currentNode = nodesToVisit.dequeue();
			visitedNodes.add(currentNode, true);
			
			for (Edge<T> edge : currentNode.getEdges()) {
			
				Integer dist = distances.get(currentNode);
					
				int test = (dist == null) ? edge.getWeight() : dist + edge.getWeight();
				
				Vertex<T> next = edge.getDestination();
				
				boolean isShorter = (distances.get(next) == null) 
						? true : test < distances.get(next);
				
				if (isShorter && visitedNodes.get(next) == null) {
					distances.add(next, test);
					nodesToVisit.queue(next);
					previous.add(next, currentNode);
				}
			}
		}
		
		// not possible to reach destination
		if (previous.size() == 0) {
			return null;
		}
		
		Vertex<T> backTracker = v2;
				
		do {
			path.add(backTracker);
			backTracker = previous.get(backTracker);
		} while (backTracker != null);
		
		// not possible to reach destination
		if (path.size() == 1) {
			return null;
		}
		
		return path;
	}

	// @Override
	public Linkedlist<Edge<T>> adjacentVertices(Vertex<T> v) {
		return v.getEdges();
	}
	
	@Override
	public String toString() {
		return nodes.toString();
	}

	// @Override
	public void bredthFirstSearch(Vertex<T> start) {
		Queue<Vertex<T>> nodesToVisit = new Queue<>();
		Hashmap<Vertex<T>, Boolean> visitedNodes = new Hashmap<>();
		
		visitedNodes.add(start, true);
		nodesToVisit.queue(start);
		
		while (nodesToVisit.size() > 0) {
			
			Vertex<T> currentNode = nodesToVisit.dequeue();
			
			for (Edge<T> edge : currentNode.getEdges()) {
				
				Vertex<T> next = edge.getDestination();
				
				if (visitedNodes.get(next) == null) {
					System.out.println(next.getLabel());
					nodesToVisit.queue(next);
					visitedNodes.add(next, true);
				}
			}
		}
		
	}

	// @Override
	public void depthFirstSearch(Vertex<T> start) {
		Stack<Vertex<T>> nodesToVisit = new Stack<>();
		Hashmap<Vertex<T>, Boolean> visitedNodes = new Hashmap<>();
		
		visitedNodes.add(start, true);
		nodesToVisit.push(start);
		
		depthFirstSearch(visitedNodes, nodesToVisit);
	}
	
	private void depthFirstSearch(Hashmap<Vertex<T>, Boolean> visitedNodes, Stack<Vertex<T>> s) {
		
		if (s.size() == 0) {
			return;
		}
		
		Vertex<T> currentNode = s.pop();
		
		for (Edge<T> edge : currentNode.getEdges()) {
			
			Vertex<T> next = edge.getDestination(); 
			
			if (visitedNodes.get(next) == null) {
				System.out.println(next.getLabel());
				s.push(next);
				visitedNodes.add(next, true);
				depthFirstSearch(visitedNodes, s);
			}
		}
	}
	
	public static class Vertex<T> {

		private T label;

		private Linkedlist<Edge<T>> edges = new Linkedlist<>();
		
		public Vertex(T label) {
			this.label = label;
		}
		
		public T getLabel() {
			return label;
		}

		public void setLabel(T label) {
			this.label = label;
		}
		
		public Linkedlist<Edge<T>> getEdges() {
			return edges;
		}

		public void setEdges(Linkedlist<Edge<T>> edges) {
			this.edges = edges;
		}

		@Override
		public boolean equals(Object v) {
			return this == v;
		}
		
		public void addAdjacentVertex(Vertex<T> v, int weigth, boolean directed) {
			
			Edge<T> edge = new Edge<>(this, v, weigth, directed);
			
			if (!this.edges.contains(edge)) {
				this.edges.add(edge);
				if (!directed) {
					v.addAdjacentVertex(this, weigth);
				}
			}
		}
		
		public void addAdjacentVertex(Vertex<T> v, int weigth) {
			addAdjacentVertex(v, weigth, false);
		}
		
		public void addAdjacentVertex(Vertex<T> v) {
			addAdjacentVertex(v, 1, false);
		}
		
		@Override
		public String toString() {
			String s = "\n" + label.toString() + "\n";
			for (Edge<T> edge : edges) {
				
				int weight = edge.getWeight();
				
				if (edge.isDirected()) {
					s += "\t-- " + weight + " -> " + edge.getDestination().getLabel() + "\n";
				}
				else {
					s += "\t<- " + weight + " -> " + edge.getDestination().getLabel() + "\n";
				}
			}
			return s.substring(0, s.length());
		}
	}
	
	public static class Edge<T> {

		private Vertex<T> destination;
		private Vertex<T> source;
		
		private int weight;
		private boolean directed;
				
		public Edge(Vertex<T> source, Vertex<T> destination, int weight, boolean directed) {
			this.destination = destination;
			this.source = source;
			this.weight = weight;
			this.directed = directed;
		}
		
		public Vertex<T> getSource() {
			return source;
		}

		public void setSource(Vertex<T> source) {
			this.source = source;
		}

		public Vertex<T> getDestination() {
			return destination;
		}

		public void setDestination(Vertex<T> destination) {
			this.destination = destination;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		public boolean isDirected() {
			return directed;
		}

		public void setDirected(boolean directed) {
			this.directed = directed;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object edge) {
			
			Edge<T> e = (Edge<T>) edge;
			
			if (!e.destination.equals(destination)) {
				return false;
			}
			
			if (e.getWeight() != weight) {
				return false;
			}
			
			if (e.isDirected() != directed) {
				return false;
			}
			
			return true;
		}
	}
}