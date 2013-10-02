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

// APSPTest.java
// Tests all-pairs shortest-paths algorithms from Sections 25.1 and
// 25.2 of Introduction to Algorithms, Second edition, on the example
// in Figure 25.1 on page 626.

import com.mhhe.clrs2e.*;

public class APSPTest
{
    public static void main(String[] args)
    {
	WeightedAdjacencyMatrixGraph g =
	    new WeightedAdjacencyMatrixGraph(5, true,
					     Double.POSITIVE_INFINITY);

	Vertex v1 = new Vertex("1");
	Vertex v2 = new Vertex("2");
	Vertex v3 = new Vertex("3");
	Vertex v4 = new Vertex("4");
	Vertex v5 = new Vertex("5");

	g.addVertex(v1);
	g.addVertex(v2);
	g.addVertex(v3);
	g.addVertex(v4);
	g.addVertex(v5);

	g.addEdge(v1, v2, 3);
	g.addEdge(v1, v3, 8);
	g.addEdge(v1, v5, -4);
	g.addEdge(v2, v4, 1);
	g.addEdge(v2, v5, 7);
	g.addEdge(v3, v2, 4);
	g.addEdge(v4, v1, 2);
	g.addEdge(v4, v3, -5);
	g.addEdge(v5, v4, 6);

	System.out.println("Graph:");
	System.out.println(g);

	double[][] slowResult =
	    (new SlowAllPairsShortestPaths()).computeShortestPaths(g);

	System.out.println("Shortest-path weights from Slow-All-Pairs-Shortest-Paths:");
	AllPairsShortestPaths.printMatrix(slowResult);

	double[][] fasterResult =
	    (new FasterAllPairsShortestPaths()).computeShortestPaths(g);

	System.out.println("\nShortest-path weights from Faster-All-Pairs-Shortest-Paths:");
	AllPairsShortestPaths.printMatrix(slowResult);

	double[][] FWResult =
	    (new FloydWarshall()).computeShortestPaths(g);

	System.out.println("\nShortest-path weights from Floyd-Warshall:");
	AllPairsShortestPaths.printMatrix(slowResult);
    }
}
