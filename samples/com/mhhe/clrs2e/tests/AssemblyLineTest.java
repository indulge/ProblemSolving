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

// AssemblyLineTest.java 
// Tests the assembly-line scheduling algorithm of Section 15.1 of
// Introduction to Algorithms, Second edition.  Emulates the example
// of Figure 15.2 on page 326.

import com.mhhe.clrs2e.AssemblyLine;

public class AssemblyLineTest
{
    // Returns true if the algorithm returns expected output given the
    // proper inputs.
    public static void main(String[] args)
    {
	// Assembly time at each station.
//	double[][] a = { { 7, 9, 3, 4, 8, 4 },
//			 		{ 8, 5, 6, 4, 5, 7 } };
	double[][] a = { { 7, 9, 3, 4, 8, 4 },
	 		{ 8, 5, 100, 100, 100, 100 } };

	// Transfer time after each station.
//	double[][] t = { { 2, 3, 1, 3, 4 },
//			 { 2, 1, 2, 2, 1 } };
	double[][] t = { { 2, 3, 1, 3, 4 },
			 { 2, 100, 200, 200, 100 } };

	// Entry times.
	double[] e = { 2, 4 };

	// Exit times.
	double[] x = { 3, 2 };

	// Compute the fastest times.
	int n = a[0].length;
	AssemblyLine al = new AssemblyLine(a, t, e, x, n);

	double fStar = al.getFastestTime();

	System.out.println("Fastest time is " + fStar);
	System.out.println("Fastest way is \n" + al);
    }
}
