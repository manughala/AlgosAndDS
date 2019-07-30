package com.leetcode.linkedin;

/**
 * Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

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
public class FindTheCelebrity {

    // REFER com.leetcode.facebook.others.FindCelebrity for all the cases.
    public static void main(String args[]) {
        System.out.println(findCelebrity(3));
    }

    // Time O(n)
    // Space: O(1)
    private static int findCelebrity(int n) {

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

    // Method given to us in leetcode
    private static boolean knows(int a, int b) {
        // trying to recreate the Example 1 case in the given
        if(a == b || (a == 0 && b == 1) || (a == 2 && b == 0) || (a == 2 && b == 1)) {
            return true;
        }
        return false;
    }


}
