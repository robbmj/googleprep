package com.github.robbmj.googleprep.datastructures;

public class Point implements Comparable<Point> {
	
	public int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point add(Point v1) {
		return new Point(v1.x + this.x, v1.y + this.y);
	}
	
	@Override
	public String toString() {
		return "{" + x + ", " + y + "}";
	}
	
	@Override
	public boolean equals(Object p) {
		if (!(p instanceof Point)) return false;
		return this.x == ((Point)p).x && this.y == ((Point)p).y;
	}

	@Override
	public int compareTo(Point p) {
		int d = this.x - p.x;
		return d != 0 ? d : this.y - p.y;
	}
}
