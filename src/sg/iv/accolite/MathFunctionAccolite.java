package sg.iv.accolite;

import java.util.Arrays;



public class MathFunctionAccolite {
	
	public long mathFunction(long x) {
		long result = (long) Math.pow(x, 2);
		return result;
	}
	
	public long applyMathFunction(int[] arr) {
		
		long[] arrLong = new long[arr.length];
		for (int i=0;i<arr.length;i++) {
			int num = arr[i];
			arrLong[i] = num;
		}
		
		return applyMathFunction(arrLong, 0, arrLong.length);
		
		
	}
	
	public long applyMathFunction(long[] arr, int startIndex, int endIndex) {
		
		//
		int len = endIndex - startIndex;
		long ret = 0;
		
//		System.out.println("startindex: "+startIndex+" endindex: "+endIndex+" len: "+len);
		
		if (len < 0) {
			ret = 0;
		} else if (len < 2) {
			ret = mathFunction(arr[startIndex]);
		} else {
			int partIndex = startIndex + len/2;
			
//			System.out.println("part 1: start: "+startIndex+" end: "+ (partIndex));
//			System.out.println(Arrays.toString(Arrays.copyOfRange(arr, startIndex, partIndex)));
//			
//			System.out.println("part 2: start: "+(partIndex)+" end: "+ endIndex);
//			System.out.println(Arrays.toString(Arrays.copyOfRange(arr, partIndex, endIndex)));
			
			ret = applyMathFunction(arr,startIndex,partIndex) +  applyMathFunction(arr,partIndex,endIndex);
		}
//		System.out.println(" return: "+ret);
		return ret;
	}
	
	public static void main(String[] args) {
		MathFunctionAccolite test = new MathFunctionAccolite();
		//build a tst array
		int len = 5;
		int[] testarr = new int[len];
		for (int i=0;i<len;i++) {
			testarr[i] = i+1;
		}
		
		System.out.println(Arrays.toString(testarr));
		
		long result = test.applyMathFunction(testarr);
		System.out.println("result: "+result);
	}
}
