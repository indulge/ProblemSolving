package sg.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScratchPad {

    static Map<Integer, List<Character>> levelToValidCharacters = new HashMap<>();

    //Prefix tree
    static class ValidChars {
        Map<String, List<Character>> prefixTree = new HashMap<>();
        List<String> rootChars = new ArrayList<>();
        public static ValidChars constructLevelCharacterMap(String[] words) {
            ValidChars validChars = new ValidChars();
            for (String word : words) {
                System.out.println("word: " + word);
                for (int i = 1; i < word.length(); i++) {
                    String prefix = word.substring(0, i);
                    if (i == 1) validChars.rootChars.add(prefix);
                    List<Character> chars =
                            validChars.prefixTree.computeIfAbsent(prefix, k -> new ArrayList<>());
                    Character character = Character.valueOf(word.charAt(i));
                    chars.add(character);
                }
                System.out.println("Prefix Tree: " + validChars.prefixTree);
                System.out.println("rootChars: " + validChars.rootChars);
            }
            return validChars;
        }
    }

    static String[] words = {
            "this",
            "is",
            "not",
            "a",
            "simple",
            "boggle",
            "board",
            "test",
            "REPEATED",
            "NOTRE-PEATED"
    };

    public static void main(String[] args) {
        ValidChars validChars = ValidChars.constructLevelCharacterMap(words);
        System.out.println("validChars: " + validChars);
    }
}
