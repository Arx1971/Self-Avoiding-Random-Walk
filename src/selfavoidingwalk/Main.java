package selfavoidingwalk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Main {

	static int directions[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static Random random = new Random();

	public static void main(String[] args) {
		for (int i = 10; i <= 40; i++) {
			System.out.println(slefAvoidingRandomWlak2D(i));
		}
	}

	public static double slefAvoidingRandomWlak2D(int steps) {

		List<point> pointsList = new ArrayList<point>();

		Hashtable<Integer, List<point>> hashtable = new Hashtable<Integer, List<point>>();
		int n_saw = 50000;

		for (int i = 0; i < n_saw; i++) {

			hashtable.put(i, new ArrayList<point>());

			Set<String> visited = new HashSet<String>();

			int x = 0;
			int y = 0;

			visited.add(Integer.toString(x) + "," + Integer.toString(y));

			for (int j = 1; j <= steps; j++) {

				int r = random.nextInt(4);

				x = x + directions[r][0];
				y = y + directions[r][1];

				String coordinate = Integer.toString(x) + "," + Integer.toString(y);

				if (!visited.add(coordinate))
					break;

				if (visited.size() == steps)
					pointsList.add(new point(x, y));

				hashtable.get(i).add(new point(x, y)); // keep track of all the path with n steps

			}
		}

		/*
		 * int counter = 0; for (int i = 0; i < n_saw; i++) { if
		 * (hashtable.get(i).size() == steps) { counter++;
		 * System.out.println(hashtable.get(i) + " " + hashtable.get(i).size()); } }
		 * System.out.println(counter);
		 */
		// System.out.println(pointsList);
		// System.out.println(pointsList.size());

		double squaredDistance = 0.0;

		for (int i = 0; i < pointsList.size(); i++) {
			squaredDistance += Math.pow(pointsList.get(i).x, 2) + Math.pow(pointsList.get(i).y, 2);
		}

		double avg = (double) (squaredDistance / n_saw);

		return avg;

	}

	public static class point {

		int x;
		int y;

		public point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public String toString() {
			return Integer.toString(x) + "," + Integer.toString(y);
		}

	}

}
