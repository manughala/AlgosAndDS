package practice.algorithms;

import java.util.Arrays;

/**
 * Implements insertion sort.
 * Worst case time complexity: O(n2)
 * Best case time complexity: O(n)
 * Average case time complexity: O(n2)
 *
 * Created by Santosh Manughala.
 */
public class InsertionSort {

    public void sort(Integer[] input) {
        for(int j=0;j<input.length;j++){
            int key = input[j];
            int i = j-1;
            while (i>=0 && input[i] > key){
                input[i+1] = input[i];
                i--;
            }
            input[i+1] = key;
        }
    }


    public static void main(String[] args) {
        InsertionSort m = new InsertionSort();
        Integer[] input = {4, 2, 10, 3, 5, 1, 9, 7};
        m.sort(input);
        System.out.println("MERGED = " + Arrays.asList(input).toString());
    }
}
