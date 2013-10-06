package sg.problems.misc;

public class RemoveDuplicateChars {
	public static void main(String[] args) {
		String str=removeDuplicates("asdf;lj");
		System.out.println(str);
	}
	
 public static String removeDuplicates(String str) {
	 StringBuilder sb = new StringBuilder();
	 for (int i=0;i<str.length();i++) {
		 char c = str.charAt(i);
		 boolean isChar = false;
		 for (int j=0;j<sb.length();j++) {
			 if (sb.charAt(j)==c) {
				 isChar = true;
			 }
		 }
		 if (!isChar) {
			 sb.append(c);
		 }
		 isChar = false;
	 }
	 return sb.toString();
 }
 
 public static String removeDuplicates2(String str) {
	 StringBuilder sb = new StringBuilder();
	 boolean[] chars = new boolean[256];
	 for (int i=0;i<str.length();i++) {
		 char c = str.charAt(i);
		
		 if (!chars[c]) {
			 sb.append(c);
			 chars[c]=true;
		 }
		 
	 }
	 return sb.toString();
 }
}
