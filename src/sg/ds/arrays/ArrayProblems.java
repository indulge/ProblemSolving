package sg.ds.arrays;

import java.util.Arrays;
import java.util.Random;

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

		// System.out.println("start: "+start);
		// System.out.println("end: "+end);
		// System.out.println("mid: "+mid);
		// System.out.println("");

		if ((end - start) <= 1) {
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

	private static int findNumRotatedSortedAsc(int[] arr, int num, int start,
			int end) {
		int mid = (start + end) / 2;

		// System.out.println("start: "+start);
		// System.out.println("end: "+end);
		// System.out.println("mid: "+mid);
		// System.out.println("");

		if (arr[mid] == num) {
			return mid;
		}
		if ((end - start) <= 1) {
			return -1;
		}

		if ((arr[start] <= num) && (num <= arr[mid - 1])) {
			// binary search num in arr[start..mid]
			return binSearch(arr, num, start, mid);
		} else if ((arr[mid + 1] <= num) && (num <= arr[end - 1])) {
			// binary search num in arr[mid..end]
			return binSearch(arr, num, mid + 1, end);
		} else {
			// number lies in unsorted part
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

		if ((arr[start] <= num) && (num <= arr[mid - 1])) {
			// binary search num in arr[start..mid]
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

	
	
	
	public int[] getUnsortedArrayWithNoMissingValues(int length) {
		int[] ret = new int[length];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = i + 1;
		}

		shuffleArray(ret, length);
		return ret;
	}

	

	public void shuffleArray(int[] arr, int numberofShuffles) {
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

	// assumes sorted array, replaces in place
	public int removeDuplicatesFromSortedArray(int[] arr) { // return index of
															// last item in
															// array
		int uniqueIndex = 0;
		int dupIndex = 1;

		if (arr.length < 2) {
			return arr.length;
		}

		for (int i = 1; i < arr.length; i++) {
			if (arr[uniqueIndex] != arr[dupIndex]) {
				uniqueIndex++;
				arr[uniqueIndex] = arr[dupIndex];
				dupIndex++;

			} else {
				dupIndex++;
			}

		}

		return ++uniqueIndex;

	}

	//TODO: incomplete
	public void sortArrayWithMissingValues(int[] arr) {

		int dupCount = 0;
		int prevVal = 0;

		for (int counter = 0, i = 0; counter < arr.length; counter++) {

			System.out.println(" i: " + i + " dup: "
					+ dupCount + " prev Val: " + prevVal
					+ " current val: " + arr[i + dupCount] + " at idx: "+ (i + dupCount)
							);
			
			if (arr[i + dupCount] != i + 1 - dupCount) {

				int num1 = arr[arr[i + dupCount] - 1];
				int num2 = arr[i + dupCount];

				if (num1 == num2 && ((i+dupCount)!=num1-1)) {
					dupCount++;
					System.out.println("duplicate found");
				} else {
					arr[arr[i + dupCount] - 1] = num2;
					prevVal = num2;
					arr[i + dupCount] = num1;
				}

			} else {
//				System.out.println(" R");
				i++;
			}

			IntArrayUtil.printIntArray("inter", arr);
		}
		// //ArrayUtil.printArray("Sorted Array with no missing values: ", arr);
	}

	public int[] indexValueMismatchWithoutSorting(int[] arr) {
		int[] valCount = new int[arr.length];

		for (int counter = 0; counter < arr.length; counter++) {
			valCount[arr[counter] - 1]++;
		}

		return valCount;
	}

	public int countIndexValueMismatchWithSorting(int[] arr) {
		sortArrayWithMissingValues(arr);
		int ret = 0;
		for (int counter = 1; counter <= arr.length; counter++) {
			if (counter == arr[counter - 1]) {
				ret++;
			}
		}
		return ret;
	}

	public void sortArrayWithNoMissingValues(int[] arr) {
		for (int i = 0, counter = 0; counter < arr.length; counter++) {
			if (arr[i] != i + 1) {
				int temp = arr[arr[i] - 1];
				arr[arr[i] - 1] = arr[i];
				arr[i] = temp;
			} else {
				i++;
			}
//			ArrayUtil.printIntArray("inter", arr);
		}
		// ArrayUtil.printArray("Sorted Array with no missing values: ", arr);
	}

	public int findTwoBiggest(int[] arr) {
		if (arr.length < 2) {
			return arr[0];
		}
		
		
		int big1 = arr[0];
		int big2 = arr[1];	
		
		for (int i=2;i<arr.length;i++) {
			if (arr[i] > big1) {
				big1 = arr[i];
			} else if (arr[i] > big2)  {
				big2 = arr[i];
			}
		}
		System.out.println("big1: "+big1+" big2: "+big2);
		return big1+big2;
		
	}
	
	public int findTwoBiggestDistinct(int[] arr) {
		if (arr.length < 2) {
			//no such numbers
			System.out.println("no such numbers");
			return arr[0];
		}
		
		
		int big1 = arr[0];
		int big2 = arr[1];	
		
		int i = 2;
		
		while (i < arr.length && big1 == big2) {
			big2 = arr[i];
			i++;
		}
		
		if (big1 != big2) {
			for (;i<arr.length;i++) {
				if (arr[i] > big1  && arr[i] != big2) {
					big1 = arr[i];
				} else if (arr[i] > big2 && arr[i] != big1)  {
					big2 = arr[i];
				}
			}
			System.out.println("big1: "+big1+" big2: "+big2);
		}  else {
			//no such numbers
			System.out.println("no such numbers");
		}
		
		
		
		return big1+big2;
		
	}
	//get kth largest element from array
	public int[] findKLargest(int[] arr, int k) {
		int[] klarge = new int[k];
		
		int i = 0;
		for (;i < k; i++) {
			klarge[i] = arr[i];
		}
		
		for (;i<arr.length;i++) {
			int j=0;
			while (j<k) {
				if (klarge[j] < arr[i]) {
					klarge[j] = arr[i];
					break;
				}
				j++;
			}
		}
		return klarge;
	}
	
	//todo: complete functin with introduction of external array.
	public int getDuplicates(int[] arr) {
		int i = 0;
		int j = 0;
		int k = arr.length - 1;
		int numDuplicates  = 0;
		for (; i<arr.length; i++) {
			System.out.println("current: "+arr[j]);
			if (arr[j] != j + (numDuplicates + 1)) {
				int temp = arr[arr[j] - (numDuplicates + 1)];
				if (temp == arr[j]) {
					//this is a duplicate, replace with last number and redo;
					int last = arr[k];
					arr[k] = arr[j];
					arr[j] = last;
					k--;
					numDuplicates++;
					System.out.println("duplicate -> swap with: "+last+" after swap: "+Arrays.toString(arr));
					continue;
				} else {
					arr[arr[j] - (numDuplicates + 1)] = arr[j];
					arr[j] = temp;
					System.out.println("swap with: "+temp+" after swap: "+Arrays.toString(arr));
				}
			} else {
				j++;
			}
		}
		return k + 1;
	}
	
	/*
	 * 17.	Given two arrays A[6] and B[3], both the arrays have 3 elements each. 
	 * Both of them are sorted, need to return the merged array A[] after merging with array B[]. 
	 * Need to do this inplace.
	 */
	public static void mergeQuestion() {
		int arrlen = 3;
		int [] arra = new int[arrlen * 2];
		int [] arrb = new int[arrlen];
		
		int [] arrc = new int[arrlen * 2];
		
		arra = IntArrayUtil.getRandomIntArray(arrlen * 2, 100);
		arrb = IntArrayUtil.getRandomIntArray(arrlen, 100);
		
		Arrays.sort(arra);
		Arrays.sort(arrb);
		

//		arra = ArrayUtil.buildIntArrayFromString("11, 11, 40, 0, 0, 0", ", ");
//		arrb = ArrayUtil.buildIntArrayFromString("1, 19, 50", ", ");
		
		for (int i=0; i<arrlen; i++) {
			arrc[i] = arra[i];
		}
		
		for (int i=arrlen; i<arrlen*2; i++) {
			arrc[i] = arrb[i-arrlen];
		}
		
		
		
		Arrays.fill(arra, arrb.length, arra.length, 0);
		
//		ArrayUtil.printIntArray("arra", arra);
//		ArrayUtil.printIntArray("arrb", arrb);
//		ArrayUtil.printIntArray("arrc", arrc);
		
		int a = arrlen - 1;
		int b = arrlen - 1;
		int c = arrlen * 2 - 1;
		
		while (a >= 0 && b >= 0) {
			if (arra[a] > arrb[b]) {
				arra[c] = arra[a];
				a--;
			} else {
				arra[c] = arrb[b];
				b--;
			}
			c--;
//			ArrayUtil.printIntArray("arra inter", arra);
		}
//		System.out.println("---merging a or b left");
		while (c >= 0) {
			if (a >= 0) {
				arra[c] = arra[a];
				a--;
			} else { 
				arra[c] = arrb[b];
				b--;
			}
			c--;
//			ArrayUtil.printIntArray("arra inter", arra);
		}
//		System.out.println("---");
		
//		ArrayUtil.printIntArray("Merged Array", arra);
		Arrays.sort(arrc);
//		ArrayUtil.printIntArray("Sorter Array", arrc);
		
		for (int i=0;i<arrlen * 2 -1;i++) {
			if (arra[i] != arrc[i]) {
				System.out.println("!!!!!!!!!!! program failed !!!!!!!!!!");
			} 
		}
		
		
	}

	public static void main(String[] args) {
		// int[] arr =
		// ArrayUtil.buildIntArrayFromString("8 9 10 11 12 1 2 3 4 5 6 7");
		// int[] arr = IntArrayUtil.buildIntArrayFromString("1 2 3 4 5 6 7");
		// int[] arr = IntArrayUtil.buildIntArrayFromString("3 4 5 6 7 1 2");

		// int[] arr = IntArrayUtil.buildIntArrayFromString("8 1 2 3 4 5 6 7");
		// int[] arr = IntArrayUtil.buildIntArrayFromString("2 3 4 5 6 7 8 1");
		int[] arr = IntArrayUtil.buildIntArrayFromString("3 4 5 6 7 8 1 2");
		
		//int[] arr = IntArrayUtil.buildIntArrayFromString("1 2 3 4 5 6 7 8");

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




/*
 * 
 * 
 * public static void main(String[] args) {
		ArraysTest test = new ArraysTest();
		int arrSize = 10;
		long startTime = 0;
		long endTime = 0;

		// startTime = System.currentTimeMillis();
		// endTime = System.currentTimeMillis() - startTime;
		// System.out.println("Time Taken:"+(endTime));

		startTime = System.currentTimeMillis();
		int[] arr = test.getUnsortedArrayWithNoMissingValues(arrSize);
		endTime = System.currentTimeMillis() - startTime;
		System.out.println("Time Taken to get shuffled array:" + (endTime));

		startTime = System.currentTimeMillis();
		test.sortArrayWithNoMissingValues(arr);
		endTime = System.currentTimeMillis() - startTime;
		System.out.println("Time Taken to sort:" + (endTime));
		ArrayUtil.printIntArray("Sorted Array", arr);

//		arr = ArrayUtil.getRandomIntArray(arrSize, arrSize - 1);
		arr = ArrayUtil.buildIntArrayFromString("7, 5, 7, 5, 8, 6, 6, 2, 8, 3", ", ");
		ArrayUtil.printIntArray("Random Array", arr);
		
//		int dupIndex = test.getDuplicates(arr);
//		ArrayUtil.printIntArray("sorted", arr);
//		ArrayUtil.printIntArray("duplicates", Arrays.copyOfRange(arr, dupIndex, arrSize));
		for (int i=0;i<1000;i++)
		mergeQuestion();
		
//		arr = ArrayUtil.buildIntArrayFromString("1 1 1 1 3");
//		ArrayUtil.printIntArray("Array", arr);
//		test.findTwoBiggestDistinct(arr);
//		
//		int[] klarge = test.findKLargest(arr, 3);
//		ArrayUtil.printIntArray("klarge", klarge);

		// test.sortArrayWithNoMissingValues(arr);
		// ArrayUtil.printArray("Sorted Array", arr);

		// int[] misMatchCountArr = test.indexValueMismatchWithoutSorting(arr);
		// System.out.println("Number of mismatch: "+Arrays.toString(misMatchCountArr));

//		int misMatchCount = test.countIndexValueMismatchWithSorting(arr);
//		System.out.println("Number of mismatch: " + misMatchCount);

		// System.out.println(System.currentTimeMillis());
		// arr = test.getRandomArray(arrSize, arrSize);
		// System.out.println(System.currentTimeMillis());
		// // int[] arr = test.buildArrayFromString("1 2 3 4 5");
		// Arrays.sort(arr);
		// System.out.println(System.currentTimeMillis());
		// ArrayUtil.printArray("Sorted Array", arr);
		// System.out.println(System.currentTimeMillis());
		// int lastidx = test.removeDuplicatesFromSortedArray(arr);
		// int[] arr2 = Arrays.copyOfRange(arr, 0, lastidx);
		// System.out.println("Array with duplicates removed array: "+Arrays.toString(arr2));
	}
 * 
 * 
 * 
 */
