package com.leetcode.linkedin;

import java.util.Stack;

/**
 * Design a max stack that supports push, pop, top, peekMax and popMax.

 push(x) -- Push element x onto stack.
 pop() -- Remove the element on top of the stack and return it.
 top() -- Get the element on the top.
 peekMax() -- Retrieve the maximum element in the stack.
 popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
 Example 1:
 MaxStack stack = new MaxStack();
 stack.push(5);
 stack.push(1);
 stack.push(5);
 stack.top(); -> 5
 stack.popMax(); -> 5
 stack.top(); -> 1
 stack.peekMax(); -> 5
 stack.pop(); -> 1
 stack.top(); -> 5
 Note:
 -1e7 <= x <= 1e7
 Number of operations won't exceed 10000.
 The last four operations won't be called when stack is empty.

 * @author Santosh Manughala (SM030146).
 */
public class MaxStack {
    private Stack<Integer> stack;
    private Stack<Integer> maxStack;

    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(int x) {
        int max = maxStack.isEmpty() ? x : maxStack.peek();
        maxStack.push(max > x ? max : x);
        stack.push(x);
    }

    public int pop() {
        maxStack.pop();
        return stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }

    public int popMax() {
        int max = peekMax();
        Stack<Integer> cache = new Stack<>();
        while(max != top()) {
            cache.push(pop());
        }

        pop();

        while (!cache.isEmpty()) {
            push(cache.pop());
        }

        return max;
    }

//    private class Stack {
//        int top, capacity;
//        int[] array;
//
//        Stack(int size) {
//            array = new int[size];
//            capacity = size;
//            top = -1;
//        }
//
//        public void push(int x) {
//            if(isFull()) {
//                System.out.println("cannot add items");
//                return;
//            }
//
//            array[++top] = x;
//        }
//
//        public int pop() {
//            if(isEmpty()) {
//                System.out.println("cannot delete items");
////                System.exit(1);
//                return -1;// return -1 or just System.exit(1)
//            }
//            return array[top--];
//        }
//
//        public int top() {
//            return array[top];
//        }
//
//        public boolean isEmpty() {
//            return top == -1;
//        }
//
//        public boolean isFull() {
//            return top + 1 == capacity;
//        }
//
//        public int size() {
//            return top + 1;
//        }
//    }
}


