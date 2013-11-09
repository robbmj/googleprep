package com.github.robbmj.googleprep.datastructures.graph;

import com.github.robbmj.googleprep.datastructures.Linkedlist;

public class Vertex {

	private String label;

	private Linkedlist<Vertex> outnodes;
	
	public Vertex() {
		
	}

	public Vertex(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public void removeAdjacentVertex(Vertex v) {
		outnodes.delete(v);
	}
	
	public void addAdjacentVertex(Vertex v) {
		outnodes.add(v);
	}
	
	@Override
	public boolean equals(Object v) {
		return this == v;
	}
}
