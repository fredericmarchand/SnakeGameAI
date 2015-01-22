package model;

import java.util.ArrayList;

public class Snake {

	public static final int UP 		= 0;
	public static final int DOWN 	= 1;
	public static final int RIGHT 	= 2;
	public static final int LEFT 	= 3;
	
	private int length;
	private int direction;
	private ArrayList<Coordinate> positions;
	
	public Snake() {
		setLength(5);
		setDirection(RIGHT);
		setPositions(new ArrayList<Coordinate>());
		for (int i = 0; i < 5; ++i)
			positions.add(new Coordinate(0,0));
	}
	
	public Snake(int len){
		setLength(len);
		setDirection(RIGHT);
		setPositions(new ArrayList<Coordinate>());
		for (int i = 0; i < getLength(); ++i)
			positions.add(new Coordinate(0,0));
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public ArrayList<Coordinate> getPositions() {
		return positions;
	}

	public void setPositions(ArrayList<Coordinate> positions) {
		this.positions = positions;
	}
}
