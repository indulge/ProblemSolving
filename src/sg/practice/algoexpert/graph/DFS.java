package sg.practice.algoexpert.graph;

import java.util.ArrayList;
import java.util.List;

class DFS {
    class Program {
        // Do not edit the class below except
        // for the depthFirstSearch method.
        // Feel free to add new properties
        // and methods to the class.
        class Node {
            String name;
            List<Node> children = new ArrayList<Node>();

            public Node(String name) {
                this.name = name;
            }

            public List<String> depthFirstSearch(List<String> array) {
                depthFirstSearch(this, array);
                return array;
            }

            private void depthFirstSearch(Node root, List<String> array) {
                if (root != null) {
                    array.add(root.name);
                    System.out.println(root.name);
                    for (Node next : root.children) {
                        depthFirstSearch(next, array);
                    }
                }
            }

            public Node addChild(String name) {
                Node child = new Node(name);
                children.add(child);
                return this;
            }
        }
    }
}