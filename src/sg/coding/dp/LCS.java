package sg.coding.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sg.util.IntArrayUtil;

public class LCS {
	static Map<Integer, List<String>> longestSequences = new HashMap<Integer, List<String>>();
	public static int getLCSLength(String sa, String sb) {
		char[] aa = sa.toCharArray();
		char[] ab = sb.toCharArray();
		
		int[][] lenArr = new int[aa.length + 1][ab.length + 1];
		
		for (int i=0;i<aa.length;i++) {
			lenArr[i][0] = 0;
		}
		
		for (int j=0;j<ab.length;j++) {
			lenArr[0][j] = 0;
		}
		
		for (int i=1;i<=aa.length;i++) {
			for (int j=1;j<=ab.length;j++) {
				if(aa[i-1] == ab[j-1]) {
					lenArr[i][j] = lenArr[i - 1][j - 1] + 1;
					List<String> seqChars = longestSequences.get(lenArr[i][j]);
					if (seqChars == null) {
						seqChars = new ArrayList<String>();
						longestSequences.put(lenArr[i][j], seqChars);
					}
					seqChars.add(String.valueOf(aa[i-1]));
				} else {
					lenArr[i][j] = Math.max(lenArr[i - 1][j], lenArr[i][j - 1]);
				}
			}
			
		}
		IntArrayUtil.print2dIntArray(lenArr);
		System.out.println(longestSequences);
		return lenArr[aa.length][ab.length];
	}
	
	
	public static void main(String[] args) {
//		String a = "abcbdab";
//		String b = "bdcaba";
		
		String a = "hello";
		String b = "olleh";
		
		int len = getLCSLength(a, b);
		System.out.println("len: "+len);
	}
}
