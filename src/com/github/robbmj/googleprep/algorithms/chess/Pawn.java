package com.github.robbmj.googleprep.algorithms.chess;

import com.github.robbmj.googleprep.datastructures.Linkedlist;
import com.github.robbmj.googleprep.datastructures.Point;

public class Pawn extends ChessPeice {

	public static final char CHAR = 'p';
		
	public static final Point[] MOVES = new Point[] {
			new Point(1, 0),
			new Point(1, 1),
			new Point(1, -1),
	};
	
	public Pawn(Point position, Team side) {
		super(position, side, CHAR);
	}

	@Override
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
