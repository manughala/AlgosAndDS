package com.leetcode.facebook.treesandgraphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

/**
 *
 * Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph. Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.



 Example:



 Input:
 {"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":[{"$ref":"2"},{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}

 Explanation:
 Node 1's value is 1, and it has two neighbors: Node 2 and 4.
 Node 2's value is 2, and it has two neighbors: Node 1 and 3.
 Node 3's value is 3, and it has two neighbors: Node 2 and 4.
 Node 4's value is 4, and it has two neighbors: Node 1 and 3.


 Note:

 The number of nodes will be between 1 and 100.
 The undirected graph is a simple graph, which means no repeated edges and no self-loops in the graph.
 Since the graph is undirected, if node p has node q as neighbor, then node q must have node p as neighbor too.
 You must return the copy of the given node as a reference to the cloned graph.

 * @author Santosh Manughala (SM030146).
 */
public class CloneGraph {
    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val,List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    };

    public static void main(String args[]) {
        Node node1 = new Node(1, new ArrayList<>());
        Node node2 = new Node(2, new ArrayList<>());
        Node node3 = new Node(3, new ArrayList<>());
        Node node4 = new Node(4, new ArrayList<>());
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node3);
        node4.neighbors.add(node1);

        cloneGraph(node1);
        cloneGraphAnotherWayMoreSpace(node1);

    }

    public static void cloneGraph(Node node) {
        System.out.println("BEFORE: ");
        printDFS(node);

        if(node == null) {
            return;
        }

        LinkedList<Node> queue = new LinkedList<> ();
        Map<Node, Node> nodeToClonedNodeMap = new HashMap<>();

        queue.add(node);
        nodeToClonedNodeMap.put(node, new Node(node.val, new ArrayList<>()));

        while(!queue.isEmpty()) {
            Node temp = queue.pop();
            Node currentNode = nodeToClonedNodeMap.get(temp);

            if(temp.neighbors != null) {
                for(Node neighbour: temp.neighbors) {
                    Node neighbourClonedNode = nodeToClonedNodeMap.get(neighbour);
                    if(neighbourClonedNode == null) {
                        queue.add(neighbour);
                        neighbourClonedNode = new Node(neighbour.val, new ArrayList<>());
                        nodeToClonedNodeMap.put(neighbour, neighbourClonedNode);
                    }

                    currentNode.neighbors.add(neighbourClonedNode);
                }
            }
        }

        System.out.println("AFTER: ");
        printDFS(nodeToClonedNodeMap.get(node));
    }

    // add all the cloned nodes first, then use additional set to add neighbours
    public static void cloneGraphAnotherWayMoreSpace(Node node) {
        System.out.println("BEFORE: ");
        printDFS(node);

        if(node == null) {
            return;
        }

        Map<Node, Node> nodeToClonedNodeMap = new HashMap<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()) {
            Node temp = queue.poll();
            nodeToClonedNodeMap.put(temp, new Node(temp.val, new ArrayList<>()));

            if(temp.neighbors != null) {
                for(Node neighbour : temp.neighbors) {
                    if(!nodeToClonedNodeMap.containsKey(neighbour)) {
                        queue.add(neighbour);
                    }
                }
            }
        }


        queue.add(node);
        Set<Node> nodeSet = new HashSet<>();
        nodeSet.add(node);

        while(!queue.isEmpty()) {
            Node temp = queue.poll();

            if(temp.neighbors != null) {
                for(Node neighbour : temp.neighbors) {
                    if(!nodeSet.contains(neighbour)) {
                        queue.add(neighbour);
                        nodeSet.add(neighbour);
                    }
                    nodeToClonedNodeMap.get(temp).neighbors.add(nodeToClonedNodeMap.get(neighbour));
                }
            }
        }

        System.out.println("AFTER: ");
        printDFS(nodeToClonedNodeMap.get(node));
    }

    private static void printDFS (Node node) {
        Stack<Node> stack = new Stack<>();
        stack.add(node);

        Map<Node, List<Node>> nodeToNeighbours = new HashMap<>();

        while(!stack.isEmpty()) {
            Node temp = stack.pop();
            if(!nodeToNeighbours.containsKey(temp)) {
                System.out.println("node: " + temp.val);
                System.out.println("reference: " + temp);
                List<Node> neighbours = temp.neighbors;
                nodeToNeighbours.put(temp, neighbours);

                for(Node neighbour : neighbours) {
                    System.out.println("neighbour: " + neighbour.val);
                    stack.add(neighbour);
                }
            }
        }

    }


}
