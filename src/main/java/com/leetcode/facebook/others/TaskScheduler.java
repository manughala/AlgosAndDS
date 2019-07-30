package com.leetcode.facebook.others;

import com.leetcode.facebook.sortingandsearching.MergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

/**Task Scheduler

 Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

 However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

 You need to return the least number of intervals the CPU will take to finish all the given tasks.



 Example:

 Input: tasks = ["A","A","A","B","B","B"], n = 2
 Output: 8
 Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.


 Note:

 The number of tasks is in the range [1, 10000].
 The integer n is in the range [0, 100].

 * @author Santosh Manughala (SM030146).
 */
public class TaskScheduler {

    public static void main(String args[]) {
//        char[] tasks = new char[] {'A', 'A', 'B', 'D', 'C', 'A', 'A', 'B', 'A', 'D', 'C'};
        char[] tasks = new char[] {'A', 'A', 'A', 'B', 'B', 'B'};
        System.out.println(leastIntervalBruteForce(tasks, 2));
        System.out.println(leastIntervalBestCaseI(tasks, 2));
        System.out.println(leastIntervalBestCaseII(tasks, 2));
    }

    // NOTE In this case, we concentrate on getting the idle slots needed.
    // For that, we first sort the array with no. of instances. then the idle slots will be the max - 1 -> if we
    // have 4 As and n = 2, the idle slots needed will be  3 * 2 = 6 between these A's
    // From there, we try to insert next lower values and get the idle slots we can have in between.
    // Finally we add it to the tasks length as we just got the idle slots without the tasks.
    // Time: O(n) -> the number of tasks we iterate over once.
    // Space: O(1) -> constant space to store 26 chars
    private static int leastIntervalBestCaseII(char[] tasks, int n) {
        int[] map = new int[26];
        for(char c : tasks) {
            map[c-'A']++;
        }

        Arrays.sort(map);
        int maxVal = map[25] - 1, idleSlots = maxVal * n;

        for(int i = 24; i >= 0 && map[i] > 0; i--) {
            idleSlots -= Math.min(map[i], maxVal);
        }

        return idleSlots > 0 ? idleSlots + tasks.length : tasks.length;
    }

    // Time: O(intervals) -> it wont increase with the number of tasks, so it will be the number of intervals.
    // We iterate over tasks array only once. (O(n)).Sorting tasks array of length n takes O(26log(26))= O(1) time.
    // After this, number of iterations will be number of intervals.
    // Space: O(1) -> reason: queue and temp size will not exceed O(26); with increase in number of tasks, we would never increase the space so constant
    private static int leastIntervalBestCaseI(char[] tasks, int n) {
        if(tasks == null || tasks.length == 0) {
            return 0;
        }

        int map[] = new int[26];

        for(int i = 0; i < tasks.length; i++) {
            map[tasks[i] - 'A']++;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for(int i : map) {
            if(i > 0) {
                queue.add(i);
            }
        }

        int intervals = 0;

        while(!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int i = 0;

            while(i <= n) {
                if(!queue.isEmpty()) {
                    if(queue.peek() > 1) {
                        temp.add(queue.poll() - 1);
                    } else {
                        queue.poll();
                    }
                }

                intervals++;

                if(queue.isEmpty() && temp.size() == 0) {
                    break;
                }
                i++;
            }

            for(int j : temp) {
                queue.add(j);
            }
        }

        return intervals;
    }

    // Time: O(intervals) -> it wont increase with the number of tasks, so it will be the number of intervals.
    // We iterate over tasks array only once. (O(n)).Sorting tasks array of length n takes O(26log(26))= O(1) time.
    // After this, number of iterations will be number of intervals.
    // Space: O(1) -> constant size array is used
    private static int leastIntervalBruteForce(char[] tasks, int n) {
        if(tasks == null || tasks.length == 0) {
            return 0;
        }

        int map[] = new int[26];

        for(int i = 0; i < tasks.length; i++) {
            map[tasks[i] - 'A']++;
        }

        Arrays.sort(map);
        int intervals = 0;

        while(map[25] > 0) {
            int i = 0;
            while(i <= n) {
                if(map[25] == 0) {
                    break;
                }

                if(i < 26 && map[25-i] > 0) {
                    map[25-i]--;
                }

                intervals++;
                i++;
            }
            Arrays.sort(map);
        }

        return intervals;
    }

}
