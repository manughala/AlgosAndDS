package com.leetcode.amazon.explore.arraysandstrings;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * You have an array of logs.  Each log is a space delimited string of words.

 For each log, the first word in each log is an alphanumeric identifier.  Then, either:

 Each word after the identifier will consist only of lowercase letters, or;
 Each word after the identifier will consist only of digits.
 We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.

 Reorder the logs so that all of the letter-logs come before any digit-log.
 The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.
 The digit-logs should be put in their original order.

 Return the final order of the logs.

 Example 1:

 Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
 Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]


 Note:

 0 <= logs.length <= 100
 3 <= logs[i].length <= 100
 logs[i] is guaranteed to have an identifier, and a word after the identifier.

 * @author Santosh Manughala (SM030146).
 */
public class ReorderLogFiles {
    public static void main(String args[]) {
        for(String s: reorderLogFiles(new String[]{"a1 9 2 3 1", "g1 act car","zo4 4 7", "ab1 off key dog", "a8 act zoo"})) {
            System.out.println(s);
        }
    }

    private static String[] reorderLogFiles(String[] logs) {
        Comparator<String> logComparator =  new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String[] s1Split = s1.split(" ", 2);
                String[] s2Split = s2.split(" ");

                boolean isS1Digit = Character.isDigit(s1Split[1].charAt(0));
                boolean isS2Digit = Character.isDigit(s2Split[1].charAt(0));

                if(!isS1Digit && !isS2Digit) {
                    int comp = s1Split[1].compareTo(s2Split[1]);
                    if(comp != 0) {
                        return comp;
                    }

                    return s1Split[0].compareTo(s2Split[0]);
                }

                if(isS1Digit) {
                    if(isS2Digit) {
                        // both are digits, so put in the same order for digit logs
                        return 0;
                    }
                    // s1 is digit but s2 is not
                    // we want to return letter logs before digit logs
                    return 1;
                }

                // handle case where s1 is not digit
                // since we handled the case where s1 is digit and s2 is not AND s1 not digit and s2 is also not, here s2 must be a digit.
                if (!isS1Digit) {
                    return -1;
                }

                return 0;
            }
        };

        Arrays.sort(logs, logComparator);

        return logs;
    }
}
