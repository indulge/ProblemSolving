package sg.ds.tree.binary;

import java.util.*;

public class BinaryTree {

    private Node<Integer> root;

    public BinaryTree() {

    }

    public BinaryTree(Node<Integer> root) {
        this.root = root;
    }

    public Node<Integer> getRoot() {
        return root;
    }

    public void setRoot(Node<Integer> root) {
        this.root = root;
    }

    public void printTree() {
        BTreePrinter.printNode(root);
    }

    public void printTreeLevelOrder() {
        BTreePrinter.printNode(root);
    }

    public void addNodeLevelOrder(Integer data) {
        if (data == null) {
            return;
        }

        if (root == null) {
            root = new Node<>(data);
            return;
        }

        Node<Integer> curr = null;
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            curr = queue.remove();
            if (curr.left != null) {
                queue.add(curr.left);
            } else {
                curr.left = new Node<>(data);
                break;
            }
            if (curr.right != null) {
                queue.add(curr.right);
            } else {
                curr.right = new Node<>(data);
                break;
            }
        }
    }

    public void addNodeRandom(Integer data) {
        if (data == null) {
            return;
        }

        if (root == null) {
            root = new Node<>(data);
            return;
        }

        addNodeRandom(root, data);

    }

    private void addNodeRandom(Node<Integer> node, Integer data) {
        Node currNode = node;

        if (currNode.left == null) {
            currNode.left = new Node<>(data);

        } else if (currNode.right == null) {
            currNode.right = new Node<>(data);

        } else {
            int lr = (int) ((Math.random() * 10));
            if (lr % 2 == 0) {
                addNodeRandom(currNode.left, data);
            } else {
                addNodeRandom(currNode.right, data);
            }
        }
    }

    public static BinaryTree buildTreeLevelOrder(String input) {
        BinaryTree tree = new BinaryTree();
        String[] dataArr = input.split(" ");
        for (int i = 0; i < dataArr.length; i++) {
            tree.addNodeLevelOrder(Integer.parseInt(dataArr[i]));
        }
        return tree;
    }

    public static BinaryTree buildTreeRandom(String input) { // simple utility function to build
        BinaryTree tree = new BinaryTree();
        String[] dataArr = input.split(" ");
        for (int i = 0; i < dataArr.length; i++) {
                tree.addNodeRandom(Integer.parseInt(dataArr[i]));
        }
        return tree;
    }

    public static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        public Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private static class BTreePrinter {

        public static <T> void printNode(Node<T> root) {
            int maxLevel = BTreePrinter.maxLevel(root);
            printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        }

        private static <T> void printNodeInternal(List<Node<T>> nodes, int level, int maxLevel) {
            if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
                return;

            int floor = maxLevel - level;
            int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
            int firstSpaces = (int) Math.pow(2, (floor)) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

            BTreePrinter.printWhitespaces(firstSpaces);

            List<Node<T>> newNodes = new ArrayList<Node<T>>();
            for (Node<T> node : nodes) {
                if (node != null) {
                    System.out.print(node.data);
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    System.out.print(" ");
                }

                BTreePrinter.printWhitespaces(betweenSpaces);
            }
            System.out.println("");

            for (int i = 1; i <= endgeLines; i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    BTreePrinter.printWhitespaces(firstSpaces - i);
                    if (nodes.get(j) == null) {
                        BTreePrinter.printWhitespaces(endgeLines + endgeLines + i
                                + 1);
                        continue;
                    }

                    if (nodes.get(j).left != null)
                        System.out.print("/");
                    else
                        BTreePrinter.printWhitespaces(1);

                    BTreePrinter.printWhitespaces(i + i - 1);

                    if (nodes.get(j).right != null)
                        System.out.print("\\");
                    else
                        BTreePrinter.printWhitespaces(1);

                    BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
                }

                System.out.println("");
            }

            printNodeInternal(newNodes, level + 1, maxLevel);
        }

        private static void printWhitespaces(int count) {
            for (int i = 0; i < count; i++)
                System.out.print(" ");
        }

        private static <T> int maxLevel(Node<T> node) {
            if (node == null)
                return 0;

            return Math.max(BTreePrinter.maxLevel(node.left),
                    BTreePrinter.maxLevel(node.right)) + 1;
        }

        private static <T> boolean isAllElementsNull(List<T> list) {
            for (Object object : list) {
                if (object != null)
                    return false;
            }

            return true;
        }

    }
}














