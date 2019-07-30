package com.leetcode.facebook.treesandgraphs;

/**
 * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/description/
 *
 * Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.

 Let's take the following BST as an example, it may help you understand the problem better:





 We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

 The figure below shows the circular doubly linked list for the BST above. The "head" symbol means the node it points to is the smallest element of the linked list.





 Specifically, we want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.

 The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.




 * @author Santosh Manughala (SM030146).
 */
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};

public class BinaryTreeToSortedDoublyLinkedList {


    public static void main (String args[]) {
        Node node1 = new Node(1, null, null);
        Node node3 = new Node(3, null, null);
        Node node2 = new Node(2, node1, node3);
        Node node5 = new Node(5, null, null);
        Node node4 = new Node(4, node2, node5);

        Node result = treeToDoublyListI(node4);
//        Node result = treeToDoublyListII(node4);
    }

    static Node prev = null;
    // O(n) space O(1) -> I think htis is also inplace, as we are not using any arbituary space.. we are using dummy though is it  inplace turely.. idont know
    // but these approaches are using :
  //  step1: inorder tranversal by recursion to connect the original BST
   // step2: connect the head and tail to make it circular
    private static Node treeToDoublyListI(Node root) {
        if (root == null) return null;
        Node dummy = new Node(0, null, null);
        prev = dummy;
        helper(root);
        //connect head and tail
        prev.right = dummy.right;
        dummy.right.left = prev;
        return dummy.right;
    }

    private static void helper (Node cur) {
        if (cur == null) return;
        helper(cur.left);
        prev.right = cur;
        cur.left = prev;
        // BETTER WAY link(prev, cur);
        prev = cur;
        helper(cur.right);
    }

    /*
dfs, L, R,
Link L(head, tail) -> curr -> R(head, tail)
Return head in dfs
In main func: return head, smallest element
*/
    // O(n) space O(1) -> I think htis is inplace
    public static Node treeToDoublyListII(Node root) {
        if (root == null) return root;
        if (root.left == null && root.right == null) {
            link(root, root);
            return root;
        }

        // link them
        Node leftHead = treeToDoublyListII(root.left); // left head
        Node rightHead = treeToDoublyListII(root.right); // right head

        Node leftTail = leftHead != null ? leftHead.left : null;
        Node rightTail = rightHead != null ? rightHead.left : null;

        Node head = null;
        if (rightHead == null) {
            head = leftHead;
            link(leftTail, root);
            link(root, leftHead);
        } else if (leftHead == null){
            head = root;
            link(root, rightHead);
            link(rightTail, root);
        } else { // both leftHead,rightHead exist
            head = leftHead;
            link(leftTail, root);
            link(root, rightHead);
            link(rightTail, leftHead);
        }

        return head;
    }

    private static void link(Node nodeA, Node nodeB) {
        nodeA.right = nodeB;
        nodeB.left = nodeA;
    }
}
