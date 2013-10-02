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

// BTreeTest.java
// Tests the BTree class.  Creates a B-tree by inserting several
// elements and then deleting them.  Prints out the entire tree after
// several of the operations.

import com.mhhe.clrs2e.*;

public class BTreeTest
{
    public static void main(String[] args)
    {
	BTree tree = new BTree(3);

	tree.insert(new Name("K"));
	tree.insert(new Name("S"));
	tree.insert(new Name("C"));
	tree.insert(new Name("T"));
	tree.insert(new Name("Y"));
	tree.insert(new Name("Z"));
	tree.insert(new Name("X"));
	tree.insert(new Name("A"));
	tree.insert(new Name("O"));
	tree.insert(new Name("J"));
	tree.insert(new Name("N"));
	tree.insert(new Name("E"));
	tree.insert(new Name("R"));
	tree.insert(new Name("G"));
	tree.insert(new Name("P"));
	tree.insert(new Name("D"));
	tree.insert(new Name("V"));
	tree.insert(new Name("M"));
	tree.insert(new Name("U"));
	System.out.println(tree);

	tree.insert(new Name("B"));
	System.out.println(tree);

	tree.insert(new Name("Q"));
	System.out.println(tree);

	tree.insert(new Name("L"));
	System.out.println(tree);

	tree.insert(new Name("F"));
	System.out.println(tree);

	tree.delete("L");
	System.out.println(tree);

	tree.delete("Y");
	System.out.println(tree);

	tree.delete("X");
	System.out.println(tree);

	tree.delete("S");
	System.out.println(tree);

	tree.delete("R");
	System.out.println(tree);

	tree.delete("T");
	System.out.println(tree);

	tree.delete("U");
	System.out.println(tree);

	tree.delete("O");
	System.out.println(tree);

	tree.delete("K");
	System.out.println(tree);

	tree.delete("E");
	System.out.println(tree);

	tree.delete("A");
	System.out.println(tree);

	tree.delete("M");
	System.out.println(tree);

	tree.delete("Na");
	System.out.println(tree);

	tree.delete("J");
	System.out.println(tree);

	tree.delete("B");
	System.out.println(tree);

	tree.delete("N");
	System.out.println(tree);

	tree.delete("D");
	tree.delete("F");
	tree.delete("C");
	System.out.println(tree);

	tree.delete("V");
	System.out.println(tree);

	tree.delete("V");
	tree.delete("Q");
	tree.delete("Z");
	tree.delete("G");
	System.out.println(tree);
 
	tree.delete("P");
	System.out.println(tree);
   }
}
