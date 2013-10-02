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

// HashTableTest.java
// Tests the various implementations of hash tables from Chapter 11 of
// Introduction to Algorithms, Second edition.

import java.util.Random;
import com.mhhe.clrs2e.*;

public class HashTableTest
{
    public static final int range = 10000;

    // Prints the result of a test. 
    public static boolean claim(boolean b, String msg)
    {
	print("Testing " + msg + ": ");

	if (b) 
	    println("Succeeded");
	else 
	    println("Failed");

	return b;
    }

    public static void print(String msg)
    {
	System.out.print(msg);
    }

    public static void println(String msg)
    {
	print(msg + "\n");
    }

    public static class Tester
    {
	protected Class type;
	protected Dictionary table;
	protected Random rand;

	public Tester(Class type)
	{
	    rand = new Random();
	    this.type = type;
	    setUp();
	}

	public void setUp()
	{
	    try {
		this.table = (Dictionary) type.newInstance();
	    }
	    catch (InstantiationException ex) {
		println("Error Instantiation Exception occurred.");
		throw new RuntimeException("Argument was not an "
				       + "implementation of Dictionary");
	    }
	    catch (IllegalAccessException ex) {
		println("Illegal access exception occurred.");
	    }
	}

	// Insert an object.  Delete it.  Search for it.
	public boolean test0()
	{
	    setUp();
	    Integer k = new Integer(rand.nextInt(range));
	    DynamicSetInteger d = new DynamicSetInteger(k);

	    Object handle = table.insert(d);
	    table.delete(handle);

	    handle = table.search(k);

	    return handle == null;
	}

	// Insert the same item 10 times.  Search and delete it 5 times.
	public boolean test1()
	{
	    setUp();
	    Integer k = new Integer(rand.nextInt(range));
	    DynamicSetInteger d = new DynamicSetInteger(k);

	    for (int i = 0; i < 10; i++)
		table.insert(d);

	    for (int i = 0; i < 5; i++) {
		Object handle = table.search(k);
		
		if (handle == null)
		    return false;

		table.delete(handle);
	    }

	    return true;
	}

	public boolean test2()
	{
	    setUp();
	    Integer a = new Integer(rand.nextInt(range));
	    Integer b = new Integer(rand.nextInt(range));

	    while (a.compareTo(b) == 0)
		b = new Integer(rand.nextInt(range));

	    DynamicSetInteger m = new DynamicSetInteger(a);
	    DynamicSetInteger n = new DynamicSetInteger(b);

	    Object x = table.insert(m);
	    Object y = table.insert(n);

	    table.delete(x);

	    if (table.search(a) != null)
		return false;

	    x = table.insert(m);
	    table.delete(y);

	    if (table.search(b) != null)
		return false;

	    table.delete(x);
	    y = table.insert(n);	    

	    if (table.search(a) != null)
		return false;

	    return true;
	}

	public boolean testAll()
	{
	    boolean b = true;

	    b &= claim(test0(), "test0");
	    b &= claim(test1(), "test1");
	    b &= claim(test2(), "test2");

	    return b;
	}
    }

    public static void main(String[] args)
    {
	Tester tester = new Tester(ChainedHashTable.class);
	
	if (tester.testAll())
	    println("Hash table with chaining is fine");
	else
	    println("Hash table with chaining is broken");

	tester = new Tester(LinearProbingHashTable.class);
	if (tester.testAll())
	    println("Hash table with linear probing is fine");
	else
	    println("Hash table with linear probing is broken");

	tester = new Tester(QuadraticProbingHashTable.class);
	if (tester.testAll())
	    println("Hash table with quadratic probing is fine");
	else
	    println("Hash table with quadratic probing is broken");

	tester = new Tester(DoubleHashingHashTable.class);
	if (tester.testAll())
	    println("Hash table with double hashing is fine");
	else
	    println("Hash table with double hashing is broken");
    }
}
