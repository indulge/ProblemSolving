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

// HuffmanTest.java
// Tests the implementation of the algorithm to create Huffman trees
// in Section 16.3 of Introduction to Algorithms, Second edition.
// Emulates the example of Figure 16.5 on page 389.

import com.mhhe.clrs2e.Huffman;

public class HuffmanTest
{
    // Test the Huffman class.
    public static void main(String[] args)
    {
	Huffman.PrefixCodeItem[] characters = {
	    new Huffman.PrefixCodeItem('a', 45),
	    new Huffman.PrefixCodeItem('b', 13),
	    new Huffman.PrefixCodeItem('c', 12),
	    new Huffman.PrefixCodeItem('d', 16),
	    new Huffman.PrefixCodeItem('e',  9),
	    new Huffman.PrefixCodeItem('f',  5) };

	Huffman huff = new Huffman(characters);

	// Print out the codewords.
	System.out.println("Codewords:");
	for (int i = 0; i < characters.length; i++)
	    System.out.println(characters[i].getChar() + ": " +
			       characters[i].getCodeWord());

	// Decode a word, which is the String encoding of "cafe".
	String encodedCafe = "100011001101";
	String decodedWord = huff.decode(encodedCafe);

	System.out.println("\nDecoding of \"" + encodedCafe + "\" is \"" +
			   decodedWord + "\"");

	// Decode a word, which is the packed-bit encoding of
	// "deadbeef".
	int[] encodedDeadbeef = { 0x00eeef5f };
	decodedWord = huff.decode(encodedDeadbeef, 26);

	System.out.println("\nDecoding of {" + encodedDeadbeef[0] +
			   "} is \"" + decodedWord + "\"");
    }
}
