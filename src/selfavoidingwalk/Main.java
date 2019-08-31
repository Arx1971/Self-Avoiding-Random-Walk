package selfavoidingwalk;

import java.util.List;

public class Main {

	static int directions[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) {

		int maze[][] = { { 1, 1, 1, 1 }, { 1, 1, 0, 1 }, { 0, 1, 0, 1 }, { 1, 1, 1, 1 } };

		// boolean visited[][] = new boolean[maze.length][maze[0].length];

		System.out.println(countPaths(maze, 0, 0, 0, 3, 3));

	}

	public static int countPaths(int maze[][], int x, int y, int count, int dstrow, int dstcol) {

		if (x == dstrow && y == dstcol) {
			count++;
			return count;
		}

		maze[x][y] = 0;

		if (isValid(x, y, maze)) {
			
			if (x + 1 < maze.length && maze[x + 1][y] == 1)
				count = countPaths(maze, x + 1, y, count, dstrow, dstcol);
			
			if (x - 1 >= 0 && maze[x - 1][y] == 1)
				count = countPaths(maze, x - 1, y, count, dstrow, dstcol);

			if (y + 1 < maze[0].length && maze[x][y + 1] == 1)
				count = countPaths(maze, x, y + 1, count, dstrow, dstcol);

			if (y - 1 >= 0 && maze[x][y - 1] == 1)
				count = countPaths(maze, x, y - 1, count, dstrow, dstcol);
		}

		maze[x][y] = 1;

		return count;
	}

	public static boolean isValid(int row, int col, int grid[][]) {

		return ((row >= 0 && row < grid.length) && (col >= 0 && col < grid[0].length));

	}

}
