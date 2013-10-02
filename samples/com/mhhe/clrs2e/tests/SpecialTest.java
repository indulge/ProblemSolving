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

// SpecialTest.java
// Tests CountingSort, RadixSort, and BucketSort.

import java.util.Random;
import javax.swing.JOptionPane;
import com.mhhe.clrs2e.*;

public class SpecialTest
{
    private static Random rand;

    public static void printArray(Object[] array)
    {
	for (int i = 0; i < array.length; i++)
	    System.out.println(array[i].toString());
    }

    public static boolean isSorted(Comparable[] array)
    {
	for (int i = 0; i < array.length; i++)
	    if (i < array.length-1 && array[i].compareTo(array[i+1]) > 0)
		return false;

	return true;
    }

    // Tests CountingSort on integers.
    public static boolean testCountingSort(int size)
    {
	IntegerValue[] array = new IntegerValue[size];
	for (int i = 0; i < size; i++)
	    array[i] = new IntegerValue(rand.nextInt(10000));
	
	CountingSort sorter = new CountingSort();
	sorter.sort(array);

	printArray(array);
	return isSorted(array);
    }

    // Tests RadixSort on integers.
    public static boolean testRadixSort(int size)
    {
	IntegerValue[] array = new IntegerValue[size];
	for (int i = 0; i < size; i++)
	    array[i] = new IntegerValue(rand.nextInt(10000));
	
	RadixSort sorter = new RadixSort();
	sorter.sort(array);

	printArray(array);
	return isSorted(array);
    }

    // Tests BucketSort on reals.
    public static boolean testBucketSort(int size)
    {
	DoubleValued[] array = new Value[size];
	double unsortedTotal = 0;
	for (int i = 0; i < size; i++) {
	    array[i] = new Value(rand.nextDouble());
	    unsortedTotal += array[i].getKey();
	}

	System.out.println("Before sorting:");
	printArray(array);
	
	BucketSort sorter = new BucketSort();
	sorter.sort(array);

	System.out.println("After sorting:");
	printArray(array);
	double sortedTotal = 0;
	for (int i = 0; i < array.length; i++)
	    sortedTotal += array[i].getKey();

	// Print the totals before and after sorting.
	System.out.println("Totals\n\tBefore sorting: " +
			   unsortedTotal + "\n\tAfter sorting:  " +
			   sortedTotal);
	return isSorted(array);
    }


    public static void main(String[] args)
    {
	int size;		// Number of elements to sort
	if (args.length > 0)
	    size = Integer.parseInt(args[0]);
	else
	    size = Integer.parseInt(JOptionPane.showInputDialog(
		     "How many numbers to sort?"));
	rand = new Random();

	System.out.println("Testing Counting Sort");
	if (testCountingSort(size))
	    System.out.println("Counting Sort OK");
	else
	    System.out.println("Counting Sort Broken");
	
	System.out.println("Testing Radix Sort");
	if (testRadixSort(size))
	    System.out.println("Radix Sort OK");
	else
	    System.out.println("Radix Sort Broken");
	
	System.out.println("Testing Bucket Sort");
	if (testBucketSort(size))
	    System.out.println("Bucket Sort OK");
	else
	    System.out.println("Bucket Sort Broken");

	System.exit(0);
    }
}
