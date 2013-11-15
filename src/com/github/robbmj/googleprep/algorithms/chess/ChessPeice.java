package com.github.robbmj.googleprep.algorithms.chess;

import com.github.robbmj.googleprep.datastructures.Linkedlist;
import com.github.robbmj.googleprep.datastructures.Point;

public abstract class ChessPeice implements IChessPeice, Comparable<ChessPeice> {

	protected Point position;
	protected Team side;
	protected char representation;
	
	public ChessPeice(Point position, Team side, char representation) {
		setPosition(position);
		this.side = side;
		this.representation = representation;
	}
	
	public abstract Linkedlist<Point> legalMoves(ChessSquare[][] board);

	@Override
	public char getChar() {
		return representation;
	}

	@Override
	public Team getTeam() {
		return side;
	}

	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public void setPosition(Point position) {
		this.position = position;
	}
	
	public boolean isInBounds(Point p, int boardSize) {
		return (p.x >= 0 && p.x < boardSize) && 
			   (p.y >= 0 && p.y < boardSize); 
	}
	
	public int compareTo(ChessPeice cp) {
		return this.position.compareTo(cp.position);
	}
}
