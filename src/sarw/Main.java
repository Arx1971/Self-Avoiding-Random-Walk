package sarw;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Main {

	static int directions[][] = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	static int N_SAW = 5000000;
	static Random random = new Random();

	public static void main(String args[]) {

		TwoDimensionWalk twoD = new TwoDimensionWalk();

		Thread thread = new Thread(twoD);
		thread.start();

		System.out.println("<\t" + "R^2" + "\t>\t\t" + "f_Saw(n)");

		/*
		 * TwoDimensionWalk twoD[] = new TwoDimensionWalk[21]; Thread thread[] = new
		 * Thread[21]; for (int i = 0; i <= 20; i++) { twoD[i] = new TwoDimensionWalk(i
		 * + 10); thread[i] = new Thread(twoD[i]); thread[i].start(); }
		 * 
		 * for (int i = 0; i <= 20; i++) { try { thread[i].join(); } catch (Exception e)
		 * { // TODO: handle exception } }
		 */

	}

	public static class TwoDimensionWalk extends Thread {

		int steps;

		public TwoDimensionWalk() {

		}

		public TwoDimensionWalk(int steps) {
			this.steps = steps;
		}

		static int N_SAW_TOTAL = 0;

		public static double selfAvoidingWalk(int steps) {

			List<Double> end_to_end_Distance = Collections.synchronizedList(new ArrayList<Double>());
			N_SAW_TOTAL = 0;
			for (int i = 1; i <= N_SAW; i++) {

				Set<String> visited = new HashSet<String>();
				List<String> path = new ArrayList<String>();
				int x = 0;
				int y = 0;

				path.add(Integer.toString(x) + "," + Integer.toString(y));

				for (int j = 1; j <= steps; j++) {

					int r = random.nextInt(4);

					x += directions[r][0];
					y += directions[r][1];

					path.add(Integer.toString(x) + "," + Integer.toString(y));

					if (!visited.add(Integer.toString(x) + "," + Integer.toString(y)))
						break;

					if (visited.size() == steps) {
						double distance = Math.pow(x, 2) + Math.pow(y, 2);
						end_to_end_Distance.add(distance);
						// System.out.println(path + " " + " " + path.size() + " " + distance);
					}

				}

			}

			double mean = 0.0;

			for (int i = 0; i < end_to_end_Distance.size(); i++) {
				mean += end_to_end_Distance.get(i);
			}

			double avg = (double) mean / end_to_end_Distance.size();

			N_SAW_TOTAL = end_to_end_Distance.size();

			return avg;

		}

		public void run() {

			// System.out.println(steps + " -> " + selfAvoidingWalk(steps));

			double solutions = 0.0;
			double fsaw = 0.0;
			String twoDfliepath = "/home/adnanrahin/eclipse-workspace/Self-Avoiding-Random-Walk/result.txt";
			try (FileWriter writer = new FileWriter(twoDfliepath)) {
				for (int i = 10; i <= 40; i++) {
					solutions = selfAvoidingWalk(i);
					fsaw = ((double) N_SAW_TOTAL / N_SAW * Thread.activeCount());
					System.out.println(i + " " + solutions + "\t\t"
							+ (double) ((double) N_SAW_TOTAL / N_SAW * Thread.activeCount()));

					writer.write(Double.toString(solutions) + "\t\t" + Double.toString(fsaw));
					writer.write("\n");
				}
				writer.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

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
