package sarw;

public class Points {

	int x, y;

	public Points(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return Integer.toString(x) + "," + Integer.toString(y);
	}

}