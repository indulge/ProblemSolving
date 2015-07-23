package sg.iv.flipkart.july_2015.machine_round;

import java.util.Arrays;

import sg.util.ArrayUtil;

//2 -How do you find if a string is a palindrome or not?
public class Problem2 {

	public static void main(String[] args) {
		String str = Arrays.toString(ArrayUtil.getRandomIntArray(5, 3)).replace(",","").replace("[", "").replace("]", "");
		System.out.println("str: " + str + " is palindrome: " + isPalindrome(str));
	}

	public static boolean isPalindrome(String str) {
		for (int i = 0, j = str.length() - 1; i < str.length() / 2; i++, j--) {
			if (str.charAt(i) != str.charAt(j))
				return false;
		}
		return true;
	}

}
