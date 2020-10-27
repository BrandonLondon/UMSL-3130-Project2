/*
 * Author: Brandon London
 * Date: 10/26/20
 * Class: 3130 Algorithms Fall 2020
 * Professor: Galina Piatnitskaia 
 * Purpose: Global implementation of swap but some functions have it coded directly because of debugging.
 */
package edu.umsl.cs.UMSL3130Project2.sort;

public class QuickSort {

	protected static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}