package sarw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {

	static Random random = new Random();

	public static void main(String args[]) {

		long start2d = System.currentTimeMillis();

		TwoDimensionWalk thread2d[] = new TwoDimensionWalk[Globals.N_T];

		for (int i = 0; i < Globals.N_T; i++) {
			thread2d[i] = new TwoDimensionWalk(40);
			thread2d[i].start();
		}

		for (int i = 0; i < Globals.N_T; i++) {
			try {
				thread2d[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (int k = 1; k <= Globals.steps; k++) {

			int N_SAW_TOTAL = 0;
			double mean = 0.0;

			for (int i = 0; i < Globals.N_T; i++) {

				Map<Integer, List<Points>> LocaldataSet = thread2d[i].dataSet;
				List<Points> list = new ArrayList<Points>();

				list = LocaldataSet.get(k);
				N_SAW_TOTAL += list.size();
				for (int j = 0; j < list.size(); j++) {
					mean += (Math.pow(list.get(j).x, 2) + Math.pow(list.get(j).y, 2));
				}

			}

			double fsaw = (double) (N_SAW_TOTAL) / (Globals.N_T * Globals.N_SAW);

			System.out.println(k + "\t" + (mean / N_SAW_TOTAL) + "\t\t\t" + fsaw);
		}

		long end2d = System.currentTimeMillis();

		double time = (end2d - start2d) / 1000.0;

		System.out.println("Total Time to Execute 2D: " + time);

		// Three Dimensions

		long start3d = System.currentTimeMillis();

		ThreeDimensionWalk thread3d[] = new ThreeDimensionWalk[Globals.N_T];

		for (int i = 0; i < Globals.N_T; i++) {
			thread3d[i] = new ThreeDimensionWalk(Globals.steps);
			thread3d[i].start();
		}

		for (int i = 0; i < Globals.N_T; i++) {
			try {
				thread3d[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (int k = 1; k <= Globals.steps; k++) {

			int N_SAW_TOTAL = 0;
			double mean = 0.0;

			for (int i = 0; i < Globals.N_T; i++) {

				Map<Integer, List<Tuple>> LocaldataSet = thread3d[i].dataSet;
				List<Tuple> list = new ArrayList<Tuple>();

				list = LocaldataSet.get(k);
				N_SAW_TOTAL += list.size();
				for (int j = 0; j < list.size(); j++) {
					mean += (Math.pow(list.get(j).x, 2) + Math.pow(list.get(j).y, 2) + Math.pow(list.get(j).z, 2));
				}

			}

			double fsaw = (double) (N_SAW_TOTAL) / (Globals.N_T * Globals.N_SAW);

			System.out.println(k + "\t" + (mean / N_SAW_TOTAL) + "\t\t\t" + fsaw);
		}

		long end3d = System.currentTimeMillis();

		double time3d = (end3d - start3d) / 1000.0;

		System.out.println("Total Time to Execute 3D: " + time3d);

		// Four Dimensions

		long start4d = System.currentTimeMillis();

		FourDimensionWalk thread4d[] = new FourDimensionWalk[Globals.N_T];

		for (int i = 0; i < Globals.N_T; i++) {
			thread4d[i] = new FourDimensionWalk(Globals.steps);
			thread4d[i].start();
		}

		for (int i = 0; i < Globals.N_T; i++) {
			try {
				thread4d[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (int k = 1; k <= Globals.steps; k++) {

			int N_SAW_TOTAL = 0;
			double mean = 0.0;

			for (int i = 0; i < Globals.N_T; i++) {

				Map<Integer, List<FourDpoints>> LocaldataSet = thread4d[i].dataSet;
				List<FourDpoints> list = new ArrayList<FourDpoints>();

				list = LocaldataSet.get(k);
				N_SAW_TOTAL += list.size();
				for (int j = 0; j < list.size(); j++) {
					mean += (Math.pow(list.get(j).x, 2) + Math.pow(list.get(j).y, 2) + Math.pow(list.get(j).z, 2)
							+ Math.pow(list.get(j).w, 2));
				}

			}

			double fsaw = (double) (N_SAW_TOTAL) / (Globals.N_T * Globals.N_SAW);

			System.out.println(k + "\t" + (mean / N_SAW_TOTAL) + "\t\t\t" + fsaw);
		}

		long end4d = System.currentTimeMillis();

		double time4d = (end4d - start4d) / 1000.0;

		System.out.println("Total Time to Execute 3D: " + time4d);

	}

}
