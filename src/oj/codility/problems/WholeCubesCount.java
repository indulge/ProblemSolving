package oj.codility.problems;
import java.math.BigDecimal;
import java.math.BigInteger;




public class WholeCubesCount {
	
	
	static class Solution {
		public int getCubeNumbers (int A, int B) {
			int count = 0;
			for(int i=A;i<=B;i++) {
				double dcr = Math.pow(i, (double)1/3);
				BigDecimal cr = new BigDecimal(dcr);
				BigInteger icr = new BigInteger(String.valueOf(cr.intValue()));
				//System.out.println(i+", "+cr+" , "+(double)icr+" , "+(double)Math.pow(i, 1/3));
				System.out.println(cr + ", " + icr);
				if (cr.equals(icr)) count++;
			}
			return count;
		}
	}
	
	
	public static void main(String[] args) {
		 	
//		int A = Math.random() * -20000;
//		int B = Math.random() 
		  Solution s = new Solution();
		  System.out.println(s.getCubeNumbers(8, 65));
	}
}
