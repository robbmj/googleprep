package com.github.robbmj.googleprep.algorithms.chess;

import com.github.robbmj.googleprep.datastructures.Linkedlist;
import com.github.robbmj.googleprep.datastructures.Point;

public class Knight extends ChessPeice {

	public static final char CHAR = 'k';
		
	public static final Point[] MOVES = new Point[] {
			new Point(-2, -1),
			new Point(-2, 1),
			new Point(-1, -2),
			new Point(-1, 2),
			new Point(2, -1),
			new Point(2, 1),
			new Point(1, -2),
			new Point(1, 2),
	};
	
	public Knight(Point position, Team side) {
		super(position, side, CHAR);
	}
	
	public Linkedlist<Point> legalMoves(ChessSquare[][] board) {
		return legalMoves(board.length, false);
	}
	
	// this method has been added so that 
	// TopCoder: http://community.topcoder.com/stat?c=problem_statement&pm=2430&rd=5072
	// can be implemented
	public Linkedlist<Point> legalMoves(int boardSize, boolean allowNegitives) {
		
		Linkedlist<Point> legalMoves = new Linkedlist<>();
		
		for (Point move : MOVES) {
			
			Point possibleMove = position.add(move);
			
			boolean inBounds = allowNegitives 
					? isInBounds(possibleMove, boardSize) 
					: super.isInBounds(possibleMove, boardSize);
			
			if (inBounds) {
				legalMoves.add(possibleMove);
			}
		}
		
		return legalMoves;
	}
	
	@Override
	public boolean isInBounds(Point p, int boardSize) {
		return (p.x >= -boardSize && p.x < boardSize) && 
			   (p.y >= -boardSize && p.y < boardSize); 
	}
}
