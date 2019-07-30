package com.leetcode.linkedin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.

 Example 1:

 Input: [[1,3],[2,6],[8,10],[15,18]]
 Output: [[1,6],[8,10],[15,18]]
 Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 Example 2:

 Input: [[1,4],[4,5]]
 Output: [[1,5]]
 Explanation: Intervals [1,4] and [4,5] are considered overlapping.

 NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

 * @author Santosh Manughala (SM030146).
 */
public class MergeIntervals {

    // NOTE: REFER com.leetcode.facebook.sortingandsearching for all solutions
    // NOTE instead of an object interval, now question has changed to bidirectional ints
    public static void main(String args[]) {
        int[][] nums = new int[][] {
                {1,3},
                {2,6},
                {8,10},
                {15,18}
            };

//        int[][] result = mergeIntervals(nums);
        int[][] result = mergeIntervalsNewInputsBetter(nums);

        for(int[] i : result) {
            for(int j : i) {
                System.out.println(j);
            }
        }
    }

    // Time: O(nlogn) - cos of sorting
    // Space: O(1)
    private static int[][] mergeIntervalsNewInputsBetter (int[][] nums) {
        if(nums == null || nums.length <= 1) {
            return nums;
        }

        Arrays.sort(nums, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });

        int k = 0, i = 1;

        while (i < nums.length) {
            if(nums[i][0] <= nums[k][1]) {
                nums[k][1] = Math.max(nums[k][1], nums[i][1]);
                i++;
            } else {
                k++;
                nums[k][0] = nums[i][0];
                nums[k][1] = nums[i][1];
                i++;
            }
        }

        nums = Arrays.copyOf(nums, k + 1);
        return nums;

    }

    private static class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    //Time: O(nlogn)
    // Space:
    //  O(1) - inplace sorting
    //  O(n)-  additional space for sorting
    private static int[][] mergeIntervals (int[][] nums) {
        if(nums == null || nums.length <= 0) {
            return nums;
        }

        List<Interval> intervals = new ArrayList<>();

        for(int[] num : nums) {
            intervals.add(new Interval(num[0], num[1]));
        }

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });

        LinkedList<Interval> merged = new LinkedList<>();

        for(Interval interval : intervals) {
            if(merged.isEmpty() || merged.getLast().end < interval.start) {
                merged.add(interval);
            } else {
                merged.getLast().end = Math.max(merged.getLast().end, interval.end);
            }
        }

        int[][] result = new int[merged.size()][nums[0].length];
        int i = 0;
        for(Interval interval : merged) {
            result[i][0] = interval.start;
            result[i][1] = interval.end;
            i++;
        }

        return result;
    }
}
