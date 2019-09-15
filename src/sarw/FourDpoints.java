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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + w;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FourDpoints other = (FourDpoints) obj;
		if (w != other.w)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

}
