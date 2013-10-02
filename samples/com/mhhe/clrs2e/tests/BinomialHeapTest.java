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

// BinomialHeapTest.java
// Code to test binomial heaps.  Emulates examples from Chapter 19 of
// Introduction to Algorithms, Second edition.

import com.mhhe.clrs2e.*;

public class BinomialHeapTest
{
    public static void main(String[] args)
    {
	testUnion();
	testExtractMin();
	testDecreaseKey();
    }

    // Emulate Figure 19.5 on page 464.
    public static void testUnion()
    {
	MergeableHeap h1 = new BinomialHeap();

	h1.insert(new DynamicSetInteger(33));
	h1.insert(new DynamicSetInteger(15));
	h1.insert(new DynamicSetInteger(28));
	h1.insert(new DynamicSetInteger(41));
	h1.insert(new DynamicSetInteger(25));
	h1.insert(new DynamicSetInteger(7));
	h1.insert(new DynamicSetInteger(12));

	MergeableHeap h2 = new BinomialHeap();
	h2.insert(new DynamicSetInteger(8));
	h2.insert(new DynamicSetInteger(22));
	h2.insert(new DynamicSetInteger(24));
	h2.insert(new DynamicSetInteger(23));
	h2.insert(new DynamicSetInteger(30));
	h2.insert(new DynamicSetInteger(32));
	h2.insert(new DynamicSetInteger(45));
	h2.insert(new DynamicSetInteger(55));
	h2.insert(new DynamicSetInteger(6));
	h2.insert(new DynamicSetInteger(44));
	h2.insert(new DynamicSetInteger(17));
	h2.insert(new DynamicSetInteger(10));
	h2.insert(new DynamicSetInteger(29));
	h2.insert(new DynamicSetInteger(31));
	h2.insert(new DynamicSetInteger(48));
	h2.insert(new DynamicSetInteger(50));
	h2.insert(new DynamicSetInteger(3));
	h2.insert(new DynamicSetInteger(37));
	h2.insert(new DynamicSetInteger(18));

	System.out.println("Before union:");
	System.out.println("H1:\n" + h1);
	System.out.println("H2:\n" + h2);

	MergeableHeap h = MergeableHeap.union(h1, h2);
	System.out.println("After union:");
	System.out.println("H:\n" + h);
    }

    // Emulate Figure 19.7 on page 469.
    public static void testExtractMin()
    {
	MergeableHeap h = new BinomialHeap();

	h.insert(new DynamicSetInteger(25));
	h.insert(new DynamicSetInteger(1));
	h.insert(new DynamicSetInteger(12));
	h.insert(new DynamicSetInteger(18));
	h.insert(new DynamicSetInteger(23));
	h.insert(new DynamicSetInteger(16));
	h.insert(new DynamicSetInteger(26));
	h.insert(new DynamicSetInteger(42));
	h.insert(new DynamicSetInteger(6));
	h.insert(new DynamicSetInteger(29));
	h.insert(new DynamicSetInteger(14));
	h.insert(new DynamicSetInteger(38));
	h.insert(new DynamicSetInteger(8));
	h.insert(new DynamicSetInteger(17));
	h.insert(new DynamicSetInteger(27));
	h.insert(new DynamicSetInteger(11));
	h.insert(new DynamicSetInteger(10));
	h.insert(new DynamicSetInteger(13));
	h.insert(new DynamicSetInteger(28));
	h.insert(new DynamicSetInteger(77));
	h.insert(new DynamicSetInteger(37));
	h.insert(new DynamicSetInteger(41));

	System.out.println("Before extract min:\n" + h);
	DynamicSetInteger x = (DynamicSetInteger) h.extractMin();
	System.out.println("Extracted " + x);
	System.out.println("After extract min:\n" + h);
	System.out.println("New minimum is " + h.minimum());
    }

    // Emulate Figure 19.8 on page 471, plus some deletions.
    public static void testDecreaseKey()
    {
	MergeableHeap h = new BinomialHeap();

	Object n6 = h.insert(new DynamicSetInteger(6));
	h.insert(new DynamicSetInteger(29));
	h.insert(new DynamicSetInteger(14));
	h.insert(new DynamicSetInteger(38));
	Object n8 = h.insert(new DynamicSetInteger(8));
	Object n17 = h.insert(new DynamicSetInteger(17));
	h.insert(new DynamicSetInteger(27));
	h.insert(new DynamicSetInteger(11));
	Object n10 = h.insert(new DynamicSetInteger(10));
	h.insert(new DynamicSetInteger(13));
	h.insert(new DynamicSetInteger(28));
	h.insert(new DynamicSetInteger(77));
	Object n16 = h.insert(new DynamicSetInteger(16));
	h.insert(new DynamicSetInteger(23));
	Object n26 = h.insert(new DynamicSetInteger(26));
	h.insert(new DynamicSetInteger(42));
	h.insert(new DynamicSetInteger(12));
	h.insert(new DynamicSetInteger(18));
	h.insert(new DynamicSetInteger(37));
	h.insert(new DynamicSetInteger(41));
	Object n25 = h.insert(new DynamicSetInteger(25));

	System.out.println("Before decrease key of 26 to 7:\n" + h);
	((BinomialHeap) h).decreaseKey(n26, new Integer(7));
	System.out.println("After decrease key:\n" + h);

	// Check that handles are still correct.
	System.out.println("Object that should have key 6 has key " +
			   ((BinomialHeap) h).dereference(n6));
	System.out.println("Object that should have key 10 has key " +
			   ((BinomialHeap) h).dereference(n10));
	System.out.println("Object that should have key 16 has key " +
			   ((BinomialHeap) h).dereference(n16));
	System.out.println("Object that should have key 7 has key " +
			   ((BinomialHeap) h).dereference(n26));

	// Verify that we can delete the first node on the root list.
	((BinomialHeap) h).delete(n25);
	System.out.println("After deleting 25:\n" + h);

	// Then delete the object with key 17.
	((BinomialHeap) h).delete(n17);
	System.out.println("After deleting 17:\n" + h);

	// Check that handles are still correct.
	System.out.println("Object that should have key 6 has key " +
			   ((BinomialHeap) h).dereference(n6));
	System.out.println("Object that should have key 8 has key " +
			   ((BinomialHeap) h).dereference(n8));
    }
}
