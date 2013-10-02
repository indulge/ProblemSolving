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

// DiffConstraintsTest.java
// Tests the algorithm to solve a system of difference constraints.
// Emulates solving the system (24.3)-(24.10) on page 602 of
// Introduction to Algorithms, Second edition.

import com.mhhe.clrs2e.DifferenceConstraints;

public class DiffConstraintsTest
{
    public static void main(String[] args)
    {
	DifferenceConstraints system = new DifferenceConstraints();

	system.addConstraint(1, 2, 0);
	system.addConstraint(1, 5, -1);
	system.addConstraint(2, 5, 1);
	system.addConstraint(3, 1, 5);
	system.addConstraint(4, 1, 4);
	system.addConstraint(4, 3, -1);
	system.addConstraint(5, 3, -3);
	system.addConstraint(5, 4, -3);
	
	System.out.println("The system:");
	System.out.println(system);

	double[] solution = system.findFeasibleSolution();

	if (solution == null)
	    System.out.println("\nInfeasible");
	else {
	    System.out.println("\nSolution:");
	    for (int i = 1; i < solution.length; i++)
		System.out.println("x_" + i + " = " + solution[i]);
	}
    }
}
