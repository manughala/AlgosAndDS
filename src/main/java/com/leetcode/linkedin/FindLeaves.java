package com.leetcode.linkedin;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.



 Example:

 Input: [1,2,3,4,5]

 1
 / \
 2   3
 / \
 4   5

 Output: [[4,5,3],[2],[1]]


 Explanation:

 1. Removing the leaves [4,5,3] would result in this tree:

 1
 /
 2


 2. Now removing the leaf [2] would result in this tree:

 1


 3. Now removing the leaf [1] would result in the empty tree:

 []

 * @author Santosh Manughala (SM030146).
 */
public class FindLeaves {

     // Definition for a binary tree node.
     public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;

         TreeNode(int x) {
             val = x;
         }
     }

     public static void main(String args[]) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

//        List<List<Integer>> result = findLeavesI(root);
        List<List<Integer>> result = findLeavesII(root);
        int level = 0;
        for(List<Integer> list : result) {
            System.out.println(level++);
            for(int i : list) {
                System.out.println(i);
            }
        }
     }

    //Time: O(n)
    // space O(n)
     private static List<List<Integer>> findLeavesII(TreeNode root) {
         List<List<Integer>> result = new ArrayList<>();
         List<Integer> list = new ArrayList<>();

         while(root != null) {
             list = new ArrayList<>();
             root = findLeavesRecurII(root, list);
             result.add(list);
         }
         return result;
     }

    private static TreeNode findLeavesRecurII(TreeNode root, List<Integer> list) {
         if(root == null) {
             return null;
         }

         if(root.left == null && root.right == null) {
             list.add(root.val);
             return null;
         }

         if(root.left != null) {
             root.left = findLeavesRecurII(root.left, list);
         }

         if(root.right != null) {
             root.right = findLeavesRecurII(root.right, list);
         }

         return root;
    }

    //Time: O(n)
    // space O(n)
    private static List<List<Integer>> findLeavesI(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        findLeavesRecurI(root, result);
        return result;
    }

     private static int findLeavesRecurI(TreeNode root, List<List<Integer>> result) {
         if(root == null) {
             return -1;
         }

         int height = Math.max(findLeavesRecurI(root.left, result), findLeavesRecurI(root.right, result)) + 1;

         if(height == result.size()) {
             result.add(new ArrayList<>());
         }

         result.get(height).add(root.val);

         //optional -> ask interviewer if we have to replace the leaf nodes
         root.left = root.right = null;

         return height;
     }
}
