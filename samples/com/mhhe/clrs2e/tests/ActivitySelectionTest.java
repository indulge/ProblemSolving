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

// ActivitySelectionTest.java
// Tests the recursive and iterative greedy activity-selection
// algorithms of Section 16.1 of Introduction to Algorithms, Second
// edition.  Emulates the example used in Section 16.1.

import com.mhhe.clrs2e.*;

public class ActivitySelectionTest
{
    public static void main(String[] args)
    {
	Activity[] activities = {
	    new Activity(Double.NEGATIVE_INFINITY, 0),
	    new Activity(1, 4),
	    new Activity(3, 5),
	    new Activity(0, 6),
	    new Activity(5, 7),
	    new Activity(3, 8),
	    new Activity(5, 9),
	    new Activity(6, 10),
	    new Activity(8, 11),
	    new Activity(8, 12),
	    new Activity(2, 13),
	    new Activity(12, 14) };

	// Make sure that the activities are sorted by finish time.
	(new MergeSort()).sort(activities);

	// Select a maximum set of mutually compatible activities
	// using both the recursive and iterative algorithms, and
	// print out the result of each.
	ActivitySelector activitySelector = new RecursiveActivitySelector();
	Activity[] selectedActivities = activitySelector.selector(activities);

	System.out.println("Activities selected by the recursive algorithm:");
	for (int i = 0; i < selectedActivities.length; i++)
	    System.out.println(selectedActivities[i]);

	activitySelector = new GreedyActivitySelector();
	selectedActivities = activitySelector.selector(activities);

	System.out.println("\nActivities selected by the iterative algorithm:");
	for (int i = 0; i < selectedActivities.length; i++)
	    System.out.println(selectedActivities[i]);
    }
}
