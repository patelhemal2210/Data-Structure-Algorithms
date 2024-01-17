package main.java.com.hemalpatel.algorithms.sorting;

/**
 * Swap two adjacent elements and move the largest element to the end. Repeat until sorted.
 */
public class BubbleSort {

    public static int[] sort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }

        return data;
    }
}
