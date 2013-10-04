package sg.problems.misc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArrayPairSum {

	class IntPair {
		public final int a;
		public final int b;

		IntPair(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public String toString() {
			return "[" + a + ", " + b + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + a;
			result = prime * result + b;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			IntPair other = (IntPair) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (a != other.a)
				return false;
			if (b != other.b)
				return false;
			return true;
		}

		private ArrayPairSum getOuterType() {
			return ArrayPairSum.this;
		}
		
		

	}

	int arrSize = 10;
	int MAX_MAG = 10;
	int[] arr = new int[arrSize];

	public ArrayPairSum() {
		for (int i = 0; i < arrSize; i++) {
			arr[i] = (int)(Math.random() * MAX_MAG);
		}
	}
	
	public ArrayPairSum(String str) {
		String[]  numlist = str.split(", ");
		int i = 0;
		for (String ns:numlist) {
			arr[i] = Integer.parseInt(ns);
			i++;
		}
//		for (int i = 0; i < arrSize; i++) {
//			arr[i] = (int)(Math.random() * MAX_MAG);
//		}
	}
	
	public void printArray() {
		System.out.println("");
		System.out.println(Arrays.toString(arr));
//		for (int i = 0; i < arrSize; i++) {
//			System.out.print(" " + arr[i]);
//		}
		System.out.println("");
	}
	void printPairSum(int k) {
		Set<Integer> seen = new HashSet<Integer>();
		Set<IntPair> output = new HashSet<IntPair>();
		for (int num : arr) {
			int target = k - num;
			if (seen.contains(target)) {
				output.add(new IntPair(num, target));
			} else
			seen.add(num);
		}
		System.out.println(output);
	}

	public static void main(String[] args) {
		//ArrayPairSum test = new ArrayPairSum();
		//7, 4, 0, 0, 1, 9, 4, 1, 1, 7
		//[[1, 7], [4, 4], [1, 7], [1, 7`], [7, 1]]
		ArrayPairSum test = new ArrayPairSum("7, 4, 0, 0, 1, 9, 4, 1, 1, 7");
		test.printArray();
		test.printPairSum(8);
	}

}
