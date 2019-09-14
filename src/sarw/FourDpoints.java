package sarw;

public class FourDpoints {

	int x, y, z, w;

	public FourDpoints(int x, int y, int z, int w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public String toString() {
		return Integer.toString(x) + "," + Integer.toString(y) + "," + Integer.toString(z) + "," + Integer.toString(w);
	}

}
