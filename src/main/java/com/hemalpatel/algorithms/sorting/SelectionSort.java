package main.java.com.hemalpatel.algorithms.sorting;

/**
 * Find the smallest element in the list and swapping with the first element in the unsorted list.
 */
public class SelectionSort {

    // TODO : This function can be modified to support generic instead of int later
    // This function is deliberately implemented with int array to set up a basic logic for selection sort
    public static int[] sort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = data[i];
                data[i] = data[minIndex];
                data[minIndex] = temp;
            }
        }

        return data;
    }
}
