package practice.algorithms;

import java.util.Arrays;

/**
 * Implements bubble sort.
 * Worst case time complexity: O(n2)
 * Best case time complexity: O(n)
 * Average case time complexity: O(n2)
 *
 * Created by Santosh Manughala.
 */
public class BubbleSort {

    public Integer[] sort(Integer[] input) {
        boolean swapped = true;

        while (swapped) {
            swapped = false;
            for (int i = 1; i < input.length; i++) {
                if (input[i - 1] > input[i]) {
                    int temp = input[i];
                    input[i] = input[i - 1];
                    input[i - 1] = temp;
                    swapped = true;
                }
            }
        }
        return input;
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        Integer[] input = {4, 2, 10, 3, 5, 1, 9, 7};
        Integer[] output = bubbleSort.sort(input);
        System.out.println("Sorted = " + Arrays.asList(output).toString());
    }
}
