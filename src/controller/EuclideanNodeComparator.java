package controller;

import java.util.Comparator;

import model.Coordinate;
import model.Node;

public class EuclideanNodeComparator implements Comparator<Node> {
	private Coordinate food;
	
	public EuclideanNodeComparator(Coordinate food) {
		this.setFood(food);
	}
	
	@Override
	public int compare(Node o1, Node o2) {
		double rowDist1 = Math.pow(o1.getRow() - food.getRow(), 2);
		double colDist1 = Math.pow(o1.getCol() - food.getCol(), 2);
		double rowDist2 = Math.pow(o2.getRow() - food.getRow(), 2);
		double colDist2 = Math.pow(o2.getCol() - food.getCol(), 2);
		return (int)(Math.sqrt(rowDist1 + colDist1) - Math.sqrt(rowDist2 + colDist2));
	}

	public Coordinate getFood() {
		return food;
	}

	public void setFood(Coordinate food) {
		this.food = food;
	}
}
