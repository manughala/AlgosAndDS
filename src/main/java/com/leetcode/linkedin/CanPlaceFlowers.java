package com.leetcode.linkedin;

/**
 * Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

 Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

 Example 1:
 Input: flowerbed = [1,0,0,0,1], n = 1
 Output: True
 Example 2:
 Input: flowerbed = [1,0,0,0,1], n = 2
 Output: False
 Note:
 The input array won't violate no-adjacent-flowers rule.
 The input array size is in the range of [1, 20000].
 n is a non-negative integer which won't exceed the input array size.

 * @author Santosh Manughala (SM030146).
 */
public class CanPlaceFlowers {
    public static void main(String args[]) {
//        int[] flowerbed = new int[]{1, 0, 0, 0, 1};
        int[] flowerbed = new int[]{0};
//        int[] flowerbed = new int[]{0, 0, 0, 0, 1};
        System.out.println(canPlaceFlowers(flowerbed, 1));
        System.out.println(canPlaceFlowers(flowerbed, 2));
    }

    // time O(n)
    // space O(1)
    private static boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(flowerbed == null || flowerbed.length == 0 || flowerbed.length < n) {
            return false;
        }

        int canPlace = 0;
        for(int i = 0; i < flowerbed.length; i++) {
            if(flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                canPlace++;
                flowerbed[i] = 1;
            }
        }

        // one optimization we can do is, place this in the for loop after if -> when ever we reach the size n,
        // we finish the loop. still the time in O(n) but saves some time
        if(canPlace >= n) {
            return true;
        }

        return false;
    }
}
