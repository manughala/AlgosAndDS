package com.leetcode.linkedin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.

 Note:

 1 <= w.length <= 10000
 1 <= w[i] <= 10^5
 pickIndex will be called at most 10000 times.
 Example 1:

 Input:
 ["Solution","pickIndex"]
 [[[1]],[]]
 Output: [null,0]
 Example 2:

 Input:
 ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 [[[1,3]],[],[],[],[],[]]
 Output: [null,0,1,1,1,0]
 Explanation of Input Syntax:

 The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.

 * @author Santosh Manughala (SM030146).
 */
public class RandomPickWithWeight {

    public static void main(String args[]) {
        RandomPickWithWeight pickWithWeight = new RandomPickWithWeight(new int[] {1, 3});
        System.out.println(pickWithWeight.pickIndex());
    }


    // PROBLEM CLARITY:
    // If we are given an array of weights 1, 3 -> that means that there will be an occurrence of a string/word (ex sam) 1 time
    // then another word (ex tan) 3 times -> arrays looks {sam, tan, tan, tan)
    // Now the problem is asking us to pick a random value from here.
    // In general, if we have 4 words, probability of a word to be picked is 25%, but with this weights array, we have to pick
    // a random number from weights proportionate to the index
    // SOLUTION 1:
    // brute force: we can construct the new array and pick a random value from there, but since we can have upto 10000 weights,
    // we cannot maintain that array
    // SOLTION 2:
    // first find the total array length till that index -> meaning at the last index, you will get the size of the new array
    // we have a pick a random from 1 till the size and do a binary search on the given weighted array to finding the lowest index
    // Time: O(N)
    // Space: O(N)
    public RandomPickWithWeight(int[] w) {
        int total = 0;
        weightsSum = new ArrayList<>();

        for(int i = 0; i < w.length; i++) {
            total += w[i];
            weightsSum.add(total);
        }
    }

    List<Integer> weightsSum;

    // Time O(logn)
    // Space: O(N)
    public int pickIndex() {
        int idx = new Random().nextInt(weightsSum.get(weightsSum.size() - 1));

        int low = 0, high = weightsSum.size() - 1;

        while (low != high) {
            int mid =  (low + high) / 2;
            // Using this is exceeding time limit on leetcode
//            int mid = (low + (high - low)) / 2;
            if(idx >= weightsSum.get(mid)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}
