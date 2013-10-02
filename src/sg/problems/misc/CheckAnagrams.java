package sg.problems.misc;

public class CheckAnagrams {
	
	public static boolean checkAnagrams(String s1, String s2) {
		if (s1 == null || s2 == null || (s1.length() != s2.length()) || s1.length() == 0) {
			return false;
		}
		int[] charCount = new int[256];
		for (int i=0;i<256;i++) {
			charCount[i] = 0;
		}
		char[] str1chars = s1.toCharArray();
		char[] str2chars = s2.toCharArray();
		
		for (int i =0 ;i<str1chars.length;i++) {
			charCount[str1chars[i]] += 1;
			charCount[str2chars[i]] -= 1;
		}
		for (int i=0;i<256;i++) {
			if (charCount[i]!=0) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		String s1 = "aaab";
		String s2 = "aaba";
		String s3 = "aba";
		String s4 = "aaaaabbbbbbb";
		String s5 = "ab";
		
		System.out.println(checkAnagrams(s1, s2));
		System.out.println(checkAnagrams(s1, s3));
		System.out.println(checkAnagrams(s1, s4));
		System.out.println(checkAnagrams(s1, s5));
		System.out.println(checkAnagrams(s2, s3));
	}
}
