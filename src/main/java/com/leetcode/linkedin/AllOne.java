package com.leetcode.linkedin;

/**
 * Implement a data structure supporting the following operations:

 Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
 Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
 GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
 GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
 Challenge: Perform all these in O(1) time complexity.

 * @author Santosh Manughala (SM030146).
 */
public class AllOne {

    public static void main(String args[]) {
        AllOne allOne = new AllOne();
        allOne.inc("santosh");
        allOne.inc("santosh");
        allOne.inc("santosh");
        allOne.inc("santosh");
        allOne.inc("manughala");
        allOne.dec("santosh");
        allOne.inc("manughala");
        allOne.inc("kumar");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
        allOne.inc("santosh");
        allOne.inc("kumar");
        allOne.dec("manughala");
        allOne.dec("manughala");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
        allOne.inc("manughala");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
    }
    /** Initialize your data structure here. */
    public AllOne() {

    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {

    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {

    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return "";
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return "";
    }
}
