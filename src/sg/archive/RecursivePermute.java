package sg.archive;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/*
 * algorithm permute(i)  
   if i == N  output A  
   else  
      for j = i to N do  
         swap(A[i], A[j])  
         permute(i+1)  
         swap(A[i], A[j])  
         http://www.cse.ohio-state.edu/~gurari/course/cse693s04/cse693s04su45.html
 */

public class RecursivePermute {
	
	static void recursivePermute(int[] n, int start) {
		int end = n.length-1;
		
//		System.out.println("start: "+start);
//		System.out.println("end: "+end);
//		System.out.println("Start: "+Arrays.toString(n));
		
		if (start >= end) {
			System.out.println("end: "+Arrays.toString(n));
			return;
		}
		
		for (int i = start;i<=end;i++) {
			int temp = n[start];
			n[start]=n[i];
			n[i]=temp;
			
//			System.out.println("first swap: "+Arrays.toString(n));
			recursivePermute(n, start+1);
			
			temp = n[start];
			n[start]=n[i];
			n[i]=temp;
		}
	}
	
	static void iterativePermute(int[] n) {
		Stack startPoint = new Stack();
		startPoint.push(0);
		int end = n.length-1;
		while (!startPoint.isEmpty()) {
			Integer intObj = (Integer)startPoint.pop();
			int start = intObj.intValue();
			System.out.println("Start: "+start);
			for (int i = start;i<=end;i++) {
				int temp = n[start];
				n[start]=n[i];
				n[i]=temp;
				
				startPoint.push(i);
				System.out.println("end: "+Arrays.toString(n));
				
				temp = n[start];
				n[start]=n[i];
				n[i]=temp;
			}
		}
	}
	public static void main(String[] args) {
		int[] n= new int[3];
		for (int i=0;i<n.length;i++) {
			n[i]=i+1;
		}
//		recursivePermute(n, 0);
		iterativePermute(n);
	}

}
