
// Definition for a Node.

import java.util.ArrayList;
import java.util.List;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

class L133CloneGraph {
    // Intuition: Go over the nodes of the original graph, for each node if the node
    // has not yet been created , create it, and set its children
    public Node cloneGraph(Node node) {
        if (node == null || node.neighbors == null) {
            return node;
        }
        Node clone = new Node();
        clone.val = node.val;
        Node[] memory = new Node[100];
        memory[node.val - 1] = clone;
        RecursiveClone(node, clone, memory);
        return clone;
    }

    private void RecursiveClone(Node original, Node clone, Node[] memory) {
        if (original.neighbors == null || original.neighbors.isEmpty()) {
            return;
        } else {
            clone.neighbors = new ArrayList<>();
            for (Node neighbor : original.neighbors) {
                Node neighborClone = memory[neighbor.val - 1];
                if (neighborClone == null) {
                    neighborClone = new Node();
                    neighborClone.val = neighbor.val;
                    memory[neighbor.val - 1] = neighborClone;
                    RecursiveClone(neighbor, neighborClone, memory);
                }
                clone.neighbors.add(neighborClone);
            }
        }
    }
}