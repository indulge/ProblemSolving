package sg.practice;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

class Node<T> {
    T data;

    public Node(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "[" + data + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(data, node.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}

/**
 * Assumes there are no disconnected nodes when testing.
 * Simple re-write of graph for practice.
 */
public interface GraphT<T> {

    Map<Node<T>, List<Node<T>>> graphAdjacency();

    Map<Node<T>, List<Node<T>>> reverseAdjacency();

    List<Node<T>> graphBfs();
    List<Node<T>> graphDfs();

    List<Node<String>> graphDfs(Node<String> startNode);
    List<Node<String>> graphBfs(Node<String> startNode);

    default List<Node<T>> bfsProcessor(Consumer<Node<T>> nodeProcessor) {
        List<Node<T>> bfs = graphBfs();
        bfs.forEach(
                node -> nodeProcessor.accept(node)
        );
        return bfs;
    }

    default List<Node<T>> dfsProcessor(Consumer<Node<T>> nodeProcessor) {
        List<Node<T>> dfs = graphDfs();
        dfs.forEach(
                node -> nodeProcessor.accept(node)
        );
        return dfs;
    }

    default void bfsPrinter() {
        bfsProcessor(GraphT::nodePrinter);
    }

    default void dfsPrinter() {
        dfsProcessor(GraphT::nodePrinter);
    }

    // From node1 -> node2
    default void addEdge(Node<T> node1, Node<T> node2) {
        graphAdjacency()
                .computeIfAbsent(node1, k -> new ArrayList<>())
                .add(node2);
        reverseAdjacency()
                .computeIfAbsent(node2, k -> new ArrayList<>())
                .add(node1);

    }

    default void addEdge(T data1, T data2) {
        Node<T> node1 = new Node<>(data1);
        Node<T> node2 = new Node<>(data2);
        addEdge(node1, node2);
    }

    private static <T> void nodePrinter(Node<T> node) {
        System.out.print("[" + node.data + "]");
    }
}

// use queue for BFS, stack for DFS.
// int values for data.
class Graph1 implements GraphT<String> {
    Map<Node<String>, List<Node<String>>> graphAdjacency = new HashMap<>();
    Map<Node<String>, List<Node<String>>> reverseAdjacency = new HashMap<>();

    @Override
    public Map<Node<String>, List<Node<String>>> graphAdjacency() {
        return graphAdjacency;
    }

    @Override
    public Map<Node<String>, List<Node<String>>> reverseAdjacency() {
        return reverseAdjacency;
    }

    @Override
    public List<Node<String>> graphBfs() {
        Set<Node<String>> startNodes = getRootNodes();
        System.out.println("Root Nodes: " + startNodes);
        List<Node<String>> fullBfs = new ArrayList<>();
        Map<Node<String>, Integer> nodeVisit = new HashMap<>();
        if (startNodes != null) {
            startNodes.forEach(
                    node -> {
                        if (nodeVisit.putIfAbsent(node, Integer.valueOf(1)) == null) {
                            List<Node<String>> bfs = graphBfs(node, nodeVisit);
                            fullBfs.addAll(bfs);
                        }
                    }
            );
        }
        return fullBfs;
    }

    @Override
    public List<Node<String>> graphBfs(Node<String> startNode) {
        return graphBfs(startNode, new HashMap<>());
    }

    @Override
    public List<Node<String>> graphDfs(Node<String> startNode) {
        return graphDfs(startNode, new HashMap<>());
    }

    @Override
    public List<Node<String>> graphDfs() {
        Set<Node<String>> startNodes = getRootNodes();
        System.out.println("Root Nodes: " + startNodes);
        List<Node<String>> fullDfs = new ArrayList<>();
        if (startNodes != null) {
            Map<Node<String>, Integer> nodeVisit = new HashMap<>();
            startNodes.forEach(
                    node -> {
                        if (nodeVisit.get(node) == null) {
                            List<Node<String>> dfs = graphDfs(node, nodeVisit);
                            fullDfs.addAll(dfs);
                        }
                    }
            );
        }
        return fullDfs;
    }

    private List<Node<String>> graphBfs(Node<String> startNode, Map<Node<String>, Integer> nodeVisit) {
        List<Node<String>> bfs = new ArrayList<>();
        Queue<Node<String>> bfsQueue = new LinkedList<>();
        bfsQueue.add(startNode);

        // Value null = not visited, 1 = in queue, 2 = processed.
        nodeVisit.put(startNode, Integer.valueOf(1));
        while (!bfsQueue.isEmpty()) {

            // Process the node...
            Node<String> nodeToProcess = bfsQueue.remove();
            System.out.println("Processing: " + nodeToProcess);
            nodeVisit.put(nodeToProcess, Integer.valueOf(2));
            bfs.add(nodeToProcess);
            // End process node.

            List<Node<String>> nextNodes = graphAdjacency.get(nodeToProcess);
            if (nextNodes != null) {
                nextNodes.forEach(
                        node -> {
                            Integer visitStatus = nodeVisit.putIfAbsent(node, Integer.valueOf(1));
                            if (visitStatus == null) {
                                bfsQueue.add(node);
                            } else if (visitStatus == 1) {
                                System.out.println("Cycle found at node: " + node);
                            }
                        }
                );
            }
        }
        return bfs;
    }

    private List<Node<String>> graphDfs(Node<String> startNode, Map<Node<String>, Integer> nodeVisit) {
        List<Node<String>> dfs = new ArrayList<>();
        Stack<Node<String>> dfsStack = new Stack<>();

        dfsStack.add(startNode);
        nodeVisit.put(startNode, Integer.valueOf(1));
        while (!dfsStack.empty()) {
            List<Node<String>> nextNodes = graphAdjacency.get(dfsStack.peek());
            int stackSize = dfsStack.size();
            if (nextNodes != null) {
                nextNodes.forEach(
                        node -> {
                            Integer visitStatus = nodeVisit.get(node);
                            if (visitStatus == null) {
                                dfsStack.push(node);
                                nodeVisit.put(node, Integer.valueOf(1));
                            } else if (visitStatus == 1) {
                                System.out.println("Cycle found at node: " + node);
                            }
                        }
                );
            }

            if (stackSize == dfsStack.size()) {
                // Process the node...
                Node<String> nodeToProcess = dfsStack.pop();
                System.out.println("Processing: " + nodeToProcess);
                nodeVisit.put(nodeToProcess, Integer.valueOf(2));
                dfs.add(nodeToProcess);
                // End process node.
            }
        }
        return dfs;
    }

    private Set<Node<String>> getRootNodes() {
        return graphAdjacency
                .keySet()
                .stream()
                .filter(node -> reverseAdjacency.get(node) == null)
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        GraphT<String> sample1 = buildSample1();
        System.out.println("Adjacency: " + sample1.graphAdjacency());
        List<Node<String>> bfs1 = sample1.graphDfs(new Node<>("s"));
        System.out.println("BFS: From: S => " + bfs1);

        List<Node<String>> bfs2 = sample1.graphBfs();
        System.out.println("BFS: " + bfs2);

        List<Node<String>> dfs2 = sample1.graphDfs();
        System.out.println("DFS: " + dfs2);
    }

    public static GraphT<String> buildSample1() {
        Graph1 graph = new Graph1();
        graph.addEdge("s", "x");
        graph.addEdge("s", "a");
        graph.addEdge("a", "z");
        graph.addEdge("x", "d");
        graph.addEdge("x", "c");
        graph.addEdge("c", "f");
        graph.addEdge("c", "v");
        graph.addEdge("d", "f");
        graph.addEdge("f", "v");
        graph.addEdge("p", "v");
        return graph;
    }

    public static GraphT<String> buildSampleWithCycle() {
        Graph1 graph = new Graph1();
        graph.addEdge("s", "x");
        graph.addEdge("s", "a");
        graph.addEdge("a", "z");
        graph.addEdge("x", "d");
        graph.addEdge("x", "c");
        graph.addEdge("c", "f");
        graph.addEdge("c", "v");
        graph.addEdge("d", "f");
        graph.addEdge("f", "v");
        graph.addEdge("v", "c"); //introduces cycle.
        graph.addEdge("p", "v");
        return graph;
    }
}
