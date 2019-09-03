package selfavoidingwalk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

	final static int N_SAW = 1000000;
	static int directions2D[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static Random random = new Random();

	public static void main(String[] args) {

		TwoDimensions twoD_ = new TwoDimensions();

		Thread t1 = new Thread(twoD_);

		t1.start();
	}

	public static class TwoDimensions extends Thread {

		int steps;

		public TwoDimensions() {
			// Default Constructor
		}

		public TwoDimensions(int steps) {
			this.steps = steps;
		}

		public static double slefAvoidingRandomWlak2D(int steps) {

			List<point> pointsList = new ArrayList<point>();

			Hashtable<Integer, List<point>> hashtable = new Hashtable<Integer, List<point>>();

			for (int i = 0; i < N_SAW; i++) {

				hashtable.put(i, new ArrayList<point>());

				Set<String> visited = new HashSet<String>();

				int x = 0;
				int y = 0;

				visited.add(Integer.toString(x) + "," + Integer.toString(y));

				for (int j = 1; j <= steps; j++) {

					int r = ThreadLocalRandom.current().nextInt(4);

					x = x + directions2D[r][0];
					y = y + directions2D[r][1];

					String coordinate = Integer.toString(x) + "," + Integer.toString(y);

					if (!visited.add(coordinate))
						break;

					if (visited.size() == steps)
						pointsList.add(new point(x, y));

					hashtable.get(i).add(new point(x, y)); // keep track of all the path with n steps

				}
			}

			double squaredDistance = 0.0;

			for (int i = 0; i < pointsList.size(); i++) {
				squaredDistance += Math.pow(pointsList.get(i).x, 2) + Math.pow(pointsList.get(i).y, 2);
			}

			double avg = (double) (squaredDistance / N_SAW);

			return avg;

		}

		public void run() {

			for (int i = 1; i <= 40; i++) {
				System.out.println(slefAvoidingRandomWlak2D(i));
			}

		}

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
