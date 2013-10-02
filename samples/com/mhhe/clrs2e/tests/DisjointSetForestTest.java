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

// DisjointSetForestTest.java
// Tests the DisjointSetForest class.  Emulates the example in Figure
// 21.4 on page 506 of Introduction to Algorithms, Second edition.

// Note: The example in Figure 21.4 cannot be emulated by the
// disjoint-set-forest code with union by rank and path compression.
// The tree structures produced here differ from those in the figure.

import com.mhhe.clrs2e.*;

public class DisjointSetForestTest
{
    public static void main(String[] args)
    {
	DisjointSetUnion forest = new DisjointSetForest();

	Object b = forest.makeSet(new String("b"));
	Object c = forest.makeSet(new String("c"));
	Object d = forest.makeSet(new String("d"));
	Object e = forest.makeSet(new String("e"));
	Object f = forest.makeSet(new String("f"));
	Object g = forest.makeSet(new String("g"));
	Object h = forest.makeSet(new String("h"));

	forest.union(b, h);
	forest.union(e, c);
	forest.union(h, c);
	forest.union(g, d);
	forest.union(d, f);

	System.out.println("Before union(e,g):");
	System.out.println(b);
	System.out.println(c);
	System.out.println(d);
	System.out.println(e);
	System.out.println(f);
	System.out.println(g);
	System.out.println(h);

	forest.union(e, g);

	System.out.println("\nAfter union(e,g):");
	System.out.println(b);
	System.out.println(c);
	System.out.println(d);
	System.out.println(e);
	System.out.println(f);
	System.out.println(g);
	System.out.println(h);
    }
}
