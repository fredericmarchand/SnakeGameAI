package controller;

import java.util.LinkedList;
import java.util.List;
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
			if (fringe.size() == 0)
				System.out.println("empty fringe");
			
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
			
			//Check for snake body positions

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
			if (fringe.size() == 0)
				System.out.println("empty fringe");
			
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
			
			//Check for snake body positions

			fringe.push(new Node(currNode.getRow()+1, currNode.getCol()  , currNode));
			fringe.push(new Node(currNode.getRow()-1, currNode.getCol()  , currNode));
			fringe.push(new Node(currNode.getRow()  , currNode.getCol()+1, currNode));
			fringe.push(new Node(currNode.getRow()  , currNode.getCol()-1, currNode));
			visited.add(currNode);
		}
	}
	
	public static void AStarSearch(){
		
	}
	
}
