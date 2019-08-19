package com.leetcode.amazon.explore.design;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 isFull() - return true if the stack reached the capacity
 isEmpty() - return true if the stack is empty

 Example:

 Stack stack = new Stack();
 stack.push(-2);
 stack.push(0);
 stack.push(2);
 stack.push(-3);
 stack.top();   --> Returns -3.
 stack.pop();
 stack.top();      --> Returns 2.
 stack.top();      --> Returns 0.
 stack.top();   --> Returns -2.

 * @author Santosh Manughala (SM030146).
 */
public class Stack {
    public static void main(String args[]) {
        Stack stack = new Stack(4);
        System.out.println("push 5 " + stack.push(5));
        System.out.println("push 2 " + stack.push(2));
        System.out.println("push 2 " + stack.push(2));
        System.out.println("top = 2, actual: " + stack.top());
        System.out.println("push 6 " + stack.push(6));
        System.out.println("push 3 " + stack.push(3));
        System.out.println("pop 6 " + stack.pop());
        System.out.println("top: 2 " + stack.top());
        System.out.println("pop 2 " + stack.pop());
        System.out.println("top: 2 " + stack.top());
        System.out.println("isFull? false, actual: " + stack.isFull());
        System.out.println("isEmpty? false, actual: " + stack.isEmpty());
        System.out.println("pop: 2 " + stack.pop());
        System.out.println("pop: 5 " + stack.pop());
        System.out.println("top: -1 " + stack.top());
        System.out.println("isFull? false, actual: " + stack.isFull());
        System.out.println("isEmpty? true, actual: " + stack.isEmpty());
    }

    int capacity, top = -1;
    int[] elements;

    public Stack(int capacity) {
        this.capacity = capacity;
        this.elements = new int[capacity];
    }

    public boolean push(int x) {
        if(isFull()) {
            System.out.println("Cannot add more items, capacity full!");
            return false;
        }

        elements[++top] = x;
        return true;
    }

    public boolean pop() {
        if(isEmpty()) {
            System.out.println("Cannot remove items when empty!");
            return false;
        }

        int x = elements[top--];
        return true;
    }

    public int top() {
        return isEmpty() ? -1 : elements[top];
    }

    public boolean isFull() {
        return (top + 1) == capacity;
    }

    public boolean isEmpty() {
        return top == -1;
    }
}
