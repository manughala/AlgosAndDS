package com.leetcode.amazon.explore.arraysandstrings;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

 Symbol       Value
 I             1
 V             5
 X             10
 L             50
 C             100
 D             500
 M             1000
 For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II.
 The number twenty seven is written as XXVII, which is XX + V + II.

 Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII.
 Instead, the number four is written as IV. Because the one is before the five we subtract it making four.
 The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

 I can be placed before V (5) and X (10) to make 4 and 9.
 X can be placed before L (50) and C (100) to make 40 and 90.
 C can be placed before D (500) and M (1000) to make 400 and 900.
 Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.

 Example 1:

 Input: 3
 Output: "III"
 Example 2:

 Input: 4
 Output: "IV"
 Example 3:

 Input: 9
 Output: "IX"
 Example 4:

 Input: 58
 Output: "LVIII"
 Explanation: L = 50, V = 5, III = 3.
 Example 5:

 Input: 1994
 Output: "MCMXCIV"
 Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

 * @author Santosh Manughala (SM030146).
 */
public class IntegerToRoman {

    public static void main(String args[]) {
        System.out.println("getRomanI: Expected: III, Actual: " + getRomanI(3));
        System.out.println("getRomanI: Expected: IV, Actual: " + getRomanI(4));
        System.out.println("getRomanI: Expected: IX, Actual: " + getRomanI(9));
        System.out.println("getRomanI: Expected: LVIII, Actual: " + getRomanI(58));
        System.out.println("getRomanI: Expected: MCMXCIV, Actual: " + getRomanI(1994));
        System.out.println("getRomanI: Expected: MMMCMXCIX, Actual: " + getRomanI(3999));


        System.out.println("getRomanII: Expected: III, Actual: " + getRomanII(3));
        System.out.println("getRomanII: Expected: IV, Actual: " + getRomanII(4));
        System.out.println("getRomanII: Expected: IX, Actual: " + getRomanII(9));
        System.out.println("getRomanII: Expected: LVIII, Actual: " + getRomanII(58));
        System.out.println("getRomanII: Expected: MCMXCIV, Actual: " + getRomanII(1994));
        System.out.println("getRomanII: Expected: MMMCMXCIX, Actual: " + getRomanII(3999));
    }

    // Time: O(n) -> number of digits
    // Space: O(1)
    private static String getRomanI(int number) {
        String[] M = new String[]{"", "M", "MM", "MMM"};
        String[] C = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] X = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] I = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return M[number/1000] + C[(number%1000)/100] + X[(number%100)/10] + I[number%10];
    }

    // Time: O(n) -> number of digits
    // Space: O(1)
    private static String getRomanII(int number) {
        String[] romans = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "Xl", "X", "IX", "V", "IV", "I"};
        int[] nums = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < nums.length; i++) {
            while (number >= nums[i]) {
                builder.append(romans[i]);
                number -= nums[i];
            }
        }

        return builder.toString();
    }
}
