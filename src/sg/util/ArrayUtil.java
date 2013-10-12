package sg.util;

import java.util.Arrays;
import java.util.Random;

public class ArrayUtil {
	public static int[] buildIntArrayFromString(String str) {
		return buildIntArrayFromString(str, " ");
	}

	public static int[] buildIntArrayFromString(String str, String sep) {
		String[] strA = str.split(sep);
		int[] ret = new int[strA.length];

		for (int i = 0; i < strA.length; i++) {
			ret[i] = Integer.parseInt(strA[i]);
		}
		return ret;
	}
	
	public static int[] getSequentialIntArray(int start, int length) {
		if (length < 0) return null;
		int[] arr = new int[length];
		for (int i = 0; i< length; i++) {
			arr[i] = i + start;
		}
		return arr;
	}
	
	public static int[] getRandomIntArray(int length, int min, int max) {
		int[] ret = new int[length];
		Random random = new Random();
		for (int i = 0; i < ret.length; i++) {
			double minmax = (random.nextDouble() * 100);
//			System.out.println((int)minmax);
//			System.out.println((int)minmax%2);
//			System.out.println(minmax%2);
			if ((int)minmax%2 == 0) {
				//produce a -ve number
				ret[i] = -1 * random.nextInt(Math.abs(min));
			} else {
				//produce a +ve number
				ret[i] = random.nextInt(max);
			}
			
		}
		// System.out.println("Random Array: "+Arrays.toString(ret));
		return ret;
	}
	
	public static int[] getRandomIntArray(int length, int max) {
		int[] ret = new int[length];
		Random random = new Random();
		for (int i = 0; i < ret.length; i++) {
			
			ret[i] = random.nextInt(max);
		}
		// System.out.println("Random Array: "+Arrays.toString(ret));
		return ret;
	}
	
	public static class BinarySearch {

		// takes a sorted array
		public int binarySearchRecursive(int value, int[] arr) {
			return binarySearchRecursive(value, arr, 0, arr.length);
		}

		public int binarySearchRecursive(int value, int[] arr, int start, int end) {
			
			int mid = (start + end) / 2;
					
			if (end <= start) {
				return -1;
			} 
			if (arr[mid] == value) {
				return mid;
			} else if (arr[mid] < value) {
				return binarySearchRecursive(value, arr, mid + 1, end);
			} else {
				return binarySearchRecursive(value, arr, start, mid);
			}
			

		}
		
		
		public int binarySearchIterative(int value, int[] arr) {
			
			int start = 0;
			int end = arr.length;

			while (start < end) {
				int mid = (start + end) / 2; 
				if (arr[mid] == value) {
					return mid;
				} else if (arr[mid] < value) {
					start = mid + 1;
				} else {
					end = mid;
				}
			}
			
		return -1;

		}
	}

	public static void shuffleIntArray(int[] arr) {
		shuffleIntArray(arr, arr.length);
	}
	public static void shuffleIntArray(int[] arr, int numberofShuffles) {
		Random random = new Random();
		for (int i = 0; i < numberofShuffles; i++) {
			int idx1 = random.nextInt(arr.length);
			int idx2 = random.nextInt(arr.length);
			int temp = arr[idx1];
			arr[idx1] = arr[idx2];
			arr[idx2] = temp;
		}
		return;
	}

	public static void printIntArray(int[] arr) {
		System.out.println(Arrays.toString(arr));
	}
	
	public static void printIntArray(String msg, int[] arr) {
		System.out.println(msg + ": " + Arrays.toString(arr));
	}
	
	public static void print2dIntArray(int[][] arr) {
		System.out.println("");
		for (int i=0;i<arr.length;i++) {
			for (int j=0;j<arr[i].length;j++) {
				System.out.print(String.format("%5s", arr[i][j]));
			}
			System.out.println("");
		}
	}
}
