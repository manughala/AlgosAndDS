package com.leetcode.linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Design a data structure that supports all following operations in average O(1) time.

 insert(val): Inserts an item val to the set if not already present.
 remove(val): Removes an item val from the set if present.
 getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 Example:

 // Init an empty set.
 RandomizedSet randomSet = new RandomizedSet();

 // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 randomSet.insert(1);

 // Returns false as 2 does not exist in the set.
 randomSet.remove(2);

 // Inserts 2 to the set, returns true. Set now contains [1,2].
 randomSet.insert(2);

 // getRandom should return either 1 or 2 randomly.
 randomSet.getRandom();

 // Removes 1 from the set, returns true. Set now contains [2].
 randomSet.remove(1);

 // 2 was already in the set, so return false.
 randomSet.insert(2);

 // Since 2 is the only number in the set, getRandom always return 2.
 randomSet.getRandom();

 * @author Santosh Manughala (SM030146).
 */
public class InsertDeleteGetRandom {

    public static void main(String args[]) {
        InsertDeleteGetRandom randomSet = new InsertDeleteGetRandom();
        System.out.println(randomSet.insert(1));
        System.out.println(randomSet.remove(2));
        System.out.println(randomSet.insert(2));
        System.out.println(randomSet.getRandom());
        System.out.println(randomSet.remove(1));
        System.out.println(randomSet.insert(2));
        System.out.println(randomSet.getRandom());
    }

    List<Integer> items;
    Map<Integer, Integer> itemsToIndex;
    Random random;


    /** Initialize your data structure here. */
    public InsertDeleteGetRandom() {
        items = new ArrayList<>();
        itemsToIndex = new HashMap<>();
        random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(itemsToIndex.get(val) != null) {
            return false;
        }

        itemsToIndex.put(val, items.size());
        items.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(itemsToIndex.get(val) == null) {
            return false;
        }

        int indexToRemove = itemsToIndex.get(val), lastIndex = items.size() - 1;

        items.set(indexToRemove, items.get(lastIndex));
        itemsToIndex.put(items.get(lastIndex), indexToRemove);

        items.remove(lastIndex);
        itemsToIndex.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return items.get(random.nextInt(items.size()));
    }
}
