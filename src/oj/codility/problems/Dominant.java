package oj.codility.problems;

import java.math.BigInteger;
import java.util.Arrays;

public class Dominant {

	public int arrLeader(int[] A) {
		int count = 0;
		int x = 0;
		for (int i = 0; i < A.length; i++) {
			if (count == 0) {
				x = A[i];
				count++;
			} else if (A[i] == x) {
				count++;
			} else {
				count--;
				// count−−;
			}
		}
		if (count <= 0) {
			return -1;
		}
		return count;
	}
	
	public int arrLeader2(int[] A) {
		int n = A.length;
		for(int i = 0; i< n/2+1; i++)
	    {
	        int count=1;
	        for(int j = i+1; j < n; j++)
	        {
	            if (A[i]==A[j]) {count++; if (count > (n/2)) return A[i];}
	        }
	    }

	    return -1;
	}
	
	public int arrLeader3(int[] A) {
		Arrays.sort(A);
		if (A.length == 1) return A[0];
		int n = A.length;
		int count = 0;
//		System.out.println(Arrays.toString(A));
		int curr = A[0];
		for(int i = 1; i < n; i++)
	    {
//			System.out.println(count+", "+n/2);
			
//			if (n%2 == 1) {
//				
//			} else {
//				
//			}
			
			
	       if (curr == A[i]) count++;
	       else {
	    	   count = 1;
	    	   curr = A[i];
	       }
	       if (count >= (n/2)) return A[n/2];
	     
	    }

	    return -1;
	}

	public static void main(String[] args) {
		//int[] arr = null;
		//int[] arr = {4, 2, 2, 3, 2, 4, 2, 2, 6, 4};
		int[] arr = {100, 1,1,50,1};
		//int[] arr = {23,3,67,67,67};
		//int[] arr = {1,2,3,4,5,6,2,2,2,2,2,2,2,2};
		//int[] arr = {2,4,4,4,7};
		//int[] arr = {2,2};
		Dominant d = new Dominant();
		int res = 0;
//		res = d.arrLeader(arr);
//		res = d.arrLeader2(arr);
		
		res = d.arrLeader3(arr);
		
		System.out.println(res);
	}
}

/*
 * element x; int count �? 0; For(i = 0 to n − 1) { if(count == 0) { x �? A[i];
 * count++; } else if (A[i] == x) count++; else count−−; } Check if x is
 * dominant element by scanning array A
 */

