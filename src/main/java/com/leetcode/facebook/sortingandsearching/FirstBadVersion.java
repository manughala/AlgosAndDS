package com.leetcode.facebook.sortingandsearching;

/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

 Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

 You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

 Example:

 Given n = 5, and version = 4 is the first bad version.

 call isBadVersion(3) -> false
 call isBadVersion(5) -> true
 call isBadVersion(4) -> true

 Then 4 is the first bad version.

 * @author Santosh Manughala (SM030146).
 */
public class FirstBadVersion {

    public static void main(String args[]) {
        System.out.println(firstBadVersionBruteForce(2));
        System.out.println(firstBadVersionBestCase(2));

    }

    private static int firstBadVersionBruteForce(int n) {
        for(int i = 1; i <= n; i++) {
            if(isBadVersion(i)) {
                return i;
            }
        }

        return -1;
    }

    private static int firstBadVersionBestCase(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return isBadVersion(left) ? left : -1;
    }

    private static int binarySearch(int[] nums, int k) {
        int start = 0, mid = 0, end = nums.length;

        while (start <= end) {
            mid = start + end / 2;
            if(nums[mid] == k) {
                return mid;
            } else if (nums[mid] > k) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return  -1;
    }

    private static int binarySearchRecur(int[] nums, int k, int start, int end) {
        if(start <= end) {
            int mid = (start + end) / 2;
            if(nums[mid] > k) {
                return binarySearchRecur(nums, k, mid + 1, end);
            } else if (nums[mid] < k) {
                return binarySearchRecur(nums, k, start,mid - 1);
            } else {
                return mid;
            }
        }
        return -1;
    }

    // 1 false 2 true 3 true 4 true 5 true

    private static boolean isBadVersion(int version) {
        if(version == 90 || version == 91 || version == 92 ||version == 93 ||version == 94 ||version == 95 ||version == 96 ||version == 97 ||version == 98 ||version == 99)
            return true;

        return false;
    }
}
