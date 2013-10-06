package sg.archive.linkedlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class FindAnagramList {

	public static void main(String[] args) {
		FindAnagramList test = new FindAnagramList();
		String[] words = {"abcd","dcab","aasdf","bdasdf","aasdfb","cbada","bcaad"};
		test.printAnagrams(words);

	}
	
	public void printAnagrams(String[] words) {
		if (words==null) return;
		Map< List<Character>, List<String> > anagrams = 
			new HashMap< List<Character>, List<String> >();
		for (String word: words) {
			List<Character> aset = new ArrayList<Character>();
			//make sure that set is sorted
			for (int i=0;i<word.length();i++) {
				aset.add(word.charAt(i));
			}
			Collections.sort(aset);
			List<String> anagramList = anagrams.get(aset);
			if (anagramList == null) {
				anagramList = new ArrayList<String>();
				anagramList.add(word);
				anagrams.put(aset, anagramList);
			} else {
				anagramList.add(word);
			}

		}
		Set<List<Character>> keySet = anagrams.keySet();
		for (List<Character> keyObj:keySet) {
			List<String> anagramList = anagrams.get(keyObj);
			System.out.print(keyObj);
			System.out.print(" : ");
			System.out.println(anagramList);
		}
	}
}
