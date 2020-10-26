package edu.umsl.cs.UMSL3130Project2.sort;

public class QuickSort {

	protected static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}