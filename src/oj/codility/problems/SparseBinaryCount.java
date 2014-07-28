package oj.codility.problems;

import java.math.BigInteger;

import sg.util.MathUtil;

public class SparseBinaryCount {
	public int sparse_binary_count(String S, String T, int K) {
		BigInteger bs = new BigInteger(S, 2);
		BigInteger bt = new BigInteger(T, 2);
		
		long ls = bs.longValue();
		long lt = bt.longValue();
		
		System.out.println(ls);
		System.out.println(lt);
		
		int count = 0;
		for (long i = ls; i <= lt; i++) {
//			System.out.println(i);
			if (MathUtil.isKSparse(i,K)) count++;
		}
		return count;
	}
	
	public static void main(String[] args) {
		SparseBinaryCount sc = new SparseBinaryCount();
		//S = "101" (A = 5), T = "1111" (B=15) and K=2,
		long t1 = System.nanoTime();
		int count = sc.sparse_binary_count("101", "101010101010010001", 2);
		System.out.println(count);
		long t2 = System.nanoTime();
		long t = t2 - t1;
		long secs = t / (1000 * 1000 * 1000);
		long ms = t / (1000 * 1000);
		long us = t/1000;
		
		System.out.println("milli sec: "+ms);
	}
}

