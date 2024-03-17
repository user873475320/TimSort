package TestsOnFiles;

import Timsort.Timsort;
import java.io.*;

public class TimeTest {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("TestsOnFiles/data.txt"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("TestsOnFiles/result.txt", true));

		for (int i = 0; i < 100; i++) {
			String s = reader.readLine();
			String[] arraySplit = s.split(" ");
			int[] array = new int[arraySplit.length];
			for (int j = 0; j < array.length; j++) {
				array[j] = Integer.parseInt(arraySplit[j]);
			}

			Timsort timSort = new Timsort();
			long start = System.nanoTime();
			timSort.sort(array, array.length);
			long end = System.nanoTime();
			long time = end - start;

			writer.write("Length: " + array.length + " Time work program: " + time + "\n");
			writer.flush();
		}

		writer.flush();
		writer.close();
	}
}

