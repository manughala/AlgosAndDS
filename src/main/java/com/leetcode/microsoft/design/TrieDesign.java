package com.leetcode.microsoft.design;

/**
 * Implement a trie with insert, search, and startsWith methods.

 Example:

 Trie trie = new Trie();

 trie.insert("apple");
 trie.search("apple");   // returns true
 trie.search("app");     // returns false
 trie.startsWith("app"); // returns true
 trie.insert("app");
 trie.search("app");     // returns true
 Note:

 You may assume that all inputs are consist of lowercase letters a-z.
 All inputs are guaranteed to be non-empty strings.

 * @author Santosh Manughala (SM030146).
 */
public class TrieDesign {

    public static void main(String args[]) {
        String word = "apple";

        TrieDesign trie = new TrieDesign();
        trie.insert(word);
        System.out.println("Expected true, actual : " + trie.search(word));
        System.out.println("Expected true, actual : " + trie.search("apple"));
        System.out.println("Expected false, actual : " + trie.search("app"));
        System.out.println("Expected true, actual : " + trie.startsWith("app"));
        trie.insert("app");
        System.out.println("Expected true, actual : " + trie.search("app"));
    }

    Trie trie;
    /** Initialize your data structure here. */
    public TrieDesign() {
        this.trie = new Trie();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie tempTrie = this.trie;

        for(int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';

            if(tempTrie.children[idx] == null) {
                tempTrie.children[idx] = new Trie();
            }

            if(i == word.length() - 1) {
                tempTrie.isLeaf = true;
            }

            tempTrie = tempTrie.children[idx];
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie tempTrie = this.trie;

        for(int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';

            if(tempTrie.children[idx] == null) {
                return false;
            }

            if(i == word.length() - 1) {
                return tempTrie.isLeaf;
            }

            tempTrie = tempTrie.children[idx];
        }

        return false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie tempTrie = this.trie;

        for(char c : prefix.toCharArray()) {
            int idx = c - 'a';

            if(tempTrie.children[idx] == null) {
                return false;
            }

            tempTrie = tempTrie.children[idx];
        }

        return true;
    }
}

class Trie {
    Trie[] children;
    boolean isLeaf;

    public Trie() {
        children = new Trie[26];
    }
}
