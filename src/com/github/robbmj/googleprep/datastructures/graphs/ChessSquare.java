package com.github.robbmj.googleprep.datastructures.graphs;

public class ChessSquare<T extends ChessPeice> {

	T peice;
	
	public ChessSquare() {
		
	}
	
	public ChessSquare(T peice) {
		this.peice = peice;
	}
	
	public boolean isEmpty() {
		return peice == null;
	}
	
	@Override
	public String toString() {
		if (isEmpty()) {
			return "| |";
		}
		return "|" + peice.toString() + "|";
	}
}
