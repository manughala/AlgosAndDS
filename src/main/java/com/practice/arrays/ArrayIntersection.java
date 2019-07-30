package com.practice.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Given two arrays, write a function to compute their intersection.

 Input: nums1 = [1,2,2,1], nums2 = [2,2]
 Output: [2,2]

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
public class ArrayIntersection {

    public static void main(String[] args) {
        int[] nums1 = {1,2,2,1}; // 4,5,9
        int[] nums2 = {2,2}; // 4 4 8 9 9

        int[] intersection = getIntersectionBruteForce(nums1, nums2);
        System.out.println("The intersection is: \n");
        for(int i: intersection) {
            System.out.print(i);
        }

//        printIntersection(nums1, nums2, nums1.length, nums2.length);
    }


    // Prints intersection of arr1[0..m-1] and arr2[0..n-1]
    static void printIntersection(int arr1[], int arr2[], int m, int n)
    {
        // Before finding intersection, make sure arr1[0..m-1]
        // is smaller
        if (m > n)
        {
            int tempp[] = arr1;
            arr1 = arr2;
            arr2 = tempp;

            int temp = m;
            m = n;
            n = temp;
        }

        // Now arr1[] is smaller
        // Sort smaller array arr1[0..m-1]
        Arrays.sort(arr1);

        // Search every element of bigger array in smaller array
        // and print the element if found
        for (int i = 0; i < n; i++)
        {
            if (binarySearch(arr1, 0, m - 1, arr2[i]) != -1)
                System.out.print(arr2[i] + " ");
        }
    }

    // A recursive binary search function. It returns location of x in
    // given array arr[l..r] is present, otherwise -1
    static int binarySearch(int arr[], int l, int r, int x)
    {
        if (r >= l)
        {
            int mid = l + (r - l) / 2;

            // If the element is present at the middle itself
            if (arr[mid] == x)
                return mid;

            // If element is smaller than mid, then it can only
            // be present in left subarray
            if (arr[mid] > x)
                return binarySearch(arr, l, mid - 1, x);

            // Else the element can only be present in right subarray
            return binarySearch(arr, mid + 1, r, x);
        }

        // We reach here when element is not present in array
        return -1;
    }



    private static int[] getIntersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1); // 1,1,2,2
        Arrays.sort(nums2); // 2,2

        int i= 0, j= 0;
        int nums1Lenght = nums1.length;
        int nums2Lenght = nums2.length;
        List<Integer> intersectionList = new ArrayList<> ();

        while(i<nums1Lenght && j < nums2Lenght) {
            if(nums1[i] > nums2[j]) {
                j++;
            } else if(nums1[i] < nums2[j]) {
                i++;
            } else {
                intersectionList.add(nums1[i]);

                i++;
                j++;
            }
        }

        int[] intersection = new int[intersectionList.size()];
        for(int k= 0; k<intersectionList.size(); k++) {
            intersection[k] = intersectionList.get(k);
        }

        return intersection;
    }

    private static int[] getIntersectionBruteForce(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> nums1KeyToCount = new HashMap<>(nums1.length);

        for(int i : nums1) {
            if (nums1KeyToCount.containsKey(i)) {
                nums1KeyToCount.put(i, nums1KeyToCount.get(i) + 1);
            } else {
                nums1KeyToCount.put(i, 1);
            }
        }

        List<Integer> intersectionList = new ArrayList<> ();

        for(int i : nums2) {
            if (nums1KeyToCount.containsKey(i)) {
                if (nums1KeyToCount.get(i) > 1) {
                    nums1KeyToCount.put(i, nums1KeyToCount.get(i) - 1);
                } else {
                    nums1KeyToCount.remove(i);
                }
                intersectionList.add(i);
            }
        }

        int[] intersection = new int[intersectionList.size()];
        for(int i= 0; i<intersectionList.size(); i++) {
            intersection[i] = intersectionList.get(i);
        }

        return intersection;
    }
}
