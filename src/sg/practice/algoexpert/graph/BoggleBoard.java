package sg.practice.algoexpert.graph;

import java.util.*;

public class BoggleBoard {
    class Template {
        class Program {
            public List<String> boggleBoard(char[][] board, String[] words) {
                // Write your code here.
                return new ArrayList<String>();
            }
        }
    }

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

    static class Pair {
        final int i;
        final int j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "{" +
                    i +
                    ", "
                    + j +
                    '}';
        }
    }

    static class Program {
        static List<String> validWords = new ArrayList<>();
        static Map<String, Integer> foundWords = new HashMap<>();
        static ValidChars validChars = null;


        public static List<String> boggleBoard(char[][] board, String[] words) {
            validChars = ValidChars.constructLevelCharacterMap(words);
            validWords = Arrays.asList(words);
            int rows = board.length;
            int cols = board[0].length;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    boolean[][] visitMap = new boolean[rows][cols];
                    for (int k = 0; k < rows; k++) {
                        Arrays.fill(visitMap[k], false);
                    }
                    if (validChars.rootChars.contains(board[i][j] + "")) {
                        System.out.println("Processing:" + board[i][j]);
                        findWordsAt(i, j, board, visitMap, Character.toString(board[i][j]), validChars);
                    }
                }
            }
            List<String> result = new ArrayList<String>();
            result.addAll(foundWords.keySet());
            System.out.println("Found Words:" + foundWords);
            return result;
        }

        private static void findWordsAt(int i, int j, char[][] board, boolean[][] visitMap, String currentWord, ValidChars validChars) {
            if (validWords.contains(currentWord)) {
                int count = foundWords.computeIfAbsent(currentWord, k -> 1);
                foundWords.put(currentWord, count++);
                System.out.println("Found:" + currentWord);
            }

            visitMap[i][j] = true;
            List<Pair> nextElements = getNextElements(i, j, board, visitMap, currentWord, validChars);
            for (Pair nextElement : nextElements) {
                findWordsAt(nextElement.i, nextElement.j, board, visitMap,
                        currentWord + board[nextElement.i][nextElement.j], validChars);
            }
            visitMap[i][j] = false; // This is required because, if there are branches up the path, then they should be able use if again.
            //eg. if branches are like this a->[b,x]->[b->c,x->d]
            //for b, C: a->b->C->e->f, while for loop processes e and f, visited(row,col) for C will remain true,
            //but when it goes back to x, then it should be able to use C again.
        }

        private static List<Pair> getNextElements(int i, int j, char[][] board, boolean[][] visitMap, String currentWord, ValidChars validChars) {
            int[] rowAdditions = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
            int[] colAdditions = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

            int maxRow = board.length;
            int maxCol = board[0].length;
            List<Pair> nextElements = new ArrayList<>();
            List<Character> nextValidChars = validChars.prefixTree.get(currentWord);

            //calculate next elements for 6 possible neighbours.
            for (int index = 0; index < rowAdditions.length; index++) {
                int nextI = i + rowAdditions[index];
                int nextJ = j + colAdditions[index];
                if (nextI >= 0 && nextI < maxRow && nextJ >= 0 && nextJ < maxCol) {
                    if (!visitMap[nextI][nextJ]) {
                        char currentChar = board[nextI][nextJ];
                        if (nextValidChars != null && nextValidChars.contains(currentChar)) {
                            nextElements.add(new Pair(nextI, nextJ));
                        }
                    }
                }
            }
            return nextElements;
        }
    }

}

// Submitted code ----------------- as below.

//Prefix tree
class ValidChars {
    Map<String, List<Character>> prefixTree = new HashMap<>();
    List<String> rootChars = new ArrayList<>();

    public static ValidChars constructLevelCharacterMap(String[] words) {
        ValidChars validChars = new ValidChars();
        for (String word : words) {
            System.out.println("word: " + word);
            if (word.length() == 1) {
                validChars.rootChars.add(word);
                continue;
            }
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

class Pair {
    final int i;
    final int j;

    public Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public String toString() {
        return "{" +
                i +
                ", "
                + j +
                '}';
    }
}

class Program {
    static List<String> validWords =null;
    static Map<String, Integer> foundWords = null;
    static ValidChars validChars = null;



    public static List<String> boggleBoard(char[][] board, String[] words) {
        foundWords = new HashMap<>();
        validChars = ValidChars.constructLevelCharacterMap(words);
        validWords = Arrays.asList(words);
        int rows = board.length;
        int cols = board[0].length;
        System.out.println("validWords:"+validWords);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean[][] visitMap = new boolean[rows][cols];
                for (int k = 0; k < rows; k++) {
                    Arrays.fill(visitMap[k], false);
                }
                if (validChars.rootChars.contains(board[i][j] + "")) {
                    System.out.println("Processing:"+board[i][j]);
                    findWordsAt(i, j, board, visitMap, Character.toString(board[i][j]), validChars);
                }
            }
        }
        List<String> result = new ArrayList<String>();
        result.addAll(foundWords.keySet());
        return result;
    }

    private static void findWordsAt(int i, int j, char[][] board, boolean[][] visitMap, String currentWord, ValidChars validChars) {
        if (validWords.contains(currentWord)) {
            int count = foundWords.computeIfAbsent(currentWord, k -> 1);
            foundWords.put(currentWord, count++);
            System.out.println("Found:" + currentWord);
            // return;
        }

        visitMap[i][j] = true;
        List<Pair> nextElements = getNextElements(i, j, board, visitMap, currentWord, validChars);
        for (Pair nextElement : nextElements) {
            findWordsAt(nextElement.i, nextElement.j, board, visitMap,
                    currentWord + board[nextElement.i][nextElement.j], validChars);
        }
        visitMap[i][j] = false;
    }

    private static List<Pair> getNextElements(int i, int j, char[][] board, boolean[][] visitMap, String currentWord, ValidChars validChars) {
        int[] rowAdditions = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
        int[] colAdditions = {-1, 0, +1, -1, 0, +1, -1, 0, 1};

        int maxRow = board.length;
        int maxCol = board[0].length;
        List<Pair> nextElements = new ArrayList<>();
        List<Character> nextValidChars = validChars.prefixTree.get(currentWord);
        //calculate next elements for 6 possible neighbours.
        for (int index = 0; index < rowAdditions.length; index++) {
            int nextI = i + rowAdditions[index];
            int nextJ = j + colAdditions[index];
            if (nextI >= 0 && nextI < maxRow && nextJ >= 0 && nextJ < maxCol) {
                if (!visitMap[nextI][nextJ]) {
                    char currentChar = board[nextI][nextJ];

                    if (nextValidChars != null && nextValidChars.contains(currentChar)) {
                        nextElements.add(new Pair(nextI, nextJ));
                    }
                }
            }
        }
        return nextElements;
    }
}