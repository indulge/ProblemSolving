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

// ConnCompTest.java
// Tests the the connected components algorithm, using the example in
// Figure 21.1 on page 500 of Introduction to Algorithms, Second
// edition.

import com.mhhe.clrs2e.*;

public class ConnCompTest
{
    public static void main(String[] args)
    {
	// Make an undirected graph with 10 vertices.
	Graph graph = new AdjacencyListGraph(10, false);

	Vertex a = new Vertex("a");
	Vertex b = new Vertex("b");
	Vertex c = new Vertex("c");
	Vertex d = new Vertex("d");
	Vertex e = new Vertex("e");
	Vertex f = new Vertex("f");
	Vertex g = new Vertex("g");
	Vertex h = new Vertex("h");
	Vertex i = new Vertex("i");
	Vertex j = new Vertex("j");

	graph.addVertex(a);
	graph.addVertex(b);
	graph.addVertex(c);
	graph.addVertex(d);
	graph.addVertex(e);
	graph.addVertex(f);
	graph.addVertex(g);
	graph.addVertex(h);
	graph.addVertex(i);
	graph.addVertex(j);

	graph.addEdge(a, b);
	graph.addEdge(a, c);
	graph.addEdge(b, c);
	graph.addEdge(b, d);
	graph.addEdge(e, f);
	graph.addEdge(e, g);
	graph.addEdge(h, i);

	ConnectedComponents components = new ConnectedComponents(graph);

	answer(components, a, d);
	answer(components, a, g);
	answer(components, e, g);
	answer(components, e, h);
	answer(components, h, i);
	answer(components, a, j);
    }

    private static void answer(ConnectedComponents components,
			       Vertex u, Vertex v)
    {
	boolean inSameComponent = components.sameComponent(u, v);

	System.out.print(u.getName() + " and " + v.getName() + " are ");
	if (!inSameComponent)
	    System.out.print("not ");
	System.out.println("in the same component.");
    }
}

