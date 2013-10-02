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

// IntegerValue.java
// Implementation of an integer-keyed value.  Implements the
// NonNegativeInteger interface.

import com.mhhe.clrs2e.NonNegativeInteger;
import com.mhhe.clrs2e.NegativeIntegerException;

public class IntegerValue implements NonNegativeInteger
{
    // An integer-valued key that should be nonnegative.
    private int key;

    // Initializes the key.
    public IntegerValue(int key)
    {
	if (key < 0)
	    throw new NegativeIntegerException();
	else
	    this.key = key;
    }


    // Sets the key.
    public void setKey(int key)
    {
	if (key < 0)
	    throw new NegativeIntegerException();
	else
	    this.key = key;
    }

    // Returns the key.
    public int getKey()
    {
	return key;
    }

    // Compares this IntegerValue to x.  Returns a negative integer if
    // this IntegerValue is less; 0 if the objects are equal; a
    // positive integer if this IntegerValue is greater.
    public int compareTo(Object x)
    {
	IntegerValue other = (IntegerValue) x;
	if (key < other.key)
	    return -1;
	else if (key == other.key)
	    return 0;
	else 
	    return 1;
    }

    // Returns the String representation of this IntegerValue object.
    public String toString()
    {
	return Integer.toString(key);
    }
}
