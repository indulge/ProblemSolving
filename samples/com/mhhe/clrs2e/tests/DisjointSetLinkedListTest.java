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

// DisjointSetLinkedListTeste.java
// Tests the DisjointSetLinkedList class.  Emulates the example in
// Figure 21.2 on page 502 of Introduction to Algorithms, Second
// edition, except that it uses the weighted-union heuristic.

import com.mhhe.clrs2e.*;

public class DisjointSetLinkedListTest
{
    public static void main(String[] args)
    {
	DisjointSetUnion sets = new DisjointSetLinkedList();

	Object b = sets.makeSet(new String("b"));
	Object c = sets.makeSet(new String("c"));
	Object d = sets.makeSet(new String("d"));
	Object e = sets.makeSet(new String("e"));
	Object f = sets.makeSet(new String("f"));
	Object g = sets.makeSet(new String("g"));
	Object h = sets.makeSet(new String("h"));

	sets.union(c, h);
	sets.union(e, b);
	sets.union(f, g);
	sets.union(g, d);
	sets.union(h, b);

	DisjointSetLinkedList lists = (DisjointSetLinkedList) sets;

	System.out.println("Before union(e,g):");
	System.out.println("First set:");
	System.out.println(lists.printSet(c));
	System.out.println("Second set:");
	System.out.println(lists.printSet(f));

	sets.union(e, g);

	System.out.println("\nAfter union(e,g):");
	System.out.println(lists.printSet(f));
    }
}
