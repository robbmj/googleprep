package com.github.robbmj.googleprep.datastructures.graphs;

import com.github.robbmj.googleprep.datastructures.Linkedlist;

public class Vertex<T> {

	/*private T label;

	private Linkedlist<Edge> edges = new Linkedlist<>();
	
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
	
	public Linkedlist<Edge> getEdges() {
		return edges;
	}

	public void setEdges(Linkedlist<Edge> edges) {
		this.edges = edges;
	}

	@Override
	public boolean equals(Object v) {
		return this == v;
	}
	
	public void addAdjacentVertex(Edge edge) {
		this.edges.add(edge);
	}
	
	@Override
	public String toString() {
		String s = label.toString() + "\n\t";
		for (Edge edge : edges) {
			s += edge.getDestination().getLabel() + " -> ";
		}
		return s.substring(0, s.length() - 4) + "\n";
	}*/
}
