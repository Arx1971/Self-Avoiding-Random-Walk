package sarw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {

	static Random random = new Random();

	public static void main(String args[]) {

		/*
		 * long start = System.currentTimeMillis();
		 * 
		 * TwoDimensionWalk thread[] = new TwoDimensionWalk[Globals.N_T];
		 * 
		 * for (int i = 0; i < Globals.N_T; i++) { thread[i] = new TwoDimensionWalk(40);
		 * thread[i].start(); }
		 * 
		 * for (int i = 0; i < Globals.N_T; i++) { try { thread[i].join(); } catch
		 * (InterruptedException e) { e.printStackTrace(); } }
		 * 
		 * for (int k = 1; k <= Globals.steps; k++) {
		 * 
		 * int N_SAW_TOTAL = 0; double mean = 0.0;
		 * 
		 * for (int i = 0; i < Globals.N_T; i++) {
		 * 
		 * Map<Integer, List<Points>> LocaldataSet = thread[i].dataSet; List<Points>
		 * list = new ArrayList<Points>();
		 * 
		 * list = LocaldataSet.get(k); N_SAW_TOTAL += list.size(); for (int j = 0; j <
		 * list.size(); j++) { mean += (Math.pow(list.get(j).x, 2) +
		 * Math.pow(list.get(j).y, 2)); }
		 * 
		 * }
		 * 
		 * double fsaw = (double) (N_SAW_TOTAL) / (Globals.N_T * Globals.N_SAW);
		 * 
		 * System.out.println(k + "\t" + (mean / N_SAW_TOTAL) + "\t\t\t" + fsaw); }
		 * 
		 * long end = System.currentTimeMillis();
		 * 
		 * double time = (end - start) / 1000.0;
		 * 
		 * System.out.println("Total Time to Execute: " + time);
		 */

		// Three Dimensions
		
		long start = System.currentTimeMillis();

		ThreeDimensionWalk thread[] = new ThreeDimensionWalk[Globals.N_T];

		for (int i = 0; i < Globals.N_T; i++) {
			thread[i] = new ThreeDimensionWalk(Globals.steps);
			thread[i].start();
		}

		for (int i = 0; i < Globals.N_T; i++) {
			try {
				thread[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (int k = 1; k <= Globals.steps; k++) {

			int N_SAW_TOTAL = 0;
			double mean = 0.0;

			for (int i = 0; i < Globals.N_T; i++) {

				Map<Integer, List<Tuple>> LocaldataSet = thread[i].dataSet;
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

		long end = System.currentTimeMillis();

		double time = (end - start) / 1000.0;

		System.out.println("Total Time to Execute: " + time);

	}

}
