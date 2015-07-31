package sg.problems.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sg.util.PrettyPrinter;



public class LCS1 {
	
	private static List<Character> getLongestSubSequence(String a, String b) {
		
		
		int[][] lengthArr = new int[a.length() + 1][b.length() + 1];
		
		for (int i = 0; i < a.length(); i++) {
			lengthArr[i][0] = 0;
			
		}
		
		for (int j = 0; j < b.length(); j++) {
			lengthArr[0][j] = 0;
		}
		
		char[] ac = a.toCharArray();
		char[] bc = b.toCharArray();
		
		List<Character> substr = new ArrayList<Character>();
		
		for (int i = 1; i <= a.length(); i++) {
			for (int j = 1; j <= b.length(); j++) {
				if (ac[i - 1] == bc[j - 1]) {
					lengthArr[i][j] = 1 + lengthArr[i - 1][j - 1];
				} else {
					//getLongestSubSequenceLength(ac, bc, i - 1, j, lengthArr);
					//getLongestSubSequenceLength(ac, bc, i, j -1, lengthArr);
					lengthArr[i][j] = Math.max(lengthArr[i - 1][j], lengthArr[i][j - 1]);
				}
				if ((i == a.length()) && (lengthArr[i][j] > lengthArr[i][j -1])) {
					substr.add(bc[j - 1]);
				}
			}
			System.out.print(Arrays.toString(lengthArr[i]) + " ");
			System.out.print(substr);
			System.out.println("");
		}
		PrettyPrinter.print2dArray(lengthArr);
		return substr;
		
	}
	
	public static int getLongestSubSequenceLength(String a, String b) {
		List<Character> ret = getLongestSubSequence(a,b);
		return ret.size();
	}
	public static String getLongestSubSequenceString(String a, String b) {
		List<Character> ret = getLongestSubSequence(a,b);
		return ret.toString();
	}
	
	public static void main(String[] args) {
		
		String s1 = "XMJYAUZ";
		String s2 = "MZJAWXU";
		
		int len = getLongestSubSequenceLength(s1, s2);
		System.out.println("LCS len: "+len);
		
		String ss = getLongestSubSequenceString(s1, s2);
		System.out.println("LCS String: "+ss);
	}
}
