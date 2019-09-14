package sarw;

public class Tuple {

	int x, y, z;

	public Tuple(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public String toString() {
		return Integer.toString(x) + "," + Integer.toString(y) + "," + Integer.toString(z);
	}

}
