package com.leetcode.facebook.treesandgraphs;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 Example 1:

 Input:
 11110
 11010
 11000
 00000

 Output: 1
 Example 2:

 Input:
 11000
 11000
 00100
 00011

 Output: 3

 * @author Santosh Manughala (SM030146).
 */
public class NumberOfIslands {

    public static void main (String[] args) throws java.lang.Exception
    {
        char M[][]=  new char[][]
                       {{'1', '1', '0', '0', '0'},
                        {'0', '1', '0', '0', '1'},
                        {'1', '0', '0', '1', '1'},
                        {'0', '0', '0', '0', '0'},
                        {'1', '0', '1', '0', '1'}
                };
//        char M[][]=  new char[][]
//                {{'1', '1', '1', '1', '0'},
//                {'1', '1', '0', '1', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '0', '0', '0'}
//                };

        System.out.println("Number of islands is: " + numIslandsSpaceComplicated(M));
        System.out.println("Number of islands is: " + numIslandsBestCase(M));
    }

    //Time O(hw) space O(1)
    // NOTE: here we are replacing the given grid -> 1 is replaced with 2.
    // if we have to fix it, we have to reorder the grid
    public static int numIslandsBestCase(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }

        int height = grid.length, width = grid[0].length, numOfIslands = 0;

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(grid[i][j] == '1') {
                    ++numOfIslands;
                    markNeighboursBestCase(grid, i, j);
                }
            }
        }

        for(int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(grid[i][j] == '2') {
                    grid[i][j] = '1';
                }
            }
        }
        return numOfIslands;
    }

    private static void markNeighboursBestCase(char[][] grid, int i, int j) {
        if(i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1 || grid[i][j] != '1') {
            return;
        }

        grid[i][j] = '2';

        markNeighboursBestCase(grid, i + 1, j);
        markNeighboursBestCase(grid, i - 1, j);
        markNeighboursBestCase(grid, i, j + 1);
        markNeighboursBestCase(grid, i, j - 1);

    }

    //Time O(hw) space O(hw)
    public static int numIslandsSpaceComplicated(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }

        int height = grid.length, width = grid[0].length, numOfIslands = 0;
        boolean[][] visited = new boolean[height][width];

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(!visited[i][j] && grid[i][j] == '1') {
                    ++numOfIslands;
                    markNeighboursSpaceComplicated(grid, i, j, visited);
                }
            }
         }
         return numOfIslands;
    }

    private static void markNeighboursSpaceComplicated(char[][] grid, int i, int j, boolean[][] visited) {
        if(i < 0 || i > grid.length-1 || j < 0 || j > grid[0].length-1 || visited[i][j] || grid[i][j] != '1') {
            return;
        }

        visited[i][j] = true;

        markNeighboursSpaceComplicated(grid, i+1, j, visited);
        markNeighboursSpaceComplicated(grid, i-1, j, visited);
        markNeighboursSpaceComplicated(grid, i, j+1, visited);
        markNeighboursSpaceComplicated(grid, i, j-1, visited);
    }


}
