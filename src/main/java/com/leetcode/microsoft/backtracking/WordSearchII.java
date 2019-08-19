package com.leetcode.microsoft.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.

 Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring.
 The same letter cell may not be used more than once in a word.



 Example:

 Input:
 board = [
 ['o','a','a','n'],
 ['e','t','a','e'],
 ['i','h','k','r'],
 ['i','f','l','v']
 ]
 words = ["oath","pea","eat","rain"]

 Output: ["eat","oath"]


 Note:

 All inputs are consist of lowercase letters a-z.
 The values of words are distinct.

 * @author Santosh Manughala (SM030146).
 */
public class WordSearchII {
    public static void main(String args[]) {
        char[][] board = new char[][] {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };

        List<String> words = new ArrayList<String>(){{
            add("oath");
            add("pea");
            add("eat");
            add("rain");
        }};

        List<String> wordsInTheBoard = getWordsInTheBoard(board, words);

        System.out.println("Words in the board: ");
        for(String word : wordsInTheBoard) {
            System.out.println(word);
        }
    }

    // Time:  O(M * N * 4L) - it iterates through the board of size (M * N) and it goes upto through the length of the string in the recursion stack in all 4 possible directions.
    // Space: O(1)
    private static List<String> getWordsInTheBoard(char[][] board, List<String> words) {
        List<String> result = new ArrayList<>(words.size());
        if(board == null || words == null || words.size() == 0) {
            return result;
        }

        Trie trie = buildTrie(words);

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                getWordsInTheBoard(trie, board, i, j, result);
            }
        }

        return result;
    }

    private static void getWordsInTheBoard(Trie trie, char[][] board, int i, int j, List<String> result) {
        char c = board[i][j];
        int idx = c - 'a';

        if(c == '#' || trie.children[idx] == null) {
            return;
        }

        board[i][j] = '#';

        trie = trie.children[idx];
        if(trie.word != null) {
            result.add(trie.word);
            trie.word = null;
        }

        if (i > 0) {
            getWordsInTheBoard(trie, board, i - 1, j, result);
        }

        if (j > 0) {
            getWordsInTheBoard(trie, board, i,  j - 1, result);
        }

        if (i < board.length - 1) {
            getWordsInTheBoard(trie, board, i + 1, j, result);
        }

        if (j < board[0].length - 1) {
            getWordsInTheBoard(trie, board, i , j + 1, result);
        }

        board[i][j] = c;
    }

    private static Trie buildTrie(List<String> words) {
        Trie trie = new Trie();

        for(String word : words) {
            Trie temp = trie;

            for(char c : word.toCharArray()) {
                int idx = c - 'a';

                if(temp.children[idx] == null) {
                    temp.children[idx] = new Trie();
                }

                temp = temp.children[idx];
            }

            temp.word = word;
        }

        return trie;
    }
}


class Trie {
    Trie[] children;
    String word;

    public Trie() {
        this.children = new Trie[26];
    }
}