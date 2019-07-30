package com.practice.strings;

/**
 * Given an array arr[] of integers, find out the maximum difference between any two elements such that larger element appears after the smaller number.
* Input : arr = {2, 3, 10, 6, 4, 8, 1}
 Output : 8
 Explanation : The maximum difference is between 10 and 2.

 Input : arr = {7, 9, 5, 10, 3, 2}
 Output : 2
 Explanation : The maximum difference is between 9 and 7.
 * @author Santosh Manughala (SM030146).
 */
public class MaxDifference {

    public static void maxDifference(int[] numbers) {
        int len = numbers.length;
        if(len == 1) {
            System.out.println("cannot get a max difference with only 1 element");
            return;
        }

        int maxDiff = numbers[1] - numbers[0];

        for(int i = 0; i< len; i++) {
            for(int j=i+1; j<len; j++) {
                if((numbers[j] - numbers[i]) > maxDiff) {
                    maxDiff = numbers[j] - numbers[i];
                }
            }
        }

        System.out.println("Max difference = " + maxDiff);
    }

    public static void main(String args[]) {
//        int[] numbers = {7, 9, 5, 6, 3, 2};
//        int[] numbers = {7, 9, 5, 6, 3, 2};
        int[] numbers = {7, 9, 5, 10, 3, 2};
        maxDifference(numbers);
    }
}
