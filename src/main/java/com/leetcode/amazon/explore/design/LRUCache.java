package com.leetcode.amazon.explore.design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 put(key, value) - Set or insert the value if the key is not already present.
 When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

 The cache is initialized with a positive capacity.

 Follow up:
 Could you do both operations in O(1) time complexity?

 Example:

 LRUCache cache = new LRUCache( 2 ); -> 2 is the capacity

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

    public static void main(String[] args) {
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

    int capacity;
    Map<Integer, ListNode> map;
    ListNode head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
           ListNode node = map.get(key);
           node.value = value;

           removeNode(node);
           setHead(node);
        } else {
            if(map.size() >= capacity) {
                map.remove(tail.key);
                removeNode(tail);
            }

            ListNode node = new ListNode(key, value);
            map.put(key, node);
            setHead(node);
        }
    }

    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }

        removeNode(map.get(key));
        setHead(map.get(key));

        return map.get(key).value;
    }

    private void removeNode(ListNode node) {
        if(node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if(node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
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

class ListNode {
    int key, value;
    ListNode prev, next;

    ListNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
