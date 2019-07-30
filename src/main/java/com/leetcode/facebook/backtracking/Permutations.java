package com.leetcode.facebook.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.

 Example:

 Input: [1,2,3]
 Output:
 [
 [1,2,3],
 [1,3,2],
 [2,1,3],
 [2,3,1],
 [3,1,2],
 [3,2,1]
 ]

 * @author Santosh Manughala (SM030146).
 */
public class Permutations {

    public static void main(String args[]) {
//        int[] nums = new int[] {1, 2, 3};
        int[] nums = new int[] {3, 1, 2};

        // I dont think we have to sort here, we just want permutations, so it does not matter

        List<List<Integer>> permutations = getPermutationsRecursiveI(nums);

        for(List<Integer> permutation : permutations) {
            System.out.println(permutation);
        }
    }

    /*
    Complexity Analysis

Time complexity : \mathcal{O}(\sum_{k = 1}^{N}{P(N, k)})O(∑
k=1
N
​
 P(N,k)) where P(N, k) = \frac{N!}{(N - k)!} = N (N - 1) ... (N - k + 1)P(N,k)=
(N−k)!
N!
​
 =N(N−1)...(N−k+1) is so-called k-permutations_of_n, or partial permutation.
Here first + 1 = kfirst+1=k for the expression simplicity. The formula is easy to understand : for each kk (each firstfirst) one performs N(N - 1) ... (N - k + 1)N(N−1)...(N−k+1) operations, and kk is going through the range of values from 11 to NN (and firstfirst from 00 to N - 1N−1).

Let's do a rough estimation of the result : N! \le \sum_{k = 1}^{N}{\frac{N!}{(N - k)!}} = \sum_{k = 1}^{N}{P(N, k)} \le N \times N!N!≤∑
k=1
N
​

(N−k)!
N!
​
 =∑
k=1
N
​
 P(N,k)≤N×N!, i.e. the algorithm performs better than \mathcal{O}(N \times N!)O(N×N!) and a bit slower than \mathcal{O}(N!)O(N!).

Space complexity : \mathcal{O}(N!)O(N!) since one has to keep N! solutions.
     */
    private static List<List<Integer>> getPermutationsRecursiveI(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();

        if(nums == null || nums.length == 0) {
            return permutations;
        }
        performPermutationsRecursion(nums, permutations, 0);
        return permutations;
    }

    private static void performPermutationsRecursion(int[] nums, List<List<Integer>> permutations, int index) {
        if(index == nums.length) {
            List<Integer> list = new ArrayList<>();
            for(int num : nums) {
                list.add(num);
            }
            permutations.add(list);
            return;
        }

        for(int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            performPermutationsRecursion(nums, permutations, index + 1);
            swap(nums, i, index);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

// https://www.programcreek.com/2013/02/leetcode-permutations-java/
    public ArrayList<ArrayList<Integer>> copiedIterativeAPpproachDidNOTPRACTICE(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        //start from an empty list
        result.add(new ArrayList<Integer>());

        for (int i = 0; i < num.length; i++) {
            //list of list in current iteration of the array num
            ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();

            for (ArrayList<Integer> l : result) {
                // # of locations to insert is largest index + 1
                for (int j = 0; j < l.size()+1; j++) {
                    // + add num[i] to different locations
                    l.add(j, num[i]);

                    ArrayList<Integer> temp = new ArrayList<Integer>(l);
                    current.add(temp);

                    //System.out.println(temp);

                    // - remove num[i] add
                    l.remove(j);
                }
            }

            result = new ArrayList<ArrayList<Integer>>(current);
        }

        return result;
    }
}
