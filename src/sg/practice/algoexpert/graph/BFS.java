package sg.practice.algoexpert.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BFS {
    class Template {
        class Program {
            // Do not edit the class below except
            // for the breadthFirstSearch method.
            // Feel free to add new properties
            // and methods to the class.
            class Node {
                String name;
                List<Node> children = new ArrayList<Node>();

                public Node(String name) {
                    this.name = name;
                }

                public List<String> breadthFirstSearch(List<String> array) {
                    // Write your code here.
                    return null;
                }

                public Node addChild(String name) {
                    Node child = new Node(name);
                    children.add(child);
                    return this;
                }
            }
        }
    }

    class Solution1 {
        class Program {
            // Do not edit the class below except
            // for the breadthFirstSearch method.
            // Feel free to add new properties
            // and methods to the class.
            class Node {
                String name;
                List<Node> children = new ArrayList<Node>();

                public Node(String name) {
                    this.name = name;
                }

                public List<String> breadthFirstSearch(List<String> array) {
                    Queue<Node> queue = new LinkedList<>();
                    queue.add(this);
                    while (!queue.isEmpty()) {
                        Node processNode = queue.remove();
                        array.add(processNode.name);
                        queue.addAll(processNode.children);
                    }
                    return array;
                }

                public Node addChild(String name) {
                    Node child = new Node(name);
                    children.add(child);
                    return this;
                }
            }
        }
    }
}