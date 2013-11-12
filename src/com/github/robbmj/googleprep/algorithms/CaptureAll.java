package com.github.robbmj.googleprep.algorithms;

import java.util.ArrayList;
import java.util.HashMap;

import com.github.robbmj.googleprep.datastructures.Hashmap;
import com.github.robbmj.googleprep.datastructures.Linkedlist;
import com.github.robbmj.googleprep.datastructures.Queue;

public class CaptureAll {

	
	public static void captureAll(int boardSize) {
		Square[][] board = new Square[boardSize][boardSize];
				
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				board[i][j] = new Square(new Vector(i, j), (char)32);
			}
		}
		
		Vector[] peices = new Vector[3];
		
		for (int i = 0; i < 3; i++) {
			int x = (int)(Math.random() * boardSize);
			int y = (int)(Math.random() * boardSize);
			
			Vector v = new Vector(x, y);
			
			if (i == 1 && v.equals(peices[0])) {
				i--;
				continue;
			}
			else if (i == 2 && (v.equals(peices[0]) || v.equals(peices[1]))) {
				i--;
				continue;
			}
			peices[i] = v;
		}
				
		board[peices[0].x][peices[0].y].position = peices[0];
		board[peices[0].x][peices[0].y].ocupant = '1';
		
		board[peices[1].x][peices[1].y].position = peices[1];
		board[peices[1].x][peices[1].y].ocupant = '1';
		
		Vector start = peices[2];
				
		Linkedlist<Vector> path1 = shortestPath(start, peices[0], board);
		Linkedlist<Vector> path2 = shortestPath(start, peices[1], board);
		
		Linkedlist<Vector> path3 = new Linkedlist<>();
		Linkedlist<Vector> shortestPath = new Linkedlist<>();
		
		if (path1.size() < path2.size()) {
			path3 = shortestPath(peices[0], peices[1], board);
			shortestPath = path1;
		}
		else {
			path3 = shortestPath(peices[1], peices[0], board);
			shortestPath = path2;
		}
		
		shortestPath.removeBack();
		shortestPath.add(path3);
		
		for (Vector step : shortestPath) {
			print(board, step);
			System.out.println("\t" + step);
		}
		
	}
	
	static Linkedlist<Vector> shortestPath(Vector start, Vector end, Square[][] board) {
		
		Vector[] knightMoves = new Vector[] {
				new Vector(1, 2),
				new Vector(-1, 2),
				new Vector(1, -2),
				new Vector(-1, -2),
				new Vector(2, 1),
				new Vector(-2, -1),
				new Vector(-2, 1),
				new Vector(2, -1),
		};
		
		int boardSize = board.length;
		
		Queue<Vector> nodesToVisit = new Queue<>();
		
		Vector knightPos = start;
		
		nodesToVisit.queue(knightPos);
		
		Hashmap<Vector, Boolean> visited = new Hashmap<>();
		Hashmap<Vector, Integer> distances = new Hashmap<>();
		Hashmap<Vector, Vector> previous = new Hashmap<>();
				
		while (nodesToVisit.size() > 0) {
			
			knightPos = nodesToVisit.dequeue();
		
			visited.add(knightPos, true);
						
			// calculate the positions that the knight can visit
			
			ArrayList<Vector> nextMoves = new ArrayList<>();
			
			for (Vector move : knightMoves) {
				
				Vector possibleMove = knightPos.add(move);
				if (possibleMove.x >= 0 && possibleMove.x < boardSize &&
					possibleMove.y >= 0 && possibleMove.y < boardSize) {
					nextMoves.add(possibleMove);
				}
			}
			
			for (Vector v : nextMoves) {
				
				Vector boardV = board[v.x][v.y].position;
				
				Integer dist = distances.get(knightPos);
				
				int totalDist = dist == null ? 1 : dist + 1;
				
				boolean isShorter = distances.get(boardV) == null ? true : totalDist < distances.get(boardV);
									
				if (isShorter && visited.get(boardV) == null) {
					nodesToVisit.queue(board[boardV.x][boardV.y].position);
					distances.add(boardV, totalDist);
					previous.add(boardV, knightPos);
					
				}
			}
			
		}
		
		Vector backTracker = end;
		
		Linkedlist<Vector> path = new Linkedlist<>();
		
		do {
			path.pushFront(backTracker);
			backTracker = previous.get(backTracker);
		} while (backTracker != null);
		
		return path;
	}
	
	
	static void print(Square[][] data, Vector k) {
		for (int i = 0; i < data.length; i++) {
			System.out.println("");
			System.out.print("|");
			for (int j = 0; j < data.length; j++) {
				
				if (k.x == i && k.y == j) {
					if (data[k.x][k.y].ocupant == '1') {
						data[k.x][k.y].ocupant = 'X';
					}
					else {
						data[k.x][k.y].ocupant = '-';
					}
					System.out.print("k|");
				}
				else {
					System.out.print(data[i][j] + "|");
				}
			}
			
		}
	}
	
	static class Square {
		Vector position;
		char ocupant;
		public Square(Vector v, char ocupant) {
			this.position = v;
			this.ocupant = ocupant;
		}
		public String toString() {
			return ocupant + "";
		}
		public boolean equals(Object v) {
			if (!(v instanceof Vector)) return false;
			return this.position.equals((Vector)v);
		}
	}
	
	static class Vector {
		int x, y;
		public Vector(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public Vector add(Vector v1) {
			return new Vector(v1.x + this.x, v1.y + this.y);
		}
		
		@Override
		public String toString() {
			return "{" + x + ", " + y + "}";
		}
		
		@Override
		public boolean equals(Object v) {
			return this.x == ((Vector)v).x && this.y == ((Vector)v).y;
		}
	}
	
}
