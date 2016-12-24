import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

/*********************************************************************
 * Author: A-Shawni Mitchell                                         *
 * Assignment: Sorting Methods                                       *                           
 * Date of Last Modification: 7/11/16                                *
 * Purpose: Get familar with various sorting methods in java         *
 *          and their run times                                       *
 *********************************************************************/

public class Lab5Summer16{
    
    /******************************************************************
     * BubbleSort Method: This method takes an array of integers and  *
     * sorts the array in ascending order. Returns nothing            *
     ******************************************************************/

    public void bubbleSort(int[] a){
        
        int temp;
        
        for(int i=0;i<a.length-1;i++){
            for(int j=0;j<a.length-1-i;j++){
                if(a[j]>a[j+1]){
                    temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }
        //return a;
        
    }
    
    /*********************************************************************
     * SelectionSort Method: This method takes an array of integers and  *
     * sorts the array in ascending order. Returns nothing               *
     *********************************************************************/
    
    public void selectionSort (int[] a) {
        
        int minValue=0;
        int minIndex=0;
        int temp = 0;
        
        for (int i=0; i<a.length;i++) {
            minValue = a[i];
            minIndex = i;
            for (int j=i;j<a.length;j++) {
                if (a[j] < minValue) {
                    minValue = a[j];
                    minIndex = j;
                }
            }
            if (minValue < a[i]) {
                temp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = temp;
            }
        }
        //return a;
    }
    
    /*********************************************************************
     * InsertionSort Method: This method takes an array of integers and  *
     * sorts the array in ascending order. Returns nothing               *
     *********************************************************************/
    
    public void insertionSort(int[] a){
        
        int temp;
        int key;
        int j;
        
        for (int i=1;i<a.length;i++) {
            
            key=a[i];
            j=i-1;
            
            while(j>=0 && key<a[j]) {
                temp=a[j];
                a[j]=a[j+1];
                a[j+1]=temp;
                j--;
            }
        }
        //return a;
    }
    
    /**********************************************************************
     * quickSort Method: This method takes an array of integers, a start  *
     * index and an end index. This method returns nothing                *
     **********************************************************************/
    
    public void quickSort(int[] a,int start, int end){
        
        int index = partition(a, start, end);
        
        // Recursively call quicksort with left part of the partitioned array
        if (start < index - 1) {
            quickSort(a, start, index - 1);
        }
        
        // Recursively call quick sort with right part of the partitioned array
        if (end > index) {
            quickSort(a, index, end);
        }
    }
    
    /**************************************************************************
     * partition Method: This method takes an array of integers, a left       *
     * index and a right index. This method returns an integer                *
     **************************************************************************/
    
    public int partition(int[] a,int left,int right) {
        
        int pivot = a[left]; // taking first element as pivot
        
        while (left <= right) {
            //searching number which is greater than pivot, bottom up
            while (a[left] < pivot) {
                left++;
            }
            //searching number which is less than pivot, top down
            while (a[right] > pivot) {
                right--;
            }
            
            // swap the values
            if (left <= right) {
                int tmp = a[left];
                a[left] = a[right];
                a[right] = tmp;
                
                //increment left index and decrement right index
                left++;
                right--;
            }
        }
        return left;
    }
    
    /**************************************************************************
     * mergeSortRec Method: This method takes an array of integers, and       *
     * sorts in ascending order. This method returns nothing                  *
     **************************************************************************/
    
    public void mergeSortRec(int[] a, int lowIndex,int highIndex){
        
        if (lowIndex == highIndex)
            return;
        else {
            int midIndex = (lowIndex + highIndex) / 2;
            mergeSortRec(a, lowIndex, midIndex);
            mergeSortRec(a, midIndex + 1, highIndex);
            mergeRec(a, lowIndex, midIndex, highIndex);
        }
    }
    
    /**************************************************************************
     * mergeRec Method: This method takes an array of integers, lowIndex,     *
     * midIndex, and highIndex that merges the array in ascending order.      *
     * This method returns nothing                                            *
     **************************************************************************/
    
    public void mergeRec(int[] a, int lowIndex, int midIndex, int highIndex) {
        int[] Left = new int[midIndex - lowIndex + 2];
        
        for (int i=lowIndex;i<=midIndex;i++) {
            Left[i-lowIndex]=a[i];
        }
        Left[midIndex - lowIndex + 1] = Integer.MAX_VALUE;
        int[] Right = new int[highIndex - midIndex + 1];
        
        for (int i=midIndex+1;i<=highIndex;i++) {
            Right[i - midIndex - 1] = a[i];
        }
        Right[highIndex - midIndex] = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        
        for (int k=lowIndex;k<=highIndex;k++) {
            if (Left[i] <= Right[j]) {
                a[k] = Left[i];
                i++;
            }
            else {
                a[k] = Right[j];
                j++;
            }
        }
    }
    
    /**************************************************************************
     * mergeSortIterative Method: This method takes an array of integers, and      *
     * sorts in ascending order. This method returns nothing                  *
     **************************************************************************/
    
    public void mergeSortIter(int[] a) {
        
        int[] aux = new int[a.length];
        for (int i=1; i<a.length;i*=2){
            for (int j=0;j<a.length;j+=2*i)
                mergeIter(a, aux, j, j+i, j+2*i);
        }
    }
    
    /**************************************************************************
     * mergeIterative Method: This method takes two 1D array of integers,          *
     * lowIndex, midIndex, and highIndex that merges the array in             *
     * ascending order. This method returns nothing                           *
     **************************************************************************/
    
    public void mergeIter(int[] a, int[] aux, int low, int mid, int high) {
        
        // verify that "mid" and "high" are in range
        if (mid >= a.length){
          return;
        }
        if (high > a.length){
            high = a.length;
        }
        
        int i = low;
        int j = mid;
        for (int k=low;k<high;k++) {
            if (i == mid){
                aux[k] = a[j++];
            }
            else if (j == high){
                aux[k] = a[i++];
            }
            else if (a[j] < a[i]){
                aux[k] = a[j++];
            }
            else {
                aux[k] = a[i++];
            }
        }
        // copy back
        for (int k=low;k<high;k++){
            a[k] = aux[k];
        }
    }
    
    /**************************************************************************
     * createRandomArray Method: This method takes an integer and returns     *
     * an array of integers                                                   *
     **************************************************************************/
    
    public int[] createRandomArray(int size){
        
        int[] buildArray = new int[size];
        
        for(int i=0;i<buildArray.length;i++){
            
            Random rand = new Random();
            buildArray[i]=rand.nextInt(1000);
            
        }
        return buildArray;
        
    }
    
    /**************************************************************************
     * copyArray Method: This method takes a 1D and returns                   *
     * a 1D array of integers                                                 *
     **************************************************************************/
    
    public int[] copyArray(int[] a) {
        
        int[] b = new int[a.length];
        
        for(int i=0;i<b.length;i++)
            b[i] = a[i];
        return b;
    }
     
    /********************************* Main Method **********************************/
    
    public static void main(String[] args){
        
        Scanner in = new Scanner(System.in);
        Lab5Summer16 test = new Lab5Summer16();
        
        long startTimeBubble=0;
        long endTimeBubble=0;
        long totalTimeBubble=0;
        
        long startTimeSelection=0;
        long endTimeSelection=0;
        long totalTimeSelection=0;
        
        long startTimeInsertion=0;
        long endTimeInsertion=0;
        long totalTimeInsertion=0;
        
        long startTimeQuick=0;
        long endTimeQuick=0;
        long totalTimeQuick=0;
        
        long startTimeMergeRec=0;
        long endTimeMergeRec=0;
        long totalTimeMergeRec=0;
        
        long startTimeMergeIter=0;
        long endTimeMergeIter=0;
        long totalTimeMergeIter=0;
        
        
        int run=1000;
        
        System.out.println("********************** WELCOME TO LAB 5 ***********************");
        System.out.println();
        
        System.out.println("Please enter the size array you would like to build");
        int size = in.nextInt();
        System.out.println();
        
        /*int[] myArray = test.createRandomArray(size);
        System.out.println("Before sort: "+Arrays.toString(myArray));
        System.out.println();
        test.mergeSortIter(myArray);
        System.out.println("After sort: "+Arrays.toString(myArray));*/
        
        for(int i=0;i<run;i++){
            
            int[] arrayToCopy = test.createRandomArray(size);
            int[] bubbleArray = test.copyArray(arrayToCopy);
            int[] selectionArray = test.copyArray(arrayToCopy);
            int[] insertionArray = test.copyArray(arrayToCopy);
            int[] quickArray = test.copyArray(arrayToCopy);
            int[] mergeRecArray = test.copyArray(arrayToCopy);
            
            
            //bubbleSort
            
            startTimeBubble = System.nanoTime();
            test.bubbleSort(bubbleArray);
            endTimeBubble = System.nanoTime();
            totalTimeBubble += endTimeBubble - startTimeBubble;
            
            
            //selectionSort
            
            startTimeSelection = System.nanoTime();
            test.selectionSort(selectionArray);
            endTimeSelection = System.nanoTime();
            totalTimeSelection += endTimeSelection - startTimeSelection;
            
            //insertionSort
            
            startTimeInsertion = System.nanoTime();
            test.insertionSort(insertionArray);
            endTimeInsertion = System.nanoTime();
            totalTimeInsertion += endTimeInsertion - startTimeInsertion;
            
            //quickSort
            
            startTimeQuick = System.nanoTime();
            test.quickSort(quickArray,0,quickArray.length-1);
            endTimeQuick = System.nanoTime();
            totalTimeQuick += endTimeQuick - startTimeQuick;
            
            //mergeSortRec
            
            startTimeMergeRec = System.nanoTime();
            test.mergeSortRec(mergeRecArray,0,mergeRecArray.length-1);
            endTimeMergeRec = System.nanoTime();
            totalTimeMergeRec += endTimeMergeRec - startTimeMergeRec;
            
            //mergeSortIter
            
            startTimeMergeIter = System.nanoTime();
            test.mergeSortIter(mergeIterArray);
            endTimeMergeIter = System.nanoTime();
            totalTimeMergeIter += endTimeMergeIter - startTimeMergeIter;
            
        }
        System.out.println("Total time for Bubble Sort is: "+ (totalTimeBubble/run));
        System.out.println();
        
        System.out.println("Total time for Selection Sort is: "+ (totalTimeSelection/run));
        System.out.println();
        
        System.out.println("Total time for Insertion Sort is: "+ (totalTimeInsertion/run));
        System.out.println();
        
        System.out.println("Total time for Quick Sort is: "+ (totalTimeQuick/run));
        System.out.println();
        
        System.out.println("Total time for Merge Sort(Rec) is: "+ (totalTimeMergeRec/run));
        System.out.println();
        
        System.out.println("Total time for Merge Sort(Iter) is: "+ (totalTimeMergeIter/run));
        System.out.println();
        
        
        
    }
}
