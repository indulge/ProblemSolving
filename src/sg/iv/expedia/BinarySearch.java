package sg.iv.expedia;
import java.util.Arrays;
import java.util.Random;

public class BinarySearch {

	// takes a sorted array
	public int binarySearchRecursive(int value, int[] arr) {
		return binarySearchRecursive(value, arr, 0, arr.length);
	}

	public int binarySearchRecursive(int value, int[] arr, int start, int end) {

//		int mid = (start + (end - 1)) / 2;
		int mid = (start + end) / 2;
		
//		System.out.println("start: " + start + " end: " + end + " mid: " + mid);
		
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
	
	public static void main(String[] args) {
		BinarySearch test = new BinarySearch();
		int arraySize = 5;
		
		int[] arr = new int[arraySize];
		
		
		Random random = new Random();
		for (int i=0;i<arr.length;i++) {
			arr[i] = random.nextInt(arr.length * 2);
		}
		
		Arrays.sort(arr);
		System.out.println("Array: " + Arrays.toString(arr));
		
		int numTest  = 5;
//		System.out.println("binarySearchRecursive:");
		for (int i = 0; i <numTest; i++) {
			int searchItem = random.nextInt(arr.length * 2);
			int resultRec = test.binarySearchRecursive(searchItem, arr);
			int resultIter =  test.binarySearchIterative(searchItem, arr);
			System.out.println("search: " +  searchItem + " result rec: "+ (resultRec>=0?"F":"N") + " result iter: "+ (resultIter>=0?"F":"N"));
		}
		
//		System.out.println("binarySearchIterative:");
//		for (int i = 0; i <numTest; i++) {
//			int searchItem = random.nextInt(arr.length * 2);
//			int result = test.binarySearchIterative(searchItem, arr);
//			System.out.println("search: " +  searchItem + " result: "+ (result>=0?"found":"not found"));
//		}
		
		
	}
}
