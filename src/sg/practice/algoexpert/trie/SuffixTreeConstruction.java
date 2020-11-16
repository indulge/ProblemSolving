package sg.practice.algoexpert.trie;

import java.util.HashMap;
import java.util.Map;

public class SuffixTreeConstruction {

    class Program {
        // Do not edit the class below except for the
        // populateSuffixTrieFrom and contains methods.
        // Feel free to add new properties and methods
        // to the class.
        class TrieNode {
            Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();

            @Override
            public String toString() {
                return "TrieNode{" +
                        "children=" + children +
                        '}';
            }
        }

        class SuffixTrie {
            TrieNode root = new TrieNode();
            char endSymbol = '*';

            public SuffixTrie(String str) {
                populateSuffixTrieFrom(str);
            }

            public void populateSuffixTrieFrom(String str) {
                if (str == null || str.length() == 0) return;
                for (int i = str.length() - 1; i >= 0 ; i--) {
                    TrieNode node = root;
                    for (int j = i; j < str.length(); j++) {
                        Character character = str.charAt(i);
                        node = node.children.computeIfAbsent(character, k -> new TrieNode());
                    }
                    node.children.put(Character.valueOf(endSymbol), new TrieNode());
                    System.out.println(root);
                }
            }

            public boolean contains(String str) {
                System.out.println("contains: "+str);
                if (str == null) return false;
                TrieNode node = root;
                for (int i = 0; i < str.length(); i++) {
                    System.out.println("node: "+node);
                    node = node.children.get(str.charAt(i));
                    if (node == null) return false;
                }
                if (node.children.get(Character.valueOf(endSymbol)) == null) return false;
                return true;
            }
        }
    }
}
