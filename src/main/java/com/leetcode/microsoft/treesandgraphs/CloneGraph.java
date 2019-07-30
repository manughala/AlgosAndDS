package com.leetcode.microsoft.treesandgraphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
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
    private static class Node {
        int val;
        List<Node> neighbors;

        Node(int val, List<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }

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

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        System.out.println("ORIGINAL: " + printNodes(node1).val + "===============");

        System.out.println("CLONED BEST CASE:" + printNodes(cloneGraphBestCase(node1)).val + "===============");
        System.out.println("CLONED BRUTE FORCE:" + printNodes(cloneGraphBruteForce(node1)).val + "===============");
    }

    private static Node cloneGraphBestCase(Node node) {
        if(node == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        Map<Node, Node> originalToCloneNodeMap = new HashMap<>();
        originalToCloneNodeMap.put(node, new Node(node.val, new ArrayList<>()));

        while(!queue.isEmpty()) {
            Node originalNode = queue.poll();
            Node currentClonedNode = originalToCloneNodeMap.get(originalNode);

            if(originalNode.neighbors != null) {
                for(Node neighbor : originalNode.neighbors) {
                    if(!originalToCloneNodeMap.containsKey(neighbor)) {
                        Node clonedNeighbor = new Node(neighbor.val, new ArrayList<>());
                        originalToCloneNodeMap.put(neighbor, clonedNeighbor);
                        queue.add(neighbor);
                    }

                    currentClonedNode.neighbors.add(originalToCloneNodeMap.get(neighbor));
                }
            }

        }

        return originalToCloneNodeMap.get(node);
    }

    private static Node cloneGraphBruteForce(Node node) {
        if(node == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        Map<Node, Node> originalToCloneNodeMap = new HashMap<>();


        while(!queue.isEmpty()) {
            Node originalNode = queue.poll();
            originalToCloneNodeMap.put(originalNode, new Node(originalNode.val, new ArrayList<>()));

            if(originalNode.neighbors != null) {
                for (Node neighbor : originalNode.neighbors) {
                    if (!originalToCloneNodeMap.containsKey(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        queue.add(node);
        Set<Node> visited = new HashSet<>();
        visited.add(node);

        while (!queue.isEmpty()) {
            Node originalNode = queue.poll();
            Node cloneNode = originalToCloneNodeMap.get(originalNode);

            if(originalNode.neighbors != null) {
                for (Node neighbor : originalNode.neighbors) {
                    if(!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                    Node clonedNeighbor = originalToCloneNodeMap.get(neighbor);
                    cloneNode.neighbors.add(clonedNeighbor);
                }
            }

        }

        return originalToCloneNodeMap.get(node);
    }

    private static Node printNodes(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        Set<Node> visited = new HashSet<>();
        visited.add(node);

        while(!queue.isEmpty()) {
            Node temp = queue.poll();

            System.out.println("node: " + temp.val);
            System.out.println("neighbors: " );

            for(Node neighbor : temp.neighbors) {
                System.out.println(neighbor.val);

                if(!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return node;
    }
}
