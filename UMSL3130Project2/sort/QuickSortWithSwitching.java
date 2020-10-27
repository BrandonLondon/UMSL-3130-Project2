/*
 * Author: Brandon London
 * Date: 10/26/20
 * Class: 3130 Algorithms Fall 2020
 * Professor: Galina Piatnitskaia 
 * Purpose: Implements quicksort until the last 2% then insertion
 * */
package edu.umsl.cs.UMSL3130Project2.sort;

public class QuickSortWithSwitching extends QuickSort {
	public static void insertionSort(int arr[], int size) {
		int k, j;
		for (int i = 1; i < size; i++) {
			k = arr[i];
			j = i - 1;

			while (j >= 0 && arr[j] > k) {
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = k;
		}
	}

	// method for partition
	public static int partition(int a[], int l, int h) {
		int pvt = a[h];
		int i = l - 1;
		int temp;

		for (int j = l; j <= h - 1; j++) {
			if (a[j] < pvt) {
				i++;

				// swapping for testing
				temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
		}
		temp = a[i + 1];
		a[i + 1] = a[h];
		a[h] = temp;
		return i + 1;
	}

	// mthod for quick sort
	public static void Quick_Sort(int A[], int l, int r, int size) {
		if (l < r) {

			int p = partition(A, l, r);

			// switching to Insertion sort when the number of elements in the subarray is
			// less than or equal to 2% of the original number
			if ((p - 1 - l) <= (.02 * size)) {
				insertionSort(A, p - 1 - l);
			} else {
				Quick_Sort(A, l, p - 1, size);
			}

			if ((r - p - 1) <= (.02 * size)) {
				insertionSort(A, r - p - 1);
			} else {
				Quick_Sort(A, p + 1, r, size);
			}
		}
	}

	public static void sort(int[] array) {
		Quick_Sort(array, 0, array.length - 1, array.length);
		
	}

}
