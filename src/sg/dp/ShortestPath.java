package sg.dp;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.builder.GraphBuilder;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;

import java.util.Iterator;

public class ShortestPath {

    public static void main(String[] args) {
//        SimpleGraph<Integer> graph = sg.ds.graphs.SimpleGraph.
//        bsfPrint(graph);

        //map of weight to dest 10 from given vertex.
        //vertex, weight
//        Map<Integer, Integer> weightToDest = new HashMap<>();
//        Integer dest = 10;
//        graph.vertexSet().forEach((vertex->{
//            //get list of edges from this node.
//
//            //if direct edge to dest, then add that
//            Set<DefaultWeightedEdge> edges = graph.outgoingEdgesOf(vertex);
//            edges.forEach((DefaultWeightedEdge edge) -> {
//                Integer currentMin = weightToDest.getOrDefault(vertex, Integer.MAX_VALUE);
//                Integer distanceFromEdge = weightToDest.getOrDefault(, Integer.MAX_VALUE);
//            });
//        }));
    }


    private static <T> void dfsPrint(Graph graph) {
        Iterator<String> iter = new DepthFirstIterator<>(graph);
        while (iter.hasNext()) {
            T vertex = (T)iter.next();
            System.out.println(
                    " " + vertex + " -> "
                            + graph.edgesOf(vertex).toString());
        }
    }

    private static <T> void bfsPrint(Graph graph) {
        Iterator<String> iter = new BreadthFirstIterator<>(graph);
        while (iter.hasNext()) {
            T vertex = (T)iter.next();
            System.out.println(
                    " " + vertex + " -> "
                            + graph.edgesOf(vertex).toString());
        }
    }

    private static Graph<Integer, DefaultWeightedEdge> buildEmptySimpleGraph()
    {
        return GraphTypeBuilder
                .<Integer, DefaultWeightedEdge> directed().allowingMultipleEdges(false)
                .allowingSelfLoops(false)
                .edgeClass(DefaultWeightedEdge.class)
                .weighted(true).buildGraph();
    }

    /**
     * https://youtu.be/ug7O1lSZyg0?t=743
     * @return
     */
    private static Graph<Integer, DefaultWeightedEdge> buildSampleProblemGraph()
    {
        return new GraphBuilder<>(buildEmptySimpleGraph())
                .addVertices(1,2,3,4,5,6,7,8,9,10)
                .addEdge(1, 2,5)
                .addEdge(1, 3, 5)
                .addEdge(1, 4, 6)
                .addEdge(2, 5, 4)
                .addEdge(2, 6, 7)
                .addEdge(2, 7, 8)
                .addEdge(3, 5, 8)
                .addEdge(3, 6, 10)
                .addEdge(3, 7, 5)
                .addEdge(4, 5, 4)
                .addEdge(4, 6, 5)
                .addEdge(4, 7, 7)
                .addEdge(5, 8, 6)
                .addEdge(5, 9, 8)
                .addEdge(6, 8, 9)
                .addEdge(6, 9, 7)
                .addEdge(7, 8, 5)
                .addEdge(7, 9, 7)
                .addEdge(8, 10, 8)
                .addEdge(9, 10, 9)
                .buildAsUnmodifiable();
    }

}
