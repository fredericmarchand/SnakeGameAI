package controller;

import java.util.LinkedList;

import model.Coordinate;
import model.Map;
import model.Node;
import view.GameBoard;

public class App {

	public static final int BREADTH 	= 1;
	public static final int DEPTH 		= 2;
	public static final int ASTAR1 		= 3;
	public static final int ASTAR2 		= 4;
	public static final int ASTAR3 		= 5;
	
	//arg[0] = 1 -> breadth first
	//arg[0] = 2 -> depth first
	//arg[0] = 3 -> a star heuristic 1
	//arg[0] = 4 -> a star heuristic 2
	//arg[0] = 5 -> a star heuristic mix
	//arg[1] = dimension (i.e., 20 -> 20x20)
	//arg[2] = snake length (i.e., 4, 5, 6, 7)
	//arg[3] = block coverage percentage (i.e., 10 -> 10% coverage)
	public static void main(String args[]) throws InterruptedException {
		GameBoard gb = new GameBoard("Snake Game", 20, 20);
		gb.setVisible(true);
		Map m = new Map();
		gb.update(m);
		
		int search = Integer.parseInt(args[0]);
		for (;;) {
			LinkedList<Coordinate> path = null;
			switch (search) {
			case BREADTH:
				path = Search.BreadthFirstSearch(new Node(m.getSnake().getPositions().get(0).getRow(), m.getSnake().getPositions().get(0).getCol(), null), m);
				break;
			case DEPTH:
				path = Search.DepthFirstSearch(new Node(m.getSnake().getPositions().get(0).getRow(), m.getSnake().getPositions().get(0).getCol(), null), m);
				break;
			case ASTAR1:
			case ASTAR2:
			case ASTAR3:
				path = Search.AStarSearch(new Node(m.getSnake().getPositions().get(0).getRow(), m.getSnake().getPositions().get(0).getCol(), null), m, search);
				break;
			}
			
			//If invalid search was selected, return
			if (path == null)
				return;
			
			for (Coordinate c: path) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				m.moveSnakeInDirection(Coordinate.getDirection(m.getSnake().getPositions().get(0), c));
				gb.update(m);
			}
		}
	}
}
