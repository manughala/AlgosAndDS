package com.leetcode.amazon.explore.treesandgraphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.

 Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

 Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 Example 1:

 Input: 2, [[1,0]]
 Output: true
 Explanation: There are a total of 2 courses to take.
 To take course 1 you should have finished course 0. So it is possible.
 Example 2:

 Input: 2, [[1,0],[0,1]]
 Output: false
 Explanation: There are a total of 2 courses to take.
 To take course 1 you should have finished course 0, and to take course 0 you should
 also have finished course 1. So it is impossible.
 Note:

 The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 You may assume that there are no duplicate edges in the input prerequisites.


 * @author Santosh Manughala (SM030146).
 */
public class CourseSchedule {
    public static void main(String args[]) {
        int[][] courses1 = new int[1][2];
        courses1[0][0] = 1;
        courses1[0][1] = 0;
        System.out.println("canFinishBFS Expected: true, actual: " + canFinishBFS(2, courses1));

        int[][] courses2 = new int[2][2];
        courses2[0][0] = 1;
        courses2[0][1] = 0;
        courses2[1][0] = 0;
        courses2[1][1] = 1;
        System.out.println("canFinishBFS Expected: false, actual: " + canFinishBFS(2, courses2));



        int[][] courses3 = new int[1][2];
        courses3[0][0] = 1;
        courses3[0][1] = 0;
        System.out.println("canFinishDFS Expected: true, actual: " + canFinishDFS(2, courses3));

        int[][] courses4 = new int[2][2];
        courses4[0][0] = 1;
        courses4[0][1] = 0;
        courses4[1][0] = 0;
        courses4[1][1] = 1;
        System.out.println("canFinishDFS Expected: false, actual: " + canFinishDFS(2, courses4));
    }

    private static boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        int[] pCounter = new int[numCourses];

        for(int i = 0; i < prerequisites.length; i++) {
            pCounter[prerequisites[i][0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) {
            if(pCounter[i] == 0) {
                queue.add(i);
            }
        }

        int coursesNoPrereq = queue.size();

        while (!queue.isEmpty()) {
            int currCourse = queue.poll();
            for(int[] course : prerequisites) {
                if(course[1] == currCourse) {
                    pCounter[course[0]]--;
                    if(pCounter[course[0]] == 0) {
                        queue.add(course[0]);
                        coursesNoPrereq++;
                    }
                }
            }
        }

        return coursesNoPrereq == numCourses;
    }


    private static boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        int[] visited = new int[numCourses];

        Map<Integer, List<Integer>> courseToPreReqs = new HashMap<>();
        for(int[] course : prerequisites) {
            List<Integer> preReqs = courseToPreReqs.getOrDefault(course[1], new ArrayList<>());
            preReqs.add(course[0]);
            courseToPreReqs.put(course[1], preReqs);
        }

        for(int i = 0; i < numCourses; i++) {
            if(!canFinishDFSRecur(visited, courseToPreReqs, i)) {
                return false;
            }
        }

        return true;
    }

    private static boolean canFinishDFSRecur(int[] visited, Map<Integer, List<Integer>> courseToPreReqs, int course) {
        if(visited[course] == -1) {
            return false;
        }

        if(visited[course] == 1) {
            return true;
        }

        visited[course] = -1;

        if(courseToPreReqs.containsKey(course)) {
            for(int preReq : courseToPreReqs.get(course)) {
                if(!canFinishDFSRecur(visited, courseToPreReqs, preReq)) {
                    return false;
                }
            }
        }

        visited[course] = 1;
        return true;
    }
}
