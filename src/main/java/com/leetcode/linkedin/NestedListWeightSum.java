package com.leetcode.linkedin;

import com.leetcode.facebook.design.FlattenNestedListIterator;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Example 1:

 Input: [[1,1],2,[1,1]]
 Output: 10
 Explanation: Four 1's at depth 2, one 2 at depth 1.
 Example 2:

 Input: [1,[4,[6]]]
 Output: 27
 Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27.

 * @author Santosh Manughala (SM030146).
 */

public class NestedListWeightSum {

    private static int depthSumRecur(List<FlattenNestedListIterator.NestedInteger> nestedList, int depth) {
        int sum = 0;
        for(FlattenNestedListIterator.NestedInteger nestedInteger : nestedList) {
            if(nestedInteger.isInteger()) {
                sum += depth * nestedInteger.getInteger();
            } else {
                sum += depthSumRecur(nestedInteger.getList(), depth + 1);
            }
        }
        return sum;
    }


    private static int depthSumIter(List<FlattenNestedListIterator.NestedInteger> nestedList) {
        Queue<FlattenNestedListIterator.NestedInteger> queue = new LinkedList<>();
        Queue<Integer> depthQueue = new LinkedList<>();
        int sum = 0;

        for(FlattenNestedListIterator.NestedInteger nestedInteger : nestedList) {
            queue.add(nestedInteger);
            depthQueue.add(1);
        }

        while(!queue.isEmpty()) {
            FlattenNestedListIterator.NestedInteger n = queue.poll();
            int depth = depthQueue.poll();

            if(n.isInteger()) {
                sum += depth * n.getInteger();
            } else {
                for(FlattenNestedListIterator.NestedInteger nestedInteger : n.getList()) {
                    queue.add(nestedInteger);
                    depthQueue.add(depth + 1);
                }
            }
        }

        return sum;
    }
}
