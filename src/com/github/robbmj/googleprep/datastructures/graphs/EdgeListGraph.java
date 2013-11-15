

package com.github.robbmj.googleprep.datastructures.graphs;

/**
 * 
 * Vertices: nodes
 * Edges: links
 *
 * Discreet Graph: no two nodes are connected
 * Nodes are adjacent if they are connected
 * Path: the sequence of edges that connect two nodes
 * Connected Graph: the is a path to every node
 * self loop: is an edge that goes from a node to its self
 * multi-graph: can contain parallel edges
 * directed graph: if the edges have an orientation (direction)
 * cyclic graph means you can make at least one cycle on the graph
 * acyclic means that there is no circular path
 * connected-directed graph is only connected if you can get to all nodes by following the edge directions
 * the degree of a node is the number of edges coming out of it
 * isomorphic - same graph with different orientation
 * Planar graph - graph where no edges over lap
 * Sparse graph - lots of nodes not many edges
 *
 * Adjacency Matrix: O(V^2) memory, answers is A connected to be really quickly, good for dense graphs
 *
 * Edge List: O(V^2) memory, answers what are all the nodes connected to A quickly, good for sparse graphs
 * 
 */

import java.util.ArrayList;

import com.github.robbmj.googleprep.datastructures.Hashmap;
import com.github.robbmj.googleprep.datastructures.Hashmap.Entry;
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
	
	public Path<T> shortestPath(Vertex<T> v1, Vertex<T> v2) {
					
		Linkedlist<Vertex<T>> path = new Linkedlist<>();
		
		if (v1.equals(v2)) {
			return null;
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
		
		return new Path<T>(path);
	}

	public Path<T> minimumSpanningTree(Vertex<T> start) {
		
		Hashmap<Vertex<T>, Boolean> map = new Hashmap<>();		
		map.add(start, true);
		
		Edge<T> shortest = shortestDist(map);
				
		Path<T> p = new Path<>();
		
		while (map.size() < nodes.size() && shortest != null) {
						
			p.addEdge(shortest);
			
			map.add(shortest.getDestination(), true);
			map.add(shortest.getSource(), true);
			
			shortest = shortestDist(map);
		}
		return p;
	}
	
	private Edge<T> shortestDist(Hashmap<Vertex<T>, Boolean> visited) {
		
		Edge<T> candidate = null;
		int minWeight = Integer.MAX_VALUE;
		
		for (Entry<Vertex<T>, Boolean> e : visited.getIterator()) {
						
			for (Edge<T> edge : e.key.getEdges()) {
				
				if (visited.get(edge.getDestination()) == null && edge.getWeight() < minWeight) {
					minWeight = edge.getWeight();
					candidate = edge;
				}	
			}
		}
		return candidate;
 	}
	
	public Linkedlist<Edge<T>> adjacentVertices(Vertex<T> v) {
		return v.getEdges();
	}
	
	public String toString() {
		return nodes.toString();
	}

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
		
		public Vertex<T> nearestNeighbor() {
			
			Edge<T> nearest = null;
			
			for (Edge<T> e : getEdges()) {
				if (nearest == null || e.getWeight() < nearest.getWeight()) {
					nearest = e;
				}
			}
			
			return nearest.getDestination();
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
		
		@Override
		public String toString() {
			if (isDirected()) {
				return destination.getLabel() + " -- " + weight + " -> " + source.getLabel();
			}
			return destination.getLabel() + " <- " + weight + " -> " + source.getLabel();
		}
	}
	
	public static class Path<T> {
		private Linkedlist<Edge<T>> path = new Linkedlist<>();
		private int totalWeight = 0;
		
		public Path() {}
		
		public Path(Linkedlist<Vertex<T>> path) {
			
			Vertex<T> p = null;
			
			for (Vertex<T> v : path) {
			
				if (p != null) {
					for (Edge<T> e : v.getEdges()) {
						if (p.equals(e.getDestination())) {
							this.path.add(e);
							totalWeight += e.getWeight();
						}
					}
				}
				p = v;
			}
		}
		
		public void addEdge(Edge<T> e) {
			this.path.add(e);
			this.totalWeight += e.getWeight();
		}
		
		public Linkedlist<Edge<T>> getPath() {
			return path;
		}
		
		public int getTotalWeight() {
			return totalWeight;
		}
		
		@Override
		public String toString() {
			return path.toString() + " : total weight = " + totalWeight;
		}
		
	}
}