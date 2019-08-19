package com.leetcode.amazon.explore.arraysandstrings;

/**
 * Compare two version numbers version1 and version2.
 If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.

 You may assume that the version strings are non-empty and contain only digits and the . character.

 The . character does not represent a decimal point and is used to separate number sequences.

 For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

 You may assume the default revision number for each level of a version number to be 0.
 For example, version number 3.4 has a revision number of 3 and 4 for its first and second level revision number. Its third and fourth level revision number are both 0.



 Example 1:

 Input: version1 = "0.1", version2 = "1.1"
 Output: -1
 Example 2:

 Input: version1 = "1.0.1", version2 = "1"
 Output: 1
 Example 3:

 Input: version1 = "7.5.2.4", version2 = "7.5.3"
 Output: -1
 Example 4:

 Input: version1 = "1.01", version2 = "1.001"
 Output: 0
 Explanation: Ignoring leading zeroes, both “01” and “001" represent the same number “1”
 Example 5:

 Input: version1 = "1.0", version2 = "1.0.0"
 Output: 0
 Explanation: The first version number does not have a third level revision number, which means its third level revision number is default to "0"


 Note:

 Version strings are composed of numeric strings separated by dots . and this numeric strings may have leading zeroes.
 Version strings do not start or end with dots, and they will not be two consecutive dots.

 * @author Santosh Manughala (SM030146).
 */
public class CompareVersionNumbers {
    public static void main(String args[]) {
        System.out.println(compareVersionNumbers("0.1", "1.1"));
        System.out.println(compareVersionNumbers("1.0.1", "1"));
        System.out.println(compareVersionNumbers("7.5.2.4", "7.5.3"));
        System.out.println(compareVersionNumbers("1.01", "1.001"));
        System.out.println(compareVersionNumbers("1.0", "1.0.0"));


        System.out.println(compareVersionNumbersWithoutSplit("0.1", "1.1"));
        System.out.println(compareVersionNumbersWithoutSplit("1.0.1", "1"));
        System.out.println(compareVersionNumbersWithoutSplit("7.5.2.4", "7.5.3"));
        System.out.println(compareVersionNumbersWithoutSplit("1.01", "1.001"));
        System.out.println(compareVersionNumbersWithoutSplit("1.0", "1.0.0"));
    }

    // Time: O(max(m,n)) - max length between both strings
    // Space: O(1)
    private static int compareVersionNumbersWithoutSplit(String version1, String version2) {
        int i = 0, j = 0;

        while(i < version1.length() || j < version2.length()) {
            int version1Val = 0, version2Val = 0;

            while(i < version1.length() && version1.charAt(i) != '.') {
                version1Val = version1Val * 10 + version1.charAt(i++) - '0';
            }

            while(j < version2.length() && version2.charAt(j) != '.') {
                version2Val = version2Val * 10 + version2.charAt(j++) - '0';
            }

            if(version1Val > version2Val) {
                return 1;
            }

            if(version1Val < version2Val) {
                return -1;
            }

            i++;
            j++;
        }

        return 0;
    }

    // Time: O(max(m,n)) - max length between both strings
    // Space: O(m + n)
    private static int compareVersionNumbers(String version1, String version2) {
        // Removing the above check does not fail on leetcode but i think its safe to have
        // This is a note, and could be an assumption that this will never happen..as this wont check for 2 .s in a row
        if(version1.startsWith(".") || version1.endsWith(".") || version2.startsWith(".") || version2.endsWith(".")) {
            throw new IllegalArgumentException("starts or ends with .");
        }

        // You need to escape the dot if you want to split on a literal dot:
        // Otherwise you are splitting on the regex ., which means "any character".
        // Note the double backslash needed to create a single backslash in the regex.
        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");

        int i = 0, maxLen = Math.max(version1Array.length, version2Array.length);

        while(i < maxLen) {
            int version1Val = Integer.parseInt(i < version1Array.length ? version1Array[i] : "0");
            int version2Val = Integer.parseInt(i < version2Array.length ? version2Array[i] : "0");

            if (version1Val > version2Val) {
                return 1;
            } else if (version1Val < version2Val) {
                return -1;
            }

            i++;
        }

        return 0;
    }
}
