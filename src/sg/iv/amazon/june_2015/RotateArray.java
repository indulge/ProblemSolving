package sg.iv.amazon.june_2015;

import java.util.Arrays;

import sg.util.ArrayUtil;

public class RotateArray {
	public static void main(String[] args) {
		int[] arr = ArrayUtil.getSequentialIntArray(1, 5);
		for (int i = 1; i <= arr.length; i++) {
			arr = ArrayUtil.getSequentialIntArray(1, 5);
			rotateArray(arr, i);
		}
	}

	public static void rotateArray(int[] arr, int rot) {

		int idx = 0;
		int temp = arr[0];

		for (int i = 0; i < arr.length; i++) {
			int newi = (idx + rot) % arr.length;
			//System.out.println("idx: " + idx + " newi: " + newi);
			int temp2 = arr[newi];
			arr[newi] = temp;
			temp = temp2;
			idx = newi;
			
			//System.out.println("temp: " + temp + " : " + Arrays.toString(arr));
		}

		System.out.println("rot: " + rot + " : " + Arrays.toString(arr));
	}
}
