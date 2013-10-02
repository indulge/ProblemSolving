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

// TopoSortTest.java
// Tests TopoSort.  Emulates the example in Figure 22.7 on page 550 of
// Introduction to Algorithms, Second edition.

import com.mhhe.clrs2e.*;

public class TopoSortTest
{
    public static void main(String[] args)
    {
	Vertex shirt = new Vertex("shirt");
	Vertex tie = new Vertex("tie");
	Vertex jacket = new Vertex("jacket");
	Vertex belt = new Vertex("belt");
	Vertex watch = new Vertex("watch");
	Vertex undershorts = new Vertex("undershorts");
	Vertex pants = new Vertex("pants");
	Vertex socks = new Vertex("socks");
	Vertex shoes = new Vertex("shoes");

	AdjacencyListGraph g = new AdjacencyListGraph(9, true);
	g.addVertex(shirt);
	g.addVertex(tie);
	g.addVertex(jacket);
	g.addVertex(belt);
	g.addVertex(watch);
	g.addVertex(undershorts);
	g.addVertex(pants);
	g.addVertex(socks);
	g.addVertex(shoes);

	g.addEdge(undershorts, shoes);
	g.addEdge(undershorts, pants);
	g.addEdge(pants, belt);
	g.addEdge(pants, shoes);
	g.addEdge(belt, jacket);
	g.addEdge(shirt, belt);
	g.addEdge(shirt, tie);
	g.addEdge(tie, jacket);
	g.addEdge(socks, shoes);

	System.out.println("Before TopoSort:");
	System.out.print(g);

	System.out.println("\n\nSort order");
	Vertex[] sorted = (new TopoSort()).topologicalSort(g);
	for (int i = 0; i < sorted.length; i++)
	    System.out.println(sorted[i]);
    }
}
