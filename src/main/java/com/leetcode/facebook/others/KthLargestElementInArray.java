package com.leetcode.facebook.others;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Kth Largest Element in an Array


 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

 Example 1:

 Input: [3,2,1,5,6,4] and k = 2
 Output: 5
 Example 2:

 Input: [3,2,3,1,2,4,5,5,6] and k = 4
 Output: 4
 Note:
 You may assume k is always valid, 1 ≤ k ≤ array's length.

 * @author Santosh Manughala (SM030146).
 */
public class KthLargestElementInArray {

    public static void main(String args[]) {
        int[] input = new int[] {3, 2, 1, 5, 6, 4};
        int k = 2;

//        System.out.println("Case 1: " + getIndexOfKthLargestElementI(input, k));
//        System.out.println("Case 2: " + getIndexOfKthLargestElementII(input, k));
        System.out.println("Case 3: " + getIndexOfKthLargestElementIIIBestCase(input, k));
    }

    // Time: O(nlogn) -> sorting nlogn, access O(1)
    // Space: O(1)
    private static int getIndexOfKthLargestElementI(int[] input, int k) {
        Arrays.sort(input);
        return input[input.length - k];
    }

    // Time: O(nlog(k)) -- adding an element in heap is
    // Space: O(k) -- to store k elements in heap
    private static int getIndexOfKthLargestElementII(int[] input, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i1 - i2;
            }
        });

        for(int i : input) {
            queue.offer(i);
            if(queue.size() > k) {
                queue.poll();
            }
        }

        return queue.poll();
    }

    // Time: O(n) average case, but O(n^2) worst case - when the random number chosen was either the smallest or largest always.
    // In this case we will never have any elements to the left or any elements to the right resp. So we go over n and then n-1,
    // until the last one, which will be O(n^2) -- so the performance depends on the pivot idx chosen
    // Space: O(1)
    private static int getIndexOfKthLargestElementIIIBestCase(int[] input, int k) {
        return quickSelect(input, 0, input.length - 1, input.length - k);
    }

    private static int quickSelect(int[] input, int left, int right, int kSmallest) {

        if(left == right) {
            return input[left];
        }

        Random random = new Random();
        int pivotIdx = left + random.nextInt(right - left);

        pivotIdx = partition(input, left, right, pivotIdx);

        if(pivotIdx == kSmallest) {
            return input[kSmallest];
        } else if(pivotIdx > kSmallest) {
            return quickSelect(input, left, pivotIdx - 1, kSmallest);
        }
        return quickSelect(input, pivotIdx + 1, right, kSmallest);
    }

    private static int partition(int[] input, int left, int right, int pivotIdx) {
        int pivotVal = input[pivotIdx];
        swap(input, pivotIdx, right);

        int storeIdx = left;

        for(int i = left; i <= right; i++) {
            if(pivotVal > input[i]) {
                swap(input, i, storeIdx);
                storeIdx++;
            }
        }

        swap(input, storeIdx, right);
        return storeIdx;
    }

    private static void swap (int[] input, int left, int right) {
        int temp = input[left];
        input[left] = input[right];
        input[right] = temp;
    }


}
