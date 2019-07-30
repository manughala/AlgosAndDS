package com.leetcode.facebook.design;

import java.awt.*;
import java.util.HashMap;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

 Follow up:
 Could you do both operations in O(1) time complexity?

 Example:

 LRUCache cache = new LRUCache( 2 // capacity );

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

    HashMap<Integer, ListNode> map;
    int capacity;
    ListNode head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }

    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }

        ListNode node = map.get(key);
        remove(node);
        setHead(node);

        return node.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            ListNode node = map.get(key);
            node.value = value;

            remove(node);
            setHead(node);
        } else {
            if(capacity <= map.size()) {
                map.remove(tail.key);
                remove(tail);
            }

            ListNode node = new ListNode(key, value);
            setHead(node);
            map.put(key, node);
        }
    }

    private void remove(ListNode node) {
        if(node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

        if(node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
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

    public static void main(String args[]) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}

class ListNode {
    int value, key;
    ListNode prev, next;

    ListNode(int key, int value){
        this.key = key;
        this.value = value;
    }
}
