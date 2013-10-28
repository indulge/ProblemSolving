package sg.problems.misc;

import sg.util.ArrayUtil;

public class MergeSortArrays {
	public static int[] mergeSortAscending(int[] arr1, int[] arr2) {
		int[] ret = new int[arr1.length + arr2.length];
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] < arr2[j]) {
				ret[k] = arr1[i];
				i++;
			} else {
				ret[k] = arr2[j];
				j++;
			}
			k++;
		}
		if (i >= arr1.length) {
			while (j < arr2.length) {
				ret[k] = arr1[j];
				j++;
				k++;
			}
		} else if (j >= arr2.length) {
			while (i < arr1.length) {
				ret[k] = arr1[i];
				i++;
				k++;
			}
		}
		return ret;
	}
	
	public static void main(String[] args) {
		int[] a1 = ArrayUtil.getSequentialIntArray(1, 10);
		int[] a2 = ArrayUtil.getSequentialIntArray(1, 10);
		int[] ret = MergeSortArrays.mergeSortAscending(a1, a2);
		ArrayUtil.printIntArray(ret);
	}
}
