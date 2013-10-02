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

// Job.java
// Class for a job.  Used to test priority queues.

import com.mhhe.clrs2e.DynamicSetElement;

public class Job implements DynamicSetElement
{
    private double priority;	// job's priority
    private String name;	// job's name
    private Object handle;	// handle to the job in a priority queue

    // Constructor, given name and priority.
    public Job(String name, double priority)
    {
	this.name = name;
	this.priority = priority;
	handle = null;
    }

    // Constructor, given name, priority, and handle.
    public Job(double priority, String name, Object handle)
    {
	this.name = name;
	this.priority = priority;
	this.handle = handle;
    }

    // Sets the key.
    public void setKey(Comparable key)
    {
	priority = ((Double) key).doubleValue();
    }

    // Returns the key.
    public Comparable getKey()
    {
	return new Double(priority);
    }

    // Compares the priorities of two jobs.
    public int compareTo(Object e)
    {
	double ePriority = ((Job) e).priority;

	if (priority < ePriority)
	    return -1;
	else if (priority == ePriority)
	    return 0;
	else
	    return 1;
    }
    
    // Sets the handle.
    public void setHandle(Object handle)
    {
	this.handle = handle;
    }

    // Returns the handle.
    public Object getHandle()
    {
	return handle;
    }

    // Returns the String representation of a job.
    public String toString()
    {
	return "Name = " + name + ", priority = " + priority;
    }
}
