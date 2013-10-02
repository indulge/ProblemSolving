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

// FibHeapTest.java
// Tests Fibonacci heaps by emulating examples in Chapter 20 of
// Introduction to Algorithms, Second edition.

import com.mhhe.clrs2e.*;

public class FibHeapTest
{
    public static void main(String[] args)
    {
	testInsert();
	testExtractMin();
	testDecreaseKey1();
	testDecreaseKey2();
	testDelete();
 	testUnion();
    }

    // Emulate Figure 20.2 on page 481.
    private static void testInsert()
    {
	FibHeap h = new FibHeap();
	Object n3 = h.insert(new DynamicSetInteger(3));
	Object n17 = h.insert(new DynamicSetInteger(17));
	Object n24 = h.insert(new DynamicSetInteger(24));
	Object n23 = h.insert(new DynamicSetInteger(23));
	Object n7 = h.insert(new DynamicSetInteger(7));
	Object n18 = h.addChild(n3, new DynamicSetInteger(18), true);
	Object n52 = h.addChild(n3, new DynamicSetInteger(52), false);
	Object n38 = h.addChild(n3, new DynamicSetInteger(38), false);
	Object n39 = h.addChild(n18, new DynamicSetInteger(39), true);
	Object n41 = h.addChild(n38, new DynamicSetInteger(41), false);
	Object n30 = h.addChild(n17, new DynamicSetInteger(30), false);
	Object n26 = h.addChild(n24, new DynamicSetInteger(26), true);
	Object n35 = h.addChild(n26, new DynamicSetInteger(35), false);
	Object n46 = h.addChild(n24, new DynamicSetInteger(46), false);

	System.out.println("Before inserting 21, h:\n" + h);

	Object n21 = h.insert(new DynamicSetInteger(21));
	System.out.println("After inserting 21, h:\n" + h);
    }

    // Emulate Figure 20.3 on pages 484-485.
    private static void testExtractMin()
    {
	FibHeap h = new FibHeap();
	Object n3 = h.insert(new DynamicSetInteger(3));
	Object n17 = h.insert(new DynamicSetInteger(17));
	Object n24 = h.insert(new DynamicSetInteger(24));
	Object n23 = h.insert(new DynamicSetInteger(23));
	Object n7 = h.insert(new DynamicSetInteger(7));
	Object n21 = h.insert(new DynamicSetInteger(21));
	Object n18 = h.addChild(n3, new DynamicSetInteger(18), true);
	Object n52 = h.addChild(n3, new DynamicSetInteger(52), false);
	Object n38 = h.addChild(n3, new DynamicSetInteger(38), false);
	Object n39 = h.addChild(n18, new DynamicSetInteger(39), true);
	Object n41 = h.addChild(n38, new DynamicSetInteger(41), false);
	Object n30 = h.addChild(n17, new DynamicSetInteger(30), false);
	Object n26 = h.addChild(n24, new DynamicSetInteger(26), true);
	Object n35 = h.addChild(n26, new DynamicSetInteger(35), false);
	Object n46 = h.addChild(n24, new DynamicSetInteger(46), false);

	System.out.println("Before extract min, h:\n" + h);

	DynamicSetElement extracted = h.extractMin();
	System.out.println("After extracting " + extracted.toString() +
			   ", h:\n" + h);
    }

    // Emulate Figure 20.4 on page 491.
    private static void testDecreaseKey1()
    {
	FibHeap h = new FibHeap();
	Object n7 = h.insert(new DynamicSetInteger(7));
	Object n18 = h.insert(new DynamicSetInteger(18));
	h.mark(n18);
	Object n38 = h.insert(new DynamicSetInteger(38));
	Object n24 = h.addChild(n7, new DynamicSetInteger(24), false);
	Object n17 = h.addChild(n7, new DynamicSetInteger(17), false);
	Object n23 = h.addChild(n7, new DynamicSetInteger(23), false);
	Object n26 = h.addChild(n24, new DynamicSetInteger(26), true);
	Object n46 = h.addChild(n24, new DynamicSetInteger(46), false);
	Object n30 = h.addChild(n17, new DynamicSetInteger(30), false);
	Object n35 = h.addChild(n26, new DynamicSetInteger(35), false);
	Object n21 = h.addChild(n18, new DynamicSetInteger(21), false);
	Object n39 = h.addChild(n18, new DynamicSetInteger(39), true);
	Object n52 = h.addChild(n21, new DynamicSetInteger(52), false);
	Object n41 = h.addChild(n38, new DynamicSetInteger(41), false);

	System.out.println("Before decrease key, h:\n" + h);

	h.decreaseKey(n46, new Integer(15));
	System.out.println("After decreasing 46 to 15, h:\n" + h);

	h.decreaseKey(n35, new Integer(5));
	System.out.println("After decreasing 35 to 5, h:\n" + h);
    }

    // More testing of decrease key, starting with Figure 20.4(a) on
    // page 491.
    private static void testDecreaseKey2()
    {
	FibHeap h = new FibHeap();
	Object n7 = h.insert(new DynamicSetInteger(7));
	Object n18 = h.insert(new DynamicSetInteger(18));
	h.mark(n18);
	Object n38 = h.insert(new DynamicSetInteger(38));
	Object n24 = h.addChild(n7, new DynamicSetInteger(24), false);
	Object n17 = h.addChild(n7, new DynamicSetInteger(17), false);
	Object n23 = h.addChild(n7, new DynamicSetInteger(23), false);
	Object n26 = h.addChild(n24, new DynamicSetInteger(26), true);
	Object n46 = h.addChild(n24, new DynamicSetInteger(46), false);
	Object n30 = h.addChild(n17, new DynamicSetInteger(30), false);
	Object n35 = h.addChild(n26, new DynamicSetInteger(35), false);
	Object n21 = h.addChild(n18, new DynamicSetInteger(21), false);
	Object n39 = h.addChild(n18, new DynamicSetInteger(39), true);
	Object n52 = h.addChild(n21, new DynamicSetInteger(52), false);
	Object n41 = h.addChild(n38, new DynamicSetInteger(41), false);

	System.out.println("Before decrease key, h:\n" + h);

	h.decreaseKey(n26, new Integer(15));
	System.out.println("After decreasing 26 to 15, h:\n" + h);

	h.decreaseKey(n46, new Integer(5));
	System.out.println("After decreasing 46 to 5, h:\n" + h);
    }

    // Test deletion, starting with Figure 20.4(a) on page 491.
    private static void testDelete()
    {
	FibHeap h = new FibHeap();
	Object n7 = h.insert(new DynamicSetInteger(7));
	Object n18 = h.insert(new DynamicSetInteger(18));
	h.mark(n18);
	Object n38 = h.insert(new DynamicSetInteger(38));
	Object n24 = h.addChild(n7, new DynamicSetInteger(24), false);
	Object n17 = h.addChild(n7, new DynamicSetInteger(17), false);
	Object n23 = h.addChild(n7, new DynamicSetInteger(23), false);
	Object n26 = h.addChild(n24, new DynamicSetInteger(26), true);
	Object n46 = h.addChild(n24, new DynamicSetInteger(46), false);
	Object n30 = h.addChild(n17, new DynamicSetInteger(30), false);
	Object n35 = h.addChild(n26, new DynamicSetInteger(35), false);
	Object n21 = h.addChild(n18, new DynamicSetInteger(21), false);
	Object n39 = h.addChild(n18, new DynamicSetInteger(39), true);
	Object n52 = h.addChild(n21, new DynamicSetInteger(52), false);
	Object n41 = h.addChild(n38, new DynamicSetInteger(41), false);

	System.out.println("Before deletion, h:\n" + h);

	h.delete(n46);
	System.out.println("After deleting 46, h:\n" + h);

	h.delete(n35);
	System.out.println("After deleting 35, h:\n" + h);
    }

    // Test the union method by starting with two Fibonacci heaps
    // that, together, give Figure 20.3(a) on page 484.
    private static void testUnion()
    {
	FibHeap h1 = new FibHeap();
	Object n21 = h1.insert(new DynamicSetInteger(21));
	Object n3 = h1.insert(new DynamicSetInteger(3));
	Object n17 = h1.insert(new DynamicSetInteger(17));
	Object n18 = h1.addChild(n3, new DynamicSetInteger(18), true);
	Object n52 = h1.addChild(n3, new DynamicSetInteger(52), false);
	Object n38 = h1.addChild(n3, new DynamicSetInteger(38), false);
	Object n39 = h1.addChild(n18, new DynamicSetInteger(39), true);
	Object n41 = h1.addChild(n38, new DynamicSetInteger(41), false);
	Object n30 = h1.addChild(n17, new DynamicSetInteger(30), false);

	FibHeap h2 = new FibHeap();
	Object n24 = h2.insert(new DynamicSetInteger(24));
	Object n23 = h2.insert(new DynamicSetInteger(23));
	Object n7 = h2.insert(new DynamicSetInteger(7));
	Object n26 = h2.addChild(n24, new DynamicSetInteger(26), true);
	Object n35 = h2.addChild(n26, new DynamicSetInteger(35), false);
	Object n46 = h2.addChild(n24, new DynamicSetInteger(46), false);

	System.out.println("Before union, h1:\n" + h1 +
			   "\nh2:\n" + h2);

	MergeableHeap h = FibHeap.union(h1, h2);

	System.out.println("After union, h1:\n" + h1 +
			   "\nh2:\n" + h2 +"\nh:\n" + h);
    }
}
