package com.leetcode.microsoft.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
 it should invalidate the least recently used item before inserting a new item.

 The cache is initialized with a positive capacity.

 Follow up:
 Could you do both operations in O(1) time complexity?

 Example:

 LRUCache cache = new LRUCache( 2 ); -- 2 is the capacity

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4

 * @author Santosh Manughala (SM030146).
 */
public class LRUCache {
    private Map<Character, Integer> map = new HashMap<Character, Integer>(){{
        put('a', 1);
        put('b', 2);
        put('c', 3);
        put('d', 4);
        put('e', 5);
        put('f', 6);
        put('g', 7);
        put('h', 8);
        put('i', 9);
        put('j', 10);
        put('k', 11);
        put('l', 12);
        put('m', 13);
        put('n', 14);
        put('o', 15);
        put('p', 16);
        put('q', 17);
        put('r', 18);
        put('s', 19);
        put('t', 20);
        put('u', 21);
        put('v', 22);
        put('w', 23);
        put('x', 24);
        put('y', 25);
        put('z', 26);
    }};

    public static void main(String args[]) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("Expected: 1, actual: " + cache.get(1));
        cache.put(3, 3);
        System.out.println("Expected: -1, actual: " + cache.get(2));
        cache.put(4, 4);
        System.out.println("Expected: -1, actual: " + cache.get(1));
        System.out.println("Expected: 3, actual: " + cache.get(3));
        System.out.println("Expected: 4, actual: " + cache.get(4));

    }

    private class ListNode {
        int key, val;
        ListNode next, prev;

        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private int capacity;
    private ListNode head, tail;
    private Map<Integer, ListNode> keyToValueMap;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.keyToValueMap = new HashMap<>();
    }

    // Time O(1)
    // Space O(n)
    public int get(int key) {
        if(!keyToValueMap.containsKey(key)) {
            return -1;
        }

        ListNode node = keyToValueMap.get(key);

        remove(node);
        setHead(node);

        return node.val;
    }

    // Time O(1)
    // Space O(n)
    public void put(int key, int val) {
        if(keyToValueMap.containsKey(key)) {
            ListNode node = keyToValueMap.get(key);
            node.val = val;

            remove(node);
            setHead(node);
        } else  {
            if(keyToValueMap.size() >= capacity) {
                keyToValueMap.remove(tail.key);
                remove(tail);
            }

            ListNode node = new ListNode(key, val);
            setHead(node);
            keyToValueMap.put(key, node);
        }
    }

    private void remove(ListNode node) {
        if(node.next == null) {
            tail = node.prev;
        } else {
            node.next.prev = node.prev;
        }

        if(node.prev == null) {
            head = node.next;
        } else {
            node.prev.next = node.next;
        }
    }

    private void setHead(ListNode node) {
        if(head != null) {
            head.prev = node;
        }

        node.next = head;
        node.prev = null;
        head = node;

        if(tail == null) {
            tail = head;
        }
    }
}
