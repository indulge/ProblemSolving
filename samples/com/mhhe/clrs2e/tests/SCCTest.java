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

// SCCTest.java
// Tests the strongly connected components algorithm.  Emulates Figure
// 22.9 on page 553 of Introduction to Algorithms, Second edition.

import com.mhhe.clrs2e.*;

public class SCCTest
{
    public static void main(String[] args)
    {
	Vertex a = new Vertex("a");
	Vertex b = new Vertex("b");
	Vertex c = new Vertex("c");
	Vertex d = new Vertex("d");
	Vertex e = new Vertex("e");
	Vertex f = new Vertex("f");
	Vertex g = new Vertex("g");
	Vertex h = new Vertex("h");

	AdjacencyListGraph graph = new AdjacencyListGraph(8, true);
	graph.addVertex(c);
	graph.addVertex(b);
	graph.addVertex(a);
	graph.addVertex(d);
	graph.addVertex(e);
	graph.addVertex(f);
	graph.addVertex(g);
	graph.addVertex(h);

	graph.addEdge(a,b);
	graph.addEdge(b,e);
	graph.addEdge(b,f);
	graph.addEdge(b,c);
	graph.addEdge(c,d);
	graph.addEdge(c,g);
	graph.addEdge(d,c);
	graph.addEdge(d,h);
	graph.addEdge(e,a);
	graph.addEdge(e,f);
	graph.addEdge(f,g);
	graph.addEdge(g,h);
	graph.addEdge(g,f);
	graph.addEdge(h,h);

	System.out.println("The  graph:");
	System.out.print(graph);

	SCC scc = new SCC();
	scc.stronglyConnectedComponents(graph);

	System.out.println("\n\nAfter SCC:");
	for (int i = 0; i < 8; i++) {
	    System.out.println(graph.getVertex(i) + ": " +
			       scc.getSCCNumber(i));
	}
    }
}
