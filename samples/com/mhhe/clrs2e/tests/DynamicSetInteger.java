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

// DynamicSetInteger.java
// Wrapper class to make an int look like a DynamicSetElement.

import com.mhhe.clrs2e.DynamicSetElement;

public class DynamicSetInteger implements DynamicSetElement
{
    Integer value;		// the number being stored

    // Constructor.  Stores a number.
    public DynamicSetInteger(int number)
    {
	value = new Integer(number);
    }

    // Constructor.  Stores a number.
    public DynamicSetInteger(Integer number)
    {
	value = number;
    }

    // Stores a number.
    public void setKey(Comparable key)
    {
	value = (Integer) key;
    }

    // Returns the stored number, as an Integer.
    public Comparable getKey()
    {
	return value;
    }

    // Compares this DynamicSetInteger to another, returning a
    // negative number if this DynamicSetInteger is less; 0 if they
    // are equal; a positive number if this DynamicSetInteger is
    // greater.
    public int compareTo(Object e)
    {
	return DynamicSetElement.Helper.compareTo(this, e);
    }

    // Returns the String representation of the stored number.
    public String toString()
    {
	return value.toString();
    }
}	
