package com.leetcode.microsoft.treesandgraphs;

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

    public static void main(String arg[]) {
        char[][] grid1 = new char[][] {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };

        char[][] grid2 = new char[4][5];
        grid2[0][0] = '1';
        grid2[0][1] = '1';
        grid2[0][2] = '0';
        grid2[0][3] = '0';
        grid2[0][4] = '0';

        grid2[1][0] = '1';
        grid2[1][1] = '1';
        grid2[1][2] = '0';
        grid2[1][3] = '0';
        grid2[1][4] = '0';

        grid2[2][0] = '0';
        grid2[2][1] = '0';
        grid2[2][2] = '1';
        grid2[2][3] = '0';
        grid2[2][4] = '0';

        grid2[3][0] = '0';
        grid2[3][1] = '0';
        grid2[3][2] = '0';
        grid2[3][3] = '1';
        grid2[3][4] = '1';

        System.out.println("numberOfIslands: Expected = 1, Actual = " + numberOfIslands(grid1));
        System.out.println("numberOfIslands: Expected = 3, Actual = " + numberOfIslands(grid2));

        System.out.println("numberOfIslandsExtraSpace: Expected = 1, Actual = " + numberOfIslandsExtraSpace(grid1));
        System.out.println("numberOfIslandsExtraSpace: Expected = 3, Actual = " + numberOfIslandsExtraSpace(grid2));

    }

    private static int numberOfIslands(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }

        int numOfIslands = 0;

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    numOfIslands++;
                    markNeighbours(i, j, grid);
                }
            }
        }
        // The diff is we are not using extra space for boolean array to keep track of visited cells,
        // instead updating the existing one. Should ask interviewer if we have to keep the gri intact.
        // We can change it using below logic, if we have to
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '2') {
                    grid[i][j] = '1';
                }
            }
        }
        return numOfIslands;
    }

    private static void markNeighbours(int i, int j, char[][] grid) {
        if(i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1 || grid[i][j] != '1') {
            return;
        }

        grid[i][j] = '2';

        markNeighbours(i + 1, j, grid);
        markNeighbours(i - 1, j, grid);
        markNeighbours(i, j + 1, grid);
        markNeighbours(i, j - 1, grid);
    }

    private static int numberOfIslandsExtraSpace(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int numOfIslands = 0;

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1' && !visited[i][j]) {
                    numOfIslands++;
                    markNeighboursExtraSpace(i, j, grid, visited);
                }
            }
        }
        return numOfIslands;
    }

    private static void markNeighboursExtraSpace(int i, int j, char[][] grid, boolean[][] visited) {
        if(i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1 || visited[i][j] || grid[i][j] != '1') {
            return;
        }

        visited[i][j] = true;

        markNeighboursExtraSpace(i + 1, j, grid, visited);
        markNeighboursExtraSpace(i - 1, j, grid, visited);
        markNeighboursExtraSpace(i, j + 1, grid, visited);
        markNeighboursExtraSpace(i, j - 1, grid, visited);
    }
}
