package com.leetcode.microsoft.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class LetterCombinations {
    public static void main(String args[]) {
        System.out.println("I: " + getLetterCombinations("23"));
        System.out.println("II: " + getLetterCombinationsII("23"));
        System.out.println("III: " + getLetterCombinationsIII("23"));
    }

    private static Map<String, String> charToLettersIII = new HashMap<String, String>(){{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    private static List<String> getLetterCombinationsIII(String input) {
        List<String> combinations = new ArrayList<>();

        if(input == null || input.length() == 0) {
            return combinations;
        }

        getCombinationsIII("", input, combinations, 0);

        return combinations;
    }

    private static void getCombinationsIII(String currentCombination, String input, List<String> combinations, int index) {
        if(currentCombination.length() == input.length()) {
            combinations.add(currentCombination);
            return;
        }

        String currentLetters = charToLettersIII.get(input.substring(index, index + 1));

        for(int i = 0; i < currentLetters.length(); i++) {
            String prevCombination = currentCombination;
            getCombinationsIII(currentCombination + currentLetters.substring(i, i + 1), input, combinations, index + 1);
            currentCombination = prevCombination;
        }
    }

    private static List<String> getLetterCombinationsII(String input) {
        List<String> combinations = new ArrayList<>();

        if(input == null || input.length() == 0) {
            return combinations;
        }

        getCombinationsII(combinations, input, "");

        return combinations;
    }

    private static Map<String, String> charToLettersII = new HashMap<String, String>(){{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    private static void getCombinationsII(List<String> combinations, String input, String currentCombination) {
        if(input.length() == 0) {
            combinations.add(currentCombination);
            return;
        }

        String currentLetters = charToLettersII.get(input.substring(0, 1));

        for(int i = 0; i < currentLetters.length(); i++) {
            getCombinationsII(combinations, input.substring(1), currentCombination + currentLetters.charAt(i));
        }
    }


    private static List<String> getLetterCombinations(String input) {
        List<String> combinations = new ArrayList<>();

        if(input == null || input.length() == 0) {
            return combinations;
        }

        getCombinations(combinations, new ArrayList<>(), 0, input);

        return combinations;
    }

    private static List<String[]> charToLetters = new ArrayList<String[]>() {{
        add(new String[]{});
        add(new String[]{});
        add(new String[]{"a", "b", "c"});
        add(new String[]{"d", "e", "f"});
        add(new String[]{"g", "h", "i"});
        add(new String[]{"j", "k", "l"});
        add(new String[]{"m", "n", "o"});
        add(new String[]{"p", "q", "r", "s"});
        add(new String[]{"t", "u", "v"});
        add(new String[]{"w", "x", "y", "z"});
    }};

    private static void getCombinations(List<String> combinations, List<String> currentCombination, int index, String input) {
        if(currentCombination.size() == input.length()) {
            StringBuilder builder = new StringBuilder();
            for(String s : currentCombination) {
                builder.append(s);
            }
            combinations.add(builder.toString());
            return;
        }

        String[] currentLetters = charToLetters.get(Integer.parseInt(input.substring(index, index + 1)));

        for(int i = 0; i < currentLetters.length; i++) {
            currentCombination.add(currentLetters[i]);
            getCombinations(combinations, currentCombination, index + 1, input);
            currentCombination.remove(currentCombination.size() - 1);
        }


    }
}
