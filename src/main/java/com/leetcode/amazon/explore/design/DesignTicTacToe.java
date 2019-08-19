package com.leetcode.amazon.explore.design;

/**
 * Design a Tic-tac-toe game that is played between two players on a n x n grid.

 You may assume the following rules:

 A move is guaranteed to be valid and is placed on an empty block.
 Once a winning condition is reached, no more moves is allowed.
 A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 Example:
 Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

 TicTacToe toe = new TicTacToe(3);

 toe.move(0, 0, 1); -> Returns 0 (no one wins)
 |X| | |
 | | | |    // Player 1 makes a move at (0, 0).
 | | | |

 toe.move(0, 2, 2); -> Returns 0 (no one wins)
 |X| |O|
 | | | |    // Player 2 makes a move at (0, 2).
 | | | |

 toe.move(2, 2, 1); -> Returns 0 (no one wins)
 |X| |O|
 | | | |    // Player 1 makes a move at (2, 2).
 | | |X|

 toe.move(1, 1, 2); -> Returns 0 (no one wins)
 |X| |O|
 | |O| |    // Player 2 makes a move at (1, 1).
 | | |X|

 toe.move(2, 0, 1); -> Returns 0 (no one wins)
 |X| |O|
 | |O| |    // Player 1 makes a move at (2, 0).
 |X| |X|

 toe.move(1, 0, 2); -> Returns 0 (no one wins)
 |X| |O|
 |O|O| |    // Player 2 makes a move at (1, 0).
 |X| |X|

 toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
 |X| |O|
 |O|O| |    // Player 1 makes a move at (2, 1).
 |X|X|X|
 Follow up:
 Could you do better than O(n2) per move() operation?



 * @author Santosh Manughala (SM030146).
 */
public class DesignTicTacToe {

    public static void main(String args[]) {
        DesignTicTacToe ticTacToe = new DesignTicTacToe(3);
        System.out.println("0,0,1: " + ticTacToe.move(0, 0, 1));
        System.out.println("0,2,2: " + ticTacToe.move(0, 2, 2));
        System.out.println("2,2,1: " + ticTacToe.move(2, 2, 1));
        System.out.println("1,1,2: " + ticTacToe.move(1, 1, 2));
        System.out.println("2,0,1: " + ticTacToe.move(2, 0, 1));
        System.out.println("1,0,2: " + ticTacToe.move(1, 0, 2));
        System.out.println("2,1,1: " + ticTacToe.move(2, 1, 1));

        // NOTE: Ask the interviewer what we should do when there are moves after a player won?
        System.out.println("1,2,1: " + ticTacToe.move(1, 2, 1));
    }

    int[] rows;
    int[] cols;
    int diagonal, antiDiagonal;

    /** Initialize your data structure here. */
    public DesignTicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        diagonal = 0;
        antiDiagonal = 0;
    }

    // Time: O(1)
    // Space: O(n)
    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int toAdd = player == 1 ? 1 : -1;

        rows[row] += toAdd;
        cols[col] += toAdd;

        if(row == col) {
            diagonal += toAdd;
        }

        int size = rows.length;

        if(col == size - 1 - row) {
            antiDiagonal += toAdd;
        }

        if(Math.abs(rows[row]) == size || Math.abs(cols[col]) == size || Math.abs(diagonal) == size || Math.abs(antiDiagonal) == size) {
            return player;
        }

        return 0;
    }



//    int[][] grid;
//
//    /** Initialize your data structure here. */
//    public DesignTicTacToe(int n) {
//        grid = new int[n][n];
//    }
//
//    // Time O(n)
//    // Space O(n^2)
//    /** Player {player} makes a move at ({row}, {col}).
//     @param row The row of the board.
//     @param col The column of the board.
//     @param player The player, can be either 1 or 2.
//     @return The current winning condition, can be either:
//     0: No one wins.
//     1: Player 1 wins.
//     2: Player 2 wins. */
//    public int move(int row, int col, int player) {
//        if (row >= grid.length || col >= grid.length) {
//            return 0;
//        }
//
//        // already used cell
//       // NOTE: Ask the interviewer what we should do when there is already a player
//        if (grid[row][col] != 0) {
//            return -1;
//        }
//
//        grid[row][col] = player == 1 ? 1 : 2;
//
//        if(checkVeriticalWin(col, player) || checkHorizontalWin(row, player) || checkDiagonalWin(player) || checkAntiDiagonalWin(player)) {
//            return player;
//        }
//
//        return 0;
//    }
//
//    private boolean checkVeriticalWin(int col, int player) {
//        for(int i = 0; i < grid.length; i++) {
//            if(grid[i][col] != player) {
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//    private boolean checkHorizontalWin(int row, int player) {
//        for(int i = 0; i < grid.length; i++) {
//            if(grid[row][i] != player) {
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//    private boolean checkDiagonalWin(int player) {
//        for(int i = 0; i < grid.length; i++) {
//            if(grid[i][i] != player) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean checkAntiDiagonalWin(int player) {
//        for(int i = 0; i < grid.length; i++) {
//            if(grid[i][grid.length - 1 - i] != player) {
//                return false;
//            }
//        }
//        return true;
//    }
}
