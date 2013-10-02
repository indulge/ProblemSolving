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

// JobTest.java
// Driver to test a JobQueue.

public class JobTest
{
    public static void main(String[] args)
    {
	JobQueue jq = new JobQueue();

	Job j1 = new Job("Job 1", 10);
	Job j2 = new Job("Job 2", 7);
	Job j3 = new Job("Job 3", 8);
	Job j4 = new Job("Job 4", 12);
	Job j5 = new Job("Job 5", 20);
	Job j6 = new Job("Job 6", 3);
	Job j7 = new Job("Job 7", 15);
	Job j8 = new Job("Job 8", 7);

	jq.add(j1);
	jq.add(j2);
	jq.add(j3);

	System.out.println(jq.getNextJob());
	System.out.println(jq.highestPriorityJob());
	jq.raisePriority(j1, 5);
	System.out.println(jq.getNextJob());
	jq.add(j4);
	jq.add(j5);
	System.out.println(jq.getNextJob());
	jq.add(j6);
	jq.add(j7);
	System.out.println(jq.getNextJob());
	jq.add(j8);
	jq.raisePriority(j5, 11);
	System.out.println(jq.getNextJob());
	System.out.println(jq.getNextJob());
	System.out.println(jq.getNextJob());
	System.out.println(jq.getNextJob());

	// 8 jobs added, 8 jobs removed.  If we remove now, we should
	// get a HeapUnderflowException.
	System.out.println("About to get a HeapUnderflowException...");
	System.out.println(jq.getNextJob());
    }
}
