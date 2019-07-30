package com.leetcode.facebook.treesandgraphs;

import com.leetcode.facebook.linkedlists.ReverseLinkedLists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

 If two nodes are in the same row and column, the order should be from left to right.

 Examples 1:

 Input: [3,9,20,null,null,15,7]

 3
 /\
 /  \
 9  20
 /\
 /  \
 15   7

 Output:

 [
 [9],
 [3,15],
 [20],
 [7]
 ]
 Examples 2:

 Input: [3,9,8,4,0,1,7]

 3
 /\
 /  \
 9   8
 /\  /\
 /  \/  \
 4  01   7


 Output:

 [
 [4],
 [9],
 [3,0,1],
 [8],
 [7]
 ]
 Examples 3:

 Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

 3
 /\
 /  \
 9   8
 /\  /\
 /  \/  \
 4  01   7
 /\
 /  \
 5   2

 Output:

 [
 [4],
 [9,5],
 [3,0,1],
 [8,2],
 [7]
 ]

 * @author Santosh Manughala (SM030146).
 */
public class BinaryTreeVertical {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String args[]) {
        /* creating a binary tree and entering the nodes */
//        TreeNode tree = new TreeNode(1);
//        tree.left = new TreeNode(2);
//        tree.left.left = new TreeNode(3);
//        tree.left.right = new TreeNode(4);
//        tree.left.right.left = new TreeNode(5);
//        tree.left.right.right = new TreeNode(6);
//        tree.right = new TreeNode(7);
//        tree.right.right = new TreeNode(8);
//        tree.right.right.right = new TreeNode(9);
//        tree.right.right.right.left = new TreeNode(10);
//        tree.right.right.right.left.right = new TreeNode(11);
//        tree.right.right.right.right = new TreeNode(12);

//        TreeNode tree = new TreeNode(3);
//        tree.left = new TreeNode(9);
//        tree.left.left = new TreeNode(4);
//        tree.left.right = new TreeNode(0);
//        tree.right = new TreeNode(8);
//        tree.right.right = new TreeNode(7);
//        tree.right.left = new TreeNode(1);
//
//        TreeNode doesNotWorkTree = new TreeNode(1);
//        doesNotWorkTree.left = new TreeNode(2);
//        doesNotWorkTree.left.left = new TreeNode(4);
//        doesNotWorkTree.left.right = new TreeNode(5);
//        doesNotWorkTree.right = new TreeNode(3);
//        doesNotWorkTree.right.left = new TreeNode(6);
//        doesNotWorkTree.right.left.right = new TreeNode(8);
//        doesNotWorkTree.right.right = new TreeNode(7);
//        doesNotWorkTree.right.right.right = new TreeNode(9);
//        doesNotWorkTree.right.right.left = new TreeNode(10);
//        doesNotWorkTree.right.right.left.right = new TreeNode(11);
//        doesNotWorkTree.right.right.left.right.right = new TreeNode(12);
//        doesNotWorkTree.right.right.left.right.right.right = new TreeNode(14);

        TreeNode tree = new TreeNode(1);

//        TreeNode tree = new TreeNode(1);
//        tree.left = new TreeNode(2);
//        tree.left.right = new TreeNode(3);
//        tree.right = new TreeNode(4);

//        verticalOrderBruteForceI(tree);
//        verticalOrderBruteForceII(doesNotWorkTree);
        // NOTE: the bruteforce I and II will print in pre order and also, from the treemap sorted by horizontal distance
        // If we were to have a case where the horizontal distance hd is greater than the next row, we get that first.
        // checkout doesNotWorkTree as an exmaple, where 12 is supposed be printed after 9, since it is in the same veritical row, but 12 is printed before 9.
        // SO we have to do level order traversal
        verticalOrderLevelOrderTraversal(tree);

    }

    private static void verticalOrderLevelOrderTraversal(TreeNode node) {
        List<List<Integer>> result = new ArrayList<>();
        if(node == null) {
            return;
        }

        if(node.left == null && node.right == null) {
            List<Integer> list = new ArrayList<>();
            list.add(node.val);
            result.add(list);
            return;
        }

        Map<Integer, List<Integer>> verticalLevelToNodeValue = new HashMap<>();

        LinkedList<TreeNode> nodes = new LinkedList<>();
        LinkedList<Integer> levels = new LinkedList<>();

        nodes.add(node);
        levels.add(0);

        int minLevel = 0;
        int maxLevel = 0;

        while(!nodes.isEmpty()) {
            int level = levels.poll();
            TreeNode currentNode = nodes.poll();

            minLevel = Math.min(level, minLevel);
            maxLevel = Math.max(level, maxLevel);

            if(verticalLevelToNodeValue.containsKey(level)) {
                verticalLevelToNodeValue.get(level).add(currentNode.val);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(currentNode.val);
                verticalLevelToNodeValue.put(level, list);
            }

            if(currentNode.left != null) {
                nodes.add(currentNode.left);
                levels.add(level-1);
            }

            if(currentNode.right != null) {
                nodes.add(currentNode.right);
                levels.add(level+1);
            }
        }

        for(int i=minLevel; i<=maxLevel; i++){
            if(verticalLevelToNodeValue.containsKey(i)){
                result.add(verticalLevelToNodeValue.get(i));
                System.out.println(verticalLevelToNodeValue.get(i));
            }
        }
    }

    public static List<List<Integer>> verticalOrderBruteForceII(TreeNode root) {
        Map<Integer, List<Integer>> hdToNodeValues = new TreeMap<>();
        getVerticalOrderBruteForceII(root, 0, hdToNodeValues);
        for(List<Integer> value : hdToNodeValues.values()) {
            System.out.println(value);
        }

        return new ArrayList<>(hdToNodeValues.values());
    }

    private static  void getVerticalOrderBruteForceII(TreeNode node, int hd, Map<Integer, List<Integer>> hdToNodeValues) {

        if(node == null) {
            return;
        }

        List<Integer> values = hdToNodeValues.get(hd);
        if(values == null) {
            List list = new ArrayList();
            list.add(node.val);
            hdToNodeValues.put(hd, list);
        } else {
            values.add(node.val);
        }

        getVerticalOrderBruteForceII(node.left, hd-1, hdToNodeValues);
        getVerticalOrderBruteForceII(node.right, hd+1, hdToNodeValues);
    }


    // NOTE in this we are getting the min and max before to go from min to max
    // But we can skip that completely -> done in verticalOrderBruteForceII
    public static void verticalOrderBruteForceI(TreeNode root) {
        int hd = 0;
        findMinMax(root, hd);

        Map<Integer, List<Integer>> hdToNodeValue = new TreeMap<>();
        for(int i = min; i <= max; i++) {
            getVerticalOrderBruteForceI(root, hd, i, hdToNodeValue);
        }

        List<List<Integer>> list = new ArrayList<>();
        for(List<Integer> value : hdToNodeValue.values()) {
            list.add(value);
            System.out.println(value);
        }

        System.out.println();

    }

    private static void getVerticalOrderBruteForceI(TreeNode node, int hd, int current, Map<Integer, List<Integer>> hdToNodeValue) {
        if(node == null) {
            return;
        }

        if(hd == current) {
            if(hdToNodeValue.containsKey(hd)) {
                List<Integer> list = hdToNodeValue.get(hd);
                list.add(node.val);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(node.val);
                hdToNodeValue.put(hd, list);
            }
        }

        getVerticalOrderBruteForceI(node.left, hd-1, current, hdToNodeValue);
        getVerticalOrderBruteForceI(node.right, hd+1, current, hdToNodeValue);
    }

    private static int min;
    private static int max;
    private static void findMinMax(TreeNode node, int hd) {
        if(node == null) {
            return;
        }

        if (hd < min) {
            min = hd;
        } else if (hd > max) {
            max = hd;
        }

        findMinMax(node.left, hd - 1);
        findMinMax(node.right, hd + 1);
    }
}
