package model;

import java.util.Random;

public class Map {
	
	public static final int EMPTY      = 0;
	public static final int SNAKE_HEAD = 1;
	public static final int SNAKE_BODY = 2;
	public static final int SNAKE_TAIL = 3;
	public static final int FOOD	   = 4;
	public static final int OBSTACLE   = 5;
	public static final int BORDER     = 6;
	
	public static final int INVALID_MOVE   = 0;
	public static final int VALID_MOVE     = 1;
	public static final int FOOD_MOVE      = 2;
	
	
	private int rows; //amount of squares
	private int columns;  //amount of squares
	private Snake cobra;
	private Coordinate foodCoordinates;
	
	private int map[][];
	
	public Map(){
		setRows(20);
		setColumns(20);
		setMap(new int[20][20]);
		cobra = new Snake(7);
		for (int x = 0; x < 20; ++x) {
			for (int y = 0; y < 20; ++y) {
				if (x == 0 || y == 0 || x == rows-1 || y == columns-1) {
					map[x][y] = BORDER;
				}
				else {
					map[x][y] = EMPTY;
				}
			}
		}
		initSnake();
		generateObstacles(5);
		generateFoodItem();
	}
	
	public Map(int rows, int cols){
		setRows(rows);
		setColumns(cols);
		setMap(new int[rows][cols]);
		cobra = new Snake();
		for (int x = 0; x < rows; ++x) {
			for (int y = 0; y < cols; ++y) {
				if (x == 0 || y == 0 || x == rows-1 || y == cols-1) {
					map[x][y] = BORDER;
				}
				else {
					map[x][y] = EMPTY;
				}
			}
		}
		initSnake();
		generateObstacles(10);
		generateFoodItem();
	}
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int cols) {
		this.columns = cols;
	}
	
	public Snake getSnake() {
		return cobra;
	}
	
	public int[][] getMap() {
		return map;
	}
	
	public void setMap(int map[][]) {
		this.map = map;
	}
	
	public Coordinate getFoodCoordinates() {
		return foodCoordinates;
	}
	
	public void initSnake() {
		int decrement = 0;
		
		cobra.getPositions().set(0, new Coordinate(rows/2-1, columns/2+1));
		map[cobra.getPositions().get(0).getRow()][cobra.getPositions().get(0).getCol()] = SNAKE_HEAD;
		
		for (int i = 1; i <= cobra.getLength()-2; ++i) {
			cobra.getPositions().set(i, new Coordinate(rows/2-1, columns/2 - decrement));
			decrement++;
			map[cobra.getPositions().get(i).getRow()][cobra.getPositions().get(i).getCol()] = SNAKE_BODY;
		}
		
		cobra.getPositions().set(cobra.getLength()-1, new Coordinate(rows/2-1, columns/2-decrement));
		map[cobra.getPositions().get(cobra.getLength()-1).getRow()][cobra.getPositions().get(cobra.getLength()-1).getCol()] = SNAKE_TAIL;	
	}
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public void generateObstacles(int percentage) {
		if (percentage > 50) return;
		
		int totalCells = getRows() * getColumns();
		
		for (int i = 0; i < (totalCells * percentage / 100); ++i)
		{
			int randRow = 0;
			int randCol = 0;
			while (map[randRow][randCol] != EMPTY)
			{
 				randRow = randInt(0, getRows()-1);
				randCol = randInt(0, getColumns()-1);
			}
			
			map[randRow][randCol] = OBSTACLE;
		}
	}
	
	public void generateFoodItem() {
		int randRow = 0;
		int randCol = 0;
		while (map[randRow][randCol] != EMPTY)
		{
			randRow = randInt(0, getRows()-1);
			randCol = randInt(0, getColumns()-1);
		}
		map[randRow][randCol] = FOOD;
		foodCoordinates = new Coordinate(randRow, randCol);
	}
	
	public int validMove(int direction)
	{
		switch (direction) {
			case Snake.UP:
				if (map[cobra.getPositions().get(0).getRow()-1][cobra.getPositions().get(0).getCol()] == FOOD)
					return FOOD_MOVE;
				if (map[cobra.getPositions().get(0).getRow()-1][cobra.getPositions().get(0).getCol()] == EMPTY)
					return VALID_MOVE;
				break;
			case Snake.DOWN:
				if(map[cobra.getPositions().get(0).getRow()+1][cobra.getPositions().get(0).getCol()] == FOOD)
					return FOOD_MOVE;
				if(map[cobra.getPositions().get(0).getRow()+1][cobra.getPositions().get(0).getCol()] == EMPTY)
					return VALID_MOVE;
				break;
			case Snake.RIGHT:
				if(map[cobra.getPositions().get(0).getRow()][cobra.getPositions().get(0).getCol()+1] == FOOD)
					return FOOD_MOVE;
				if(map[cobra.getPositions().get(0).getRow()][cobra.getPositions().get(0).getCol()+1] == EMPTY)
					return VALID_MOVE;
				break;
			case Snake.LEFT:
				if(map[cobra.getPositions().get(0).getRow()][cobra.getPositions().get(0).getCol()-1] == FOOD)
					return FOOD_MOVE;
				if(map[cobra.getPositions().get(0).getRow()][cobra.getPositions().get(0).getCol()-1] == EMPTY)
					return VALID_MOVE;
				break;
		}
		return INVALID_MOVE;
	}
	
	public void moveSnakeInDirection(int direction) {
		int moveType;
		if ((moveType = validMove(direction)) == INVALID_MOVE || moveType == -1)
			return;
		
		this.clearSnake();
		
		for (int i = cobra.getPositions().size()-1; i > 0; --i) {
			cobra.getPositions().set(i, cobra.getPositions().get(i-1));
		}

		switch (direction) {
			case Snake.UP:
				cobra.getPositions().set(0, new Coordinate(cobra.getPositions().get(0).getRow()-1, cobra.getPositions().get(0).getCol()));
				break;
			case Snake.DOWN:
				cobra.getPositions().set(0, new Coordinate(cobra.getPositions().get(0).getRow()+1, cobra.getPositions().get(0).getCol()));
				break;
			case Snake.RIGHT:
				cobra.getPositions().set(0, new Coordinate(cobra.getPositions().get(0).getRow(), cobra.getPositions().get(0).getCol()+1));
				break;
			case Snake.LEFT:
				cobra.getPositions().set(0, new Coordinate(cobra.getPositions().get(0).getRow(), cobra.getPositions().get(0).getCol()-1));
				break;
		}

		this.positionSnake();
		if (moveType == FOOD_MOVE)
			generateFoodItem();
	}
	
	public void clearSnake() {
		
		map[cobra.getPositions().get(0).getRow()][cobra.getPositions().get(0).getCol()] = EMPTY;
		for (int i = 1; i <= cobra.getLength()-2; ++i)
		{
			map[cobra.getPositions().get(i).getRow()][cobra.getPositions().get(i).getCol()] = EMPTY;
			map[cobra.getPositions().get(i).getRow()][cobra.getPositions().get(i).getCol()] = EMPTY;
			map[cobra.getPositions().get(i).getRow()][cobra.getPositions().get(i).getCol()] = EMPTY;
		}
		map[cobra.getPositions().get(cobra.getLength()-1).getRow()][cobra.getPositions().get(cobra.getLength()-1).getCol()] = EMPTY;	
	}
	
	public void positionSnake() {
		map[cobra.getPositions().get(0).getRow()][cobra.getPositions().get(0).getCol()] = SNAKE_HEAD;
		for (int i = 1; i <= cobra.getLength()-2; ++i)
		{
			map[cobra.getPositions().get(i).getRow()][cobra.getPositions().get(i).getCol()] = SNAKE_BODY;
			map[cobra.getPositions().get(i).getRow()][cobra.getPositions().get(i).getCol()] = SNAKE_BODY;
			map[cobra.getPositions().get(i).getRow()][cobra.getPositions().get(i).getCol()] = SNAKE_BODY;
		}
		map[cobra.getPositions().get(cobra.getLength()-1).getRow()][cobra.getPositions().get(cobra.getLength()-1).getCol()] = SNAKE_TAIL;	
	}
	
	public static void main(String args[]){
		Map b = new Map();
		b.moveSnakeInDirection(Snake.UP);
		for (int x = 0; x < b.getRows(); ++x) {
			for (int y = 0; y <  b.getColumns(); ++y) {
				System.out.print(b.getMap()[x][y]);
			}
			System.out.println();
		}
	}
	
}
