package sg.problems.misc;

import java.util.List;
import java.util.Stack;

public class GeneratePerm {

	
	public Stack<String> getPerm(String str) {
		Stack<String> perms = new Stack<String>();
		getPermRec(str, perms);
		return perms;
	}
	
	private void getPermRec(String str, Stack<String> perms) {
		if (str.length() == 1) {
			perms.push(str);
			return;
		}
		String firstChar = str.substring(0, 1);
		String restForString = str.substring(1, str.length());
		//int permSize1 = perms.size();
		getPermRec(restForString, perms);
		
//		System.out.println("perms: "+perms);
		
		Stack<String> tempStk = new Stack<String>();
		int permSize = perms.size();
		for (int i=0; i < permSize ;i++) {
//			System.out.println("i permsize: "+i+" "+perms.size());
			String temp = perms.pop();
//			System.out.println("popped: "+temp);
			tempStk.push(firstChar+temp);
			for (int j=0; j< temp.length();j++) {
				String mrg = temp.substring(0, j+1) + firstChar + temp.substring(j+1, temp.length());
				tempStk.push(mrg);
			}
//			System.out.println("perms generated: " + tempStk);
		}	
		perms.addAll(tempStk);
	}
	
	public void printPerms(String str, List<String> perms) {
		System.out.println("\nperm for: "+str);
		System.out.println(perms.size());
		System.out.println(perms);
		System.out.println("");
	}
	
	public static void main(String[] args) {
		

		
		GeneratePerm t = new GeneratePerm();
		String str = null;
		List<String> perms = null;
		str = "a";
		
		perms = t.getPerm(str);
		t.printPerms(str, perms);
		
		str = "ab";
		perms = t.getPerm(str);
		t.printPerms(str, perms);
		
		str = "abc";
		perms = t.getPerm(str);
		t.printPerms(str, perms);
		
		str = "abcd";
		perms = t.getPerm(str);
		t.printPerms(str, perms);
		
		str = "abcde";
		perms = t.getPerm(str);
		t.printPerms(str, perms);
		
	}
	
	
}
