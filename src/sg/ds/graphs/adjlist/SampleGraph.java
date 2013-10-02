/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.ds.graphs.adjlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sachin
 */
public class SampleGraph {

    Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();

    public Map<Integer, List<Integer>> graph1() {
        graph = new HashMap<Integer, List<Integer>>();

        for (int i = 1; i <= 10; i++) {
            addNode(i);
        }
        addEdge(1, 2);
        //addEdge(1, 8);
        //addEdge(1, 9);

        addEdge(2, 1);
        addEdge(2, 3);
        addEdge(2, 8);

        addEdge(3, 2);
        addEdge(3, 9);
        addEdge(3, 6);
        addEdge(3, 4);

        addEdge(4, 3);
        addEdge(4, 6);
        addEdge(4, 5);

        addEdge(5, 4);
        addEdge(5, 6);

        addEdge(6, 7);
        addEdge(6, 3);
        addEdge(6, 4);
        addEdge(6, 5);

        addEdge(7, 9);
        addEdge(7, 8);
        addEdge(7, 6);

        addEdge(8, 1);
        addEdge(8, 2);
        addEdge(8, 9);
        addEdge(8, 7);

        addEdge(9, 3);
        addEdge(9, 7);
        addEdge(9, 8);
        return graph;
    }

    public Map<Integer, List<Integer>> graph2() {
        graph = new HashMap<Integer, List<Integer>>();
        for (int i = 1; i <= 6; i++) {
            addNode(i);
        }
        addEdge(1, 2);
        addEdge(1, 4);
        addEdge(2, 5);
        addEdge(3, 5);
        addEdge(3, 6);
        addEdge(4, 2);
        addEdge(5, 4);
        addEdge(6, 6);
        
        return graph;
    }

    public void addNode(Integer newNode) {
        if (graph.containsKey(newNode)) {
            System.out.println("(graph.containsKey(newNode))");
            return;
        }
        graph.put(newNode, null);

    }

    public void addEdge(Integer fromNode, Integer toNode) {
        if (fromNode == null || toNode == null) {
            System.out.println("(fromNode==null || toNode==null)");
            return;
        }
        List<Integer> adjList = graph.get(fromNode);
        if (adjList == null) {
            adjList = new ArrayList<Integer>();
            graph.put(fromNode, adjList);
        }
        adjList.add(toNode);
    }
}
