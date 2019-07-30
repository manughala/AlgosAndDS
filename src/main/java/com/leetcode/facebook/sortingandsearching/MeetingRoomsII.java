package com.leetcode.facebook.sortingandsearching;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.

 Example 1:

 Input: [[0, 30],[5, 10],[15, 20]]
 Output: 2
 Example 2:

 Input: [[7,10],[2,4]]
 Output: 1

 * @author Santosh Manughala (SM030146).
 */
public class MeetingRoomsII {
    public static class Interval {
        int start;
        int end;
        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main (String args[]) {
        Interval interval1 = new Interval();
//        Interval interval2 = new Interval(2, 7);
//        Interval interval3 = new Interval(3, 19);
//        Interval interval4 = new Interval(8, 12);

        Interval[] intervals = new Interval[] {interval1};

        System.out.println(minMeetingRoomsMinHeap(intervals));
//        System.out.println(minMeetingRoomsBestCase(intervals));
    }

    // Time: nlogn space: n
    private static int minMeetingRoomsBestCase (Interval[] intervals) {
        if(intervals == null) {
            return 0;
        }

        if(intervals.length == 1) {
            return 1;
        }

        int[] startTimes = new int[intervals.length];
        int[] endTimes = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            startTimes[i] = intervals[i].start;
            endTimes[i] = intervals[i].end;
        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int startIdx = 0;
        int endIdx = 0;

        int minRooms = 0;

        while (startIdx < startTimes.length) {
            if(startTimes[startIdx] < endTimes[endIdx]) {
                minRooms++;
            } else {
                endIdx++;
            }
            startIdx++;
        }

        return minRooms;
    }

    // Time: nlogn space n
    private static int minMeetingRoomsMinHeap (Interval[] intervals) {
        if(intervals==null || intervals.length==0){
            return 0;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(intervals.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });

        Comparator<Interval> com = new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        };

        Arrays.sort(intervals, com);

        queue.add(intervals[0].end);
        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i].start >= queue.peek()) {
                queue.poll();
            }

            queue.add(intervals[i].end);
        }

        return queue.size();
    }
}
