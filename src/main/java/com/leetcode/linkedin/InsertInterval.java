package com.leetcode.linkedin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

 You may assume that the intervals were initially sorted according to their start times.

 Example 1:

 Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 Output: [[1,5],[6,9]]
 Example 2:

 Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 Output: [[1,2],[3,10],[12,16]]
 Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

 NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

 * @author Santosh Manughala (SM030146).
 */
public class InsertInterval {
    public static void main(String args[]) {
        int[][] nums = new int[][] {
                {1,3},
                {6,9}
        };

//        int[][] result = mergeIntervalsAverageCase(new int[][]{}, new int[] {5,7});
        int[][] result = mergeIntervalsBestCase(nums, new int[] {2, 5});

        for(int[] i : result) {
            for(int j : i) {
                System.out.println(j);
            }
        }
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
    private static int[][] mergeIntervalsAverageCase (int[][] nums, int[] newInterval) {
        if(nums == null || nums.length <= 0) {
            return nums;
        }

        List<Interval> intervals = new ArrayList<>();

        if(nums.length != 0) {
            for(int[] num : nums) {
                intervals.add(new Interval(num[0], num[1]));
            }
        }

        intervals.add(new Interval(newInterval[0], newInterval[1]));

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

        int[][] result = new int[merged.size()][2]; // should hardcode rows to 2, cos we cannot depend on nums[0] -> nums can be empty.
        int i = 0;
        for(Interval interval : merged) {
            result[i][0] = interval.start;
            result[i][1] = interval.end;
            i++;
        }

        return result;
    }

    // TIme: O(n)
    // Space: O(1)
    private static int[][] mergeIntervalsBestCase(int[][] intervals, int[] newInterval) {
        if(intervals == null) {
            return null;
        }

        List<int[]> result = new ArrayList<>();

        int i = 0, start = newInterval[0], end = newInterval[1];

        while(i < intervals.length && start > intervals[i][1]) {
            result.add(intervals[i]);
            i++;
        }

        while(i < intervals.length && intervals[i][0] <= end) {
            start = Math.min(intervals[i][0], start);
            end = Math.max(intervals[i][1], end);
            i++;
        }

        result.add(new int[]{start, end});

        while(i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[0][]);
    }

}
