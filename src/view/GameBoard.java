package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import model.Map;
import model.Snake;

public class GameBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JLabel GUI[][];
	
	private static Color EMPTY_COLOR = 		Color.white;
	private static Color SNAKE_HEAD_COLOR = Color.green;
	private static Color SNAKE_BODY_COLOR = Color.cyan;
	private static Color SNAKE_TAIL_COLOR = Color.blue;
	private static Color FOOD_COLOR = 		Color.red;
	private static Color OBSTACLE_COLOR = 	Color.gray;
	private static Color BORDER_COLOR = 	Color.darkGray;

	public GameBoard(String title, int rows, int cols) {
		super(title);
		GUI = new JLabel[rows][cols];
		buildWindow(rows, cols);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 1000);
	}
	
	private void buildWindow(int rows, int columns){//method used to construct the window
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5); // default spacing
		setLayout(layout);
		Border border = LineBorder.createGrayLineBorder();

		JPanel centerPanel = new JPanel(new GridLayout(rows, columns, 0, 0));
		for (int row = 0; row < rows; row++){
			for (int col = 0; col < columns; col++){
				GUI[row][col] = new JLabel();
				GUI[row][col].setOpaque(true);
				GUI[row][col].setBorder(border);
				centerPanel.add(GUI[row][col]);
			}
		}
		constraints.gridx = 0;
		constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        layout.setConstraints(centerPanel, constraints);
		add(centerPanel);
	}
	
	public void update(Map map){
		for (int x = 0; x < map.getRows(); ++x) {
			for (int y = 0; y < map.getColumns(); ++y) {
				switch(map.getMap()[x][y])
				{
					case Map.EMPTY:
						GUI[x][y].setForeground(EMPTY_COLOR);
						GUI[x][y].setBackground(EMPTY_COLOR);
						break;
					case Map.SNAKE_HEAD:
						GUI[x][y].setForeground(SNAKE_HEAD_COLOR);
						GUI[x][y].setBackground(SNAKE_HEAD_COLOR);
						break;
					case Map.SNAKE_BODY:
						GUI[x][y].setForeground(SNAKE_BODY_COLOR);
						GUI[x][y].setBackground(SNAKE_BODY_COLOR);
						break;
					case Map.SNAKE_TAIL:
						GUI[x][y].setForeground(SNAKE_TAIL_COLOR);
						GUI[x][y].setBackground(SNAKE_TAIL_COLOR);
						break;
					case Map.FOOD:
						GUI[x][y].setForeground(FOOD_COLOR);
						GUI[x][y].setBackground(FOOD_COLOR);
						break;
					case Map.OBSTACLE:
						GUI[x][y].setForeground(OBSTACLE_COLOR);
						GUI[x][y].setBackground(OBSTACLE_COLOR);
						break;
					case Map.BORDER:
						GUI[x][y].setForeground(BORDER_COLOR);
						GUI[x][y].setBackground(BORDER_COLOR);
						break;
				}
				
			}
		}
	}
	
	public void clearSquare(int x, int y) {
		GUI[x][y].setForeground(EMPTY_COLOR);
	}
	
	public static void main(String args[]) {
		GameBoard gb = new GameBoard("Snake Game", 20, 20);
		gb.setVisible(true);
		Map m = new Map();
		
		m.moveSnakeInDirection(Snake.UP);
		m.moveSnakeInDirection(Snake.RIGHT);
		gb.update(m);
		
	}
}
