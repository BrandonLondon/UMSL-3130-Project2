package edu.umsl.cs.UMSL3130Project2.sort;
public class SeeIfWorks {
 
    private static int []a;
    public static void main(String[] args) {
        // Get a random generated array
        a = getArray();
        System.out.println("before sort");
        sort();
        System.out.println("DOne PLESER");
         
    }
     
    // This method sorts an array and internally calls quickSort 
    public static void sort(){
        int left = 0;
        int right = 999;
             
        quickSort(left, right);
        System.out.println("DOne after");
    }
     
    // This method is used to sort the array using quicksort algorithm.
    // It takes the left and the right end of the array as the two cursors.
    private static void quickSort(int left,int right){
         
        // If both cursor scanned the complete array quicksort exits
        if(left >= right)
            return;
         
        // For the simplicity, we took the right most item of the array as a pivot 
        int pivot = a[right];
        int partition = partition(left, right, pivot);
         
        // Recursively, calls the quicksort with the different left and right parameters of the sub-array
        quickSort(0, partition-1);
        quickSort(partition+1, right);
    }
     
    // This method is used to partition the given array and returns the integer which points to the sorted pivot index
    private static int partition(int left,int right,int pivot){
        int leftCursor = left-1;
        int rightCursor = right;
        while(leftCursor < rightCursor){
                while(a[++leftCursor] < pivot);
                while(rightCursor > 0 && a[--rightCursor] > pivot);
            if(leftCursor >= rightCursor){
                break;
            }else{
                swap(leftCursor, rightCursor);
            }
        }
        swap(leftCursor, right);
        return leftCursor;
    }
     
    // This method is used to swap the values between the two given index
    public static void swap(int left,int right){
        int temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }
     
    public static int[] getArray(){
        int size=100000;
        int []array = new int[size];
        int item = 0;
        for(int i=0;i<size;i++){
            item = (int)(Math.random()*1000); 
            array[i] = item;
        }
        System.out.println("DOne Generating");
        return array;
    }
 
}