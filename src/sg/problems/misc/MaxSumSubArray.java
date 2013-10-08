package sg.problems.misc;

import sg.util.ArrayUtil;


public class MaxSumSubArray {
	public static void maxSumSubArray( int[] array)
	{
	    int maxSumSoFar = Integer.MIN_VALUE;
	    int curSum = 0;
	    int a=0, b=0 , s=0, i = 0;
	    
	    for( i = 0; i < array.length; i++ ) {
	    	
	        curSum += array[i];
	        if ( curSum > maxSumSoFar ) {
	            maxSumSoFar = curSum;
	            a = s;
	            b = i;
	        }
	        if( curSum < 0 ) {
	            curSum = 0;
	            s = i + 1;
	        }
//	        System.out.println("curSum: "+curSum+" start: "+ a +" end: "+b+" maxSumSoFar: "+maxSumSoFar);
	    }
	    System.out.println("start: "+ a +" end: "+b+" maxsum: "+maxSumSoFar);
	  
	}
	
	public static void main(String[] args) {
		int[] arr  = ArrayUtil.buildIntArrayFromString("1 2 3 -4 -5 2 4 -5 7", " ");
		ArrayUtil.printIntArray("array", arr);
		//arr  = ArrayUtil.buildIntArrayFromString("-1 -2 -3 -4", " ");
		maxSumSubArray(arr);
	}
	
}
