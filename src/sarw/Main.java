package sarw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

	static int directions[][] = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	static int N_SAW = 1000000;
	static Random random = new Random();

	public static void main(String args[]) {

		System.out.println("<\t" + "R^2" + "\t>\t\t\t\t" + "f_Saw(n)");

		TwoDimensionWalk twoD = new TwoDimensionWalk();
		Thread thread = new Thread(twoD);
		thread.start();

	}

	public static class TwoDimensionWalk extends Thread {

		int steps;

		public TwoDimensionWalk() {

		}

		public TwoDimensionWalk(int steps) {
			this.steps = steps;
		}

		static int N_SAW_TOTAL = 0;

		public static void selfAvoidingWalk(int steps) {

			Map<Integer, List<points>> dataSet = new ConcurrentHashMap<>();

			N_SAW_TOTAL = 0;

			for (int i = 1; i <= steps; i++) {
				dataSet.put(i, new ArrayList<>());
			}

			for (int i = 1; i <= N_SAW; i++) {

				Set<String> visited = new HashSet<String>();

				int x = 0;
				int y = 0;
				visited.add(Integer.toString(x) + "," + Integer.toString(y));

				for (int j = 1; j <= steps; j++) {

					int r = ThreadLocalRandom.current().nextInt(4);

					x += directions[r][0];
					y += directions[r][1];

					boolean tmp = visited.contains(Integer.toString(x) + "," + Integer.toString(y));

					if (tmp == false) {
						visited.add(Integer.toString(x) + "," + Integer.toString(y));
						dataSet.get(j).add(new points(x, y));
					} else {
						break;
					}

				}

			}

			for (int i = 1; i <= steps; i++) {

				double mean = 0.0;

				for (int j = 0; j < dataSet.get(i).size(); j++) {
					int x = dataSet.get(i).get(j).x;
					int y = dataSet.get(i).get(j).y;
					mean += Math.pow(x, 2) + Math.pow(y, 2);
				}

				double fsaw = (double) dataSet.get(i).size() / (100 * N_SAW);

				System.out.println(i + "\t " + (double) mean / dataSet.get(i).size() + " \t\t" + fsaw);

			}

		}

		public void run() {

			long start = System.currentTimeMillis();

			selfAvoidingWalk(40);

			long end = System.currentTimeMillis();

			double time = (double) (end - start) / 1000;

			System.out.println("Total Time to complete: " + time);

		}

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
