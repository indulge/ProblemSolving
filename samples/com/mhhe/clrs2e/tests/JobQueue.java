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

// JobQueue.java
// Priority queue of jobs.
// A low numeric value for a priority actually means that the job has
// higher priority.

import com.mhhe.clrs2e.MinPriorityQueue;
import com.mhhe.clrs2e.MinHeapPriorityQueue;
import com.mhhe.clrs2e.KeyUpdateException;

public class JobQueue
{
    private MinPriorityQueue queue; // min-priority queue

    // Constructor.  Makes an empty queue.
    public JobQueue()
    {
	queue = new MinHeapPriorityQueue();
    }

    // Inserts a Job.
    public void add(Job j)
    {
	j.setHandle(queue.insert(j));
    }

    // Returns the job with the highest priority (lowest number).
    public Job highestPriorityJob()
    {
	return (Job) queue.minimum();
    }

    // Removes and returns the job with the highest priority.
    public Job getNextJob()
    {
	Job min = (Job) queue.extractMin();
	min.setHandle(null);	// free up the handle
	return min;
    }

    // Gives a job higher priority.
    public void raisePriority(Job j, double newPriority)
    {
	try {
	    queue.decreaseKey(j.getHandle(), new Double(newPriority));
	}
	catch (KeyUpdateException e) {
	    System.out.println("Illegal decreaseKey call: old key = " +
			       j.getKey() + ", new key = " + newPriority);
	}
    }
}
