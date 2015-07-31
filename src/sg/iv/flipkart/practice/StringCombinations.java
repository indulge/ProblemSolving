package sg.iv.flipkart.practice;

public class StringCombinations {

	private static int count = 1;

	public static void printCombination(String str) {
		count = 1;
		printCombination("", str);
	}

	private static void printCombination(String prefix, String suffix) {
		if (suffix == null || suffix.isEmpty()) {
			if (prefix != null && !prefix.isEmpty()) {
				System.out.println(count + " :" + prefix);
			}
			count++;
			return;
		}
		printCombination(prefix + suffix.charAt(0), suffix.substring(1));
		printCombination(prefix, suffix.substring(1));
	}

	public static void main(String[] args) {
		String str = "abcd";
		System.out.println("2^(n-1):"+(long)(Math.pow(2, str.length())-1));
		printCombination(str);
		System.out.println("\n\nn!: "+fact(str.length()));
		printPermutations(str);
	}

	public static void printPermutations(String str) {
		count = 1;
		printPermutations("", str);
	}

	private static void printPermutations(String prefix, String suffix) {
		if (suffix == null || suffix.isEmpty()) {
			if (prefix != null && !prefix.isEmpty()) {
				System.out.println(count + " :" + prefix);
			}
			count++;
			return;
		}
		for (int i = 0; i < suffix.length(); i++) {
			printPermutations(prefix+suffix.charAt(i), suffix.substring(0,i) + suffix.substring(i+1,suffix.length()));
		}
	}
	
	private static long fact(int n) {
		if (n<0) return -1;
		if (n==1) return 1;
		return (n*fact(n-1));
	}
}
