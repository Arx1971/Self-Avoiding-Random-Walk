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
		slefAvoidingRandomWlak(12);

	}

	public static void slefAvoidingRandomWlak(int steps) {

		List<point> pointsList = new ArrayList<point>();

		Hashtable<Integer, List<point>> hashtable = new Hashtable<Integer, List<point>>();
		int number = 10000;

		int rSqueare = 0;

		for (int i = 0; i < number; i++) {

			hashtable.put(i, new ArrayList<point>());

			Set<String> visited = new HashSet<String>();

			int x = 0;
			int y = 0;

			for (int j = 1; j <= steps; j++) {

				int r = random.nextInt(4);

				x = x + directions[r][0];
				y = y + directions[r][1];

				String coordinate = Integer.toString(x) + "," + Integer.toString(y);

				if (!visited.add(coordinate))
					break;
				
				else {
					hashtable.get(i).add(new point(x, y));
				}
			}
		}
		int counter = 0;
		for (int i = 0; i < number; i++) {
			if (hashtable.get(i).size() == steps) {
				counter++;
				System.out.println(i + " " + hashtable.get(i) + " " + hashtable.get(i).size());
			}
		}
		System.out.println(counter);

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
