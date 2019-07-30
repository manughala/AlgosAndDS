package com.leetcode.microsoft.backtracking;

/**
 * Given a 2D board and a word, find if the word exists in the grid.

 The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring.
 The same letter cell may not be used more than once.

 Example:

 board =
 [
 ['A','B','C','E'],
 ['S','F','C','S'],
 ['A','D','E','E']
 ]

 Given word = "ABCCED", return true.
 Given word = "SEE", return true.
 Given word = "ABCB", return false.


 * @author Santosh Manughala (SM030146).
 */
public class WordSearch {
    public static void main(String args[]) {
        char[][] board = new char[][] {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        System.out.println("Expected: true, Actual: "+ hasWord(board, "ABCCED"));
        System.out.println("Expected: true, Actual: "+ hasWord(board, "SEE"));
        System.out.println("Expected: false, Actual: "+ hasWord(board, "ABCB"));

        System.out.println("Expected: true, Actual: "+ hasWordNoExtraSpace(board, "ABCCED"));
        System.out.println("Expected: true, Actual: "+ hasWordNoExtraSpace(board, "SEE"));
        System.out.println("Expected: false, Actual: "+ hasWordNoExtraSpace(board, "ABCB"));
    }

    // Time:  O(M * N * 4L) - it iterates through the board of size (M * N) and it goes upto through the length of the string in the recursion stack in all 4 possible directions.
    // Space: O(1)
    // NOTE: NOT SURE WHY WE ARE GETTING FALSE FOR SECOND INPUT IN THIS CASE IN HERE> LEETCODE HAS IT RIGHT
    private static boolean hasWordNoExtraSpace(char[][] board, String word) {
        if(board == null || board.length == 0 || word == null || word.length() == 0) {
            return false;
        }

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == word.charAt(0) && hasWordRecurNoExtraSpace(i, j, board, word, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean hasWordRecurNoExtraSpace(int i, int j, char[][] board, String word, int index) {
        if(word.length() == index) {
            return true;
        }

        if(i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 || board[i][j] != word.charAt(index)) {
            return false;
        }

        char originalChar = board[i][j];
        board[i][j] = '0';

        if(hasWordRecurNoExtraSpace(i + 1, j, board, word, index + 1) ||
                hasWordRecurNoExtraSpace(i - 1, j, board, word, index + 1) ||
                hasWordRecurNoExtraSpace(i, j + 1, board, word, index + 1) ||
                hasWordRecurNoExtraSpace(i, j - 1, board, word, index + 1)) {
            return true;
        }

        board[i][j] = originalChar;
        return false;
    }

    // Time:  O(M * N * 4L) - it iterates through the board of size (M * N) and it goes upto through the length of the string in the recursion stack in all 4 possible directions.
    // Space: O(M * N)
    private static boolean hasWord(char[][] board, String word) {
        if(board == null || board.length == 0 || word == null || word.length() == 0) {
            return false;
        }

        boolean[][] visited = new boolean[board.length][board[0].length];

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == word.charAt(0) && hasWordRecur(i, j, board, word, 0, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean hasWordRecur(int i, int j, char[][] board, String word, int index, boolean[][] visited) {
        if(word.length() == index) {
            return true;
        }

        if(i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 || visited[i][j] || board[i][j] != word.charAt(index)) {
            return false;
        }

        visited[i][j] = true;
        if(hasWordRecur(i + 1, j, board, word, index + 1, visited) ||
        hasWordRecur(i - 1, j, board, word, index + 1, visited) ||
        hasWordRecur(i, j + 1, board, word, index + 1, visited) ||
        hasWordRecur(i, j - 1, board, word, index + 1, visited)) {
            return true;
        }

        visited[i][j] = false;
        return false;
    }
}
