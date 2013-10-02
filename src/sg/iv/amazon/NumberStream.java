package sg.iv.amazon;

import java.util.Arrays;

public class NumberStream {
	public static void main(String[] args) {
		//Heap h1 = new Heap(, 10);
		Heap h1 = Heap.createRandomHeap(Heap.HeapType.MAX, 10);
		h1.printHeap();
		h1.truncate();
		System.out.println("\ntruncated");
		h1.printHeap();
		int size = 10;
		for (int i=0; i<size; i++) {
			//int  num =  (int) (Math.random() * size);
			//h1.insertValue(num);
			h1.insertValue(i);
			
			System.out.print("\n\n iter: " + i);
			h1.printHeap();
		}
	}
}

//interface MinHeap {
//	public int getMaxValue();
//	public int getMinValue();
//}

// integer heap
class Heap {
	public static enum HeapType {
		MIN, MAX
	};

	HeapType heapType;
	private int[] heapArray;
	int lastIndex = 0;

	public Heap(HeapType type, int size) {
		heapArray = new int[size];
		this.heapType = type;
	}

	public void truncate() {
		heapArray = new int[heapArray.length];
		lastIndex = 0;
	}
	
	public void insertValue(int val) {
		if (lastIndex < heapArray.length) {
			heapArray[lastIndex] = heapArray[0];
			heapArray[0] = val;
			lastIndex++;
			System.out.println("\narr after insert:");
			System.out.println(Arrays.toString(Arrays.copyOfRange(heapArray, 0, lastIndex)));
			
			if (this.heapType == Heap.HeapType.MAX) {
				maxHeapify(0);
			} else {
				minHeapify(0);
			}
			System.out.println("\narr after heapify:");
			System.out.println(Arrays.toString(Arrays.copyOfRange(heapArray, 0, lastIndex)));
	
		} else {
			System.out.println("Heap full");
		}
	}

	//make this protected
	public int getValue() {
		return 0;
	}
	
	public int removeValue() {
		return 0;
	}
	
	public static Heap createRandomHeap(HeapType type, int size) {
		Heap heap = new Heap(type, size);
		int[] arr = new int[size];
		for (int i=0; i<size; i++) {
			arr[i] =  (int) (Math.random() * size);
		}
		Arrays.sort(arr);
		if (type == Heap.HeapType.MAX) {
			for (int i=0; i<size/2; i++) {
				int temp = arr[i];
				arr[i] = arr[size - (i + 1)];
				 arr[size - (i + 1)] = temp;
			}
		} 
		
		heap.heapArray = arr;
		heap.lastIndex = size;
		return heap;
	}

	public void printHeap() {
		int levelMax = 0;
		System.out.println("");
		for (int i = 0; i < lastIndex; i++) {
			System.out.print(heapArray[i] + " ");
			if (i == levelMax) {
				levelMax = (i + 1) * 2;
				System.out.println("");
			}
		}
	}

	private void minHeapify(int level) {
		int li = level * 2 + 1;
		int ri = level * 2 + 2;
		
		if (li < lastIndex) {
			if (heapArray[level] > heapArray[li]) {
				int temp = heapArray[level];
				heapArray[level] = heapArray[li];
				heapArray[li] = temp;
			} 
			if ((ri) < lastIndex) {
				if (heapArray[level] > heapArray[ri]) {
					int temp = heapArray[level];
					heapArray[level] = heapArray[ri];
					heapArray[li] = temp;
				}
				minHeapify(li);
				minHeapify(ri);
				return;
			}
			minHeapify(li);
		}	
	}
	
	private void maxHeapify(int level) {
		System.out.println("processing: "+level);
		int li = level * 2 + 1;
		int ri = level * 2 + 2;
		
		if (li < lastIndex) {
			if (heapArray[level] < heapArray[li]) {
				int temp = heapArray[level];
				heapArray[level] = heapArray[li];
				heapArray[li] = temp;
				
			} 
			if ((ri) < lastIndex) {
				if (heapArray[level] < heapArray[ri]) {
					int temp = heapArray[level];
					heapArray[level] = heapArray[ri];
					heapArray[li] = temp;
					
				}
				
				
				return;
			}
			maxHeapify(li);
		}	
		
	}
}