package Maze;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MazePanel extends JPanel {
	boolean displayPath = false;
	int margin = 3;
	boolean pathFound = false;

	Maze m;

	public MazePanel() {
		try {
			m = new Maze("src/mazes.txt");
			if (MazeSolver.solveMaze(m)) {
				pathFound = true;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void findPath() {
		displayPath = true;
		if (!pathFound) {
			JOptionPane.showMessageDialog(null, "java.io.PathNotFoundException", "Error", 0);
		}
		repaint();
	}

	public void clearPath() {
		displayPath = false;
		repaint();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		int width = getWidth() / m.maze[0].length - margin;
		int height = getHeight() / m.maze.length - margin;
		for (int i = 0; i < m.maze.length; i++) {
			for (int j = 0; j < m.maze[i].length; j++) {
				if (m.maze[i][j] == 1 || m.maze[i][j] == 3) {
					g.fillRect(margin + j * (width + margin), margin + j * (height + margin), width, height);
				} else if (m.maze[i][j] == 2) {
					g.setColor(Color.RED);
					g.fillRect(margin + j * (width + margin), margin + j * (height + margin), width, height);
					g.setColor(Color.WHITE);
				}
			}
		}
		if (displayPath) {
			for (Position p : m.path) {
				g.setColor(Color.BLACK);
				g.fillRect(margin + p.x * (width + margin), margin + p.y * (height + margin), width, height);
			}
		}
	}
}