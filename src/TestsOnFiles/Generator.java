package TestsOnFiles;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Generator {
	public static void main(String[] args) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("src/TestsOnFiles/data.txt", true));
		for (int i = 0; i < 100; i++) {
			int[] array = new int[(int) (100 + Math.random() * 10000)];
			for (int j = 0; j < array.length; j++) {
				array[j] = (int) (Math.random() * 1000);
			}
			for (int j = 0; j < array.length; j++) {
				writer.write(array[j] + " ");
			}
			writer.write("\r\n");

			writer.flush();
		}
		writer.flush();
		writer.close();
	}
}


