package com.mhhe.clrs2e.tests;
/************************************************************************
 *
 * 1. This software is for the purpose of demonstrating one of many
 * ways to implement the algorithms in Introduction to Algorithms,
 * Second edition, by Thomas H. Cormen, Charles E. Leiserson, Ronald
 * L. Rivest, and Clifford Stein.  This software has been tested on a
 * limited set of test cases, but it has not been exhaustively tested.
 * It should not be used for mission-critical applications without
 * further testing.
 *
 * 2. McGraw-Hill licenses and authorizes you to use this software
 * only on a microcomputer located within your own facilities.
 *
 * 3. You will abide by the Copyright Law of the United States.
 *
 * 4. You may prepare a derivative version of this software provided
 * that your source code indicates that it is based on this software and
 * also that you have made changes to it.
 *
 * 5. If you believe that you have found an error in this software,
 * please send email to clrs-java-bugs@mhhe.com.  If you have a
 * suggestion for an improvement, please send email to
 * clrs-java-suggestions@mhhe.com.
 *
 ***********************************************************************/

// SSSPTest.java
// Tests single-source shortest-paths algorithms from Chapter 24 of
// Introduction to Algorithms, Second edition.

import com.mhhe.clrs2e.*;

public class SSSPTest
{
    // Tests Bellman-Ford.  Emulates the example in Figure 24.4 on
    // page 589.
    public static void testBF()
    {
	System.out.println("Testing Bellman-Ford");

	WeightedAdjacencyListGraph g = new WeightedAdjacencyListGraph(5, true);

	Vertex s = new Vertex("s");
	Vertex t = new Vertex("t");
	Vertex x = new Vertex("x");
	Vertex y = new Vertex("y");
	Vertex z = new Vertex("z");

	g.addVertex(s);
	g.addVertex(t);
	g.addVertex(x);
	g.addVertex(y);
	g.addVertex(z);

	g.addEdge(s, t, 6);
	g.addEdge(s, y, 7);
	g.addEdge(t, x, 5);
	g.addEdge(t, y, 8);
	g.addEdge(t, z, -4);
	g.addEdge(x, t, -2);
	g.addEdge(y, x, -3);
	g.addEdge(y, z, 9);
	g.addEdge(z, s, 2);
	g.addEdge(z, x, 7);

	System.out.println("Before Bellman-Ford:");
	System.out.print(g);

	BellmanFord bf = new BellmanFord(g);
	bf.computeShortestPaths(s);

	System.out.println("\nBellman-Ford returns " +
			   bf.hasNoNegativeWeightCycle());
	for (int i = 0; i < 5; i++) {
	    System.out.println(g.getVertex(i) + ": " +
			       bf.getShortestPathInfo(i));
	}
    }

    // Tests Dijkstra's algorithm.  Emulates the example in Figure
    // 24.6 on page 596.
    public static void testDijkstra()
    {
	System.out.println("\n\nTesting Dijkstra");

	WeightedAdjacencyListGraph g = new WeightedAdjacencyListGraph(5, true);

	Vertex s = new Vertex("s");
	Vertex t = new Vertex("t");
	Vertex x = new Vertex("x");
	Vertex y = new Vertex("y");
	Vertex z = new Vertex("z");

	g.addVertex(s);
	g.addVertex(t);
	g.addVertex(x);
	g.addVertex(y);
	g.addVertex(z);

	g.addEdge(s, t, 10);
	g.addEdge(s, y, 5);
	g.addEdge(t, x, 1);
	g.addEdge(t, y, 2);
	g.addEdge(x, z, 4);
	g.addEdge(y, t, 3);
	g.addEdge(y, x, 9);
	g.addEdge(y, z, 2);
	g.addEdge(z, s, 7);
	g.addEdge(z, x, 6);

	System.out.println("\nBefore Dijkstra:");
	System.out.print(g);

	Dijkstra dijk = new Dijkstra(g);
	dijk.computeShortestPaths(s);

	System.out.println("\nAfter Dijkstra:");
	for (int i = 0; i < 5; i++) {
	    System.out.println(g.getVertex(i) + ": " +
			       dijk.getShortestPathInfo(i));
	}
    }

    // Tests the DAG-Shortest-Paths algorithm.  Emulates the example
    // in Figure 24.5 on page 593.
    public static void testDAGSP()
    {
	System.out.println("\n\nTesting DAGShortestPaths");

	WeightedAdjacencyListGraph g = new WeightedAdjacencyListGraph(6, true);

	Vertex r = new Vertex("r");
	Vertex s = new Vertex("s");
	Vertex t = new Vertex("t");
	Vertex x = new Vertex("x");
	Vertex y = new Vertex("y");
	Vertex z = new Vertex("z");

	g.addVertex(r);
	g.addVertex(s);
	g.addVertex(t);
	g.addVertex(x);
	g.addVertex(y);
	g.addVertex(z);

	g.addEdge(r, s, 5);
	g.addEdge(r, t, 3);
	g.addEdge(s, t, 2);
	g.addEdge(s, x, 6);
	g.addEdge(t, x, 7);
	g.addEdge(t, y, 4);
	g.addEdge(t, z, 2);
	g.addEdge(x, y, -1);
	g.addEdge(x, z, 1);
	g.addEdge(y, z, -2);

	System.out.println("\nBefore DAGShortestPaths:");
	System.out.print(g);

	DAGShortestPaths dagSP = new DAGShortestPaths(g);
	dagSP.computeShortestPaths(s);

	System.out.println("\nAfter DAGShortestPaths:");
	for (int i = 0; i < 6; i++) {
	    System.out.println(g.getVertex(i) + ": " +
			       dagSP.getShortestPathInfo(i));
	}
    }

    public static void main(String[] args)
    {
	testBF();
	testDijkstra();
	testDAGSP();
    }
}

