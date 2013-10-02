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

// OptimalBSTTest.java

// Tests the implementation of the dynamic-programming algorithm for
// creating an optimal binary search tree from Section 15.5 of
// Introduction to Algorithms, Second edition.  Emulates the example
// of Figure 15.8 on page 362.

import com.mhhe.clrs2e.OptimalBinarySearchTree;

public class OptimalBSTTest
{
    public static void main(String[] args)
    {
	double[] p = { 0,    0.15, 0.10, 0.05, 0.10, 0.20} ;
	double[] q = { 0.05, 0.10, 0.05, 0.05, 0.05, 0.15 };

	OptimalBinarySearchTree obst = 
	    new OptimalBinarySearchTree(p, q);

	int n = obst.getNumberOfKeys();
	int[][] root = obst.getRootTable();

	// Print the root table, as in Figure 15.8 on page 362, but
	// rotated by 45 degrees.
	System.out.println("Root table:");
	for (int i = 1; i <= n; i++) {
	    for (int j = 1; j <= n; j++) {
		if (i <= j)
		    System.out.print(root[i][j] + " ");
		else
		    System.out.print("  ");
	    }

	    System.out.println("");
	}
    }
}
