package sg.problems.misc;

import sg.util.IntArrayUtil;

public class ArrayProblems {

	// find min or reset point in rotated, sorted array
	// returns index of value
	// [8 9 1 2 3 4 5 6 7] --> return 2 = index of 1
	// numbers must be distinct
	public static int findMinRotatedSortedAsc(int[] arr) {

		if (arr[0] < arr[arr.length - 1]) {
			// array is completely sorted
			return 0;
		}

		return findMinRotatedSortedAsc(arr, 0, arr.length);
	}

	private static int findMinRotatedSortedAsc(int[] arr, int start, int end) {
		
		int mid = (start + end) / 2;
		
//		System.out.println("start: "+start);
//		System.out.println("end: "+end);
//		System.out.println("mid: "+mid);
//		System.out.println("");
		
		if ((end-start) <= 1) {
			return end;
		}

		

		if (arr[start] > arr[mid]) {
			// reset point must be within this range
			return findMinRotatedSortedAsc(arr, start, mid);
		} else {
			return findMinRotatedSortedAsc(arr, mid, end);
		}

	}

	public static int findNumRotatedSortedAsc(int[] arr, int num) {
		return findNumRotatedSortedAsc(arr, num, 0, arr.length);
	}
	
	private static int findNumRotatedSortedAsc(int[] arr, int num, int start, int end) {
		int mid = (start + end) / 2;
		
		
//		System.out.println("start: "+start);
//		System.out.println("end: "+end);
//		System.out.println("mid: "+mid);
//		System.out.println("");
		
		
		if (arr[mid] == num) {
			return mid;
		}
 		if ((end - start) <= 1) {
 			return -1;
		}
		
		if ( (arr[start] <= num) && (num <= arr[mid - 1]) ) {
			//binary search num in arr[start..mid]
			return binSearch(arr, num, start, mid);
		} else  if ( (arr[mid + 1] <= num) && (num <= arr[end-1]) ) {
			//binary search num in arr[mid..end]
			return binSearch(arr, num, mid + 1, end);
		} else {
			//number lies in unsorted part
			if (arr[start] > arr[mid]) {
				return findNumRotatedSortedAsc(arr, num, start, mid);
			} else {
				return findNumRotatedSortedAsc(arr, num, mid, end);
			}
			
		}
	}
	
	private static int binSearch(int[] arr, int num, int start, int end) {
		int mid = (start + end) / 2;
		if (arr[mid] == num) {
			return mid;
		}
 		if ((end - start) <= 1) {
 			return -1;
		}
 		
 		if ( (arr[start] <= num) && (num <= arr[mid - 1]) ) {
			//binary search num in arr[start..mid]
			return binSearch(arr, num, start, mid);
		} else {
			return binSearch(arr, num, mid + 1, end);
		}
 		
	}
	
	public static int findMaxRotatedSortedAsc(int[] arr) {

		if (arr[0] < arr[arr.length - 1]) {
			// array is completely sorted
			return arr.length - 1;
		}
		return findMinRotatedSortedAsc(arr, 0, arr.length) - 1;
	}

	public static void main(String[] args) {
//		int[] arr = ArrayUtil.buildIntArrayFromString("8 9 10 11 12 1 2 3 4 5 6 7");
		// int[] arr = ArrayUtil.buildIntArrayFromString("1 2 3 4 5 6 7");
		// int[] arr = ArrayUtil.buildIntArrayFromString("3 4 5 6 7 1 2");
		
//		 int[] arr = ArrayUtil.buildIntArrayFromString("8 1 2 3 4 5 6 7");
//		 int[] arr = ArrayUtil.buildIntArrayFromString("2 3 4 5 6 7 8 1");
//		 int[] arr = ArrayUtil.buildIntArrayFromString("3 4 5 6 7 8 1 2");
		 int[] arr = IntArrayUtil.buildIntArrayFromString("1 2 3 4 5 6 7 8");
		 
		int idx = findMinRotatedSortedAsc(arr);
		System.out.println(" idx: " + idx);
		System.out.println(" min val: " + arr[idx]);
		
		idx = findMaxRotatedSortedAsc(arr);
		System.out.println(" idx: " + idx);
		System.out.println(" max val: " + arr[idx]);
		
		idx = findNumRotatedSortedAsc(arr, 2);
		System.out.println(" idx: " + idx);
		if (idx >= 0)
		System.out.println(" num val: " + arr[idx]);
		
	}
}
