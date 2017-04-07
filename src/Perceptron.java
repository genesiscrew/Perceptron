import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Perceptron {

	private Map<Image, ArrayList<Integer>> featureValues = new HashMap<Image, ArrayList<Integer>>();
	private ArrayList<Image> images = new ArrayList<Image>();
	// private double[] weights = new double[51];
	ArrayList<Integer> weights = new ArrayList<Integer>(Collections.nCopies(51, 0));

	public Perceptron(Map<Image, ArrayList<Integer>> featureValues, ArrayList<Image> images) {

		this.featureValues = featureValues;
		this.images = images;
	}

	public boolean train() {
		int success = 0;
		int epoch = 0;

		while (epoch < 1000) {

			for (Image image : images) {

				int sum = 0;

				for (int i = 0; i < 50; i++) {
					sum += weights.get(i) * featureValues.get(image).get(i);
				}
				sum += weights.get(50) * 1;
				// System.out.println("sum is: " + sum);
				if (image.getLabel().equals("#Yes") && sum <= 0) {
					// System.out.println("updating weights 1");
					updateWeights(true, featureValues.get(image));
				} else if (image.getLabel().equals("#other") && sum > 0) {
					// System.out.println("updating weights 2");
					updateWeights(false, featureValues.get(image));
				} else {
					success++;
				}

			}
			// System.out.println(success);
			if (success == images.size()) {
				System.out.println("training complete. epoch cycles: " + epoch);
				return true;
			} else {
				success = 0;
			}

			epoch++;
		}
		System.out.println("training failed");
		return false;
	}

	private void updateWeights(boolean b, ArrayList<Integer> values) {
		for (int i = 0; i < weights.size(); i++) {
			if (i == 50) {

				if (b) {
					weights.set(i, weights.get(i) + 1);
				} else {
					weights.set(i, weights.get(i) - 1);

				}

			} else {
				if (b) {
					weights.set(i, weights.get(i) + values.get(i));
				} else {
					weights.set(i, weights.get(i) - values.get(i));

				}
			}

		}

	}

	public void printFinalWeights() {

		for (int i = 0; i < weights.size(); i++) {
			if (i == 50) {
				System.out.println("Threshhold weight " + (i + 1) + ": " + weights.get(i));
			} else {
				System.out.println("weight " + (i + 1) + ": " + weights.get(i));
			}
		}

	}

	public void printFinalFeatures() {
		int count = 1;

		for (Image image : featureValues.keySet()) {

			System.out.print("Feature values for Image " + count + ": ");

			for (Integer i : featureValues.get(image)) {

				System.out.print(i);

			}
			count++;
			System.out.println();

		}

	}

}
