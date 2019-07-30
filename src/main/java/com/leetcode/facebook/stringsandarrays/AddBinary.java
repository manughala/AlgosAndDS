package com.leetcode.facebook.stringsandarrays;

/**
 * Given two binary strings, return their sum (also a binary string).

 The input strings are both non-empty and contains only characters 1 or 0.

 Example 1:

 Input: a = "11", b = "1"
 Output: "100"

 Example 2:

 Input: a = "1010", b = "1011"
 Output: "10101"

 * @author Santosh Manughala (SM030146).
 */
public class AddBinary {

    public static void main(String args[]) {
        addBinary("1011", "101");
//        addBinary(Long.parseLong("1011"), Long.parseLong("101"));
//        addBinary(1011, 101);

        // My estimate:
        // Time: O(n)
        // Space: O(1)
    }

    private static void addBinary(String a, String b) {
        int sum = 0;
        String result = "";
        int i = a.length() - 1, j = b.length() - 1;

        while(i >= 0 || j >= 0 || sum == 1) {
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;

            result =  (char) (sum%2 + '0') + result;
            sum /= 2;
            i--;j--;

        }

        System.out.println(result);
    }

    private static void addBinary(long binary1, long binary2) {
        int[] result = new int[20];
        int carry = 0, i = 0;

        while(binary1 != 0 || binary2 != 0) {
            result[i++] = (int)(binary1%10 + binary2%10 + carry)%2; // -> 1 + 1 + 0 -> 0
            carry = (int)(binary1%10 + binary2%10 + carry)/2; // 1

            binary1 = binary1/10;
            binary2 = binary2/10;
        }

        if(carry != 0) {
            result[i++] = carry;
        }

        i--;
        while (i >= 0) {
            System.out.println(result[i--]);
        }
    }

}
