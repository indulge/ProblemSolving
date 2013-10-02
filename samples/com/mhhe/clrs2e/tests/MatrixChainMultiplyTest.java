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

// MatrixChainMultiplyTest.java
// Tests the implementation of the dynamic-programming algorithm in
// Section 15.2 of Introduction to Algorithms, Second edition, to find
// an optimal order for multiplying a matrix chain.  Emulates the
// example of Figure 15.3 on page 337.

import com.mhhe.clrs2e.MatrixChainMultiply;

public class MatrixChainMultiplyTest
{
    // Runs the test.
    public static void main(String[] args)
    {
	int[] p = {30, 35, 15, 5, 10, 20, 25};
	MatrixChainMultiply mult = new MatrixChainMultiply(p);
	System.out.println(mult);
    }
}
