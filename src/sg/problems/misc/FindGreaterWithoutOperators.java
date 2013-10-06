package sg.problems.misc;

/*
 Let’s try to solve this by “re-wording” the problem. We will re-word the problem until we get something that has removed all if statements.
 Rewording 1: If a > b, return a; else, return b.
 Rewording 2: If (a - b) is negative, return b; else, return a.
 Rewording 3: If (a - b) is negative, let k = 1; else, let k = 0. Return a - k * (a - b).
 Rewording 4: Let c = a - b. Let k = the most significant bit of c. Return a - k * c.
 We have now reworded the problem into something that fits the requirements. The code for this is below.
 1 int getMax(int a, int b) {
 2 int c = a - b;
 3 int k = (c >> 31) & 0x1;
 4 int max = a - k * c;
 5 return max;
 6 }
 */

public class FindGreaterWithoutOperators {
	public static void main(String[] args) {
		System.out.println(getGreater(-10, 20));
		System.out.println(getGreater(-20, 10));
	}
	
	public static int getGreater(int a, int b) {
		int c = a - b;
		int sign = ( c >> 31 ) & 1;
		int greater = a - sign * (a - b);
		return greater;
//		System.out.println("(a-b)>>31)" + a+" "+b+" "+(((a-b)>>31)));
//		return a + (((a-b)>>31) * (a - b));
	}
}
