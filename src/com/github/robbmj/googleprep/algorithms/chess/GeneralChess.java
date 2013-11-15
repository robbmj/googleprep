package com.github.robbmj.googleprep.algorithms.chess;

import java.util.Arrays;

import com.github.robbmj.googleprep.algorithms.chess.IChessPeice.Team;
import com.github.robbmj.googleprep.datastructures.Hashmap;
import com.github.robbmj.googleprep.datastructures.Hashmap.Entry;
import com.github.robbmj.googleprep.datastructures.Linkedlist;
import com.github.robbmj.googleprep.datastructures.MinHeap;
import com.github.robbmj.googleprep.datastructures.Point;
import com.github.robbmj.googleprep.datastructures.Queue;

public class GeneralChess {

	// http://community.topcoder.com/stat?c=problem_statement&pm=2915&rd=5853 TODO test with sample data
	public static int fastKnight(Point start, Point pawn1, Point pawn2) {
		
		int boardSize = 8;
		
		ChessSquare[][] board = new ChessSquare[boardSize][boardSize];
		
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				
				if (i == pawn1.x && j == pawn1.y) {
					board[i][j] = new ChessSquare(pawn1, new Pawn(pawn1, Team.WHITE));
					continue;
				}
				
				if (i == pawn2.x && j == pawn2.y) {
					board[i][j] = new ChessSquare(pawn2, new Pawn(pawn2, Team.WHITE));
					continue;
				}
				
				board[i][j] = new ChessSquare(new Point(i, j));
			}
		}
						
		Linkedlist<Point> path1 = shortestPath(start, pawn1, board);
		Linkedlist<Point> path2 = shortestPath(start, pawn2, board);
		
		Linkedlist<Point> path3 = new Linkedlist<>();
		Linkedlist<Point> shortestPath = new Linkedlist<>();
		
		if (path1.size() < path2.size()) {
			path3 = shortestPath(pawn1, pawn2, board);
			shortestPath = path1;
		}
		else {
			path3 = shortestPath(pawn2, pawn1, board);
			shortestPath = path2;
		}
		
		shortestPath.removeBack();
		shortestPath.add(path3);
		
		/*for (Point step : shortestPath) {
			print(board, new Knight(step, Team.BLACK));
			System.out.println("\t" + step);
		}*/
		
		return shortestPath.size() - 1;
	}
	
	public static int fastKnight2(Knight start, Pawn... pawns) {
		ChessSquare[][] board = new ChessSquare[8][8];
		initBoard(board, pawns);
		
		Linkedlist<Point> shortestPath = shortestPath(start.getPosition(), pawns, board);
		initBoard(board, pawns);
		
		/*for (Point step : shortestPath) {
			print(board, new Knight(step, Team.BLACK));
			System.out.println("\t" + step);
		}*/
				
		return shortestPath.size() - 1;
	}
	
	
	// http://community.topcoder.com/stat?c=problem_statement&pm=2430&rd=5072
	public static Linkedlist<String> attackPositions(Point[] points, int boardSize) {
		Linkedlist<String> knightPositions = new Linkedlist<>();
		Hashmap<String, Integer> set = new Hashmap<>();
		
		for (Point p : points) {
			Knight k = new Knight(p, Team.WHITE);
			
			Linkedlist<Point> legalMoves = k.legalMoves(boardSize, true);
			
			for (Point lm : legalMoves) {
				if (set.get(lm.toString()) == null) {
					set.add(lm.toString(), 1);
				}
				else {
					int timesOccured = set.get(lm.toString());
					set.add(lm.toString(), ++timesOccured);
				}
			}
		}
		
		for (Entry<String, Integer> e : set.getIterator()) {
			if (e.value == points.length) {
				knightPositions.add(e.key);
			}
		}
		return knightPositions;
	}
	
	private static Linkedlist<Point> shortestPath(Point start, Pawn[] pawns , ChessSquare[][] board) {
		
		Queue<Point> nodesToVisit = new Queue<>();
		
		Point knightPos = start;
		
		nodesToVisit.queue(knightPos);
		
		Hashmap<Point, Boolean> visited = new Hashmap<>();
		Hashmap<Point, Integer> distances = new Hashmap<>();
		Hashmap<Point, Point> previous = new Hashmap<>();
		
		Linkedlist<Point> listPath = null;
		int numCaptured = 0;
		
		while (nodesToVisit.size() > 0) {
			
			knightPos = nodesToVisit.dequeue();
		
			if (board[knightPos.x][knightPos.y].isOccupied() &&
				board[knightPos.x][knightPos.y].getOcupant() instanceof Pawn) {
				
				board[knightPos.x][knightPos.y].setOcupant(null);
				
				if (listPath == null) {
					listPath = convert(previous, knightPos);
				}
				else {
					listPath.removeBack();
					listPath.add(convert(previous, knightPos));
				}
				
				numCaptured++;
				
				if (numCaptured == pawns.length) {
					break;
				}
				
				visited = new Hashmap<>();
				distances = new Hashmap<>();
				previous = new Hashmap<>();
				nodesToVisit = new Queue<>();
			}
			
			visited.add(knightPos, true);
									
			Knight knight = new Knight(knightPos, Team.BLACK);
			
			Linkedlist<Point> nextMoves = knight.legalMoves(board);
						
			for (Point v : nextMoves) {
				
				Point boardV = board[v.x][v.y].getPosition();
				
				Integer dist = distances.get(knightPos);
				
				int totalDist = dist == null ? 1 : dist + 1;
				
				boolean isShorter = distances.get(boardV) == null ? true : totalDist < distances.get(boardV);
									
				if (isShorter && visited.get(boardV) == null) {
					nodesToVisit.queue(board[boardV.x][boardV.y].getPosition());
					distances.add(boardV, totalDist);
					previous.add(boardV, knight.getPosition());
				}
			}
		}
		
		return listPath;
	}

	private static Linkedlist<Point> convert(Hashmap<Point, Point> path, Point end) {
		
		Linkedlist<Point> listPath = new Linkedlist<>();
		
		do {
			listPath.pushFront(end);
			end = path.get(end);
		} while (end != null);
		
		return listPath;
	}
		
	private static Linkedlist<Point> shortestPath(Point start, Point end, ChessSquare[][] board) {
		
		Queue<Point> nodesToVisit = new Queue<>();
		
		Point knightPos = start;
		
		nodesToVisit.queue(knightPos);
		
		Hashmap<Point, Boolean> visited = new Hashmap<>();
		Hashmap<Point, Integer> distances = new Hashmap<>();
		Hashmap<Point, Point> previous = new Hashmap<>();
	
		while (nodesToVisit.size() > 0) {
			
			knightPos = nodesToVisit.dequeue();
		
			visited.add(knightPos, true);
									
			Knight knight = new Knight(knightPos, Team.BLACK);
			
			Linkedlist<Point> nextMoves = knight.legalMoves(board);
						
			for (Point v : nextMoves) {
				
				Point boardV = board[v.x][v.y].getPosition();
				
				Integer dist = distances.get(knightPos);
				
				int totalDist = dist == null ? 1 : dist + 1;
				
				boolean isShorter = distances.get(boardV) == null ? true : totalDist < distances.get(boardV);
									
				if (isShorter && visited.get(boardV) == null) {
					nodesToVisit.queue(board[boardV.x][boardV.y].getPosition());
					distances.add(boardV, totalDist);
					previous.add(boardV, knight.getPosition());
				}
			}
		}
		
		Point backTracker = end;
		Linkedlist<Point> path = new Linkedlist<>();
		
		do {
			path.pushFront(backTracker);
			backTracker = previous.get(backTracker);
		} while (backTracker != null);
		
		return path;
	}
	
	private static void initBoard(ChessSquare[][] board, ChessPeice... positions) {
				
		Linkedlist<ChessPeice> points = new Linkedlist<>(Arrays.asList(positions));
		MinHeap<ChessPeice> min = new MinHeap<>(points);
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				
				ChessPeice cp = min.peek();
				Point p = new Point(i, j);
				
				if (cp != null) {
					if (cp.getPosition().x == i && cp.getPosition().y == j) {
						board[i][j] = new ChessSquare(p, cp);
						min.remove();
						continue;
					}
				}
				board[i][j] = new ChessSquare(p, null);
			}
		}
	}
		
	public static void print(ChessSquare[][] data, ChessPeice k) {
		for (int i = 0; i < data.length; i++) {
			System.out.println("");
			System.out.print("|");
			for (int j = 0; j < data.length; j++) {
				
				Point p = k.getPosition();
				
				if (p.x == i && p.y == j) {
					if (data[p.x][p.y].isOccupied()) {
						data[p.x][p.y].setOcupant(k);
					}
					else {
						data[p.x][p.y].setOcupant(k);
					}
					System.out.print(k.getChar() + "|");
				}
				else {
					System.out.print(data[i][j] + "|");
				}
			}
		}
	}
}
