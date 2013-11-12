package com.github.robbmj.googleprep.algorithms.chess;

import com.github.robbmj.googleprep.datastructures.Point;

public class ChessSquare {
	private Point position;
	private ChessPeice ocupant;
	
	public ChessSquare(Point v) {
		this(v, null);
	}
	
	public ChessSquare(Point v, ChessPeice ocupant) {
		this.position = v;
		this.ocupant = ocupant;
	}
	
	public String toString() {
		if (ocupant == null) return " ";
		return ocupant.getChar() + "";
	}
	
	public boolean equals(Object cs) {
		if (!(cs instanceof ChessSquare)) return false;
		return this.position.equals(((ChessSquare)cs).position);
	}
		
	public IChessPeice getOcupant() {
		return ocupant;
	}

	public void setOcupant(ChessPeice ocupant) {
		this.ocupant = ocupant;
	}
	
	public Point getPosition() {
		return position;
	}

	public boolean isOccupied() {
		return ocupant != null;
	}
}
