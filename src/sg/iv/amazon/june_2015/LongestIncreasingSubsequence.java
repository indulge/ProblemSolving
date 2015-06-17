package sg.iv.amazon.june_2015;

import java.util.Arrays;

//
public class LongestIncreasingSubsequence {
	private static int max_so_far = 0;
	public static int getLongestIncreasingSubsequence(int[] seq, int i) {
		if (i==0) return 1;
		if (seq.length == 1) return 1;
		if (i>=seq.length) return max_so_far;
		
		int maxi = 0; //max length of increasing subsequence upto length i
		for (int j=0;j<i;j++) {
//			System.out.println(seq[j] + " < " + seq[i]);
			int maxj = getLongestIncreasingSubsequence(seq, j);
			if (seq[j] < seq[i]) {
				maxi = Math.max(maxj + 1, maxi);
				
			}
			System.out.println("i: " + i + " maxj: " + maxj + " maxi: " + maxi);
		}
		if (maxi > max_so_far) max_so_far = maxi;
		return max_so_far;

	}
	
	public static int getLongestIncreasingSubsequenceDP(int[] seq) {
		int[] lis = new int[seq.length];
		Arrays.fill(lis, 1);
		lis[0]=1;
		for (int i=1; i<seq.length;i++) 
			for (int j=0;j<i;j++) {
				if (seq[i]>seq[j]) {
					lis[i] = Math.max(lis[i], lis[j]+1);
				}
			}
		System.out.println("LIS: "+Arrays.toString(lis));
		Arrays.sort(lis);
		return lis[seq.length-1];
	}
	public static void main(String[] args) {
//		int[] seq = {10, 22, 9, 33, 21, 50, 41, 60, 80};
		int[] seq = {10, 20, 10,20,30,40,50};
		System.out.println("Sequence: "+Arrays.toString(seq));
		
		int maxLen = getLongestIncreasingSubsequence(seq, seq.length-1);
		System.out.println("Max Rec: " + maxLen);
		
		maxLen = getLongestIncreasingSubsequenceDP(seq);
		System.out.println("Max DP: " + maxLen);
	}
}
