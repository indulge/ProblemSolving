package sg.archive.linkedlist;

import java.util.Arrays;

public class GeneratePermutations {
	
	static boolean permGen(int[] n) {
		if (n==null) {
			return false;
		}  else if (n.length <= 1) {
			return false;
		}
		
		int i=n.length-2;
		for (;i>=0;i--) {
			if (n[i]<n[i+1]) {
				break;
			}
		}
		System.out.println("i: "+i);
		int j = n.length-1;
		int small = j;
		for (;j>(i+1);j--) {
			if (n[small] > n[j]) {
				small = j;
			}
		}
		System.out.println("j: "+j);
//		if ((small==(n.length-1)) && (i==0)) {
//			return true;
//		} else {
//			
//		}
		
		int temp = n[i];
		n[i] = n[small];
		n[small] = temp;
//		System.out.println(Arrays.toString(n));
		//reverse n-i elements
		int numElems = (n.length-(i+1))/2;
//		System.out.println("numElems: "+numElems);
		for (int k=1; k <=numElems; k++) {
			temp = n[i+k];
			n[i+k] = n[n.length-k];
			n[n.length-k] = temp;
		}
		
		System.out.println("\n"+Arrays.toString(n)+"\n");
		return true;
	}
	
	public static void main(String[] args) {
		int[] n= new int[3];
		for (int i=0;i<n.length;i++) {
			n[i]=i+1;
		}
		while (permGen(n)) {
			
		}
	}

}
