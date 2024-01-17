package main.java.com.hemalpatel.algorithms.sorting;

/**
 * Swap two adjacent elements and move the largest element to the end. Repeat until sorted.
 */
public class BubbleSort {

    // TODO : This function can be modified to support generic instead of int later
    // This function is deliberately implemented with int array to set up a basic logic for bubble sort
    public static int[] sort(int[] data) {

        // Swapped flag will keep a track if the remaining elements are already sorted
        boolean swapped = true;
        for (int i = 0; i < data.length && swapped; i++) {
            swapped = false;
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    swapped = true;
                }
            }
        }

        return data;
    }
}
