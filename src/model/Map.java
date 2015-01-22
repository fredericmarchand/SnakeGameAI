package model;

public class Map {
	
	public static final int EMPTY      = 0;
	public static final int SNAKE_HEAD = 1;
	public static final int SNAKE_BODY = 2;
	public static final int SNAKE_TAIL = 3;
	public static final int FOOD	   = 4;
	public static final int OBSTACLE   = 5;
	public static final int BORDER     = 6;
	
	private int rows; //amount of squares
	private int columns;  //amount of squares
	private Snake cobra;
	
	private int map[][];
	
	public Map(){
		setRows(20);
		setColumns(20);
		setMap(new int[20][20]);
		cobra = new Snake();
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
	
	public int[][] getMap() {
		return map;
	}
	
	public void setMap(int map[][]) {
		this.map = map;
	}
	
	//Only works for size 5 for now
	public void initSnake() {
		cobra.getPositions().set(0, new Coordinate(rows/2-1, columns/2+1));
		map[cobra.getPositions().get(0).getRow()][cobra.getPositions().get(0).getCol()] = SNAKE_HEAD;
		
		cobra.getPositions().set(1, new Coordinate(rows/2-1, columns/2));
		cobra.getPositions().set(2, new Coordinate(rows/2-1, columns/2-1));
		cobra.getPositions().set(3, new Coordinate(rows/2-1, columns/2-2));
		map[cobra.getPositions().get(1).getRow()][cobra.getPositions().get(1).getCol()] = SNAKE_BODY;
		map[cobra.getPositions().get(2).getRow()][cobra.getPositions().get(2).getCol()] = SNAKE_BODY;
		map[cobra.getPositions().get(3).getRow()][cobra.getPositions().get(3).getCol()] = SNAKE_BODY;
		
		cobra.getPositions().set(4, new Coordinate(rows/2-1, columns/2-3));
		map[cobra.getPositions().get(4).getRow()][cobra.getPositions().get(4).getCol()] = SNAKE_TAIL;	
	}
	
	public void moveSnakeInDirection(int direction) {
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
	}
	
	public void clearSnake() {
		map[cobra.getPositions().get(0).getRow()][cobra.getPositions().get(0).getCol()] = EMPTY;
		map[cobra.getPositions().get(1).getRow()][cobra.getPositions().get(1).getCol()] = EMPTY;
		map[cobra.getPositions().get(2).getRow()][cobra.getPositions().get(2).getCol()] = EMPTY;
		map[cobra.getPositions().get(3).getRow()][cobra.getPositions().get(3).getCol()] = EMPTY;
		map[cobra.getPositions().get(4).getRow()][cobra.getPositions().get(4).getCol()] = EMPTY;	
	}
	
	public void positionSnake() {
		map[cobra.getPositions().get(0).getRow()][cobra.getPositions().get(0).getCol()] = SNAKE_HEAD;
		map[cobra.getPositions().get(1).getRow()][cobra.getPositions().get(1).getCol()] = SNAKE_BODY;
		map[cobra.getPositions().get(2).getRow()][cobra.getPositions().get(2).getCol()] = SNAKE_BODY;
		map[cobra.getPositions().get(3).getRow()][cobra.getPositions().get(3).getCol()] = SNAKE_BODY;
		map[cobra.getPositions().get(4).getRow()][cobra.getPositions().get(4).getCol()] = SNAKE_TAIL;	
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
