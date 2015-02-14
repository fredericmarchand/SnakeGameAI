package model;

import java.util.Collections;
import java.util.LinkedList;

public class Node {

	//State information
	private Node previous;
	private int row;
	private int col;
	
	public Node(int row, int col, Node previous) {
		this.setRow(row);
		this.setCol(col);
		this.previous = previous;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	public boolean equalState(Node node) {
		if (node.row == this.row && node.col == this.col)
			return true;
		return false;
	}

	public Node getPrevious() {
		return previous;
	}

	public void setPrevious(Node previous) {
		this.previous = previous;
	}
	
	public static LinkedList<Coordinate> getPath(Node node) {
		LinkedList<Coordinate> path = new LinkedList<Coordinate>();
		Node currNode = node;
		while (currNode.getPrevious() != null) {
			path.add(new Coordinate(currNode.getRow(), currNode.getCol()));
			currNode = currNode.getPrevious();
		}
		
		Collections.reverse(path);
		return path;
	}
	
}
