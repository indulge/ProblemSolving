package sg.problems.misc;
import java.util.ArrayList;
import java.util.List;

public class PrintCombination {

	public static void printCombinations(char[] chars, int n, int r, List<String> combinations) {
		if (r==1) {
			List<String> temp = new ArrayList<String>();
			for (int i = 0;i<combinations.size();i++) {
				String str = combinations.get(i);
				for (char c:chars) {
					str = str + c;
					temp.add(str);
				}
			}
			combinations.addAll(temp);
			
		} else {
			
		}
	}
	
	public static void printBracketCombinations(String combination, int n, int open, int close) {
		if (close > open || open > n) return;
		
		if (open == n && close == n) {
			System.out.println(combination);
		}  else {
			
			if (open < n) {
		
			String combination_o = combination + "{";
			printBracketCombinations(combination_o, n, open + 1, close);
		} 
		if (close < n) {
			String combination_c  = combination + "}";
			printBracketCombinations(combination_c, n, open, close + 1);
		} 
	
	}
	
	}
	public static void perm(String prefix, String input,int k)
	{
		
		if(prefix.length()==k)
			System.out.println(prefix);
		else{
			for(int i =0;i<input.length();i++)
			{
				String temppre = prefix + input.charAt(i);
				String tempinp = input.substring(i+1);
				
//				System.out.println("prefix: "+temppre);
//				System.out.println("input: "+tempinp);
				
				perm(temppre,tempinp,k);
			}
		}
		
	}
	
	public static void printBracketCombinations(int n) {
		printBracketCombinations("", n, 0,0);
	}
	public static void main(String[] args) {
		printBracketCombinations(2);
		
		perm("","abc",2);
		
	}
}
