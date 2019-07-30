package com.leetcode.facebook.stringsandarrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Given two arrays, write a function to compute their intersection.

 Example 1:

 Input: nums1 = [1,2,2,1], nums2 = [2,2]
 Output: [2,2]

 Example 2:

 Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 Output: [4,9]
 Note:

 Each element in the result should appear as many times as it shows in both arrays.
 The result can be in any order.
 Follow up:

 What if the given array is already sorted? How would you optimize your algorithm?
 What if nums1's size is small compared to nums2's size? Which algorithm is better?
 What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

 * @author Santosh Manughala (SM030146).
 */
public class InteresectArrays {

    //TODO: read haash table hash map impl -> time complexities
    public static void main(String args[]) {//TODO handle edge cases
        int[] nums = intersect(new int[]{1,2,2,1}, new int[]{2,2});
//        int[] nums1 = intersect(null, null);

        for(int num : nums) {
            System.out.println(num);
        }
    }

    private static int[] intersect(int[] nums1, int nums2[]) {
        if(nums1 == null|| nums2 == null) {
            throw new IllegalArgumentException("null nums given");
        }

        if(nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> resultsList = new ArrayList();
        int i = 0, j = 0, k = 0;

        while(i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                resultsList.add(nums1[i++]);
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }

        int[] result = new int[resultsList.size()];
        for(int m : resultsList) {
            result[k++] = m;
        }

        return result;
    }

    private static int[] intersectBruteForce(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nums1Map = new HashMap();
        int size = nums1.length < nums2.length ? nums1.length : nums2.length;
        int[] result = new int[size];
        int i = 0;

        for(int num : nums1) {
            if(nums1Map.containsKey(num)) {
                nums1Map.put(num, nums1Map.get(num) + 1) ;
            } else {
                nums1Map.put(num, 1);
            }
        }

        for(int num : nums2) {
            if (nums1Map.containsKey(num)) {
                if (nums1Map.get(num) > 1) {
                    nums1Map.put(num, nums1Map.get(num) - 1);
                } else {
                    nums1Map.remove(num);
                }
            }
        }

        for(int num : nums1Map.keySet()) {
            Integer val = nums1Map.get(num);
            while(val>0) {
                result[i++] = num;
                val--;
            }
        }

        return result;
    }
}
