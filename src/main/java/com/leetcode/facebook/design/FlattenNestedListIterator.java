package com.leetcode.facebook.design;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Flatten Nested List Iterator

 Given a nested list of integers, implement an iterator to flatten it.

 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Example 1:

 Input: [[1,1],2,[1,1]]
 Output: [1,1,2,1,1]
 Explanation: By calling next repeatedly until hasNext returns false,
 the order of elements returned by next should be: [1,1,2,1,1].
 Example 2:

 Input: [1,[4,[6]]]
 Output: [1,4,6]
 Explanation: By calling next repeatedly until hasNext returns false,
 the order of elements returned by next should be: [1,4,6].

 * @author Santosh Manughala (SM030146).
 */
public class FlattenNestedListIterator implements Iterator<Integer> {

    // NestedInteger Provided
    //
    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }



    // NOTE CANNOT RUN here as we dont have impl for nested integer.

    // WE CAN ALSO DO LAZY LOADING WHERE YOU CAN DUMP EVERYTHING IN A STACK/QUEUE IN CONSTRUCTOR AND in HASNEXT METHOD WE CAN POPULATE PERFORM LOADING

    /* ITERATIVE WAY OF DOING THINGS: NOTE THAT THE QUEUE IS UPDATED UPFRONT IN THE CONSTRUCTOR */
    static Queue<Integer> queue = new ArrayDeque<Integer>();
    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
        Queue<NestedInteger> q = new LinkedList<>();

        for(NestedInteger nestedInteger : nestedList) {
            q.add(nestedInteger);
        }

        while(!q.isEmpty()) {
            NestedInteger n = q.poll();
            if(n.isInteger()) {
                queue.add(n.getInteger());
            } else {
                for(NestedInteger nestedInteger : n.getList()) {
                    q.add(nestedInteger);
                }
            }
        }
    }

    @Override
    public Integer next() {
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }


//    /* RECURSIVE WAY OF DOING THINGS: NOTE THAT THE QUEUE IS UPDATED UPFRONT IN THE CONSTRUCTOR*/
//    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
//        flattenNestedListRecur(nestedList);
//    }
//
//    @Override
//    public Integer next() {
//        return queue.poll();
//    }
//
//    @Override
//    public boolean hasNext() {
//        return !queue.isEmpty();
//    }
//
//    static Queue<Integer> queue = new ArrayDeque<Integer>();
//    private static void flattenNestedListRecur(List<NestedInteger> nestedList) {
//        for (NestedInteger nestedInteger : nestedList) {
//            if(nestedInteger.isInteger()) {
//                queue.add(nestedInteger.getInteger());
//            } else {
//                flattenNestedListRecur(nestedInteger.getList());
//            }
//        }
//    }

}
