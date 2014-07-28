package sg.problems.quicksort;

import java.util.Arrays;

import sg.util.ArrayUtil;

public class QuickSort {
	private static void partition(int[] arr, int start, int end) {
		int smallEnd = start - 1;
		int key = arr[end - 1];
		for (int i = start; i < end - 1; i++) {
			if (arr[i] < key) {
				smallEnd = smallEnd + 1;
				int temp = arr[smallEnd];
				arr[smallEnd] = arr[i];
				arr[i] = temp;
			}
		}
		
		smallEnd = smallEnd + 1;
		arr[end - 1] = arr[smallEnd];
		arr[smallEnd] = key;
	}
	
//	private static void partition(int[] arr, int start, int end) {
//		int key = arr[end - 1];
//	}

	public static void partition(int[] arr) {
		partition(arr, 0, arr.length);
	}

	public static void main(String[] args) {
		// int arr[] = ArrayUtil.getRandomIntArray(10, 100);
		// int arr[] =
		// ArrayUtil.buildIntArrayFromString("20 4 5 7 40 3 50 9 2");
		int pass = 0;
		int fail = 0;
		for (int i = 0; i < 10; i++) {
//			int orig[] = ArrayUtil.getRandomIntArray(10, 100);
			int orig[] = ArrayUtil.getRandomIntArray(10, 10);

			ArrayUtil.printIntArray("orig: ", orig);
			int[] arr1 = Arrays.copyOf(orig, orig.length);
			QuickSort.partition(arr1);
			ArrayUtil.printIntArray("part: ", arr1);

			// int[] arr2 = Arrays.copyOf(orig, orig.length);
			// // QuickSorter.partition(arr2, 0, arr2.length - 1);
			// QuickSorter.part2(arr2, 0, arr2.length);
			//
			// ArrayUtil.printIntArray("part ex",arr2);
			//
			// int comp = ArrayUtil.compareArrays(arr1, arr2);

//			if (comp < 0) {
//				fail++;
//				System.out.println(" \n**Case Failed** ");
//			} else
//				pass++;
		}
//		System.out.println("pass: " + pass + " failed: " + fail);
	}

}
