package main.java.com.hemalpatel.algorithms.sorting;

/**
 * Scan the sorted array to find the correct position of first element of unsorted array and place it.
 */
public class InsertionSort {

    // TODO : This function can be modified to support generic instead of int later
    // This function is deliberately implemented with int array to set up a basic logic for selection sort
    public static int[] sort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            int selectedElement = data[i + 1];
            int insertionIndex = i + 1;
            for (int j = i; (j >= 0) && (selectedElement < data[j]); j--) {
                data[j + 1] = data[j];
                insertionIndex = j;
            }
            data[insertionIndex] = selectedElement;
        }

        return data;
    }
}
