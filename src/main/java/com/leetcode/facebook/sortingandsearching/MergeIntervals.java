package com.leetcode.facebook.sortingandsearching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

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

 * @author Santosh Manughala (SM030146).
 */
public class MergeIntervals {

    public static class Interval {
        int start, end;
        Interval() {}
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String args[]) {
        Interval interval1 = new Interval(1, 5);
        Interval interval2 = new Interval(3, 6);
        Interval interval3 = new Interval(8, 9);

        List<Interval> intervals = new ArrayList<Interval>() {{
            add(interval1);
            add(interval2);
            add(interval3);
        }};


        mergeIntervalsAndCalculateLength(intervals);
        System.out.println(getLength());



//        List<Interval> result = mergeIntervalsBruteForce(intervals);
////        List<Interval> result = mergeIntervalsBestCase(intervals);
//
//        for(Interval interval : result) {
//            System.out.println("start: " + interval.start + " end: " + interval.end);
        }

//    }

    private static int length = 0;
    private static void mergeIntervalsAndCalculateLength(List<Interval> intervals) {
        if(intervals == null || intervals.size() == 0) {
            return;
        }

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });

        LinkedList<Interval> merged = new LinkedList<>();
        merged.add(intervals.get(0));

        for(int i = 1; i < intervals.size(); i++) {
            if(merged.getLast().end < intervals.get(i).start) {
                merged.add(intervals.get(i));
                length += merged.getLast().end - merged.getLast().start;
            } else {
                merged.getLast().end = Math.max(merged.getLast().end, intervals.get(i).end);
            }
        }

        length += merged.getLast().end - merged.getLast().start;

        return;
    }

    private static int getLength() {
        return length;
    }

    private static Map<Interval, List<Interval>> graphs;
    private static Map<Integer, List<Interval>> compToNodes;
    private static Set<Interval> visited;

    // Time: O(n^2) - goes through entire intervals twice to find the overlap
    // Space: O(n^2) - if we have overlapping for every interval, we will store the original and its link
    private static List<Interval> mergeIntervalsBruteForce(List<Interval> intervals) {
        if(intervals == null || intervals.size() == 0) {
            return null;
        }

        if(intervals.size() == 1) {
            return intervals;
        }

        buildGraphs(intervals);
        buildComponents(intervals);

        List<Interval> merged = new ArrayList<>();
        for(int component : compToNodes.keySet()) {
            List<Interval> intervalsOverlapping = compToNodes.get(component);

            int minStart = intervalsOverlapping.get(0).start;
            int maxEnd = intervalsOverlapping.get(0).end;

            for(Interval intervalOverlapping : intervalsOverlapping) {
                minStart = Math.min(minStart, intervalOverlapping.start);
                maxEnd = Math.max(maxEnd, intervalOverlapping.end);
            }

            merged.add(new Interval(minStart, maxEnd));
        }

        return merged;
    }

    private static void buildComponents(List<Interval> intervals) {
        compToNodes = new HashMap<>();
        visited = new HashSet<>();
        int component = 0;


        for(Interval interval : intervals) {
            if(!visited.contains(interval)) {
                buildComponents(component, interval);
                component++;
            }
        }
    }

    private static void buildComponents(int component, Interval interval) {
        Stack<Interval> stack = new Stack<>();

        stack.push(interval);

        while(!stack.isEmpty()) {
            Interval temp = stack.pop();
            if(!visited.contains(temp)) {
                visited.add(temp);

                if (compToNodes.get(component) == null) {
                    compToNodes.put(component, new ArrayList<>());
                }
                compToNodes.get(component).add(temp);

                if(graphs.get(temp) != null) {
                    for (Interval interval1 : graphs.get(temp)) {
                        stack.push(interval1);
                    }
                }
            }
        }
    }

    private static void buildGraphs(List<Interval> intervals) {
         graphs = new HashMap<>();

        for(Interval interval : intervals) {
            graphs.put(interval, new ArrayList<>());
        }

        for(Interval interval1 : intervals) {
            for (Interval interval2 : intervals) {
                if(interval1.end >= interval2.start && interval2.end >= interval1.start) {
                    graphs.get(interval1).add(interval2);
                    graphs.get(interval2).add(interval1);
                }
            }
        }
    }



    // Time: O(nlogn)
    // Space: O(1) (or O(n)-- If we can sort intervals in place, we do not need more than constant additional space.
    // Otherwise, we must allocate linear space to store a copy of intervals and sort that.
    private static List<Interval> mergeIntervalsBestCase(List<Interval> intervals) {
        if(intervals == null || intervals.size() == 0 ) {
            return null;
        }

        if(intervals.size() == 1) {
            return intervals;
        }

        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });


        // definetely need a linked list because we need to get the last item in the list of merged.
        // if we use i to get that, merged list might or might not even have that -> consider all the
        // 5 given intervals are overlapping, merge will only have 1 while i-1 will be at 4 or something
        LinkedList<Interval> merged = new LinkedList<>();
        for(int i = 0; i < intervals.size(); i++) {
            if(merged.isEmpty() || merged.getLast().end < intervals.get(i).start) {
                merged.add(intervals.get(i));
            } else {
                merged.getLast().end = Math.max(merged.getLast().end, intervals.get(i).end);
            }
        }

        return merged;
    }
}
