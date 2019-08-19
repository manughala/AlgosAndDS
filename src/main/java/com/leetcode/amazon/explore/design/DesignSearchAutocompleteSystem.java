package com.leetcode.amazon.explore.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#').
 * For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.
 *
 * Here are the specific rules:

 The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
 If less than 3 hot sentences exist, then just return as many as you can.
 When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 Your job is to implement the following functions:

 The constructor function:

 AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences.
 Times is the corresponding times a sentence has been typed. Your system should record these historical data.

 Now, the user wants to input a new sentence. The following function will provide the next character the user types:

 List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#').
 Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.


 Example:
 Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
 The system have already tracked down the following sentences and their corresponding times:
 "i love you" : 5 times
 "island" : 3 times
 "ironman" : 2 times
 "i love leetcode" : 2 times
 Now, the user begins another search:

 Operation: input('i')
 Output: ["i love you", "island","i love leetcode"]
 Explanation:
 There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman".
 Also we only need to output top 3 hot sentences, so "ironman" will be ignored.

 Operation: input(' ')
 Output: ["i love you","i love leetcode"]
 Explanation:
 There are only two sentences that have prefix "i ".

 Operation: input('a')
 Output: []
 Explanation:
 There are no sentences that have prefix "i a".

 Operation: input('#')
 Output: []
 Explanation:
 The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.


 Note:

 The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
 The number of complete sentences that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.
 Please use double-quote instead of single-quote when you write test cases even for a character input.
 Please remember to RESET your class variables declared in class AutocompleteSystem, as static/class variables are persisted across multiple test cases. Please see here for more details.

 * @author Santosh Manughala (SM030146).
 */
public class DesignSearchAutocompleteSystem {

    // NOTE: instead of initializing the data structure in the constructor, decided to use a method so i dont have to comment out code.

    public static void main(String args[]) {
        HashMap<String, Integer> stringToCount = initializeDataStructBruteForceI(new String[] {"i love you", "island","ironman", "i love leetcode"}, new int[] {5, 3, 2, 2});
        System.out.println("input c: " + inputBruteForceI('i', stringToCount));
        System.out.println("input c: " + inputBruteForceI(' ', stringToCount));
        System.out.println("input c: " + inputBruteForceI('a', stringToCount));
        System.out.println("input c: " + inputBruteForceI('#', stringToCount));

        HashMap<String, Integer>[] stringToCountByChars = initializeDataStructIntermediateCase(new String[] {"i love you", "island","ironman", "i love leetcode"}, new int[] {5, 3, 2, 2});
        System.out.println("input c: " + inputIntermediateCase('i', stringToCountByChars));
        System.out.println("input c: " + inputIntermediateCase(' ', stringToCountByChars));
        System.out.println("input c: " + inputIntermediateCase('a', stringToCountByChars));
        System.out.println("input c: " + inputIntermediateCase('#', stringToCountByChars));

        Trie trie = initializeDataStructBestCase(new String[] {"i love you", "island","ironman", "i love leetcode"}, new int[] {5, 3, 2, 2});
        System.out.println("input c: " + inputBestCase('i', trie));
        System.out.println("input c: " + inputBestCase(' ', trie));
        System.out.println("input c: " + inputBestCase('a', trie));
        System.out.println("input c: " + inputBestCase('#', trie));
    }


    // Time: initialize -> O(n * l) input -> O(n + mlogm)  -> n number of sentences formed; mlogm for sorting all the methods need that
    // Space: O(n * m)
    static Trie root;
    static String currentString = "";
    private static Trie initializeDataStructBestCase(String[] sentences, int[] times) {
        root = new Trie();
        for(int i = 0; i < sentences.length; i++) {
            buildTrie(sentences[i], root, times[i]);
        }

        return root;
    }

    private static void buildTrie(String sentence, Trie root, int times) {
        Trie temp = root;
        for(char c : sentence.toCharArray()) {
            int idx = c == ' ' ? 26 : c - 'a';
            if(temp.children[idx] == null) {
                temp.children[idx] = new Trie();
            }

            temp = temp.children[idx];
        }

        temp.times += times;
    }

    private static List<String> inputBestCase(char c, Trie root) {
        List<String> result = new ArrayList<>();
        if (c == '#') {
            buildTrie(currentString, root, 1);
            currentString = "";
            return result;
        }

        currentString += c;

        List<ListNode> nodes = searchTrie(currentString, root);

        if(!nodes.isEmpty()) {
            Collections.sort(nodes, new Comparator<ListNode>() {
                @Override
                public int compare(ListNode l1, ListNode l2) {
                    if(l1.times == l2.times) {
                        return l1.sentence.compareTo(l2.sentence);
                    } else {
                        return l2.times - l1.times;
                    }
                }
            });

            for(int i = 0; i < Math.min(3, nodes.size()); i++) {
                result.add(nodes.get(i).sentence);
            }
        }

        return result;
    }

    private static List<ListNode> searchTrie(String currString, Trie root) {
        Trie temp = root;
        List<ListNode> nodes = new ArrayList<>();

        for(char c : currString.toCharArray()) {
            int idx = c == ' ' ? 26 : c - 'a';

            if(temp.children[idx] == null) {
                return nodes;
            }

            temp = temp.children[idx];
        }

        traverseTrie(currString, temp, nodes);
        return nodes;
    }

    private static void traverseTrie(String currentString, Trie temp, List<ListNode> nodes) {
        if(temp.times > 0) {
            nodes.add(new ListNode(currentString, temp.times));
        }

        for(char c = 'a'; c <= 'z'; c++) {
            if(temp.children[c - 'a'] != null) {
                traverseTrie(currentString + c, temp.children[c - 'a'], nodes);
            }
        }

        if(temp.children[26] != null) {
            traverseTrie(currentString + ' ', temp.children[26], nodes);
        }
    }

    // Time: initialize -> O(n * l); input O(s * mlogm) -> s is size of hashmap
    // Space: O(n * l)
    static HashMap<String, Integer>[] stringToCountByChars;
    static String currSearchString = "";
    private static HashMap<String, Integer>[] initializeDataStructIntermediateCase(String[] sentences, int[] times) {
        stringToCountByChars = new HashMap[26];
        for(int i = 0; i < 26; i++) {
            stringToCountByChars[i] = new HashMap<>();
        }

        for(int i = 0; i < sentences.length; i++) {
            stringToCountByChars[sentences[i].charAt(0) - 'a'].put(sentences[i], times[i]);
        }

        return stringToCountByChars;
    }

    private static List<String> inputIntermediateCase(char c, HashMap<String, Integer>[] stringToCountByChars) {
        List<String> result = new ArrayList<>();

        if(c == '#') {
            stringToCountByChars[currSearchString.charAt(0) - 'a'].put(currSearchString, stringToCountByChars[currSearchString.charAt(0) - 'a'].getOrDefault(currSearchString, 0) + 1);
            currSearchString = "";
            return result;
        }

        currSearchString += c;

        List<ListNode> nodes = new ArrayList<>();
        for(String key : stringToCountByChars[currSearchString.charAt(0) - 'a'].keySet()) {
            if(key.indexOf(currSearchString) == 0) {
                nodes.add(new ListNode(key, stringToCountByChars[currSearchString.charAt(0) - 'a'].get(key)));
            }
        }

        if(!nodes.isEmpty()) {
            Collections.sort(nodes, new Comparator<ListNode>() {
                @Override
                public int compare(ListNode l1, ListNode l2) {
                    if(l1.times == l2.times) {
                        return l1.sentence.compareTo(l2.sentence);
                    } else {
                        return l2.times - l1.times;
                    }
                }
            });

            for(int i = 0; i < Math.min(3, nodes.size()); i++) {
                result.add(nodes.get(i).sentence);
            }
        }

        return result;
    }

    // Time: initialize -> O(n * l); input -> O(n + mlogm) -> n is num of sentences, m is list to be sorted
    // Space: O(n * l)
    static HashMap<String, Integer> stringToCount;
    static String currSearch = "";
    private static HashMap<String, Integer> initializeDataStructBruteForceI(String[] sentences, int[] times) {
        stringToCount = new HashMap<>();

        for(int i = 0; i < sentences.length; i++) {
            // assuming there will be no duplicates
            // if there are duplicates, we should get from map and add
            stringToCount.put(sentences[i], times[i]);
        }

        return stringToCount;
    }

    private static List<String> inputBruteForceI(char c, HashMap<String, Integer> stringToCount) {
        List<String> result = new ArrayList<>();

        if(c == '#') {
            stringToCount.put(currSearch, stringToCount.getOrDefault(currSearch, 0) + 1);
            currSearch = "";
            return result;
        }

        currSearch += c;

        List<ListNode> nodes = new ArrayList<>();
        for(String string : stringToCount.keySet()) {
            if(string.indexOf(currSearch) == 0) {
                nodes.add(new ListNode(string, stringToCount.get(string)));
            }
        }

        if(!nodes.isEmpty()) {
            Collections.sort(nodes, new  Comparator<ListNode>() {
                @Override
                public int compare(ListNode l1, ListNode l2) {
                    if(l1.times == l2.times) {
                        return l1.sentence.compareTo(l2.sentence);
                    } else {
                        return l2.times - l1.times;
                    }
                }
            });

            for(int i = 0; i < Math.min(3, nodes.size()); i++) {
                result.add(nodes.get(i).sentence);
            }
        }

        return result;

    }

    static class ListNode {
        String sentence;
        int times;

        ListNode(String sentence, int times) {
            this.sentence = sentence;
            this.times = times;
        }
    }
}

class Trie {
    Trie[] children;
    int times;
    Trie() {
        children = new Trie[27];
    }
}

