package com.leetcode.facebook.design;

import java.util.HashMap;

/**
 * Design a data structure that supports the following two operations:

 void addWord(word)
 bool search(word)
 search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

 Example:

 addWord("bad")
 addWord("dad")
 addWord("mad")
 search("pad") -> false
 search("bad") -> true
 search(".ad") -> true
 search("b..") -> true
 Note:
 You may assume that all words are consist of lowercase letters a-z.

 * @author Santosh Manughala (SM030146).
 */
public class AddAndSearchWordDataStructDesign {

    // TRIE NODE IMPL : https://www.programcreek.com/2014/05/leetcode-implement-trie-prefix-tree-java/
    public static void main(String args[]) {
        AddAndSearchWordDataStructDesign dataStruct = new AddAndSearchWordDataStructDesign();
        dataStruct.addWord("bad");
        dataStruct.addWord("dad");
        dataStruct.addWord("dad");
        dataStruct.addWord("mad");
        dataStruct.addWord("madassa");

        System.out.println("search(\"pad\") : " + dataStruct.search("pad"));
        System.out.println("search(\"bad\") : " + dataStruct.search("bad"));
        System.out.println("search(\"dad\") : " + dataStruct.search("dad"));
        System.out.println("search(\"mad\") : " + dataStruct.search("mad"));
        System.out.println("search(\".ad\") : " + dataStruct.search(".ad"));
        System.out.println("search(\".ad....a\") : " + dataStruct.search(".ad...a"));
        System.out.println("search(\".a\") : " + dataStruct.search(".a"));
        System.out.println("search(\"b..\") : " + dataStruct.search("b.."));
    }

    public TrieNodeArrays trieNode = null;

    /** Initialize your data structure here. */
    public AddAndSearchWordDataStructDesign() {
        trieNode = new TrieNodeArrays();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {

        TrieNodeArrays temp = trieNode;
        for(char c : word.toCharArray()) {
            int idx = c - 'a';


            if(temp.next[idx] == null) {
                TrieNodeArrays node = new TrieNodeArrays();
                temp.next[idx] = node;
            }

            temp = temp.next[idx];
        }

        temp.isLeaf = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return searchRecurring(word, 0, trieNode);
    }

    private static boolean searchRecurring(String word, int index, TrieNodeArrays trieNode) {

        if(index == word.length() && trieNode.isLeaf) {
            return true;
        }

        if(index >= word.length()) {
            return false;
        }

        TrieNodeArrays temp = trieNode;
        char c = word.charAt(index);


            if(c == '.') {
                for(int i = 0; i < 26; i++) {
                    if(trieNode.next[i] != null) {
                        if(searchRecurring(word, index + 1, trieNode.next[i])) {
                            return true;
                        }
                    }
                }
            } else {
                int idx = c - 'a';

                if(temp.next[idx] != null) {
                    return searchRecurring(word, index + 1, trieNode.next[idx]);
                } else {
                    return false;
                }
            }


        return false;
    }



}


class TrieNodeArrays {
    TrieNodeArrays[] next;
    boolean isLeaf;

    TrieNodeArrays() {
        next = new TrieNodeArrays[26];
    }

}

// HASHMAP IMPL OF TRIE NODE: FOR PERFORMANCE CONCERNS (TODO) -> WIL HAVE TO USE ARRAYS
//    TrieNodeHashMap trieNodeHashMap = null;
//
//    /** Initialize your data structure here. */
//    public AddAndSearchWordDataStructDesign() {
//        trieNodeHashMap = new TrieNodeHashMap();
//    }
//
//    /** Adds a word into the data structure. */
//    public void addWord(String word) {
//        if(word == null || word.isEmpty()) {
//            System.out.println("cannot add empty or null words");
//            return;
//        }
//
//        HashMap<Character, TrieNodeHashMap> children = trieNodeHashMap.children;
//
//        for(int i = 0; i  < word.length(); i++) {
//            char c = word.charAt(i);
//
//            TrieNodeHashMap temp;
//            if(children.containsKey(c)) {
//                temp = children.get(c);
//            } else {
//                temp = new TrieNodeHashMap(c);
//                children.put(c, temp);
//            }
//
//            children = temp.children;
//
//            if(i == word.length() - 1) {
//                temp.isLeaf = true;
//            }
//        }
//
//    }
//
//    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
//    public boolean search(String word) {
//        HashMap<Character, TrieNodeHashMap> children = trieNodeHashMap.children;
//
//        if(children == null) {
//            return false;
//        }
//
//        for(int i = 0; i < word.length(); i++) {
//            char c = word.charAt(i);
//
//            TrieNodeHashMap temp;
//            if(children.containsKey(c)) {
//                temp = children.get(c);
//            } else {
//                return false;
//            }
//
//            if(i == word.length() - 1 && !temp.isLeaf) {
//                return false;
//            }
//
//            children = temp.children;
//        }
//
//        return true;
//    }
//}
//
//class TrieNodeHashMap {
//    boolean isLeaf;
//    char c;
//
//    HashMap<Character, TrieNodeHashMap> children = new HashMap<>();
//
//    TrieNodeHashMap() {}
//    TrieNodeHashMap(char c) {
//        this.c = c;
//    }
//}


