package com.github.robbmj.googleprep.datastructures.graphs;

@SuppressWarnings( "rawtypes" )
public class ChessPeice implements Comparable {

	private String name;
	
	public ChessPeice(String name) {
		this.name = name;
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public String toString() {
		return name;
	}
}
