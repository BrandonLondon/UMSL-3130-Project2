package edu.umsl.cs.UMSL3130Project2.sort;

public class QuickSortMedianOf3Partitioning extends QuickSort { 
     
    // This method is used to sort the array using quicksort algorithm.
    // It takes the left and the right end of the array as the two cursors.
    private static void quickSort(int a[], int left,int right){
         
        // If both cursor scanned the complete array quicksort exits
        if(left >= right)
            return;
         
        // For the simplicity, we took the right most item of the array as a pivot 
        int pivot = a[right];
        int partition = partition(a, left, right, pivot);
         
        // Recursively, calls the quicksort with the different left and right parameters of the sub-array
        quickSort(a, 0, partition - 1);
        quickSort(a, partition+1, right);
    }
     
    // This method is used to partition the given array and returns the integer which points to the sorted pivot index
    private static int partition(int a[], int left,int right,int pivot){
        int leftCursor = left-1;
        int rightCursor = right;
        while(leftCursor < rightCursor){
                while(a[++leftCursor] < pivot);
                while(rightCursor > 0 && a[--rightCursor] > pivot);
            if(leftCursor >= rightCursor){
                break;
            }else{
                swap(a, leftCursor, rightCursor);
            }
        }
        swap(a, leftCursor, right);
        return leftCursor;
    }
     
	public static void sort(int[] array) {
		quickSort(array, 0, array.length - 1);
	}
	
}
