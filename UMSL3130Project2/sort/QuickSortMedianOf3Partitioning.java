/*
 * Author: Brandon London
 * Date: 10/26/20
 * Class: 3130 Algorithms Fall 2020
 * Professor: Galina Piatnitskaia 
 * Purpose: Quicksort implementation using MedianOfThreePartitioning
 * */
package edu.umsl.cs.UMSL3130Project2.sort;

public class QuickSortMedianOf3Partitioning extends QuickSort { 
	//--------------------------------------------------------------
	   public static void recQuickSort(int arr[], int left, int right)
	   {
	      int size = right-left+1;
	      if(size <= 3)                  // manual sort if small
	         manualSort(arr, left, right);
	      else                           // quicksort if large
	      {
	         long median = medianOf3(arr, left, right);
	         int partition = partitionIt(arr, left, right, median);
	         recQuickSort(arr, left, partition-1);
	         recQuickSort(arr, partition+1, right);
	      }
	    }  // end recQuickSort()
	//--------------------------------------------------------------
	   public static long medianOf3(int theArray[], int left, int right)
	   {
	      int center = (left+right)/2;
	                                         // order left & center
	      if( theArray[left] > theArray[center] )
	         swap(theArray, left, center);
	                                         // order left & right
	      if( theArray[left] > theArray[right] )
	         swap(theArray, left, right);
	                                         // order center & right
	      if( theArray[center] > theArray[right] )
	         swap(theArray, center, right);

	      swap(theArray, center, right-1);             // put pivot on right
	      return theArray[right-1];          // return median value
	    }  // end medianOf3()
	//-------------------------------------------------------------
	//--------------------------------------------------------------
	    public static int partitionIt(int theArray[], int left, int right, long pivot)
	    {
	       int leftPtr = left;             // right of first elem
	       int rightPtr = right - 1;       // left of pivot

	       while(true)
	       {
	          while( theArray[++leftPtr] < pivot )  // find bigger
	             ;                                  //    (nop)
	          while( theArray[--rightPtr] > pivot ) // find smaller
	             ;                                  //    (nop)
	          if(leftPtr >= rightPtr)      // if pointers cross,
	             break;                    //    partition done
	          else                         // not crossed, so
	             swap(theArray, leftPtr, rightPtr);  // swap elements
	       }  // end while(true)
	       swap(theArray, leftPtr, right-1);         // restore pivot
	       return leftPtr;                 // return pivot location
	    }  // end partitionIt()
	//--------------------------------------------------------------
	   public static void manualSort(int theArray[], int left, int right)
	   {
	      int size = right-left+1;
	      if(size <= 1)
	         return;         // no sort necessary
	      if(size == 2)
	      {               // 2-sort left and right
	         if( theArray[left] > theArray[right] )
	            swap(theArray, left, right);
	         return;
	      }
	      else               // size is 3
	      {               // 3-sort left, center, & right
	         if( theArray[left] > theArray[right-1] )
	            swap(theArray, left, right-1);                // left, center
	         if( theArray[left] > theArray[right] )
	            swap(theArray, left, right);                  // left, right
	         if( theArray[right-1] > theArray[right] )
	            swap(theArray, right-1, right);               // center, right
	      }
	    }  // end manualSort()
	//--------------------------------------------------------------
     
	public static void sort(int[] array) {
		recQuickSort(array, 0, array.length - 1);
	}
	
}
