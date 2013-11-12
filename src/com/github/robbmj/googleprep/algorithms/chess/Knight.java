package com.github.robbmj.googleprep.algorithms.chess;

import com.github.robbmj.googleprep.datastructures.Linkedlist;
import com.github.robbmj.googleprep.datastructures.Point;

public class Knight extends ChessPeice {

	public static final char CHAR = 'k';
		
	public static final Point[] MOVES = new Point[] {
			new Point(1, 2),
			new Point(-1, 2),
			new Point(1, -2),
			new Point(-1, -2),
			new Point(2, 1),
			new Point(-2, -1),
			new Point(-2, 1),
			new Point(2, -1),
	};
	
	public Knight(Point position, Team side) {
		super(position, side, CHAR);
	}
	
	public Linkedlist<Point> legalMoves(ChessSquare[][] board) {
		int boardSize = board.length;
		
		Linkedlist<Point> legalMoves = new Linkedlist<>();
		
		for (Point move : MOVES) {
			
			Point possibleMove = position.add(move);
			
			if (isInBounds(possibleMove, boardSize)) {
				legalMoves.add(possibleMove);
			}
		}
		
		return legalMoves;
	}
}
