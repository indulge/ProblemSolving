package codility.problems;
// you can also use imports, for example:
// import java.math.*;


public class Demo {
	
	
	static class Solution {
		  public int equi ( int[] A ) {
			  if (A == null) return -1;
			  if (A.length == 0) return -1;
			 // if (A.length == 1) return 0;
			  
			  
			  long sum = 0;
			  for (int i = 0; i < A.length; i++) {
				  sum = sum + A[i];
			  }
			  
			// if ( (sum - A[0]) == 0) return 0;
			  
			  long sumL = 0;
			  long sumR = sum - A[0];
			  int i = 0;
			  
			  for (; i < A.length; i++) {
				  
				 // System.out.println(sumL+" : "+sumR);
				  if (sumL == sumR) {
					  return i;
				  }
				  sumL += A[i];
				  
				  if ((i + 1) ==  A.length) sumR = 0; 
				  else
				  sumR -= A[i + 1];
				  
			  }
			  
			  return -1;
		  }
		  
		  
		}
	
	public static void main(String[] args) {
		  int[] A = {-7, 1, 5, 2, -4, 3, 4};
		 // int[] A = {-7};
		  Solution s = new Solution();
		  System.out.println(s.equi(A));
	}
}