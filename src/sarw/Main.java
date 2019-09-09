package sarw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Main {

	static int directios[][] = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	static int NumberOfTrials = 100;
	static Random random = new Random();

	public static void main(String args[]) {
		selfAvoidingWalk(4);
	}

	public static void selfAvoidingWalk(int stpes) {

		List<Double> end_to_end_Distance = new ArrayList<Double>();

		for (int i = 1; i <= NumberOfTrials; i++) {

			Set<String> visited = new HashSet<String>();
			List<String> path = new ArrayList<String>();
			int x = 0;
			int y = 0;

			path.add(Integer.toString(x) + "," + Integer.toString(y));

			for (int j = 1; j <= stpes; j++) {

				int r = random.nextInt(4);

				x += directios[r][0];
				y += directios[r][1];

				path.add(Integer.toString(x) + "," + Integer.toString(y));

				if (!visited.add(Integer.toString(x) + "," + Integer.toString(y)))
					break;

				if (visited.size() == stpes) {
					double distance = Math.pow(x, 2) + Math.pow(y, 2);
					end_to_end_Distance.add(distance);
					System.out.println("Cordinates: " + path + "Size: " + path.size());
					System.out.println("End To End Distance: " + distance);
				}

			}

		}

		System.out.println("LIST OF END TO END DISTANCE: " + end_to_end_Distance);

		double mean = 0.0;

		for (int i = 0; i < end_to_end_Distance.size(); i++) {
			mean += end_to_end_Distance.get(i);
		}

		System.out.println((double) mean / end_to_end_Distance.size());

	}

	public static class points {

		int x, y;

		public points(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public String toString() {
			return Integer.toString(x) + "," + Integer.toString(y);
		}

	}

}
