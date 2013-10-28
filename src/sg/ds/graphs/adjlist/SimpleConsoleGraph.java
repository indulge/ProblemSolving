/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.ds.graphs.adjlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author sachin
 */
public class SimpleConsoleGraph {

    Map<Integer, List<Integer>> graph = null;  //simple adjacency list map with int value as key node and list of adjacent nodes as value
    Map<Integer, Integer> statusMap = new HashMap<Integer, Integer>();
    Map<Integer, Integer> distanceMap = new HashMap<Integer, Integer>();
    Map<Integer, Integer> parentMap = new HashMap<Integer, Integer>();
    Map<Integer, Integer> startTime = new HashMap<Integer, Integer>();
    Map<Integer, Integer> endTime = new HashMap<Integer, Integer>();
    
    int timeStamp = 1;
    SampleGraph sampleGraph = new SampleGraph();

    public SimpleConsoleGraph() {
        graph = new HashMap<Integer, List<Integer>>();
        //initGraph();
        initSampleGraph();
    }

    private void initSampleGraph() {
        graph = sampleGraph.graph2();

    }

    public void printGraph() {
        Set<Integer> nodes = graph.keySet();
        System.out.println("\nGraph Adjacency List:");
        System.out.println("Node Set:" + nodes);
        for (Integer node : nodes) {
            List<Integer> adjNodes = graph.get(node);
            System.out.println(node + "->" + adjNodes);
        }
        System.out.println("------------------------");
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

    public static void main(String[] args) {
        SimpleConsoleGraph scg = new SimpleConsoleGraph();
        scg.printGraph();
//        scg.dfs();
        scg.bfs();
    }

//    DFS(G)
//1  for each vertex u Ã¢Ë†Ë† V [G]
//2       do color[u] Ã¢â€ ï¿½ WHITE
//3          Ã�â‚¬[u] Ã¢â€ ï¿½ NIL
//4  time Ã¢â€ ï¿½ 0
//5  for each vertex u Ã¢Ë†Ë† V [G]
//6       do if color[u] = WHITE
//7             then DFS-VISIT(u)
//
//DFS-VISIT(u)
//1  color[u] Ã¢â€ ï¿½ GRAY     Ã¢â€“Â¹White vertex u has just been discovered.
//2  time Ã¢â€ ï¿½ time +1
//3  d[u] time
//4  for each v Ã¢Ë†Ë† Adj[u]  Ã¢â€“Â¹Explore edge(u, v).
//5       do if color[v] = WHITE
//6             then Ã�â‚¬[v] Ã¢â€ ï¿½ u
//7                         DFS-VISIT(v)
//8  color[u] BLACK      Ã¢â€“Â¹ Blacken u; it is finished.
//9  f [u] Ã¢â€“Â¹ time Ã¢â€ ï¿½ time +1
//
    private void initDS() {
        distanceMap.clear();
        statusMap.clear();
        parentMap.clear();
    }

    public void dfs() {
        initDS();
        System.out.println("\n-----------DFS-----------------");
        Set<Integer> nodes = graph.keySet();
        for (Integer node : nodes) {
            statusMap.put(node, 1);

        }



        nodes = statusMap.keySet();
        for (Integer node : nodes) {
            if (statusMap.get(node) == 1) {


//                statusMap.put(node,2);
//                startTime.put(node, timeStamp);
//                timeStamp++;
                dfsSourceRec(node);
                //dfsSource(node);

            }

        }
        System.out.println("------------------------");
        System.out.println("--------Distance Map----------------");
        System.out.println(distanceMap);
        System.out.println("------------------------");
        System.out.println("--------Parent Map----------------");
        System.out.println(parentMap);
        System.out.println("------------------------");
        System.out.println("--------Time Stamp----------------");
        nodes = graph.keySet();
        for (Integer node : nodes) {
            System.out.println(node + "->" + startTime.get(node) + "/" + endTime.get(node));
        }
        System.out.println("------------------------");
    }

//BFS(G, s)
// 1  for each vertex u Ã¢Ë†Ë† V [G] - {s}
// 2       do color[u] Ã¢â€ ï¿½ WHITE
// 3          d[u] Ã¢â€ ï¿½ Ã¢Ë†Å¾
// 4          Ã�â‚¬[u] Ã¢â€ ï¿½ NIL
// 5  color[s] Ã¢â€ ï¿½ GRAY
// 6  d[s] Ã¢â€ ï¿½ 0
// 7  Ã�â‚¬[s] Ã¢â€ ï¿½ NIL
// 8  Q Ã¢â€ ï¿½ ÃƒËœ
// 9  ENQUEUE(Q, s)
//10  while Q Ã¢â€°Â  ÃƒËœ
//11      do u Ã¢â€ ï¿½ DEQUEUE(Q)
//12         for each v Ã¢Ë†Ë† Adj[u]
//13             do if color[v] = WHITE
//14                   then color[v] Ã¢â€ ï¿½ GRAY
//15                        d[v] Ã¢â€ ï¿½ d[u] + 1
//16                        Ã�â‚¬[v] Ã¢â€ ï¿½ u
//17                        ENQUEUE(Q, v)
//18         color[u] Ã¢â€ ï¿½ BLACK
//
    public void bfs() {
        initDS();
        System.out.println("\n-----------BFS-----------------");
        Set<Integer> nodes = graph.keySet();
        for (Integer node : nodes) {
            statusMap.put(node, 1);
            distanceMap.put(node, Integer.MAX_VALUE);
            parentMap.put(node, null);
        }
        nodes = statusMap.keySet();
        for (Integer node : nodes) {
            if (statusMap.get(node) == 1) {
                distanceMap.put(node, 0);
                parentMap.put(node, null);
                bfsSource(node);
            }

        }
        System.out.println("------------------------");
        System.out.println("--------Distance Map----------------");
        System.out.println(distanceMap);
        System.out.println("------------------------");
        System.out.println("--------Parent Map----------------");
        System.out.println(parentMap);
        System.out.println("------------------------");

    }

     private void dfsSource(Integer sourceNode) {
        Stack<Integer> adjNodeStack = new Stack<Integer>();
        adjNodeStack.push(sourceNode);
        System.out.println("Pushing Node:" + sourceNode);
        startTime.put(sourceNode, timeStamp);
        timeStamp++;
        statusMap.put(sourceNode, 2);
        Integer currNode = sourceNode;

        int numPush=1;
        while (numPush>0) {

            List<Integer> adjNodes = graph.get(currNode);
            numPush=0;
            if (adjNodes != null) {

                for (Integer node : adjNodes) {
                    if (statusMap.get(node) == 1) {
                        numPush++;
                        adjNodeStack.push(node);
                        System.out.println("Pushing Node:" + node);
                        statusMap.put(node, 2);
                        startTime.put(node, timeStamp);
                        timeStamp++;
                        currNode=node;
                        break;
                    }
                }
            }
        }   //every thing is on stack now
        while (!adjNodeStack.isEmpty()) {
            currNode = adjNodeStack.pop();
            statusMap.put(currNode, 3);
            System.out.println("Processing Node:" + currNode);
            endTime.put(currNode, timeStamp);
            timeStamp++;
        }
    }

     private void dfsSourceRec(Integer sourceNode) {
        if (sourceNode == null) {
            return;
        }
        List<Integer> adjNodes = graph.get(sourceNode);
        if (adjNodes == null) {
            return;
        }
        for (Integer node : adjNodes) {

            //check distance from each adj node regardless of status
            if (statusMap.get(node) == 1) {
                statusMap.put(node, 2);
                startTime.put(node, timeStamp);
                timeStamp++;
                dfsSourceRec(node);
            }
        }
        statusMap.put(sourceNode, 3);
        System.out.println("Processing Node:" + sourceNode);
        endTime.put(sourceNode, timeStamp);
        timeStamp++;
    }

    private void bfsSource(Integer sourceNode) {
        //init all nodes to never visited
        //create a queue
        //add source node(first node) to q and process until q is empty
        //process q top, set as status 3, add all the adjacent nodes to q if they are status 1, set status as 2
        //
        //1=never visited, 2=in Queue, 3=already processed
        Queue<Integer> adjNodeQ = new LinkedList<Integer>();

        adjNodeQ.add(sourceNode);
        statusMap.put(sourceNode, 2);
        distanceMap.put(sourceNode, 0);
        parentMap.put(sourceNode, null);

        while (!adjNodeQ.isEmpty()) {
            Integer currNode = adjNodeQ.remove();
            statusMap.put(currNode, 3);
            System.out.println("Processing Node:" + currNode);
            List<Integer> adjNodes = graph.get(currNode);
            if (adjNodes == null) {
                break;
            }
            for (Integer node : adjNodes) {

                //check distance from each adj node regardless of status
                Integer prevNodeDistance = distanceMap.get(currNode);
                if (statusMap.get(node) == 1) {
                    adjNodeQ.add(node);
                    statusMap.put(node, 2);
                    parentMap.put(node, currNode);
                    distanceMap.put(node, prevNodeDistance + 1);
                }
            }
        }

    }
}
