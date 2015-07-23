package sg.iv.flipkart.july_2015.machine_round;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//3 -Given a corrupted string i.e. its original string with just the spaces at wrong places, Construct the original string .
//You are given a dictionary of words.
//Ex:-string : Com put erengineeringoriginal string: Computer Engineering
//http://www.geeksforgeeks.org/dynamic-programming-set-32-word-break-problem/

//why trie is not good idea: it will mis case for example: inthebar -> 'i' will be seperated out and nthebar will not make sense, so 'in' should be seperated out.
public class Problem3 {
 
	static List<String> dict;
	static {
		dict 
		= Arrays.asList(new String[]{
			"mobile","samsung","sam","sung","man","mango",
            "icecream","and","go","i","like","ice","cream"	});
		
//		= Arrays.asList(new String[]{
//				"a","bc","d","e", "b","c"	});
	}
	
	public static boolean doesStringContainsDictionaryWords(String str) {
		
		if (str==null || str.length()==0) return true;
		for (int i=1;i<=str.length();i++) {
//			System.out.println("prefix: "+str.substring(0, i)+" suffix: "+str.substring(i));
			if (dict.contains(str.substring(0, i)) && doesStringContainsDictionaryWords(str.substring(i))) {
				return true;
			}
		}
		return false;
	}
	
	public static void getAllPossibleOriginalSentence(String str, String curr, List<String> all) {
//		System.out.println("Called for: "+str);
		if (str==null) return;
		
		if (str.isEmpty()) {
			all.add(curr);
//			System.out.println("empty curr: "+curr);
			curr = null;
		}
		for (int i=1;i<=str.length();i++) {
			
			String prefix = str.substring(0, i);
			String suffix = str.substring(i);
//			System.out.println("prefix: "+prefix+" suffix: "+str.substring(i));
			
			if (dict.contains(prefix)) {
//				System.out.println("contains prefix: "+prefix+" suffix: "+str.substring(i));
//				System.out.println("curr:"+curr);
				if (curr==null) curr=prefix;
				else curr=curr+" "+prefix;
				
				getAllPossibleOriginalSentence(suffix, curr, all);
//				System.out.println("Returned for prefix: "+prefix+" suffix: "+str.substring(i));
				//remove prefix for which call returned
				curr = curr.substring(0, curr.lastIndexOf(prefix)).trim();
				
			}
			
		}
	}
	
	private static class Sample {
		// Returns true if string can be segmented into space separated
		// words, otherwise returns false
		static boolean wordBreak(String str)
		{
		    int size = str.length();
		    if (size == 0)   return true;
		 
		    // Create the DP table to store results of subroblems. The value wb[i]
		    // will be true if str[0..i-1] can be segmented into dictionary words,
		    // otherwise false.
		    boolean[] wb = new boolean[size+1];
		    Arrays.fill(wb, false);
		 
		    for (int i=1; i<=size; i++)
		    {
		        // if wb[i] is false, then check if current prefix can make it true.
		        // Current prefix is "str.substr(0, i)"
//		    	System.out.println("prefix:"+str.substring(0, i));
		        if (wb[i] == false && dict.contains( str.substring(0, i) ))
		            wb[i] = true;
		 
		        // wb[i] is true, then check for all substrings starting from
		        // (i+1)th character and store their results.
		        if (wb[i] == true)
		        {
		            // If we reached the last prefix
		            if (i == size)
		                return true;
		 
		            for (int j = i+1; j <= size; j++)
		            {
		                // Update wb[j] if it is false and can be updated
		                // Note the parameter passed to dictionaryContains() is
		                // substring starting from index 'i' and length 'j-i'
//		            	System.out.println("suffix:"+str.substring(i, j));
		                if (wb[j] == false && dict.contains( str.substring(i, j)))
		                    wb[j] = true;
		 
		                // If we reached the last character
		                if (j == size && wb[j] == true)
		                    return true;
		            }
//		            System.out.println("outer:"+Arrays.toString(wb));
		        }
		    }
		    
		    System.out.println(Arrays.toString(wb));
		    /* Uncomment these lines to print DP table "wb[]"
		     for (int i = 1; i <= size; i++)
		        cout << " " << wb[i]; */
		 
		    // If we have tried all prefixes and none of them worked
		    return false;
		}
	}
	
	private static class DP {
		
		public static boolean doesStringContainsDictionaryWords(String str) {
			Boolean[] table = new Boolean[str.length()+1];
			Arrays.fill(table, null);
			table[0] = doesStringContainsDictionaryWords( str, table, 0);
//			System.out.println(Arrays.toString(table));
			return table[0];
		}
		private static boolean doesStringContainsDictionaryWords(String str, Boolean[] table, int start) {
			
			if (str==null || str.length()==0) return true;
			for (int i=1;i<=str.length();i++) {
//				System.out.println("prefix: "+str.substring(0, i)+" suffix: "+str.substring(i)+" table: "+Arrays.toString(table));
				if (table[start+i] != null && table[start+i] && dict.contains(str.substring(0, i))) return true;
				if (dict.contains(str.substring(0, i))) {
					boolean ret = doesStringContainsDictionaryWords(str.substring(i), table, i);
					table[start+i] = ret;
					if (ret) return ret;
				}
			}
			return false;
		}
	}

	
	public static void main(String[] args) {
		boolean ret;
		List<String> all = null;
//		ret = doesStringContainsDictionaryWords("abcde");
//		System.out.println(ret);
		
//		ret = Sample.wordBreak("abcde");
//		System.out.println(ret);
//		
		all = new ArrayList<String>();
//		getAllPossibleOriginalSentence("abcde", null, all);
//		System.out.println(all);
		
//		ret = DP.doesStringContainsDictionaryWords("ilikesamsung");
//		System.out.println(ret);
//		ret = DP.doesStringContainsDictionaryWords("iiiiiiii");
//		System.out.println(ret);
//		ret = DP.doesStringContainsDictionaryWords("");
//		System.out.println(ret);
//		ret = DP.doesStringContainsDictionaryWords("ilikelikeimangoiii");
//		System.out.println(ret);
//		ret = DP.doesStringContainsDictionaryWords("samsungandmango");
//		System.out.println(ret);
//		ret = DP.doesStringContainsDictionaryWords("samsungandmangok");
//		System.out.println(ret);
		
		
		all = new ArrayList<String>();
		getAllPossibleOriginalSentence("ilikesamsung", null, all);
		System.out.println(all);
		all = new ArrayList<String>();
		getAllPossibleOriginalSentence("iiiiiiii", null, all);
		System.out.println(all);
		all = new ArrayList<String>();
		getAllPossibleOriginalSentence("", null, all);
		System.out.println(all);
		all = new ArrayList<String>();
		getAllPossibleOriginalSentence("ilikelikeimangoiii", null, all);
		System.out.println(all);
		all = new ArrayList<String>();
		getAllPossibleOriginalSentence("samsungandmango", null, all);
		System.out.println(all);
		all = new ArrayList<String>();
		getAllPossibleOriginalSentence("samsungandmangok", null, all);
		System.out.println(all);
		
		
	}
}
