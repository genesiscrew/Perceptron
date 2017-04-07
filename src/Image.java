
public class Image {
	public int[][] image;
	public int imageID;
	public String label;

	public Image(int[][] image, int id, String label) {
		this.image = image;
		this.imageID = id;
		this.label = label;

	}

	public int getFeatureValue(Feature feature) {

		int sum = 0;
		for (int i = 0; i < 4; i++) {

			if (getImage(feature.row[i], feature.col[i]) == feature.getSgn(i))
				sum++;

		}

		return (sum >= 3) ? 1 : 0;
	}

	public int getImage(int x, int y) {
		return this.image[x][y];
	}

	public String getLabel() {

		return label;
	}

}
