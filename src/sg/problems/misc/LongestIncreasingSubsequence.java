package sg.problems.misc;

import java.util.Arrays;

import sg.util.ArrayUtil;

public class LongestIncreasingSubsequence {

	public static void printIncreasingSequence(int[] arr) {
		int[] reta = computeIncreasingSequence(arr);
		System.out.println("");
		for (int i = 0; i < reta.length; i++) {
			if (reta[i] > 0)
				System.out.print(arr[i] + " ");
		}
		System.out.println("");
		ArrayUtil.printIntArray(reta);
	}

	public static int getLength(int[] arr) {
		int[] reta = computeIncreasingSequence(arr);
		return reta.length;
	}

	public static void computeIncreasingSequence2(int[] arr) {
		int[][] arrCount = new int[arr.length][arr.length];
		int lenCount[] = new int[arr.length];
		int len = arr.length;

		for (int i = 0; i < len; i++)
			Arrays.fill(arrCount[i], 0);

		for (int i = 0; i < len - 1; i++) {
			int count = 1;
			for (int j = i; j < len; j++) {
				if (arr[i] < arr[j]) {
					arrCount[i][j]++;
					count++;
					//System.out.println(" arr[i] , arr[j]: " + arr[i] + " " + arr[j]);
				}
				lenCount[i] = count;
			}
			System.out.println(" arr[i] , arrCount[i]: " + arr[i]);
			ArrayUtil.printIntArray(arrCount[i]);
		}
		ArrayUtil.print2dIntArray(arrCount);
		ArrayUtil.printIntArray(lenCount);
	}
	
	public static void computeIncreasingSequence3(int[] arr) {
		int[] arrCount = new int[arr.length];
		int len = arr.length;

//		for (int i = 0; i < len; i++)
			Arrays.fill(arrCount, 0);

		for (int i = 0; i < len - 1; i++) {
			for (int j = i; j < len; j++) {
				if (arr[i] < arr[j]) {
					arrCount[j]++;
					//System.out.println(" arr[i] , arr[j]: " + arr[i] + " " + arr[j]);
				}
			}
//			System.out.println(" arr[i] , arrCount[i]: " + arr[i]);
//			ArrayUtil.printIntArray(arrCount);
		}
		ArrayUtil.printIntArray(arrCount);
	}

	private static int[] computeIncreasingSequence(int[] arr) {
		int[] arrCount = new int[arr.length];
		int len = arr.length;

		for (int i = 0; i < len; i++)
			Arrays.fill(arrCount, 0);

		for (int i = 0; i < len - 1; i++) {
			int count = 1;
			int lastNum = arr[0];
			arrCount[0] = 1;
			ArrayUtil.printIntArray(arrCount);
			for (int j = 1; j < len; j++) {
				if (lastNum < arr[j]) {
					lastNum = arr[j];
					count++;
					arrCount[j] = Math.max(count, arrCount[j]);
				}
			}
		}

		int ret = arrCount[0];
		for (int i = 0; i < len; i++) {
			if (arrCount[i] > ret)
				ret = arrCount[i];
		}
		ArrayUtil.printIntArray(arrCount);
		return arrCount;

	}

	public static void main(String[] args) {
		// { 10, 22, 9, 33, 21, 50, 41, 60, 80 } is 6 and LIS is {10, 22, 33,
		// 50, 60, 80}.
		// int arr[] = {10, 22, 9, 33, 21, 50, 41, 60, 80};
//		 int arr[] = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
		// {0, 2, 6, 9, 11, 15}
		int arr[] = { 0, 100, 1, 2, 3, 4, 5, 6 };

//		System.out.println("LIS len: "
//				+ LongestIncreasingSubsequence.getLength(arr));
//		LongestIncreasingSubsequence.printIncreasingSequence(arr);
		
		LongestIncreasingSubsequence.computeIncreasingSequence2(arr);
//		LongestIncreasingSubsequence.computeIncreasingSequence3(arr);
	}

}
