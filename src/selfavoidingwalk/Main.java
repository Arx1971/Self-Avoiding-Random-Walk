package selfavoidingwalk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

	static Random random = new Random();
	final static int N_SAW = random.nextInt(1000000);
	static int directions2D[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int directions3D[][] = { { 1, 0, 0 }, { -1, 0, 0 }, { 0, 1, 0 }, { 0, -1, 0 }, { 0, 0, 1 }, { 0, 0, -1 } };
	static int directions4D[][] = { { 0, 0, 0, -1 }, { 0, 0, -1, 0 }, { 0, -1, 0, 0 }, { -1, 0, 0, 0 }, { 0, 0, 0, 1 },
			{ 0, 0, 1, 0 }, { 0, 1, 0, 0 }, { 1, 0, 0, 0 } };

	public static void main(String[] args) {

		TwoDimensions twoD_ = new TwoDimensions();

		Thread t1 = new Thread(twoD_);

		t1.start();

		/*
		 * ThreeDimensions threeD_ = new ThreeDimensions();
		 * 
		 * Thread t2 = new Thread(threeD_);
		 * 
		 * t2.start();
		 * 
		 * FourDimensions fourd_ = new FourDimensions();
		 * 
		 * Thread t3 = new Thread(fourd_);
		 * 
		 * t3.start();
		 */

	}

	public static class TwoDimensions extends Thread {

		int steps;

		public TwoDimensions() {
			// Default Constructor
		}

		public TwoDimensions(int steps) {
			this.steps = steps;
		}

		public static double slefAvoidingRandomWalk2D(int steps) {

			List<point> pointsList = new ArrayList<point>();

			Hashtable<Integer, List<point>> pointTable2d = new Hashtable<Integer, List<point>>();

			for (int i = 0; i < N_SAW; i++) {

				pointTable2d.put(i, new ArrayList<point>());

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

					pointTable2d.get(i).add(new point(x, y)); // keep track of all the path with n steps

				}
			}

			double squaredDistance = 0.0;

			for (int i = 0; i < pointsList.size(); i++) {
				squaredDistance += Math.pow(pointsList.get(i).x, 2) + Math.pow(pointsList.get(i).y, 2);
			}

			double avg = (double) (squaredDistance / pointsList.size());

			return avg;

		}

		public void run() {

			long start = System.currentTimeMillis();
			for (int i = 10; i <= 40; i++) {
				System.out.println(slefAvoidingRandomWalk2D(i));
			}
			long stop = System.currentTimeMillis();

			System.out.println("Current: " + (double) (stop - start) / 1000);

		}

	}

	public static class ThreeDimensions extends Thread {

		int steps;

		public ThreeDimensions(int steps) {
			this.steps = steps;
		}

		public ThreeDimensions() {
			// Default Constructor
		}

		public static double slefAvoidingRandomWalk3D(int steps) {

			List<tuple> tupleList = new ArrayList<tuple>();

			Hashtable<Integer, List<tuple>> tupleTable3d = new Hashtable<Integer, List<tuple>>();

			for (int i = 0; i < N_SAW; i++) {

				tupleTable3d.put(i, new ArrayList<tuple>());

				Set<String> visited = new HashSet<String>();

				int x = 0;
				int y = 0;
				int z = 0;

				visited.add(Integer.toString(x) + "," + Integer.toString(y) + "," + Integer.toString(z));

				for (int j = 1; j <= steps; j++) {

					int r = ThreadLocalRandom.current().nextInt(6);

					x = x + directions3D[r][0];
					y = y + directions3D[r][1];
					z = z + directions3D[r][2];

					String coordinate = Integer.toString(x) + "," + Integer.toString(y) + "," + Integer.toString(z);

					if (!visited.add(coordinate))
						break;

					if (visited.size() == steps)
						tupleList.add(new tuple(x, y, z));

					tupleTable3d.get(i).add(new tuple(x, y, z));

				}

			}

			double squaredDistance = 0.0;

			for (int i = 0; i < tupleList.size(); i++) {
				squaredDistance += Math.pow(tupleList.get(i).x, 2) + Math.pow(tupleList.get(i).y, 2)
						+ Math.pow(tupleList.get(i).z, 2);
			}

			double avg = (double) (squaredDistance / N_SAW);

			return avg;

		}

		public void run() {
			for (int i = 1; i <= 40; i++) {
				System.out.println(slefAvoidingRandomWalk3D(i));
			}
		}

	}

	public static class FourDimensions extends Thread {

		int steps;

		public FourDimensions(int steps) {
			this.steps = steps;
		}

		public FourDimensions() {

		}

		public static double slefAvoidingRandomWalk4D(int steps) {

			List<fourDCordinates> fouDCordinatesList = new ArrayList<fourDCordinates>();

			Hashtable<Integer, List<fourDCordinates>> fourDcordinatestable = new Hashtable<Integer, List<fourDCordinates>>();

			for (int i = 0; i < N_SAW; i++) {

				int x = 0;
				int y = 0;
				int z = 0;
				int w = 0;

				Set<String> visited = new HashSet<String>();

				visited.add(Integer.toString(x) + "," + Integer.toString(y) + "," + Integer.toString(z) + ","
						+ Integer.toString(w));

				fourDcordinatestable.put(i, new ArrayList<fourDCordinates>());

				for (int j = 1; j <= steps; j++) {

					int r = ThreadLocalRandom.current().nextInt(8);

					x = x + directions4D[r][0];
					y = y + directions4D[r][1];
					z = z + directions4D[r][2];
					w = w + directions4D[r][3];
					String coordinate = Integer.toString(x) + "," + Integer.toString(y) + "," + Integer.toString(z)
							+ "," + Integer.toString(w);

					if (!visited.add(coordinate))
						break;

					if (visited.size() == steps)
						fouDCordinatesList.add(new fourDCordinates(x, y, z, w));

					fourDcordinatestable.get(i).add(new fourDCordinates(x, y, z, w));

				}

			}

			double squaredDistance = 0.0;

			for (int i = 0; i < fouDCordinatesList.size(); i++) {
				squaredDistance += Math.pow(fouDCordinatesList.get(i).x, 2) + Math.pow(fouDCordinatesList.get(i).y, 2)
						+ Math.pow(fouDCordinatesList.get(i).z, 2) + Math.pow(fouDCordinatesList.get(i).w, 2);
			}

			double avg = (double) (squaredDistance / N_SAW);

			return avg;

		}

		public void run() {

			for (int i = 10; i <= 40; i++) {
				System.out.println(slefAvoidingRandomWalk4D(i));
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

	public static class tuple {

		int x;
		int y;
		int z;

		public tuple(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public tuple() {
			// Default Constructor
		}

		public String toString() {
			return Integer.toString(x) + "," + Integer.toString(y) + "," + Integer.toString(z);
		}
	}

	public static class fourDCordinates {

		int x;
		int y;
		int z;
		int w;

		public fourDCordinates(int x, int y, int z, int w) {

			this.x = x;
			this.y = y;
			this.z = z;
			this.w = w;

		}

		public fourDCordinates() {
			// TODO Auto-generated constructor stub
		}

		public String toString() {
			return Integer.toString(x) + "," + Integer.toString(y) + "," + Integer.toString(z) + ","
					+ Integer.toString(w);
		}

	}

}
