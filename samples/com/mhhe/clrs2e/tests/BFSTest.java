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

// BFSTest.java
// Tests breadth-first search.  Emulates Figure 22.3 on page 533 of
// Introduction to Algorithms, Second edition.

import com.mhhe.clrs2e.*;

public class BFSTest
{
    public static void main(String[] args)
    {
	Vertex r = new Vertex("r");
	Vertex s = new Vertex("s");
	Vertex t = new Vertex("t");
	Vertex u = new Vertex("u");
	Vertex v = new Vertex("v");
	Vertex w = new Vertex("w");
	Vertex x = new Vertex("x");
	Vertex y = new Vertex("y");

	AdjacencyListGraph g = new AdjacencyListGraph(8, false);
	g.addVertex(r);
	g.addVertex(s);
	g.addVertex(t);
	g.addVertex(u);
	g.addVertex(v);
	g.addVertex(w);
	g.addVertex(x);
	g.addVertex(y);

	g.addEdge(r,s);
	g.addEdge(s,w);
	g.addEdge(r,v);
	g.addEdge(w,x);
	g.addEdge(t,w);
	g.addEdge(t,x);
	g.addEdge(u,x);
	g.addEdge(t,u);
	g.addEdge(x,y);
	g.addEdge(u,y);

	System.out.println("Before BFS:");
	System.out.print(g);

	BFS bfs = new BFS();
	bfs.search(g, s);

	System.out.println("\n\nAfter BFS:");
	for (int i = 0; i < 8; i++) {
	    System.out.println(g.getVertex(i) + ": " + bfs.getBFSInfo(i));
	}
    }
}
