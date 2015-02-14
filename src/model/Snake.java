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
	
	public Snake(Snake snake, int direction) {
		setLength(snake.getLength());
		setDirection(snake.getDirection());
		setPositions(new ArrayList<Coordinate>());
		for (int i = 0; i < getLength(); ++i)
			positions.add(new Coordinate(snake.getPositions().get(i)));
		moveInDirection(this, direction);
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
	
	public boolean onSnakeBody(int row, int col) {
		for (int i = 1; i < getPositions().size(); ++i) {
			Coordinate c = getPositions().get(i);
			if (c.getRow() == row &&
				c.getCol() == col)
				return true;
		}
		return false;
	}
	
	public static void moveInDirection(Snake snake, int direction) {
		for (int i = snake.getPositions().size()-1; i > 0; --i) {
			snake.getPositions().set(i, snake.getPositions().get(i-1));
		}

		switch (direction) {
			case Snake.UP:
				snake.getPositions().set(0, new Coordinate(snake.getPositions().get(0).getRow()-1, snake.getPositions().get(0).getCol()));
				break;
			case Snake.DOWN:
				snake.getPositions().set(0, new Coordinate(snake.getPositions().get(0).getRow()+1, snake.getPositions().get(0).getCol()));
				break;
			case Snake.RIGHT:
				snake.getPositions().set(0, new Coordinate(snake.getPositions().get(0).getRow(), snake.getPositions().get(0).getCol()+1));
				break;
			case Snake.LEFT:
				snake.getPositions().set(0, new Coordinate(snake.getPositions().get(0).getRow(), snake.getPositions().get(0).getCol()-1));
				break;
		}
	}
	
}
