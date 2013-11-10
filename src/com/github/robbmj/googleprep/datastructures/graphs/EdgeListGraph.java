

package com.github.robbmj.googleprep.datastructures.graphs;

//Vertices: nodes
//Edges: links

//Discreet Graph: no two nodes are connected
//Nodes are adjacent if they are connected
//Path: the sequence of edges that connect two nodes
//Connected Graph: the is a path to every node
//self loop: is an edge that goes from a node to its self
//multi-graph: can contain parallel edges
//directed graph: if the edges have an orientation (direction)
//cyclic graph means you can make at least one cycle on the graph
//acyclic means that there is no circular path
//connected-directed graph is only connected if you can get to all nodes by following the edge directions
//the degree of a node is the number of edges coming out of it
//isomorphic - same graph with different orientation
//Planar graph - graph where no edges over lap
//Sparse graph - lots of nodes not many edges

//Adjacency Matrix: O(V^2) memory, answers is A connected to be really quickly, good for dense graphs

//Edge List: O(V^2) memory, answers what are all the nodes connected to A quickly, good for sparse graphs

import java.util.ArrayList;
import java.util.List;

import com.github.robbmj.googleprep.datastructures.Hashmap;
import com.github.robbmj.googleprep.datastructures.Linkedlist;
import com.github.robbmj.googleprep.datastructures.Queue;
import com.github.robbmj.googleprep.datastructures.Stack;

public class EdgeListGraph<T>  {

	private ArrayList<Vertex<T>> nodes;
	
	public EdgeListGraph() {
		
	}
	
	public EdgeListGraph(ArrayList<Vertex<T>> nodes) {
		this.nodes = nodes;
	}
	
	public Vertex<T> createVertex(T label) {
		return new Vertex<>(label);
	}
	
	public Edge<T> createEdge(Vertex<T> destination) {
		return new Edge<>(destination, 1, false);
	}
	
	public Edge<T> createEdge(Vertex<T> destination, int weight) {
		return new Edge<>(destination, weight, false);
	}
	
	public Edge<T> createEdge(Vertex<T> destination, int weight, boolean directed) {
		return new Edge<>(destination, weight, directed);
	}
	
	// @Override
	public List<Edge<Vertex<T>>> shortestPath(Vertex<T> v1, Vertex<T> v2) {
		
		ArrayList<Edge<Vertex<T>>> path = new ArrayList<>();
				
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
		
		public Vertex() {
			
		}

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
		
		public void addAdjacentVertex(Vertex<T> edge, int weigth) {
			this.edges.add(new Edge<T>(edge, weigth));
		}
		
		@Override
		public String toString() {
			String s = label.toString() + "\n\t";
			for (Edge<T> edge : edges) {
				s += edge.getDestination().getLabel() + " -> ";
			}
			return s.substring(0, s.length() - 4) + "\n";
		}
	}
	
	public static class Edge<T> {

		private Vertex<T> destination;
		private int weight;
		private boolean directed;
		
		public Edge(Vertex<T> destination) {
			this(destination, 1, false);
		}
		
		public Edge(Vertex<T> destination, int weight) {
			this(destination, weight, false);
		}
		
		public Edge(Vertex<T> destination, int weight, boolean directed) {
			this.destination = destination;
			this.weight = weight;
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
	}
}