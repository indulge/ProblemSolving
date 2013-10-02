package sg.problems.misc;

import java.util.Arrays;

public class UniqueChars {
	public static void main(String[] args) {
		boolean ret=isUniqueChars2("asdf");
		System.out.println("ret:"+ret);
	}
	
	public static boolean isUniqueChars(String str) {
		boolean[] chars = new boolean[256];
		Arrays.fill(chars, false);
		for (int i=0;i<str.length();i++) {
			char c = str.charAt(i);
			
			if (chars[c]) {
				return false;
			}
			chars[c] = true;
			
		}
		return true;
	}
	public static boolean isUniqueChars2(String str) {
		for (int j=0;j<str.length();j++) {
			char c = str.charAt(j);
			for (int i=j+1;i<str.length();i++) {
				if (str.charAt(i)==c) {
					return false;
				}
				
				
			}
		}
		
		return true;
	}
}
