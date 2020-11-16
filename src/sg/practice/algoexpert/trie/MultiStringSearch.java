package sg.practice.algoexpert.trie;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;

import static sg.practice.algoexpert.trie.TrieNode.TERM_STR;

//keyset = letters at current level.
class TrieNode {
    Map<String, TrieNode> nodes = new HashMap<>();
    static final String TERM_STR = "*";

    @Override
    public String toString() {
        return "TrieNode{" +
                "nodes=" + nodes +
                '}';
    }
}

/**
 * Wording of this question is not very clear.
 * by "Contained in string" it means that given string can be prefix
 * of strings in big string.
 */
class Trie {
    TrieNode prefixRoot = new TrieNode();
    TrieNode suffixRoot = new TrieNode();

    public void generatePrefixTrie(List<String> strings) {
        if (strings == null || strings.size() == 0) return;
        for (String string : strings) {
            for (int i = 0; i < string.length(); i++) {
                TrieNode node = prefixRoot;
                for (int j = i; j < string.length(); j++) {
                    String letter = string.substring(j, j + 1);
                    node = node.nodes.computeIfAbsent(letter, k -> new TrieNode());
                    System.out.println("Letter: " + letter + " Node: " + prefixRoot);
                }
                node.nodes.put(TERM_STR, null);
            }
        }
    }

    public void generateSuffixTrie(List<String> strings) {
        if (strings == null || strings.size() == 0) return;
        for (String string:strings) {
            for (int i = string.length() - 1; i >= 0; i--) {
                TrieNode node = suffixRoot;
                for (int j = i; j < string.length(); j++) {
                    String letter = string.substring(j, j+1);
                    node = node.nodes.computeIfAbsent(letter, k -> new TrieNode());
                }
                node.nodes.put(TERM_STR, null);
            }
        }
    }

    public void addStringToPrefixTrie(String string) {
        if (string == null || string.length() == 0) return;
        TrieNode node = prefixRoot;
        for (int i = 0; i < string.length(); i++) {
            String letter = string.substring(i, i + 1);
            node = node.nodes.computeIfAbsent(letter, k -> new TrieNode());
        }
        node.nodes.put(TERM_STR, null);
    }

    //Returns even if string is prefix of string starting at trie.
    public boolean prefixSearch(String string, TrieNode trie) {
        for (int i = 0; i < string.length(); i++) {
            String letter = string.substring(i, i + 1);
            if (!trie.nodes.containsKey(letter)) return false;
            trie = trie.nodes.get(letter);
        }
        //return true even if we did not read the end. Meaning input string is "prefix".
        //return trie.children.containsKey(TERM_STR);
        return true;
    }

    //returns true even if string is prefix of suffix starting at trie.
    public boolean suffixSearch(String string, TrieNode trie) {
        for (int i = 0; i < string.length(); i++) {
            String letter = string.substring(i, i + 1);
            if (!trie.nodes.containsKey(letter)) return false;
            trie = trie.nodes.get(letter);
        }
        return true;
    }

    public void printTrieJson(TrieNode trie) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(trie);
        System.out.println(json);
    }

//    public void printTrie(TrieNode trie) {
//        Stack<TrieNode> stack = new Stack<>();
////        Stack<String> stringStack = new Stack<>();
//        Set<String> keys = trie.nodes.keySet();
//        for (String key:keys) {
//            stack.push(trie.nodes.get(key));
////            stringStack.push(key);
//        }
//
//        while (!stack.isEmpty()) {
//            TrieNode node = stack.pop();
////            String prefix = stringStack.pop();
//
//            Set<String> nextKeys = node.nodes.keySet();
//            for (String nextKey:nextKeys) {
////                stringStack.push(nextKey);
//                TrieNode nextNode = node.nodes.get(nextKey);
//                if
//                stack.push(nextNode);
//            }
//
//        }
//    }
}

public class MultiStringSearch {


    static class Program {
        Trie trie = new Trie();

        public List<Boolean> multiStringSearch(String bigString, String[] smallStrings) {
            System.out.println("Big String: "+bigString+" \nSmall Strings: "+Arrays.asList(smallStrings));
            List<String> strings = Arrays.asList(bigString.split(" "));
            trie.generateSuffixTrie(strings);
            trie.generatePrefixTrie(strings);
            List<Boolean> ret = new ArrayList<>();
            for (String string : smallStrings) {
                ret.add(trie.suffixSearch(string, trie.suffixRoot));
            }
            System.out.println(ret);
            ret = new ArrayList<>();
            for (String string : smallStrings) {
                ret.add(trie.suffixSearch(string, trie.prefixRoot));
            }
            System.out.println(ret);
            return ret;
        }
    }

    public static void main(String[] args) {
        Program program = new Program();
        String big = "this is a big string";
//        String big = "abc";
        String[] small = {"this", "yo", "is", "a", "bigger", "string", "kappa"};
        program.multiStringSearch(big, small);
        System.out.println("\n\n\n\n~~suffixRoot");
        program.trie.printTrieJson(program.trie.suffixRoot);
        System.out.println("\n\n\n\n~~prefixRoot");
        program.trie.printTrieJson(program.trie.prefixRoot);
    }
}
