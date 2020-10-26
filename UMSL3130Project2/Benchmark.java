package edu.umsl.cs.UMSL3130Project2;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import edu.umsl.cs.UMSL3130Project2.sort.QuickSortBasic;
import edu.umsl.cs.UMSL3130Project2.sort.QuickSortMedianOf3Partitioning;
import edu.umsl.cs.UMSL3130Project2.sort.QuickSortRandomPivot;

public class Benchmark {

	private static final int INTEGER_MIN = 1;
	private static final int INTEGER_MAX = 10000;
	private static final int REORDER_NTH_POSITION = 10;

	private static final Random random = new Random();

	private static long time(Consumer<int[]> sortingFunction, int[] array) {
		array = copyArray(array);
		final long start = System.nanoTime();
		sortingFunction.accept(array);
		return System.nanoTime() - start;
	}

	private static int[] copyArray(int[] array) {
		int[] arrayCopy = new int[array.length];
		System.arraycopy(array, 0, arrayCopy, 0, array.length);
		return arrayCopy;
	}

	private interface ITestDataGenerator {
		int[] generate(int n);
	}

	private static final ITestDataGenerator randomTestData = (n) -> {
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = random.nextInt(INTEGER_MAX - INTEGER_MIN + 1) + INTEGER_MIN;
		}
		return array;
	};

	private static final ITestDataGenerator sortedTestData = (n) -> {
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = i + 1;
		}
		return array;
	};

	private static final ITestDataGenerator almostSortedTestData = (n) -> {
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = i + 1;
		}
		for (int i = 1; i <= n; i++) {
			if (i != 0 && i % REORDER_NTH_POSITION == 0) {
				array[i - 1] = random.nextInt(INTEGER_MAX - INTEGER_MIN + 1) + INTEGER_MIN;
			}
		}
		return array;
	};

	private static long doSortTest(String arrayType, Tuple<String, Consumer<int[]>> sortingFunctionTuple, Tuple<Integer, List<int[]>> arrayTuple, int testCount) throws IOException {
		String filename = String.format("%s_%s_%d.csv", arrayType, String.join("_", sortingFunctionTuple.x.toLowerCase().split(" ")), arrayTuple.x);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
		writer.write("Time");

		long timeSum = 0;
		int i = 0;
		do {
			writer.write("\n");
			long time = time(sortingFunctionTuple.y, arrayTuple.y.get(i));

			System.out.println(String.format("%.4f", time / 1e9d));
			writer.write(String.format("%.4f", time / 1e9d));

			timeSum += time;
		} while (++i < testCount);

		writer.close();

		return timeSum;
	}

	private static void benchmark(String message, ITestDataGenerator testDataGenerator, String arrayType, int[] arraySizes, int testCount) throws IOException {
		String filename = String.format("benchmark_%s_output.csv", arrayType);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));

		//writer.write("Array Size, Selection Sort, Insertion Sort, Bubble Sort A, Bubble Sort B, Quick Sort, Merge Sort\n");

		List<Tuple<String, Consumer<int[]>>> sortingFunctionTuples = new ArrayList<Tuple<String, Consumer<int[]>>>();
		//sortingFunctionTuples.add(new Tuple<String, Consumer<int[]>>("Quick Sort Basic", QuickSortBasic::sort));
		// sortingFunctionTuples.add(new Tuple<String, Consumer<int[]>>("Quick Sort With Switching", QuickSortWithSwitching::sort));
		sortingFunctionTuples.add(new Tuple<String, Consumer<int[]>>("Quick Sort Median Of 3 Partitioning", QuickSortMedianOf3Partitioning::sort));
		//sortingFunctionTuples.add(new Tuple<String, Consumer<int[]>>("Quick Sort Random Pivot", QuickSortRandomPivot::sort));

		List<Tuple<Integer, List<int[]>>> arrayTuples = new ArrayList<Tuple<Integer, List<int[]>>>();
		for (Integer arraySize : arraySizes) {
			Tuple<Integer, List<int[]>> arrayTuple = new Tuple<Integer, List<int[]>>(arraySize, new ArrayList<int[]>());
			for (int j = 0; j < testCount; j++) {
				arrayTuple.y.add(testDataGenerator.generate(arraySize));
			}
			arrayTuples.add(arrayTuple);
		}

		for (Tuple<Integer, List<int[]>> arrayTuple : arrayTuples) {
			writer.write(String.format("%d,", arrayTuple.x));

			for (Tuple<String, Consumer<int[]>> sortingFunctionTuple : sortingFunctionTuples) {
				System.out.println(String.format("[%s] Benchmarking \"%s\" test data with %d elements", sortingFunctionTuple.x, message, arrayTuple.x));

				long timeSum = doSortTest(arrayType, sortingFunctionTuple, arrayTuple, testCount);
				double timeAverage = timeSum / testCount;

				System.out.println(String.format("%.4f", timeAverage / 1e9d));
				writer.write(String.format("%.4f,", timeAverage / 1e9d));

				System.out.println();
			}

			System.out.flush();
			writer.write("\n");
		}

		writer.close();
	}

	public static void main(String[] args) throws IOException {
		int[] arraySizes = { 1000, 10000, 100000 };
		benchmark("random", randomTestData, "random", arraySizes, 10);
		benchmark("sorted", sortedTestData, "sorted", arraySizes, 10);
		benchmark("almost-sorted", almostSortedTestData, "almost_sorted", arraySizes, 10);
	}

	private static class Tuple<X, Y> {
		public final X x;
		public final Y y;

		public Tuple(X x, Y y) {
			this.x = x;
			this.y = y;
		}
	}

}
