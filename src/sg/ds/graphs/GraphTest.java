package sg.ds.graphs;

public class GraphTest {
    public static void main(String[] args) {
//        SimpleGraph test = SimpleGraph.buildSample1();
//        test.bfs(1);

        SimpleGraph test2 = SimpleGraph.buildSample2();
        test2.bfs("s");
        test2.bfs();

        test2.dfs("s");
        test2.topologicalSort("s");
        test2.topologicalSort();
    }
}
