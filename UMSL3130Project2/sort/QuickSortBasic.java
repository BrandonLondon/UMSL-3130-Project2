/*
 * Author: Brandon London
 * Date: 10/26/20
 * Class: 3130 Algorithms Fall 2020
 * Professor: Galina Piatnitskaia 
 * Purpose: Preforms the basic quicksort functions
 */
package edu.umsl.cs.UMSL3130Project2.sort;

public class QuickSortBasic extends QuickSort {

	private static int partition(int array[], int low, int high) {
		int pivot = array[high];
		int i = (low - 1);
		for (int j = low; j <= high - 1; j++) {
			if (array[j] < pivot) {
				i++;
				swap(array, i, j);
			}
		}
		swap(array, i + 1, high);
		return i + 1;
	}

	private static void sort(int[] array, int low, int high) {
		if (low < high) {
			int pi = partition(array, low, high);
			sort(array, low, pi - 1);
			sort(array, pi + 1, high);
		}
	}

	public static void sort(int[] array) {
		sort(array, 0, array.length - 1);
	}

}
