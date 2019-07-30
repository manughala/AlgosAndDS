package com.leetcode.facebook.sortingandsearching;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

 We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

 Example 1:
 Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 Output: [20,24]
 Explanation:
 List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 List 2: [0, 9, 12, 20], 20 is in range [20,24].
 List 3: [5, 18, 22, 30], 22 is in range [20,24].
 Note:
 The given list may contain duplicates, so ascending order means >= here.
 1 <= k <= 3500
 -105 <= value of elements <= 105.
 For Java users, please note that the input type has been changed to List<List<Integer>>. And after you reset the code template, you'll see this point.

 * @author Santosh Manughala (SM030146).
 */
public class SmallestRange {

    public static void main(String args[]) {
        List<Integer> integers1 = new ArrayList<Integer>() {{add(4);add(10);add(15);add(24);add(26);}};
        List<Integer> integers2 = new ArrayList<Integer>() {{add(0);add(9);add(12);add(20);}};
        List<Integer> integers3 = new ArrayList<Integer>() {{add(5);add(18);add(22);add(30);}};

        List<List<Integer>> nums = new ArrayList<List<Integer>>(){{add(integers1);add(integers2);add(integers3);}};

        // There is a brute force way to loop over every single pair, find the min, max of every iteration,
        // then the min range. Now update the global min range if current min range is smaller.
        // This brute force takes atleast O(n^3) time, even if improved we can use binary search to find min/max/range
        // which will end up O(n2log(k)) - still is bad. So check the below best cases

        int[][] nums1 = new int[][] {
                {4, 10, 15, 24, 26},
                {0, 9, 12, 20},
                {5, 18, 22, 30}
        };

        // leet code changed the method signature to list of list instead of nums, so both the impls here
        int[] result = smallestRangeBestCaseI(nums);
        int[] result1 = smallestRangeBestCaseI(nums1);
        int[] result2 = smallestRangeTheBestCaseII(nums1);
        int[] result2i = smallestRangeTheBestCaseII(nums);

        System.out.println("Result: ");
        for(int num : result) {
            System.out.println(num);
        }

        System.out.println("Result 1: ");
        for(int num : result1) {
            System.out.println(num);
        }

        System.out.println("Result 2: ");
        for(int num : result2) {
            System.out.println(num);
        }

        System.out.println("Result 2i: ");
        for(int num : result2i) {
            System.out.println(num);
        }
    }

    // min heap approach
    // Time: O(nlogk) - we go through same n elements in alist, but we are using min heap to find min of k elements -> to heapify we need O(logk) hence O(nlogk)
    // Space: O(m)- min heap of size m
    public static int[] smallestRangeTheBestCaseII(int[][] nums) {
        int overallMin = 0, overallMax = Integer.MAX_VALUE, overallRange = overallMax - overallMin, currentMax = Integer.MIN_VALUE;
        int[] trackMinIdxInLists = new int[nums.length];
        boolean isMinArrayTraversable = true;

        PriorityQueue<Integer> queue = new PriorityQueue<>(nums.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return nums[o1][trackMinIdxInLists[o1]] - nums[o2][trackMinIdxInLists[o2]];
            }
        });

        for (int i = 0; i < nums.length; i++) {
            queue.offer(i);
            currentMax = Math.max(currentMax, nums[i][0]);
        }

        for(int i = 0; i < nums.length && isMinArrayTraversable; i++) {
            for(int j = 0; j< nums[i].length && isMinArrayTraversable; j++){
                int currentMinIdx = queue.poll(), currentRange = currentMax - nums[currentMinIdx][trackMinIdxInLists[currentMinIdx]];

                if(currentRange < overallRange) {
                    overallRange = currentRange;
                    overallMin = nums[currentMinIdx][trackMinIdxInLists[currentMinIdx]];
                    overallMax = currentMax;
                }

                trackMinIdxInLists[currentMinIdx]++;

                if(trackMinIdxInLists[currentMinIdx] == nums[currentMinIdx].length) {
                    isMinArrayTraversable = false;
                    break;
                }

                queue.offer(currentMinIdx);
                currentMax = Math.max(currentMax, nums[currentMinIdx][trackMinIdxInLists[currentMinIdx]]);
            }
        }

        return new int[]{overallMin, overallMax};
    }

    private static int[] smallestRangeTheBestCaseII(List<List<Integer>> nums) {
        int overallMin = 0, overallMax = Integer.MAX_VALUE, overallRange = overallMax - overallMin, currentMax = Integer.MIN_VALUE;
        int[] trackMinOfListsIdx = new int[nums.size()];

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return nums.get(i1).get(trackMinOfListsIdx[i1]) - nums.get(i2).get(trackMinOfListsIdx[i2]);
            }
        });

        boolean isMinArrayTraversable = true;

        for(int i = 0; i < nums.size(); i++) {
            queue.offer(i);
            currentMax = Math.max(nums.get(i).get(0), currentMax);
        }

        for(int i = 0; i < nums.size() && isMinArrayTraversable; i++) {
            for(int j = 0 ;j < nums.get(i).size() && isMinArrayTraversable; j++) {

                int currentMinIdx = queue.poll();

                int currentRange = currentMax - nums.get(currentMinIdx).get(trackMinOfListsIdx[currentMinIdx]);

                if(currentRange < overallRange) {
                    overallRange = currentRange;
                    overallMin = nums.get(currentMinIdx).get(trackMinOfListsIdx[currentMinIdx]);
                    overallMax = currentMax;
                }

                trackMinOfListsIdx[currentMinIdx]++;

                if(trackMinOfListsIdx[currentMinIdx] == nums.get(currentMinIdx).size()) {
                    isMinArrayTraversable = false;
                    break;
                }

                queue.offer(currentMinIdx);
                currentMax = Math.max(nums.get(currentMinIdx).get(trackMinOfListsIdx[currentMinIdx]), currentMax);
            }
        }

        return new int[]{overallMin, overallMax};
    }

    // The pointers approach
    // Time : O(n * k)  - going through n itesm in a list, and k items to find min/max
    // Space: O(1)
    public static int[] smallestRangeBestCaseI(int[][] nums) {
        int overallMin = 0, overallMax = Integer.MAX_VALUE, overallRange = overallMax-overallMin;

        boolean isMinArrayTraversable = true;

        int[] trackMinOfListsIdx = new int[nums.length];

        for(int i = 0; i < nums.length && isMinArrayTraversable; i++) {
            for(int j = 0 ;j < nums[i].length && isMinArrayTraversable; j++) {

                // Find min and max looping over k lists
                int currentMinIdx = 0, currentMaxIdx = 0;

                for(int k = 0; k < nums.length; k++) {
                    if(nums[k][trackMinOfListsIdx[k]] < nums[currentMinIdx][trackMinOfListsIdx[currentMinIdx]]) {
                        currentMinIdx = k;
                    }

                    if(nums[k][trackMinOfListsIdx[k]] > nums[currentMaxIdx][trackMinOfListsIdx[currentMaxIdx]]) {
                        currentMaxIdx = k;
                    }
                }

                int currentRange = nums[currentMaxIdx][trackMinOfListsIdx[currentMaxIdx]] - nums[currentMinIdx][trackMinOfListsIdx[currentMinIdx]];

                if(currentRange < overallRange) {
                    overallRange = currentRange;
                    overallMin = nums[currentMinIdx][trackMinOfListsIdx[currentMinIdx]];
                    overallMax = nums[currentMaxIdx][trackMinOfListsIdx[currentMaxIdx]];
                }

                trackMinOfListsIdx[currentMinIdx]++;

                if(trackMinOfListsIdx[currentMinIdx] == nums[currentMinIdx].length) {
                    isMinArrayTraversable = false;
                }
            }
        }

        return new int[]{overallMin, overallMax};
    }

    public static int[] smallestRangeBestCaseI(List<List<Integer>> nums) {
        int overallMin = 0, overallMax = Integer.MAX_VALUE, overallRange = overallMax-overallMin;

        boolean isMinArrayTraversable = true;

        int[] trackMinOfListsIdx = new int[nums.size()];

        for(int i = 0; i < nums.size() && isMinArrayTraversable; i++) {
            for(int j = 0 ;j < nums.get(i).size() && isMinArrayTraversable; j++) {

                //Find min and max looping over k lists
                int currentMinIdx = 0, currentMaxIdx = 0;

                for(int k = 0; k < nums.size(); k++) {

                    if(nums.get(k).get(trackMinOfListsIdx[k]) < nums.get(currentMinIdx).get(trackMinOfListsIdx[currentMinIdx])) {
                        currentMinIdx = k;
                    }

                    if(nums.get(k).get(trackMinOfListsIdx[k]) > nums.get(currentMaxIdx).get(trackMinOfListsIdx[currentMaxIdx])) {
                        currentMaxIdx = k;
                    }
                }

                int currentRange = nums.get(currentMaxIdx).get(trackMinOfListsIdx[currentMaxIdx]) - nums.get(currentMinIdx).get(trackMinOfListsIdx[currentMinIdx]);

                if(currentRange < overallRange) {
                    overallRange = currentRange;
                    overallMin = nums.get(currentMinIdx).get(trackMinOfListsIdx[currentMinIdx]);
                    overallMax = nums.get(currentMaxIdx).get(trackMinOfListsIdx[currentMaxIdx]);
                }

                trackMinOfListsIdx[currentMinIdx]++;

                if(trackMinOfListsIdx[currentMinIdx] == nums.get(currentMinIdx).size()) {
                    isMinArrayTraversable = false;
                }
            }
        }

        return new int[]{overallMin, overallMax};
    }
}
