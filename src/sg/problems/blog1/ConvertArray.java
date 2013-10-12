package sg.problems.blog1;

import java.util.Arrays;

public class ConvertArray {
	
	private int numOfSets;
	private int lengthOfSet;
	
	private void swap(int[] arr, int prevpos, int newpos) {
		int temp = arr[prevpos];
		arr[prevpos] = arr[newpos];
		arr[newpos] = temp;
	}
	
	private int getNewPos(int index) {
		 int newpos = (index%numOfSets) * lengthOfSet + (index/numOfSets);
		 return newpos;
	}
	
	public int[] convertArraySymm(int[] arr) {
//		System.out.println("number of sets: "+numOfSets+" lengthOfSet: "+lengthOfSet);
		
		for (int i = 0; i < lengthOfSet; i++) {
			for (int j = i; j < numOfSets; j++) {
				int prevpos = i * numOfSets + j;
				int newpos = getNewPos(prevpos);
				swap(arr, prevpos, newpos);
			}
		}
		
		return arr;
	}
	
	public int[] convertArray(int[] arr, int lengthOfSet) {
		
		this.numOfSets = arr.length/lengthOfSet;
		this.lengthOfSet = lengthOfSet;
		if (this.numOfSets == this.lengthOfSet) {
			System.out.println("[symmetric input] ");
			return convertArraySymm(arr);
		}
//		System.out.println("number of sets: "+numOfSets+" lengthOfSet: "+lengthOfSet);
		
		for (int i = 0; i < lengthOfSet; i++) {
			for (int j = 0; j < numOfSets; j++) {
			
				int prevpos = i * numOfSets + j;
				int newpos = getNewPos(prevpos);
				
//				int newpos = getNewPosForElem(i, j, lengthOfSet);
//				int newpos = i + lengthOfSet * j;
				int k = 0;
				while (newpos < prevpos) {
					if (k==0) {
//						System.out.print("("+newpos+") -->");
						k++;
					}
					newpos = getNewPos(newpos);
//					System.out.print("("+newpos+") -->");
				}
//				System.out.println(" prevpos: "+prevpos + " newpos: " + newpos);
				
				swap(arr, prevpos, newpos);
//				System.out.println(Arrays.toString(arr));
				
			}
		}
		
		return arr;
	}
	
	public void test(int lengthOfSet, int numSets) {
		System.out.println("");
		System.out.println("lengthOfSet: "+ lengthOfSet + " numSets: "+numSets);
		int length = lengthOfSet * numSets;
		int[] arr = new int[length];
		for (int i = 0; i < numSets; i++) {
			for (int j = 0; j < lengthOfSet; j++) {
				//arr[i * lengthOfSet + j] = j;
				arr[i * lengthOfSet + j] = i * lengthOfSet + j;
			}
		}
		System.out.println(Arrays.toString(arr));
		convertArray(arr, lengthOfSet);
		System.out.println(Arrays.toString(arr));
		System.out.println("");
	}
	public static void main(String[] args) {
		ConvertArray test = new ConvertArray();
		
		int lengthOfSet = 4;
		int numSets = 3;
		test.test(lengthOfSet, numSets);
		
		lengthOfSet = 3;
		numSets = 3;
		test.test(lengthOfSet, numSets);
		
		lengthOfSet = 2;
		numSets = 4;
		test.test(lengthOfSet, numSets);
		
	}
}
