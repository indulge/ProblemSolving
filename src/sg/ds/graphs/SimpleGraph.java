package sg.ds.graphs;


import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.function.Consumer;

public class SimpleGraph<T> {

    Map<GraphNode<T>, List<GraphNode<T>>> graph = new HashMap<>();

    //Edge properties (Weights only for now)
    Map<Pair<GraphNode<T>, GraphNode<T>>, Integer> edgeWeights = new HashMap<>();
    Map<GraphNode<T>, List<GraphNode<T>>> reverseGraph = new HashMap<>();

    public void addNode(T nodeData) {
        GraphNode<T> node = new GraphNode<>(nodeData);
        addNode(node);
    }

    public void addNodes(T... nodes) {
        for (T node : nodes) {
            addNode(node);
        }
    }

    public List<GraphNode<T>> getNextVertices(GraphNode<T> node) {
        return graph.get(node);
    }

    public void addNode(GraphNode<T> node) {
        if (!graph.containsKey(node)) {
            graph.put(node, new ArrayList<>());
        }
        if (!reverseGraph.containsKey(node)) {
            reverseGraph.put(node, new ArrayList<>());
        }

    }

    public void addEdge(T fromNode, T toNode, Integer weight) {
        addEdge(new GraphNode(fromNode), new GraphNode(toNode), weight);
    }

    public void addEdge(T fromNode, T toNode) {
        addEdge(new GraphNode(fromNode), new GraphNode(toNode), 0);
    }

    private void addEdge(GraphNode<T> fromNode, GraphNode<T> toNode, Integer weight) {
        if (!graph.containsKey(fromNode)) {
            addNode(fromNode);
        }
        if (!graph.containsKey(toNode)) {
            addNode(toNode);
        }
        graph.get(fromNode).add(toNode);
        reverseGraph.get(toNode).add(fromNode);
        edgeWeights.put(new ImmutablePair<>(fromNode, toNode), weight);
    }

    public List<GraphNode> topSort() {
        Map<GraphNode, GraphNode> parentMap = new HashMap<>();
        final List<GraphNode> topSortNodes = new ArrayList<>();
        reverseGraph.forEach((k, v) -> {
            if (v.size() == 0) {
                System.out.println("Topsorting for Node: " + k);
                topSortNodes.addAll(topSortRec(k.data, Optional.of(parentMap)));
            }
        });
        Collections.reverse(topSortNodes);
        System.out.println("topSort():" + topSortNodes);
        return topSortNodes;
    }

    public List<GraphNode> topSort(T startNode) {
        return topSortRec(startNode, Optional.empty());
    }

    /**
     * Topological sort using DFS recursive.
     */
    public List<GraphNode> topSortRec(T startNode,
                                   Optional<Map<GraphNode, GraphNode>> parentTracker) {
        Map<GraphNode, GraphNode> parentMap = parentTracker.orElse(new HashMap<>());
        final List<GraphNode> topSortNodes = new ArrayList<>();
        dfsRecursive(new GraphNode<T>(startNode),
                (node) -> {
                    System.out.println("SortedNode:" + node);
                    topSortNodes.add(node);
                },
                parentMap);
        System.out.println(topSortNodes);
        return topSortNodes;
    }

    public void dfs(T startNode) {
        Map<GraphNode, GraphNode> parentMap = new HashMap<>();
        dfsRecursive(new GraphNode<T>(startNode),
                (node) -> System.out.println("DFS Visit: " + node),
                parentMap);
    }

    public void dfs(GraphNode<T> startNode) {
        Map<GraphNode, GraphNode> parentMap = new HashMap<>();
        dfsRecursive(startNode,
                (node) -> System.out.println("DFS Visit: " + node),
                parentMap);
    }

    /**
     * DFS - Recursive.
    */
    private void dfsRecursive(GraphNode startNode,
                              Consumer<GraphNode> visitor,
                              Map<GraphNode, GraphNode> parentMap) {
        List<GraphNode<T>> adjList = graph.get(startNode);
        for (GraphNode next : adjList) {
            if (!parentMap.containsKey(next)) {
                parentMap.put(next, startNode);
                dfsRecursive(next, visitor, parentMap);
            }
        }
        visitor.accept(startNode);
    }

    public void bfs() {
        Map<GraphNode, Integer> levelMap = new HashMap<>();
        reverseGraph.forEach((k, v) -> {
            if (v.size() == 0) {
                System.out.println("Travelling for Node: " + k);
                if (!levelMap.containsKey(k)) {
                    bfs(k.data, Optional.of(levelMap));
                }
            }
        });
    }

    public void bfs(T startNode) {
        bfs(startNode, Optional.empty());
    }

    public void bfs(T startNode, Optional<Map<GraphNode, Integer>> levelTracker) {
        Map<GraphNode, Integer> levelMap = levelTracker.orElse(new HashMap<>());
        bfs(startNode, levelMap);
        System.out.println("Final Levels: ");
        levelMap.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });
    }

    public void bfs(T startNode, Map<GraphNode, Integer> levelMap) {
        bfs(new GraphNode(startNode),
                node -> System.out.println("Visiting Node: " + node),
                levelMap);
    }

    public void bfs(GraphNode<T> startNode, Map<GraphNode, Integer> levelMap) {
        bfs(startNode,
                node -> System.out.println("Visiting Node: " + node),
                levelMap);
    }

    /**
     * BFS - Frontier method.
     * level map Maps node to level in graph.
     */
    public void bfs(GraphNode startNode,
                    Consumer<GraphNode> visitor,
                    Map<GraphNode, Integer> levelMap) {

        final List<GraphNode> frontier = new ArrayList<>();
        Map<GraphNode, GraphNode> parentPaths = new HashMap<>();

        parentPaths.put(startNode, null);
        frontier.add(startNode);

        int level = 1;
        while (frontier.size() > 0) {
            List<GraphNode> nextFrontier = new ArrayList<>();
            for (GraphNode node : frontier) {
                if (levelMap.get(node) == null) {
                    visitor.accept(node);
                    GraphNode parent = node;
                    levelMap.put(node, level);
                    for (GraphNode child : graph.get(node)) {
                        if (levelMap.get(child) == null) {
                            parentPaths.put(child, parent);
                            nextFrontier.add(child);
                        }
                    }
                }
            }
            frontier.clear();
            frontier.addAll(nextFrontier);
            level++;
        }
    }

    public static class GraphNode<T> {
        private final T data;

        public GraphNode(T data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GraphNode graphNode = (GraphNode) o;
            return data.equals(graphNode.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }

        @Override
        public String toString() {
            return "{" + data + "}";
        }
    }

    public static SimpleGraph buildSample1() {
        SimpleGraph<Integer> graph = new SimpleGraph<>();
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(5, 3);
        graph.addEdge(3, 6);
        graph.addEdge(4, 2);
        System.out.println(graph.graph);
        return graph;
    }

    public static SimpleGraph buildSample2() {
        SimpleGraph<String> graph = new SimpleGraph<>();
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
        System.out.println(graph.graph);

        return graph;
    }

    /**
     * https://youtu.be/ug7O1lSZyg0?t=743
     *
     * @return
     */
    private static SimpleGraph buildSample3() {
        SimpleGraph<Integer> graph = new SimpleGraph<>();
        graph.addNodes(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 3, 5);
        graph.addEdge(1, 4, 6);
        graph.addEdge(2, 5, 4);
        graph.addEdge(2, 6, 7);
        graph.addEdge(2, 7, 8);
        graph.addEdge(3, 5, 8);
        graph.addEdge(3, 6, 10);
        graph.addEdge(3, 7, 5);
        graph.addEdge(4, 5, 4);
        graph.addEdge(4, 6, 5);
        graph.addEdge(4, 7, 7);
        graph.addEdge(5, 8, 6);
        graph.addEdge(5, 9, 8);
        graph.addEdge(6, 8, 9);
        graph.addEdge(6, 9, 7);
        graph.addEdge(7, 8, 5);
        graph.addEdge(7, 9, 7);
        graph.addEdge(8, 10, 8);
        graph.addEdge(9, 10, 9);
        return graph;

    }

}

/**
 * Graph algorithms.
 */

/**
    DFS(G)
1  for each vertex u Ã¢Ë†Ë† V [G]
2       do color[u] Ã¢â€ ï¿½ WHITE
3          Ã â‚¬[u] Ã¢â€ ï¿½ NIL
4  time Ã¢â€ ï¿½ 0
5  for each vertex u Ã¢Ë†Ë† V [G]
6       do if color[u] = WHITE
7             then DFS-VISIT(u)

DFS-VISIT(u)
1  color[u] Ã¢â€ ï¿½ GRAY     Ã¢â€“Â¹White vertex u has just been discovered.
2  time Ã¢â€ ï¿½ time +1
3  d[u] time
4  for each v Ã¢Ë†Ë† Adj[u]  Ã¢â€“Â¹Explore edge(u, v).
5       do if color[v] = WHITE
6             then Ã â‚¬[v] Ã¢â€ ï¿½ u
7                         DFS-VISIT(v)
8  color[u] BLACK      Ã¢â€“Â¹ Blacken u; it is finished.
9  f [u] Ã¢â€“Â¹ time Ã¢â€ ï¿½ time +1
**/
