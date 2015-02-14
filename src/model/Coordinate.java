package model;

public class Coordinate {

	private int row;
	private int col;
	
	public Coordinate(int row, int col){
		this.setRow(row);
		this.setCol(col);
	}

	public Coordinate(Coordinate coordinate) {
		coordinate.row = row;
		coordinate.col = col;
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
	
	public String toString() {
		return "(" + getRow() + ", " + getCol() + ")";
	}
	
	public static int getDirection(Coordinate c1, Coordinate c2) {
		int rowDiff = c2.getRow() - c1.getRow();
		int colDiff = c2.getCol() - c1.getCol();
		
		if (rowDiff == -1)
			return Snake.UP;
		if (rowDiff == 1)
			return Snake.DOWN;
		if (colDiff == -1)
			return Snake.LEFT;
		if (colDiff == 1)
			return Snake.RIGHT;
		
		System.out.println(c1.toString() + " vs " + c1.toString());
		return -1;
		
	}
}
