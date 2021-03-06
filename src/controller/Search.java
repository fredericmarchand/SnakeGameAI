package controller;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import model.Coordinate;
import model.Map;
import model.Node;

public class Search {
	
	public static LinkedList<Coordinate> BreadthFirstSearch(Node startState, Map map){
		
		List<Node> visited = new LinkedList<Node>();
		Queue<Node> fringe = new LinkedList<Node>();
		
		for (Coordinate c: map.getSnake().getPositions()) {
			visited.add(new Node(c.getRow(), c.getCol(), null));	
		}
		visited.remove(0);
		fringe.add(startState);
		
		for (;;) {
			if (fringe.size() == 0) {
				System.out.println("Empty fringe: Stuck\nExiting in 3 seconds...");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(1);
			}
			
			Node currNode = fringe.poll();
			//System.out.println("currNode = " + "(" + currNode.getRow() + ", " + currNode.getCol() + ")");
			
			boolean exists = false;
			for (Node n: visited) {
				if (currNode.equalState(n)) {
					exists = true;
				}
			}
			
			//If the node state was already visited don't add the sub-states in the fringe
			if (exists == true) {
				continue;
			}
			
			//Is there a piece of food at this position
			if (map.getMap()[currNode.getRow()][currNode.getCol()] == Map.FOOD) {
				LinkedList<Coordinate> list = Node.getPath(currNode);
				//for (Coordinate c: list) {
				//	System.out.println(c.toString());
				//}
				return list;
			}
			
			if (map.getMap()[currNode.getRow()][currNode.getCol()] == Map.BORDER ||
				map.getMap()[currNode.getRow()][currNode.getCol()] == Map.OBSTACLE) {
				visited.add(currNode);
				continue;
			}
			
			fringe.offer(new Node(currNode.getRow()+1, currNode.getCol()  , currNode));
			fringe.offer(new Node(currNode.getRow()-1, currNode.getCol()  , currNode));
			fringe.offer(new Node(currNode.getRow()  , currNode.getCol()+1, currNode));
			fringe.offer(new Node(currNode.getRow()  , currNode.getCol()-1, currNode));
			visited.add(currNode);
		}
		
	}
	
	public static LinkedList<Coordinate> DepthFirstSearch(Node startState, Map map){
		
		List<Node> visited = new LinkedList<Node>();
		Stack<Node> fringe = new Stack<Node>();
		
		for (Coordinate c: map.getSnake().getPositions()) {
			visited.add(new Node(c.getRow(), c.getCol(), null));	
		}
		visited.remove(0);
		fringe.add(startState);
		
		for (;;) {
			if (fringe.size() == 0) {
				System.out.println("Empty fringe: Stuck\nExiting in 3 seconds...");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(1);
			}
			
			Node currNode = fringe.pop();
			//System.out.println("currNode = " + "(" + currNode.getRow() + ", " + currNode.getCol() + ")");
			
			boolean exists = false;
			for (Node n: visited) {
				if (currNode.equalState(n)) {
					exists = true;
				}
			}
			
			//If the node state was already visited don't add the sub-states in the fringe
			if (exists == true) {
				continue;
			}
			
			//Is there a piece of food at this position
			if (map.getMap()[currNode.getRow()][currNode.getCol()] == Map.FOOD) {
				LinkedList<Coordinate> list = Node.getPath(currNode);
				//for (Coordinate c: list) {
				//	System.out.println(c.toString());
				//}
				return list;
			}
			
			if (map.getMap()[currNode.getRow()][currNode.getCol()] == Map.BORDER ||
				map.getMap()[currNode.getRow()][currNode.getCol()] == Map.OBSTACLE) {
				visited.add(currNode);
				continue;
			}
			
			fringe.push(new Node(currNode.getRow()+1, currNode.getCol()  , currNode));
			fringe.push(new Node(currNode.getRow()-1, currNode.getCol()  , currNode));
			fringe.push(new Node(currNode.getRow()  , currNode.getCol()+1, currNode));
			fringe.push(new Node(currNode.getRow()  , currNode.getCol()-1, currNode));
			visited.add(currNode);
		}
	}
	
	public static int manhattanHeuristicEstimator(Coordinate food, Coordinate node) {
		int rowdiff = Math.abs(node.getRow() - food.getRow());
		int coldiff = Math.abs(node.getCol() - food.getCol());

		return ((rowdiff + coldiff));
	}
	
	public static int euclideanHeuristicEstimator(Coordinate food, Coordinate node) {
		int rowDist1 = (int) Math.pow(node.getRow() - food.getRow(), 2);
		int colDist1 = (int) Math.pow(node.getCol() - food.getCol(), 2);

		return (int)(Math.sqrt(rowDist1 + colDist1));
	}
	
	public static int estimator(Coordinate food, Coordinate node, int heuristic) {
		if (heuristic == App.ASTAR1)
			return manhattanHeuristicEstimator(food, node);
		else if (heuristic == App.ASTAR2)
			return euclideanHeuristicEstimator(food, node);
		else if (heuristic == App.ASTAR3)
			return (manhattanHeuristicEstimator(food, node) + euclideanHeuristicEstimator(food, node)) / 2;
		return 0;
	}
	
	public static LinkedList<Coordinate> AStarSearch(Node startState, Map map, int heuristic){
		Comparator<Node> comparator = null;
		if (heuristic == App.ASTAR1)
			comparator = new ManhattanNodeComparator(map.getFoodCoordinates());
		else if (heuristic == App.ASTAR2)
			comparator = new EuclideanNodeComparator(map.getFoodCoordinates());
		else if (heuristic == App.ASTAR3)
			comparator = new MixedHeuristicNodeComparator(map.getFoodCoordinates());
		
		PriorityQueue<Node> openList = new PriorityQueue<Node>(map.getRows(), comparator);
		LinkedList<Node> closedList = new LinkedList<Node>();
		
		for (Coordinate c: map.getSnake().getPositions()) {
			closedList.add(new Node(c.getRow(), c.getCol(), null));	
		}
		closedList.remove(0);
		
		openList.add(startState); 
				
		for (;;) {
			if (openList.isEmpty()) {
				System.out.println("Empty openlist: Stuck\nExiting in 3 seconds...");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(1);
			}
			
			LinkedList<Node> successors = new LinkedList<Node>();
			
			Node currNode = openList.poll(); //Pick best node in open
			
			if (currNode.getRow() == map.getFoodCoordinates().getRow() &&
				currNode.getCol() == map.getFoodCoordinates().getCol()) {
				LinkedList<Coordinate> list = Node.getPath(currNode);
				//for (Coordinate c: list) {
				//	System.out.println(c.toString());
				//}
				return list;
			}
			
			//Generate successors
			successors.add(new Node(currNode.getRow()+1, currNode.getCol()  , 
							estimator(map.getFoodCoordinates(), 
							new Coordinate(currNode.getRow()+1, currNode.getCol()), heuristic), 
							currNode.getSteps()+1, currNode));
			successors.add(new Node(currNode.getRow()-1, currNode.getCol()  , 
							estimator(map.getFoodCoordinates(), 
							new Coordinate(currNode.getRow()-1, currNode.getCol()), heuristic), 
							currNode.getSteps()+1, currNode));
			successors.add(new Node(currNode.getRow()  , currNode.getCol()+1, 
							estimator(map.getFoodCoordinates(), 
							new Coordinate(currNode.getRow(), currNode.getCol()+1), heuristic), 
							currNode.getSteps()+1, currNode));
			successors.add(new Node(currNode.getRow()  , currNode.getCol()-1, 
							estimator(map.getFoodCoordinates(), 
							new Coordinate(currNode.getRow(), currNode.getCol()-1), heuristic), 
							currNode.getSteps()+1, currNode));
			
			closedList.add(currNode); //place in closed
		
			//For each successor
			for (Node n: successors) {
				
				if (map.getMap()[currNode.getRow()][currNode.getCol()] == Map.BORDER ||
					map.getMap()[currNode.getRow()][currNode.getCol()] == Map.OBSTACLE) {
					closedList.add(currNode);
					continue;
				}
				
				boolean inOpenList = false;
				boolean inClosedList = false;
				for (Node o: openList) {
					if (n.equalState(o))
						inOpenList = true;
				}
				for (Node o: closedList) {
					if (n.equalState(o))
						inClosedList = true;
				}
				if (!inClosedList && !inOpenList){
					openList.add(n);
				}
				else {//Found in one of the lists
					Node existing = null;
					for (Node o: openList) {
						if (n.equalState(o))
							existing = o;
					}
					for (Node o: closedList) {
						if (n.equalState(o))
							existing = o;
					}
					if (n.getSteps() < existing.getSteps()) { 	//If better path
						existing.setPrevious(currNode);			//change parent pointer
						existing.setEstimate(n.getEstimate());  //update estimate
					}
				}
			}
		}
	}
}
