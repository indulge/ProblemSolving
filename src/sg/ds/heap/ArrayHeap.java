package sg.ds.heap;

import java.util.Arrays;

import sg.util.ArrayUtil;

public class ArrayHeap {

	private int[] heap;
	private int size = 0;
	private final HeapType type;

	public static enum HeapType {
		MIN, MAX
	};

	private static enum HeapifyMode {
		TOP_DOWN, BOTTOM_UP
	};

	public ArrayHeap(int max, HeapType type) {
		heap = new int[max];
		this.type = type;
	}

	public static ArrayHeap buildSampleMax(String arrString) {
		int[] arr = ArrayUtil.buildIntArrayFromString(arrString);
		ArrayHeap ret = new ArrayHeap(arr.length, HeapType.MAX);
		ArrayUtil.printIntArray(arr);
		ret.add(arr);
		// for (int i = 0; i < arr.length; i++) {
		// ret.add(arr[i]);
		// }
		ret.arrangeBottomUp();
		return ret;
	}

	public static ArrayHeap buildSampleMax(int len) {
		return buildSampleMax(len, 100);
	}
	public static ArrayHeap buildSampleMax(int len, int max) {
		ArrayHeap ret = new ArrayHeap(len, HeapType.MAX);
		int[] arr = ArrayUtil.getRandomIntArray(len, max);
		ret.add(arr);
		ArrayUtil.printIntArray(arr);
		ret.arrangeBottomUp();
		// for (int i = 0; i < arr.length; i++) {
		// ret.add(arr[i]);
		// }
		return ret;
	}

	// public void maxHeapify() {
	// maxHeapify(0, HeapifyMode.TOP_DOWN);
	// }

	public int remove() {
		if (size > 0) {
			int temp = heap[0];
			heap[0] = heap[size - 1];
			heap[size - 1] = temp;
			size--;
			if (type == HeapType.MIN) {
				minHeapify(0);
			} else {
				// while (size/2 >= 0)
				maxHeapify(0, HeapifyMode.TOP_DOWN);
			}
			return temp;
		} else {
			return -1;
		}

	}

	public void heapSort() {

	}

	public void add(int[] item) {
		heap = item;
		size = item.length;
		arrangeBottomUp();
	}

	public int getSize() {
		return size;
	}
	public void add(int item) {
		System.out.println("adding: " + item);
		
		if ((heap == null) || (size >= heap.length)) {
			int[] arr = new int[2 * size + 1];
			for (int i = 0; i < heap.length; i++) {
				arr[i] = heap[i];
			}
			heap = arr;
		}

		heap[size] = item;
		size++;
		maxHeapify((size) / 2 - 1, HeapifyMode.BOTTOM_UP);
		// arrangeBottomUp();
		//System.out.println("Heap Full");

	}

	private void arrangeBottomUp() {
		if (type == HeapType.MIN) {
			for (int i = size / 2; i >= 0; i--) {
				minHeapify(i);
			}
		} else {
			for (int i = size / 2; i >= 0; i--) {
				maxHeapify(i, HeapifyMode.BOTTOM_UP);
			}
		}
	}

	private void maxHeapify(int idx, HeapifyMode mode) {
//		System.out.println("maxHeapify for idx: " + idx + " heap: " + Arrays.toString(getHeap()));
		if (idx < 0) {
			return;
		}
		int largest = idx;
		int l = 2 * idx + 1;
		int r = 2 * idx + 2;
//		System.out.println("l: " + l + " r: " + r + " largest: " + largest + " size: " + size);
		if ((l < size) && (heap[l] > heap[largest])) {
			largest = l;
		}
		if ((r < size) && (heap[r] > heap[largest])) {
			largest = r;
		}
		if (largest != idx) {
			int temp = heap[largest];
			heap[largest] = heap[idx];
			heap[idx] = temp;
			if (mode == HeapifyMode.BOTTOM_UP) {
//				System.out.println(" idx: " + idx + " parent: " + (idx - 1) / 2);
				maxHeapify((idx - 1) / 2, mode);
			} else {
				maxHeapify(largest, mode);
			}

		}
	}

	public void minHeapify() {

	}

	private void minHeapify(int idx) {

	}

	public void printHeap() {
		System.out.println("\nHeap:");
		ArrayUtil.printIntArray(Arrays.copyOfRange(heap, 0, size));
	}

	public int[] getHeap() {
		return Arrays.copyOfRange(heap, 0, size);
	}

	public static void main(String[] args) {
		ArrayHeap test = ArrayHeap.buildSampleMax(10, 100);
//		ArrayHeap test = ArrayHeap.buildSampleMax("1 2 3 4 5 6 7");
		// test = ArrayHeap.buildSampleMax("7 6 5 4 3 2 1");
		test.printHeap();
		
		int ret = test.remove();
		System.out.println("ret: " + ret);
		
		ret = test.remove();
		System.out.println("ret: " + ret);
		
		test.printHeap();
		System.out.println("size: " + test.getSize());
		test.add(8);
		test.add(9);
		test.add(10);
		test.printHeap();
		System.out.println("size: " + test.getSize());
//		test.add(80);
//		test.add(90);
//		test.add(10);
		System.out.println("size before loop: " + test.getSize());
		int sz = test.getSize();
		for (int i = 0; i < sz; i++) {
			ret = test.remove();
//			System.out.println("");
//			System.out.println("size: " + test.getSize());
//			System.out.println("ret: " + ret);
		}
		test.printHeap();
	}
}
