package com.leetcode.facebook.others;

import java.util.Arrays;
import java.util.Stack;

/**
 * Find the Celebrity

 Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

 Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

 You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.



 Example 1:


 Input: graph = [
 [1,1,0],
 [0,1,0],
 [1,1,1]
 ]
 Output: 1
 Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j, otherwise graph[i][j] = 0 means person i does not know person j. The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.
 Example 2:


 Input: graph = [
 [1,0,1],
 [1,1,0],
 [0,1,1]
 ]
 Output: -1
 Explanation: There is no celebrity.


 Note:

 The directed graph is represented as an adjacency matrix, which is an n x n matrix where a[i][j] = 1 means person i knows person j while a[i][j] = 0 means the contrary.
 Remember that you won't have direct access to the adjacency matrix.
 * @author Santosh Manughala (SM030146).
 */
public class FindCelebrity {

    //NOTE: one more brute force way of doing things-> using graph inbound and outnbound: http://buttercola.blogspot.com/2015/09/leetcode-find-celebrity.html

    public static void main(String args[]) {
        System.out.println(findCelebrityBruteForceI(3));
        System.out.println(findCelebrityBestCaseI(3));
        System.out.println(findCelebrityBestCaseII(3));
        System.out.println(findCelebrityBestCaseIII(3));
    }

    // Time O(n^2)
    // Space: O(1)
    private static int findCelebrityBruteForceI(int n) {
        int i, j;
        for(i = 0; i < n; i++) {
            for(j = 0; j < n; j++) {
                if((knows(i, j) || !knows(j, i)) && i != j) {
                    break;
                }
            }
            if(j == n) {
                return i;
            }
        }


        return -1;
    }

    // Time O(n)
    // Space: O(1)
    private static int findCelebrityBestCaseIII(int n) {

        // Find that one person who knows most of the people
        int result = 0;
        for(int i = 0; i < n; i++) {
            if(knows(result, i)) {
                result = i;
            }
        }

        // now make sure he is the one
        for (int i = 0; i < n; i++) {
            if(i != result && (knows(result, i) || !knows(i, result))) {
                return -1;
            }
        }

        return result;
    }

    // Time O(n)
    // Space: O(1)
    private static int findCelebrityBestCaseII(int n) {

        int i = 0, j = n - 1;
        while (i < j) {
            if(knows(i, j)) {
                i++;
            } else {
                j--;
            }
        }

        for (int k = 0; k < n; k++) {
            if(i != k && (knows(i, k) || !knows(k, i))) {
                return -1;
            }
        }

        return i;
    }

    // Time O(n)
    // Space: O(n)
    private static int findCelebrityBestCaseI(int n) {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n; i++) {
            stack.push(i);
        }

        while(stack.size() > 1) {
            int i = stack.pop(), j = stack.pop();

            if(knows(i, j)) {
                stack.push(j);
            } else {
                stack.push(i);
            }
        }

        int i = stack.pop();
        for (int j = 0; j < n; j++) {
            if(i != j && (knows(i, j) || !knows(j, i))) {
                return -1;
            }
        }

        return i;
    }


    // Method given to us in leetcode
    private static boolean knows(int a, int b) {
        // trying to recreate the Example 1 case in the given
        if(a == b || (a == 0 && b == 1) || (a == 2 && b == 0) || (a == 2 && b == 1)) {
            return true;
        }
        return false;
    }

}
