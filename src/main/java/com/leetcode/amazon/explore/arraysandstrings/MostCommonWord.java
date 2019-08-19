package com.leetcode.amazon.explore.arraysandstrings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.
 * It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

 Words in the list of banned words are given in lowercase, and free of punctuation.
 Words in the paragraph are not case sensitive.  The answer is in lowercase.



 Example:

 Input:
 paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 banned = ["hit"]
 Output: "ball"
 Explanation:
 "hit" occurs 3 times, but it is a banned word.
 "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
 Note that words in the paragraph are not case sensitive,
 that punctuation is ignored (even if adjacent to words, such as "ball,"),
 and that "hit" isn't the answer even though it occurs more because it is banned.


 Note:

 1 <= paragraph.length <= 1000.
 0 <= banned.length <= 100.
 1 <= banned[i].length <= 10.
 The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
 paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
 There are no hyphens or hyphenated words.
 Words only consist of letters, never apostrophes or other punctuation symbols.

 * @author Santosh Manughala (SM030146).
 */
public class MostCommonWord {

    public static void main(String args[]) {
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
//        System.out.println(paragraph.replaceAll("[!?',;.]", " "));

        System.out.println("getMostCommonWordWRONG: Expected: ball actual: " + getMostCommonWordWRONG(paragraph, new String[]{"hit"}));
        System.out.println("getMostCommonWordWRONG: Expected: b actual: " + getMostCommonWordWRONG("a, a, a, a, b,b,b,c, c", new String[]{"a"}));

        System.out.println("getMostCommonWord: Expected: ball actual: " + getMostCommonWord(paragraph, new String[]{"hit"}));
        System.out.println("getMostCommonWord: Expected: b actual: " + getMostCommonWord("a, a, a, a, b,b,b,c, c", new String[]{"a"}));

        System.out.println("getMostCommonWordLeetCodeWay: Expected: ball actual: " + getMostCommonWordLeetCodeWay(paragraph, new String[]{"hit"}));
        System.out.println("getMostCommonWordLeetCodeWay: Expected: b actual: " + getMostCommonWordLeetCodeWay("a, a, a, a, b,b,b,c, c", new String[]{"a"}));
    }

    // Time: O(p + b) -> p is size of paragraph, b is size of banned words
    // Space: O(p + b)
    private static String getMostCommonWordLeetCodeWay(String paragraph, String[] bannedWords) {
        if(paragraph == null || paragraph.length() == 0) {
            return "";
        }
        Map<String, Integer> map = new HashMap<>();
        Set<String> banned = new HashSet<>();

        for(String word: bannedWords) {
            banned.add(word);
        }

        int mostCommonWordCount = Integer.MIN_VALUE;
        String mostCommonWord = null;

        StringBuilder builder = new StringBuilder();
        for(char c : paragraph.toCharArray()) {
            if(Character.isLetter(c)) {
                builder.append(c);
            } else if(builder.length() > 0){
                String word = builder.toString().toLowerCase();
                if(!banned.contains(word)) {
                    map.put(word, map.getOrDefault(word, 0) + 1);

                    if(mostCommonWordCount < map.get(word)) {
                        mostCommonWordCount = map.get(word);
                        mostCommonWord = word;
                    }
                }

                builder = new StringBuilder();
            }
        }

        return mostCommonWord;
    }

    // Time: O(p + b) -> p is size of paragraph, b is size of banned words
    // Space: O(p)
    private static String getMostCommonWord(String paragraph, String[] bannedWords) {
        if(paragraph == null || paragraph.length() == 0) {
            return "";
        }

        // NOTE THAT I USED "\\s+ for splitting by space
        // using " " and \\s is the same, split by space, but with "\\s+" we can
        // actually split with multiple space.
        // In the above scenario, if we replace all punctuation with space, we will sometimes end up with
        // more than one space continuosly as the original para might have "...bill, has ..."
        String[] paragraphArray = paragraph.toLowerCase().replaceAll("[!?',;.]", " ").split("\\s+");
        Map<String, Integer> map = new HashMap<>();

        for(String word : paragraphArray) {
            // NOTE: there is no contains method in arrays
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for(String word: bannedWords) {
            map.put(word, 0);
        }

        int mostCommonWordCount = Integer.MIN_VALUE;
        String mostCommonWord = null;

        for(String word: paragraphArray) {
            if(mostCommonWordCount < map.get(word)) {
                mostCommonWord = word;
                mostCommonWordCount = map.get(word);
            }
        }

        return mostCommonWord;
    }

    private static List<String> getWords(String paragraph) {
        List<String> strings = new ArrayList<>();

        int i = 0;

        while(i < paragraph.length()) {
            String word = "";

            while(i < paragraph.length()) {
                int idx = paragraph.charAt(i) - 'a';
                if (idx >= 0 && idx < 26) {
                    word += paragraph.charAt(i);
                } else {
                    i++;
                    break;
                }
                i++;
            }

            if(word.length() > 0) {
                strings.add(word);
            }
        }

        return strings;
    }

    // NOTE: THIS IS FIRST ATTEMPT -> THIS WILL NOT WORK FOR INPUT LIKE "a, a, a, a, b,b,b,c, c"
    // I ASSUMED HERE THAT AFTER PUNCTUATION THERE WILL BE SPACE
    private static String getMostCommonWordWRONG(String paragraph, String[] bannedWords) {
        if(paragraph == null || paragraph.length() == 0) {
            return "";
        }

        String[] paragraphArray = paragraph.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ");
        Map<String, Integer> map = new HashMap<>();

        for(String word : paragraphArray) {
            // NOTE: there is no contains method in arrays
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for(String word: bannedWords) {
            map.put(word, 0);
        }

        int mostCommonWordCount = Integer.MIN_VALUE;
        String mostCommonWord = null;

        for(String word: paragraphArray) {
            if(mostCommonWordCount < map.get(word)) {
                mostCommonWord = word;
                mostCommonWordCount = map.get(word);
            }
        }

        return mostCommonWord;
    }
}
