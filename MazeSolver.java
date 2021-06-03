package Maze;

public class MazeSolver {

	// 0 = wall
	// 1 = path
	// 2 = destination

	public static boolean solveMaze(Maze m) {
		Position p = m.start;
		m.path.push(p);

		while (true) {
			int y = m.path.peek().y;
			int x = m.path.peek().x;

			if (m.maze[y][x] != m.maze[p.y][p.x]) {
				m.maze[y][x] = 3;
			}

			// down
			if (isValid(y + 1, x, m)) {
				if (m.maze[y + 1][x] == 2) {
					return true;
				} else if (m.maze[y + 1][x] == 1) {
					m.path.push(new Position(y + 1, x));
					continue;
				}
			}

			// left
			if (isValid(y, x - 1, m)) {
				if (m.maze[y][x - 1] == 2) {
					return true;
				} else if (m.maze[y][x - 1] == 1) {
					m.path.push(new Position(y, x - 1));
					continue;
				}
			}

			// up
			if (isValid(y - 1, x, m)) {
				if (m.maze[y - 1][x] == 2) {
					return true;
				} else if (m.maze[y - 1][x] == 1) {
					m.path.push(new Position(y - 1, x));
					continue;
				}
			}

			// right
			if (isValid(y, x + 1, m)) {
				if (m.maze[y][x + 1] == 2) {
					return true;
				} else if (m.maze[y][x + 1] == 1) {
					m.path.push(new Position(y, x + 1));
					continue;
				}
			}

			m.path.pop(); // going back in case we have reached a dead end
			if (m.path.size() <= 0) { // nowhere new to go
				return false;
			}
		}
	}

	public static boolean isValid(int y, int x, Maze m) {
		if (y < 0 || y >= m.maze.length || x < 0 || x >= m.maze[y].length) {
			return false;
		}
		return true;
	}

}