import java.util.Random;

public class Feature {
	public int[] row = new int[4];
	public int[] col = new int[4];
	public int[] sgn = new int[4];

	public Feature() {

	}

	public int getSgn(int i) {
		return this.sgn[i];
	}

	public void setRandomValues(int randomdigit) {



		Random random = new Random(randomdigit);
		int x = random.nextInt(10);
		int y = random.nextInt(10);
		int sgn = (random.nextBoolean() == true) ? 1 : 0;
	//	System.out.println("about to print random");

		for (int u = 0; u < 4; u++) {
			row[u] = x;
			col[u] = y;
			this.sgn[u] = sgn;
			x = random.nextInt(10);
			y = random.nextInt(10);
			sgn = (random.nextBoolean() == true) ? 1 : 0;
			//System.out.println(x + " " + y + " " + sgn);
		}

	}

	public String getFeature(int i) {

		if (i >= 0 && i < 4) {

			return row[i] + ", " + col[i];

		}

		return null;
	}

}
