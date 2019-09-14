package sarw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class FourDimensionWalk extends Thread {

	Map<Integer, List<FourDpoints>> dataSet = new ConcurrentHashMap<>();
	static int directions[][] = { { 0, 0, 0, -1 }, { 0, 0, -1, 0 }, { 0, -1, 0, 0 }, { -1, 0, 0, 0 }, { 0, 0, 0, 1 },
			{ 0, 0, 1, 0 }, { 0, 1, 0, 0 }, { 1, 0, 0, 0 } };
	int steps;

	public FourDimensionWalk() {

	}

	public FourDimensionWalk(int steps) {
		this.steps = steps;
	}

	public void selfAvoidingWalk(int steps) {

		for (int i = 1; i <= steps; i++) {
			dataSet.put(i, new ArrayList<>());
		}

		for (int i = 1; i <= Globals.N_SAW; i++) {

			Set<String> visited = new HashSet<String>();

			int x = 0;
			int y = 0;
			int z = 0;
			int w = 0;

			visited.add(Integer.toString(x) + "," + Integer.toString(y) + "," + Integer.toString(z) + ","
					+ Integer.toString(w));

			for (int j = 1; j <= steps; j++) {

				int r = ThreadLocalRandom.current().nextInt(6);

				x += directions[r][0];
				y += directions[r][1];
				z += directions[r][2];
				w += directions[r][3];

				boolean tmp = visited.contains(Integer.toString(x) + "," + Integer.toString(y) + ","
						+ Integer.toString(z) + "," + Integer.toString(w));

				if (tmp == false) {
					visited.add(Integer.toString(x) + "," + Integer.toString(y) + "," + Integer.toString(z) + ","
							+ Integer.toString(w));
					dataSet.get(j).add(new FourDpoints(x, y, z, w));
				} else {
					break;
				}

			}

		}

	}

	public void run() {
		selfAvoidingWalk(steps);
	}

}