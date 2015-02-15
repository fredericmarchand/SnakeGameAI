package controller;

import java.util.Comparator;

import model.Coordinate;
import model.Node;

public class ManhattanNodeComparator implements Comparator<Node> {

	private Coordinate food;
	
	public ManhattanNodeComparator(Coordinate food) {
		this.setFood(food);
	}
	
	@Override
	public int compare(Node o1, Node o2) {
		int dist1 = Search.manhattanHeuristicEstimator(food, new Coordinate(o1.getRow(), o1.getCol()));
		int dist2 = Search.manhattanHeuristicEstimator(food, new Coordinate(o2.getRow(), o2.getCol()));

		return (dist1 - dist2);
	}

	public Coordinate getFood() {
		return food;
	}

	public void setFood(Coordinate food) {
		this.food = food;
	}
}
