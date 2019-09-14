package sarw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class ThreeDimensionWalk extends Thread {

	Map<Integer, List<Tuple>> dataSet = new ConcurrentHashMap<>();
	static int directions[][] = { { 1, 0, 0 }, { -1, 0, 0 }, { 0, 1, 0 }, { 0, -1, 0 }, { 0, 0, 1 }, { 0, 0, -1 } };
	int steps;

	public ThreeDimensionWalk() {

	}

	public ThreeDimensionWalk(int steps) {
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

			visited.add(Integer.toString(x) + "," + Integer.toString(y) + "," + Integer.toString(z));

			for (int j = 1; j <= steps; j++) {

				int r = ThreadLocalRandom.current().nextInt(6);

				x += directions[r][0];
				y += directions[r][1];
				z += directions[r][2];

				boolean tmp = visited
						.contains(Integer.toString(x) + "," + Integer.toString(y) + "," + Integer.toString(z));

				if (tmp == false) {
					visited.add(Integer.toString(x) + "," + Integer.toString(y) + "," + Integer.toString(z));
					dataSet.get(j).add(new Tuple(x, y, z));
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