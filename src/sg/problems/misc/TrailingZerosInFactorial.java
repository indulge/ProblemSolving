package sg.problems.misc;

public class TrailingZerosInFactorial {

	public static void main(String[] args) {
		System.out.println(numZeros(100));
	}
	public static int numZeros(int num) {
		int count = 0;
		if (num < 0) {
			System.out.println("Factorial is not defined for < 0");
			return 0;
		}
		for (int i = 5; num / i > 0; i *= 5) {
			count += num / i;
		}
		return count;
	}
}
