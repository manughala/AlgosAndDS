package com.leetcode.linkedin;

/**
 * Design a HashMap without using any built-in hash table libraries.

 To be specific, your design should include these functions:

 put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
 get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.

 Example:

 MyHashMap hashMap = new MyHashMap();
 hashMap.put(1, 1);
 hashMap.put(2, 2);
 hashMap.get(1);            // returns 1
 hashMap.get(3);            // returns -1 (not found)
 hashMap.put(2, 1);          // update the existing value
 hashMap.get(2);            // returns 1
 hashMap.remove(2);          // remove the mapping for 2
 hashMap.get(2);            // returns -1 (not found)

 Note:

 All keys and values will be in the range of [0, 1000000].
 The number of operations will be in the range of [1, 10000].
 Please do not use the built-in HashMap library.

 * @author Santosh Manughala (SM030146).
 */
public class MyHashMap {
    public static void main(String args[]) {
        MyHashMap hashMap = new MyHashMap();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        System.out.println(hashMap.get(1));
        System.out.println(hashMap.get(3));
        hashMap.put(2, 4);
        System.out.println(hashMap.get(2));
        hashMap.remove(2);
        System.out.println(hashMap.get(2));
    }

    private class ListNode {
        int key, value;
        ListNode next;
        ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    ListNode[] nodes;
    int capacity;

    /** Initialize your data structure here. */
    public MyHashMap() {
        capacity = 10000;
        nodes = new ListNode[capacity];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = hash(key);
        ListNode current = nodes[index], previous = null, newNode = new ListNode(key, value);
        boolean overridden = false;

        while(current != null) {
            if(current.key == key) {
                current.value = value;
                overridden = true;
                break;
            }

            previous = current;
            current = current.next;
        }

        if(!overridden) {
            if (previous == null) {
                nodes[index] = newNode;
            } else {
                previous.next = newNode;
            }
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = hash(key);
        ListNode current = nodes[index];
        while(current != null) {
            if(current.key == key) {
                return current.value;
            }

            current = current.next;
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = hash(key);
        ListNode current = nodes[index], previous = null;

        while(current != null) {
            if(current.key == key) {
                    if(previous == null) {
                        nodes[index] = current.next;
                    } else {
                        previous.next = current.next;
                    }
                break;
            }

            previous = current;
            current = current.next;
        }
    }

    private int hash(int key) {
        return key % capacity;
    }
}
