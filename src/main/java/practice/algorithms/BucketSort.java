package practice.algorithms;

import java.util.Arrays;

/**
 * Implements bucket sort.
 * Worst case time complexity: O(n2)
 * Best case time complexity: O(n+k)
 * Average case time complexity: O(n+k)
 *
 * Created by Santosh Manughala.
 */
public class BucketSort {

    public void sort(Integer[] input) {
        int[] bucket = new int[input.length + 1];

        for (int i = 0; i < input.length; i++) {
            bucket[input[i]]++;
        }

        int position = 0;
        for (int i = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i]; j++) {
                input[position++] = i;
            }
        }
    }

    public static void main(String[] args) {
        BucketSort bucketSort = new BucketSort();
        Integer[] input = {5, 3, 0, 2, 4, 1, 0, 5, 2, 3, 1, 4};
        bucketSort.sort(input);
        System.out.println(Arrays.asList(input).toString());
    }
}
