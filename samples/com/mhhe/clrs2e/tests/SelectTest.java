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

// SelectTest.java
// Tests the minimum, maximum, randomized selection, and deterministic
// selection algorithms from Chapter 9 of Introduction to Algorithms,
// Second edition.

import java.util.Random;
import com.mhhe.clrs2e.*;

public class SelectTest
{
    static private int count;	// number of elements in a selection test
    static Random rand;		// a random number generator

    // Prints the contents of an array.
    public static void printArray(Object[] array)
    {
	for (int i = 0; i < array.length; i++)
	    System.out.println(i + ": " + array[i].toString());
	System.out.println();	

    }

    // Returns a new array of random Value objects.
    public static Comparable[] makeRandomArray()
    {
	Comparable[] array = new Value[count];
	for (int i = 0; i < count; i++)
	    array[i] = new Value(rand.nextInt(10000));

	return array;
    }

    // Tests the minimum and maximum algorithms, returning true if
    // they give the correct answer, false otherwise.
    public static boolean testMinMax()
    {
	Comparable[] array = makeRandomArray();
	Comparable min = MinMax.minimum(array);
	Comparable max = MinMax.maximum(array);
	(new Quicksort()).sort(array);

	printArray(array);
	System.out.println("Min = " + min.toString());
	System.out.println("Max = " + max.toString());

	return (min.compareTo(array[0]) == 0) 
	    && (max.compareTo(array[array.length - 1]) == 0);
    }

    // Tests the randomized selection algorithm, returning true if it
    // returns the correct answer, false otherwise.
    public static boolean testRandomizedSelect()
    {
	Comparable[] array = makeRandomArray();
	int i = rand.nextInt(array.length);
	Comparable x = (new RandomizedSelect()).select(array, i);
	(new Quicksort()).sort(array);
	
	printArray(array);
	System.out.println(Integer.toString(i) + "th = " + x.toString());
	return array[i-1] == x;
    }

    // Tests the deterministic selection algorithm, returning true if
    // it returns the correct answer, false otherwise.
    public static boolean testDeterministicSelect()
    {
	Comparable[] array = makeRandomArray();
	int i = rand.nextInt(array.length);
	Comparable x = (new DeterministicSelect()).select(array, i);
	(new Quicksort()).sort(array);
	
	printArray(array);
	System.out.println(Integer.toString(i) + "th = " + x.toString());
	return array[i-1] == x;
    }

    public static void main(String[] args)
    {
	count = 20;
	rand = new Random();

	System.out.println("Testing MinMax");
	if (testMinMax())
	    System.out.println("MinMax OK");
	else
	    System.out.println("MinMax Broken");

	System.out.println("Testing RandomizedSelect");
	if (testRandomizedSelect())
	    System.out.println("RandomizedSelect OK");
	else
	    System.out.println("RandomizedSelect Broken");

	System.out.println("Testing DeterministicSelect");
	if (testDeterministicSelect())
	    System.out.println("DeterministicSelect OK");
	else
	    System.out.println("DeterministicSelect Broken");

	System.exit(0);
    }
}
