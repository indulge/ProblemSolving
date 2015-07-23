package sg.iv.flipkart.july_2015.machine_round;

import java.util.Arrays;

import sg.iv.adobe.ArrayUtil;

//1 -You are given a sorted array of size 7 but only 4 elements in it and a sorted array of 3 elements. 
//How would to combine the elements into the first array in such a way that array is sorted.
public class Problem1 {
	
	static int[] arr1;
	static int[] arr2;
	
	public static void main(String[] args) {
		int len1 = 4;
		int len2 = 3;
		init(len1, len2);
		ArrayUtil.printIntArray(arr1);
		ArrayUtil.printIntArray(arr2);
		int i = len1+len2-1;
		int j = len1-1;
		int k = len2-1;
		for (;i>=0;i--) {
			if (k<0) arr1[i]=arr1[j--];
			else if (j<0) arr1[i]=arr2[k--];
			else if (arr2[k]<arr1[j]) {
				arr1[i] = arr1[j];
				j--;
			} else {
				arr1[i] = arr2[k];
				k--;
			}
		}
		ArrayUtil.printIntArray(arr1);
	}
	
	public static void init(int len1, int len2) {
		arr1 = ArrayUtil.getRandomIntArray(len1+len2, 10);
		arr2 = ArrayUtil.getRandomIntArray(len2, 10);
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		for (int i = len1; i < (len1+len2); i++) {
			arr1[i] = -1;
		}
	}
}
