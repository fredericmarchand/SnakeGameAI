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
		int rowdiff1 = Math.abs(o1.getRow() - food.getRow());
		int coldiff1 = Math.abs(o1.getCol() - food.getCol());
		int rowdiff2 = Math.abs(o2.getRow() - food.getRow());
		int coldiff2 = Math.abs(o2.getCol() - food.getCol());
		return ((rowdiff1 + coldiff1) - (rowdiff2 + coldiff2));
	}

	public Coordinate getFood() {
		return food;
	}

	public void setFood(Coordinate food) {
		this.food = food;
	}
}
