package sg.archive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class FindAnagramsSet {

	public static void main(String[] args) {
		FindAnagramsSet test = new FindAnagramsSet();
		String[] words = {"abcd","dcab","aasdf","bdasdf","aasdfb","cb","bc"};
		test.printAnagrams(words);

	}
	
	public void printAnagrams(String[] words) {
		if (words==null) return;
		Map< Set<Character>, List<String> > anagrams = 
			new HashMap< Set<Character>, List<String> >();
		for (String word: words) {
			Set<Character> aset = new TreeSet<Character>();
			//make sure that set is sorted
			for (int i=0;i<word.length();i++) {
				aset.add(word.charAt(i));
			}
			
			List<String> anagramList = anagrams.get(aset);
			if (anagramList == null) {
				anagramList = new ArrayList<String>();
				anagramList.add(word);
				anagrams.put(aset, anagramList);
			} else {
				anagramList.add(word);
			}

		}
		Set<Set<Character>> keySet = anagrams.keySet();
		for (Set<Character> keyObj:keySet) {
			List<String> anagramList = anagrams.get(keyObj);
			System.out.print(keyObj);
			System.out.print(" : ");
			System.out.println(anagramList);
		}
	}
}
