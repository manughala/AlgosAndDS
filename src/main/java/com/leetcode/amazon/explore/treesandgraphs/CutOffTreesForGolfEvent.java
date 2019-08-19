package com.leetcode.amazon.explore.treesandgraphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:

 0 represents the obstacle can't be reached.
 1 represents the ground can be walked through.
 The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.


 You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).

 You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.

 You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.

 Example 1:

 Input:
 [
 [1,2,3],
 [0,0,4],
 [7,6,5]
 ]
 Output: 6


 Example 2:

 Input:
 [
 [1,2,3],
 [0,0,0],
 [7,6,5]
 ]
 Output: -1


 Example 3:

 Input:
 [
 [2,3,4],
 [0,0,5],
 [8,7,6]
 ]
 Output: 6
 Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.


 Hint: size of the given matrix will not exceed 50x50.

 * @author Santosh Manughala (SM030146).
 */
public class CutOffTreesForGolfEvent {

    public static void main(String args[]) {
        List<List<Integer>> forest = new ArrayList<>();
        List<Integer> forest1 = new ArrayList<>();
        forest1.add(1);
        forest1.add(2);
        forest1.add(3);
        forest.add(forest1);

        List<Integer> forest2 = new ArrayList<>();
        forest2.add(0);
        forest2.add(0);
        forest2.add(4);
        forest.add(forest2);

        List<Integer> forest3 = new ArrayList<>();
        forest3.add(7);
        forest3.add(6);
        forest3.add(5);
        forest.add(forest3);

        System.out.println("distance: " + cutOffTree(forest));
    }

    // Time: O(m^2*n^2)
    // Space: O(m*n)
    private static int cutOffTree(List<List<Integer>> forest) {

        List<int[]> trees = new ArrayList<>();

        for(int i = 0; i < forest.size(); i++) {
            for(int j = 0; j < forest.get(0).size(); j++) {
                int tree = forest.get(i).get(j);
                if(tree > 1) {
                    trees.add(new int[]{tree, i, j});
                }
            }
        }

//        Collections.sort(trees, (tree1, tree2) -> tree1[0] - tree2[0]);
        Collections.sort(trees, new Comparator<int[]>() {
            @Override
            public int compare(int[] tree1, int[] tree2) {
                return tree1[0] - tree2[0];
            }
        });

        int sr = 0, sc = 0, result = 0;

        for(int[] tree : trees) {
            int distance = dist(sr, sc, tree[1], tree[2], forest);
            if(distance < 0) {
                return -1;
            }
            result += distance;
            sr = tree[1];
            sc = tree[2];
        }

        return result;
    }

    static int dr[] = new int[]{1, -1, 0, 0};
    static int dc[] = new int[]{0, 0 , 1, -1};

    private static int dist(int sr, int sc, int tr, int tc, List<List<Integer>> forest) {
        boolean[][] visited = new boolean[forest.size()][forest.get(0).size()];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sr, sc, 0}); // source row, source col, distance
        visited[sr][sc] = true;

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();

            if(curr[0] == tr && curr[1] == tc) {
                return curr[2];
            }

            for(int d = 0; d < 4; d++) {
                int r = curr[0] + dr[d];
                int c = curr[1] + dc[d];

                if(r >= 0 && r < forest.size() && c >= 0 && c < forest.get(0).size() && !visited[r][c] && forest.get(r).get(c) > 0) {
                    queue.add(new int[]{r, c, curr[2] + 1});
                    visited[r][c] = true;
                }
            }
        }

        return -1;
    }
}
