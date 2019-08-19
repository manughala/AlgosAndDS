package com.leetcode.amazon.explore.arraysandstrings;

/**
 * Given an array of characters, compress it in-place.

 The length after compression must always be smaller than or equal to the original array.

 Every element of the array should be a character (not int) of length 1.

 After you are done modifying the input array in-place, return the new length of the array.


 Follow up:
 Could you solve it using only O(1) extra space?


 Example 1:

 Input:
 ["a","a","b","b","c","c","c"]

 Output:
 Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]

 Explanation:
 "aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".


 Example 2:

 Input:
 ["a"]

 Output:
 Return 1, and the first 1 characters of the input array should be: ["a"]

 Explanation:
 Nothing is replaced.


 Example 3:

 Input:
 ["a","b","b","b","b","b","b","b","b","b","b","b","b"]

 Output:
 Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].

 Explanation:
 Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
 Notice each digit has it's own entry in the array.


 Note:

 All characters have an ASCII value in [35, 126].
 1 <= len(chars) <= 1000.

 * @author Santosh Manughala (SM030146).
 */
public class StringCompression {
    public static void main(String args[]) {
        char[] chars = new char[]{'a','a','b','c','c','c', 'c'};
        compress(chars);
        for(char c: chars) {
            System.out.println(c);
        }


    }

    public static String deCompress(String s){
        if(s == null || s.isEmpty()){
            return s;
        }
        StringBuilder sb = new StringBuilder();
        int count = 0;
        char prv = 0;
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(!Character.isDigit(c)){
                while(count > 0){
                    sb.append(prv);
                    count--;
                }
                prv = c;
            }else{
                count = count * 10 + c - '0';
            }
        }
        if(prv!=0){
            sb.append(prv);
        }
        return sb.toString();
    }

    public static String compress(String s){
        if(s == null || s.isEmpty()){
            return s;
        }

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<s.length();){
            int count = 0;
            char c = s.charAt(i);
            while(i<s.length() && c == s.charAt(i)){
                i++;
                count++;
            }
            if(count > 0){
                sb = sb.append(c).append(count);
            }
        }
        return sb.toString();
    }

    // Time O(n)
    // Space O(1)
    private static int compress(char[] chars) {
        int start = 0, end = 0;

        while(end < chars.length) {
            char currChar = chars[end];
            int count = 0;

            while(end < chars.length && chars[end] == currChar) {
                end++;
                count++;
            }

            chars[start++] = currChar;

            if(count != 1) {
                for (char c : Integer.toString(count).toCharArray()) {
                    chars[start++] = c;
                }
            }
        }

        return start;
    }
}
