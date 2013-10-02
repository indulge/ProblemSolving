package sg.iv.expedia;
import java.util.Arrays;


public class MergeSort {

	public void sort(int[] arr) {
		sort(arr, 0, arr.length);
	}
	public void sort(int[] arr, int start, int end) {
		if (end-start <= 1) {
			return;
		} 
		
		int mid = (start+end)/2;
		sort(arr, start, mid);
		sort(arr, mid, end);
		merge(arr, start, mid, end);
	}
	
	public void merge(int[] arr, int start, int mid, int end) {
//		System.out.println(" start: "+start+" mid: "+mid+" end: "+end);
//		ArrayUtil.printIntArray("start->mid",Arrays.copyOfRange(arr, start, mid));
//		ArrayUtil.printIntArray("mid->end",Arrays.copyOfRange(arr, mid, end));
		int i = start;
		int j = mid;
		int k = 0;
		int[] result = new int[end - start];
		while (i<mid && j<end) {
			if (arr[i] < arr[j]) {
				result[k] = arr[i];
				i++;
			} else {
				result[k] = arr[j];
				j++;
			}
			k++;
		}
		
		for (;k<(end - start);k++) {
//			System.out.println(" i: "+i+" j: "+j+" k: "+k);
			if(i>=mid) {
				result[k] = arr[j];
				j++;
			} else {
				result[k] = arr[i];
				i++;
			}
		}
		
		for (i=0;i<(end - start);i++) {
			arr[start+i] = result[i];
		}
//		ArrayUtil.printIntArray("result",result);
//		ArrayUtil.printIntArray("arr",arr);
//		return result;
	}
	
	public static void main(String[] args) {
		MergeSort test = new MergeSort();
		int[] arr = ArrayUtil.getRandomIntArray(1000000, 1000000);
		//arr = ArrayUtil.buildIntArrayFromString("33, 84, 87, 19, 70, 96, 29, 35, 17, 9", ", ");
//		ArrayUtil.printIntArray(arr);
		ArrayUtil.printIntArray("sorted",Arrays.copyOf(arr, 1000));
		test.sort(arr);
		ArrayUtil.printIntArray("sorted",Arrays.copyOf(arr, 1000));
	}
}
