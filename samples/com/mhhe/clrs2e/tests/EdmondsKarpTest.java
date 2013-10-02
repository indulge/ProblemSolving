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

// EdmondsKarpTest.java
// Tests the Edmonds-Karp algorithm.  Uses the flow network given in
// Figure 26.5 on page 659 of Introduction to Algorithms, Second
// edition.

import com.mhhe.clrs2e.*;

public class EdmondsKarpTest
{
    public static void main(String[] args)
    {
	FlowNetwork g = new FlowNetwork(6);

	Vertex s = new Vertex("s");
	Vertex v1 = new Vertex("v1");
	Vertex v2 = new Vertex("v2");
	Vertex v3 = new Vertex("v3");
	Vertex v4 = new Vertex("v4");
	Vertex t = new Vertex("t");

	g.addVertex(s);
	g.addVertex(v1);
	g.addVertex(v2);
	g.addVertex(v3);
	g.addVertex(v4);
	g.addVertex(t);

	g.addEdge(s, v1, 16, 0);
	g.addEdge(s, v2, 13, 0);
	g.addEdge(v1, v2, 10, 4);
	g.addEdge(v1, v3, 12, 0);
	g.addEdge(v2, v4, 14, 0);
	g.addEdge(v3, t, 20, 0);
	g.addEdge(v3, v2, 9, 0);
	g.addEdge(v4, v3, 7, 0);
	g.addEdge(v4, t, 4, 0);

	System.out.println("Before Edmonds-Karp:");
	System.out.print(g);
	
	(new EdmondsKarp()).computeMaxFlow(g, s, t);

	System.out.println("After Edmonds-Karp:");
	System.out.print(g);
	System.out.println("\nFlow value is " + g.getFlowValue(s));
    }
}


	
