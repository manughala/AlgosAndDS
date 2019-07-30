package com.leetcode.facebook.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

 A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



 Example:

 Input: "23"
 Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 Note:

 Although the above answer is in lexicographical order, your answer could be in any order you want.

 * @author Santosh Manughala (SM030146).
 */
public class LetterCombinationsOfPhoneNumber {

    public static void main(String args[]) {
        String test1 = "123";
        String test2 = "233";
        String test3 = "456";
        String test4 = "23";

        List<String> result1 = letterCombinationsRecursiveI(test2);
        List<String> result2 = letterCombinationsRecursiveII(test2);
// COPIED CODE FROM https://github.com/awangdev/LintCode/blob/master/Java/Letter%20Combinations%20of%20a%20Phone%20Number.java
// DOES NOT WORK ASIS -> goes to infinite loop did not debug; iterative is pain
//  List<String> result = letterCombinationsIterative(test2);
        System.out.println(result1);
        System.out.println(result2);
    }

    private static Map<String, String> digitToLetters = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    private static List<String> letterCombinationsRecursiveI(String digits) {
        List<String> result = new ArrayList<>();
        if(digits == null || digits.isEmpty()) {
            return result;
        }

        // Should ask the question to the interviewer, if we have to ignore 0 or 1
        // as they dont have digits OR throw exception.
        // I chose to throw exception here.
        if(digits.contains("1") || digits.contains("0")) {
            throw new IllegalArgumentException("1 is not a valid digit");
        }

        performCombineI("", digits, result);

        return result;
    }

    private static void performCombineI(String currentCombination, String digits, List<String> result) {
        if(digits.length() == 0) {
            result.add(currentCombination);
        } else {
            String currentDigit = digits.substring(0, 1);
            String currentDigitToLetters = digitToLetters.get(currentDigit);

            for (int i = 0; i < currentDigitToLetters.length(); i++) {
                performCombineI(currentCombination + currentDigitToLetters.charAt(i), digits.substring(1), result);
            }
        }
    }

    private static List<String> letterCombinationsRecursiveII(String digits) {
        ArrayList<String> result = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        ArrayList<String[]> lettersPerDigitsIndex = new ArrayList<String[]>();
        lettersPerDigitsIndex.add(new String[]{});//key 0: nothing
        lettersPerDigitsIndex.add(new String[]{});//key 1: nothing
        lettersPerDigitsIndex.add(new String[]{"a","b","c"});
        lettersPerDigitsIndex.add(new String[]{"d","e","f"});
        lettersPerDigitsIndex.add(new String[]{"g","h","i"});
        lettersPerDigitsIndex.add(new String[]{"j","k","l"});
        lettersPerDigitsIndex.add(new String[]{"m","n","o"});
        lettersPerDigitsIndex.add(new String[]{"p","q","r","s"});
        lettersPerDigitsIndex.add(new String[]{"t","u","v"});
        lettersPerDigitsIndex.add(new String[]{"w","x","y","z"});

        ArrayList<String> currentCombination = new ArrayList<String>();
        performCombineII(result, currentCombination, lettersPerDigitsIndex, digits, 0);

        return result;
    }

    private static void performCombineII(List<String> result, List<String> currentCombination, List<String[]> digitToLetters, String digits, int index) {

        if(index == digits.length()) {
            StringBuffer sb = new StringBuffer();
            for(String letter : currentCombination) {
                sb.append(letter);
            }

            result.add(new String(sb));
            return;
        }

        String[] lettersOfCurrentDigit = digitToLetters.get(Integer.parseInt(digits.substring(index, index + 1)));

        for(String letter : lettersOfCurrentDigit) {
            currentCombination.add(letter);
            performCombineII(result, currentCombination, digitToLetters, digits, index+1);
            currentCombination.remove(letter);
        }
    }


    //NOTE DOES NOT WORK -> GOES TO INFINITE LOOP
    private static List<String> letterCombinationsIterative(String digits) {
        List<String> result = new ArrayList<>();
        if(digits == null || digits.isEmpty()) {
            return result;
        }

        // Should ask the question to the interviewer, if we have to ignore 0 or 1
        // as they dont have digits OR throw exception.
        // I chose to throw exception here.
        if(digits.contains("1") || digits.contains("0")) {
            throw new IllegalArgumentException("1 is not a valid digit");
        }

        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8,"tuv");
        map.put(9,"wxyz");

        Queue<String> queue = new LinkedList<String>();

        //init
        int index = 0;
        int digit = Integer.parseInt(digits.substring(index, index + 1));
        String keys = map.get(digit);
        index++;

        for (int i = 0; i < keys.length(); i++) {
            queue.offer(keys.substring(i,i+1));
        }
        int size = queue.size();

        while (index < digits.length() && !queue.isEmpty()) {
            String str = queue.poll();
            digit = Integer.parseInt(digits.substring(index, index + 1));
            keys = map.get(digit);
            for (int i = 0; i < keys.length(); i++) {
                queue.offer(str + keys.substring(i,i+1));
            }
            size--;
            if (size == 0 && index < digits.length() - 1) {
                index++;
                size = queue.size();
            }
        }//end while

        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }

        return result;
    }


}
