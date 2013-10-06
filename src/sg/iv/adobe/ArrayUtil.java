package sg.iv.adobe;


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
	
	public static void printIntArray(int[] arr) {
		System.out.println("Array: " + Arrays.toString(arr));
	}
	
	public static int[] getRandomIntArray(int length, int range) {
		int[] ret = new int[length];
		Random random = new Random();
		for (int i = 0; i < ret.length; i++) {
			ret[i] = random.nextInt(range) + 1;
		}
		// System.out.println("Random Array: "+Arrays.toString(ret));
		return ret;
	}

	public static void printIntArray(String msg, int[] arr) {
		System.out.println(msg + ": " + Arrays.toString(arr));
	}
}
