package com.github.robbmj.googleprep.algorithms.chess;

import com.github.robbmj.googleprep.datastructures.Point;

public interface IChessPeice {

	public static final int WHITE = 1;
	public static final int BLACK = 2;

	public static enum Team {
		WHITE,
		BLACK
	}
	
	public char getChar();
	
	public Team getTeam();
	
	public Point getPosition();
	
	public void setPosition(Point position);
}
