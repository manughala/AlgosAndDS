package com.leetcode.linkedin;

import java.util.Stack;

/**
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


