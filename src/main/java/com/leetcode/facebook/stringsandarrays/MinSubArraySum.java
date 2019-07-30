package com.leetcode.facebook.stringsandarrays;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

 Example:

 Input: s = 7, nums = [2,3,1,2,4,3]
 Output: 2
 Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 Follow up:
 If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).

 * @author Santosh Manughala (SM030146).
 */
public class MinSubArraySum {

    // TODO: do we have to account for negative numbersL https://www.geeksforgeeks.org/find-subarray-with-given-sum-in-array-of-integers/
    public static void main(String args[]) {
        int[] nums = new int[]{2,3,1,2,4,3};
        // 2, 5, 6, 8, 12, 15
        int sum = 7;

        if(nums == null || nums.length < 1) {
            throw new IllegalArgumentException("error");
        }

        if(nums.length == 1 && nums[0] == sum) {
            System.out.println("The min subarray length for the sum is: " + 1);
        }

        int result = minSubArraySumLengthBruteForce(nums, sum);
//        int result = minSubArraySumLengthBestCase(nums, sum);
//        int result = minSubArraySumLengthAverageCase(nums, sum); // FIXME: does not work
        System.out.println("The min subarray length for the sum is: " + result);

    }

    // Time complexity: O(n^2)
    private static int minSubArraySumLengthBruteForce(int[] nums, int sum) {
        int i = 0;
        int minLength = nums.length;
        boolean minFound = false;

        while(i<nums.length) {
            int count = 0;
            for (int j = i; j < nums.length; j++) {
                count += nums[j];
                if (count >= sum && minLength > j-i+1) {
                    minLength = j-i+1; // since j is index, add 1 to get the length
                    minFound = true;
                }
            }
            i++;
        }

        if(!minFound) {
            return 0;
        }

        return minLength;
    }

    //2,3,1,2,4,3
    //Time complexity: O(n) - best one
    private static int minSubArraySumLengthBestCase(int[] nums, int sum) {
        int length = nums.length, start = 0, end = 0, minLength = length + 1, curSum = 0;
        boolean minFound = false;

        while (end < length) {
            // TODO: I think yes, but I added this, check if we need this, it works for all positive mostly.
            if(nums[end] >= sum) {
                return 1;
            }

            while (curSum < sum && end < length) {
                curSum += nums[end++];
            }

            while(curSum >= sum && start < length){
                minFound = true;
                if(end - start < minLength) {
                    minLength = end-start;
                }

                curSum -= nums[start++];
            }
        }

        return minFound ? minLength : 0;
    }

    // Time complexity: O(nlogn)
    // TODO: this is not working, first insert to sums array with all the sums, then search for the result using binary search
    // The inserting isn O(n) while the search is logn, so O(nlogn)
    // https://leetcode.com/articles/minimum-size-subarray-sum/
    // http://www.cnblogs.com/grandyang/p/4501934.html
    // 2,3,1,2,4,3
    private static int minSubArraySumLengthAverageCase (int[] nums, int sum) {
        int length = nums.length, result = length + 1;
        int[] sums = new int[length+1];

        for(int i = 1; i < length + 1; ++i) {
            sums[i] = nums[i-1] + sums[i-1];
        }

        for(int i = 0; i < length + 1; ++i) {
            int right = searchRight(i + 1, length, sums[i] + sum, sums);
            if(right == length + 1) {
                break;
            }
            if(result > right - i) {
                result = right - i;
            }
        }


        return result == length + 1 ? 0 : result;
    }
    private static int searchRight(int left, int right, int key, int[] sums) {
        while (left < right) {
            int mid  = (left + right) / 2;
            if(sums[mid] >= key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


}
