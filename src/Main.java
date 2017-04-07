import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

	static ArrayList<Image> images = new ArrayList<Image>();
	static ArrayList<Feature> features = new ArrayList<Feature>();
	static Map<Image, ArrayList<Integer>> featureValues = new HashMap<Image, ArrayList<Integer>>();

	public static void main(String[] args) throws FileNotFoundException, IOException {

		loadData(args[0]);
		CreateRandomFeatures();
		calculateFeatureValues();
		Perceptron perceptron = new Perceptron(featureValues, images);
		perceptron.train();
		perceptron.printFinalWeights();
		perceptron.printFinalFeatures();

	}

	private static void calculateFeatureValues() {
		for (int i = 0; i < images.size(); i++) {
			ArrayList<Integer> values = new ArrayList<Integer>();
			for (int y = 0; y < features.size(); y++) {
				values.add(images.get(i).getFeatureValue(features.get(y)));
				//System.out.print(values.get(y));
			}
			//System.out.println();
			featureValues.put(images.get(i), values);
		}

	}

	private static void CreateRandomFeatures() {
		for (int i = 0; i < 50; i++) {
			Feature f = new Feature();
			f.setRandomValues(i);
			features.add(f);
		}

	}

	private static void loadData(String data) throws FileNotFoundException {
		File file = new File(data);
		Scanner input = new Scanner(file);

		String p1 = input.nextLine();
		int imageID = 0;

		while (input.hasNext()) {

			String label = input.nextLine();
			int width = input.nextInt();
			int height = input.nextInt();
			String values = null;
			values = input.next();
			String value = null;
			while (!values.equals("P1")) {
				if (value == null) {
					value = values;
				} else {
					value += values;
				}
				if (input.hasNext()) {
					values = input.nextLine();

				} else {
					char[] value1 = value.toCharArray();
					int[] value2 = new int[value1.length];
					for (int i = 0; i < value1.length; i++) {
						value2[i] = Character.getNumericValue(value1[i]);
					}


					int[][] imagePixels = new int[width][height];
					int x = 0;
					for (int i = 0; i < width; i++) {
						for (int u = 0; u < height; u++) {
							imagePixels[i][u] = value2[x];
							x++;
						}
					}
					Image image = new Image(imagePixels, imageID,label);
					images.add(image);
					//System.out.println(images.size());
					return;
				}

			}
			char[] value1 = value.toCharArray();
			int[] value2 = new int[value1.length];
			for (int i = 0; i < value1.length; i++) {
				value2[i] = Character.getNumericValue(value1[i]);
			}


			int[][] imagePixels = new int[width][height];
			int x = 0;
			for (int i = 0; i < width; i++) {
				for (int u = 0; u < height; u++) {
					imagePixels[i][u] = value2[x];
					x++;
				}
			}
			Image image = new Image(imagePixels, imageID,label);
			images.add(image);




		}



		input.close();

	}

}
