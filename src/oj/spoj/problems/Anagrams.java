package oj.spoj.problems;

public class Anagrams
{
  public static void main (String[] args) throws java.lang.Exception
  {
     java.io.BufferedReader r = new java.io.BufferedReader (new java.io.InputStreamReader (System.in));
     String s;
     int readCount = 0;
     s=r.readLine();
     readCount = Integer.parseInt(s);
     for (int i=0;i<readCount;i++) {
    	 s=r.readLine();
    	 String[] strs = s.split(" ");
    	 boolean isAnagram = checkAnagrams(strs[0], strs[1]);
    	 if (isAnagram) {
    		 System.out.println("YES"); 
    	 } else {
    		 System.out.println("NO"); 
    	 }
     }
  }
  
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
}
