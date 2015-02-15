package controller;

import java.util.Comparator;

import model.Coordinate;
import model.Node;

public class MixedHeuristicNodeComparator implements Comparator<Node> {

	private Coordinate food;
	
	public MixedHeuristicNodeComparator(Coordinate food) {
		this.setFood(food);
	}
	
	@Override
	public int compare(Node o1, Node o2) {
		int dist1 = (Search.manhattanHeuristicEstimator(food, new Coordinate(o1.getRow(), o1.getCol())) + 
					 Search.euclideanHeuristicEstimator(food, new Coordinate(o1.getRow(), o1.getCol()))) / 2;
		int dist2 = (Search.manhattanHeuristicEstimator(food, new Coordinate(o2.getRow(), o2.getCol())) + 
					 Search.euclideanHeuristicEstimator(food, new Coordinate(o2.getRow(), o2.getCol()))) / 2;

		return (dist1 - dist2);
	}

	public Coordinate getFood() {
		return food;
	}

	public void setFood(Coordinate food) {
		this.food = food;
	}
}
