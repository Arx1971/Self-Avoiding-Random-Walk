package sarw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class SelfAvoidingPolygon extends Thread {
	
	Map<Integer, List<Points>> dataSet = new ConcurrentHashMap<>();
	final int directions[][] = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	int steps;

	public SelfAvoidingPolygon() {

	}

	public SelfAvoidingPolygon(int steps) {
		this.steps = steps;
	}

	public void selfAvoidPolygon(int steps) {

		for (int i = 1; i <= steps; i++) {
			dataSet.put(i, new ArrayList<>());
		}

		for (int i = 1; i <= Globals.N_SAW; i++) {

			Set<String> visited = new HashSet<String>();

			int x = 0;
			int y = 0;

			String s = Integer.toString(x) + "," + Integer.toString(y);

			visited.add(s);

			for (int j = 1; j <= steps; j++) {

				int r = ThreadLocalRandom.current().nextInt(4);

				x += directions[r][0];
				y += directions[r][1];

			}
			
			

		}

	}

	public void run() {

	}

}
