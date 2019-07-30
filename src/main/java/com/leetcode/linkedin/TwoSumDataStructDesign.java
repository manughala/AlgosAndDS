package com.leetcode.linkedin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design and implement a TwoSum class. It should support the following operations: add and find.

 add - Add the number to an internal data structure.
 find - Find if there exists any pair of numbers which sum is equal to the value.

 Example 1:
//-1,0,1,2,-1,-4
 add(1); add(3); add(5);
 find(4) -> true
 find(7) -> false
 Example 2:

 add(3); add(1); add(2);
 find(3) -> true
 find(6) -> false
 * @author Santosh Manughala (SM030146).
 */
public class TwoSumDataStructDesign {

    public static void main(String args[]) {
        TwoSumI twoSumI = new TwoSumI();
        twoSumI.add(-1);
        twoSumI.add(0);
        twoSumI.add(1);
        twoSumI.add(2);
        twoSumI.add(-1);
        twoSumI.add(-4);
        System.out.println(twoSumI.find(3));
        System.out.println(twoSumI.find(-6));

        TwoSumII twoSumII = new TwoSumII();
        twoSumII.add(1);
        twoSumII.add(5);
        twoSumII.add(3);
        System.out.println(twoSumII.find(4));
        System.out.println(twoSumII.find(7));
    }
}

class TwoSumI {

    List<Integer> nums;

    /**
     * Initialize your data structure here.
     */
    public TwoSumI() {
        nums = new ArrayList<>();
    }

    /**
     * Add the number to an internal data structure..
     */
    public void add(int number) {
        nums.add(number);
    }

    /**
     * Find if there exists any pair of numbers which sum is equal to the value.
     */
    // Time: O(n)
    //Space: O(n)
    public boolean find(int value) {
        for (int num : nums) {
            int complement = value - num;

            if (nums.contains(complement)) {
                return true;
            }
        }
        return false;
    }
}

//        Collections.sort(nums);
//
//        int i = 0, j = nums.size() - 1;
//
//        while(i < j) {
//            if(nums.get(i) + nums.get(j) == value) {
//                return true;
//            } else if(nums.get(i) + nums.get(j) > value) {
//                j--;
//            } else {
//                i++;
//            }
//        }
//
//        return false;
//    }
//}

class TwoSumII {
    Map<Integer, Integer> map;

    /** Initialize your data structure here. */
    public TwoSumII() {
        map = new HashMap<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    // Time: O(n)
    //Space: O(n)
    public boolean find(int value) {
        for(int i : map.keySet()) {
            if(value - i != i) {
                if(map.containsKey(value - 1)) {
                    return true;
                }
            } else if (map.get(i) >= 2) {
                return true;
            }
        }
        return false;
    }
}

