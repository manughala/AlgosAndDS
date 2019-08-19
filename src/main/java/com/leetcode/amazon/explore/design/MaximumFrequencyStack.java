package com.leetcode.amazon.explore.design;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
/**
 * Implement FreqStack, a class which simulates the operation of a stack-like data structure.

 FreqStack has two functions:

 push(int x), which pushes an integer x onto the stack.
 pop(), which removes and returns the most frequent element in the stack.
 If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.


 Example 1:

 Input:
 ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
 [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
 Output: [null,null,null,null,null,null,null,5,7,5,4]
 Explanation:
 After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:

 pop() -> returns 5, as 5 is the most frequent.
 The stack becomes [5,7,5,7,4].

 pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
 The stack becomes [5,7,5,4].

 pop() -> returns 5.
 The stack becomes [5,7,4].

 pop() -> returns 4.
 The stack becomes [5,7].


 Note:

 Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
 It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
 The total number of FreqStack.push calls will not exceed 10000 in a single test case.
 The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
 The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.

 * @author Santosh Manughala (SM030146).
 */
public class MaximumFrequencyStack {

    public static void main(String args[]) {
        MaximumFrequencyStack stack = new MaximumFrequencyStack();
        stack.push(5);
        stack.push(7);
        stack.push(5);
        stack.push(7);
        stack.push(4);
        stack.push(5);
        stack.push(5);
        System.out.println("pop 5: " + stack.pop());
        System.out.println("pop 5: " + stack.pop());
        System.out.println("pop 7: " + stack.pop());
        System.out.println("pop 5: " + stack.pop());
        System.out.println("pop 4: " + stack.pop());
        System.out.println("pop 7: " + stack.pop());
        System.out.println("pop 5: " + stack.pop());
    }

    Map<Integer, Stack<Integer>> freqToStack;
    Map<Integer, Integer> valueToFreq;
    int maxFreq;

    MaximumFrequencyStack() {
        freqToStack = new HashMap<>();
        valueToFreq = new HashMap<>();
        maxFreq = 0;
    }

    // Time: O(1)
    // space: O(n)
    public void push(int x) {
        int freq = valueToFreq.getOrDefault(x, 0) + 1;

        maxFreq = Math.max(freq, maxFreq);
        valueToFreq.put(x, freq);

        if(freqToStack.get(freq) == null) {
            freqToStack.put(freq, new Stack<>());
        }

        freqToStack.get(freq).push(x);
    }

    // Time: O(1)
    // space: O(n)
    public int pop() {
        int x = freqToStack.get(maxFreq).pop();
        valueToFreq.put(x, valueToFreq.get(x) - 1);
        if(freqToStack.get(maxFreq).size() == 0) {
            maxFreq--;
        }

        return x;
    }


}
