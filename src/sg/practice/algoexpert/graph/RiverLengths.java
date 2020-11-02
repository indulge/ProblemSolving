package sg.practice.algoexpert.graph;

import java.util.ArrayList;
import java.util.List;

class RiverLengths {
    class SolutionSubmitted {
//            import java.util.*;
//
//            class Program {
//
//
//                static class Pair {
//                    final int i;
//                    final int j;
//
//                    public Pair(int i, int j) {
//                        this.i = i;
//                        this.j = j;
//                    }
//                    @Override
//                    public String toString() {
//                        return "["+i + "," + j+"]";
//                    }
//                }
//
//                public static List<Integer> riverSizes(int[][] matrix) {
//                    System.out.println(Arrays.deepToString(matrix).replace("],", "]\n"));
//                    Boolean[][] tracker = new Boolean[matrix.length][matrix[0].length];
//                    List<Integer> riverSizes = new ArrayList<>();
//                    //rows
//                    for (int i = 0; i < matrix.length; i++) {
//                        for (int j = 0; j < matrix[0].length; j++) {
//                            if (tracker[i][j] == null) {
//                                if (matrix[i][j] == 1) {
//                                    int currentRiverSize = getRiverSize(i, j, matrix, tracker);
//                                    System.out.println("i: " + i + " J: " + j + " river: " + currentRiverSize);
//                                    riverSizes.add(currentRiverSize);
//                                }
//                            }
//                        }
//                    }
//
//                    // Write your code here.
//                    System.out.println("riverSizes: " + riverSizes);
//                    return riverSizes;
//                }
//
//                private static int getRiverSize(int i, int j, int[][] matrix, Boolean[][] tracker) {
//                    System.out.println("getRiverSize: i: " + i + " J: " + j);
//                    if (tracker[i][j] == null) {
//                        tracker[i][j] = true;
//                        Pair nextTile = getNextTile(i, j, matrix.length, matrix[0].length, matrix, tracker);
//                        System.out.println("nextTile: "+nextTile);
//                        int len = 1;
//                        while (nextTile != null) {
//                            len += getRiverSize(nextTile.i, nextTile.j, matrix, tracker);
//                            nextTile = getNextTile(i, j, matrix.length, matrix[0].length, matrix, tracker);
//                        }
//                        return len;
//                    }
//                    return 1;
//                }
//
//                private static Pair getNextTile(int i, int j, int maxI, int maxJ, int[][] matrix, Boolean[][] tracker) {
//                    if (i - 1 >= 0 && matrix[i-1][j] == 1 && tracker[i-1][j] == null) return new Pair(i - 1, j);
//                    if (j + 1 < maxJ && matrix[i][j+1] == 1 && tracker[i][j+1] == null) return new Pair(i, j + 1);
//                    if (i + 1 < maxI && matrix[i+1][j] == 1 && tracker[i+1][j] == null) return new Pair(i + 1, j);
//                    if (j - 1 >= 0 && matrix[i][j-1] == 1 && tracker[i][j-1] == null) return new Pair(i, j - 1);
//                    return null;
//                }
//
//
//            }

    }

    class Solution1 {
        class Program {

            class Pair {
                final int i;
                final int j;

                public Pair(int i, int j) {
                    this.i = i;
                    this.j = j;
                }

                @Override
                public String toString() {
                    return "[" + i + "," + j + "]";
                }
            }

            public List<Integer> riverSizes(int[][] matrix) {
                Boolean[][] tracker = new Boolean[matrix.length][matrix[0].length];
                List<Integer> riverSizes = new ArrayList<>();
                //rows
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        if (tracker[i][j] == null) {
                            tracker[i][j] = true;
                            if (matrix[i][j] == 1) {
                                int currentRiverSize = getRiverSize(i, j, matrix, tracker);
                                System.out.println("i: " + i + " J: " + j + " river: " + currentRiverSize);
                                riverSizes.add(currentRiverSize);
                            }
                        }
                    }
                }

                // Write your code here.
                return riverSizes;
            }

            private int getRiverSize(int i, int j, int[][] matrix, Boolean[][] tracker) {
                if (tracker[i][j] == null) {
                    tracker[i][j] = true;
                    Pair nextTile = getNextTile(i, j, matrix.length, matrix[0].length, matrix, tracker);
                    if (nextTile != null && matrix[nextTile.i][nextTile.j] == 1) {
                        return 1 + getRiverSize(nextTile.i, nextTile.j, matrix, tracker);
                    }
                    if (matrix[i][j] == 1) return 1;
                }
                return 0;
            }

            private Pair getNextTile(int i, int j, int maxI, int maxJ, int[][] matrix, Boolean[][] tracker) {
                if (i - 1 >= 0 && matrix[i - 1][j] == 1 && tracker[i - 1][j] == null) return new Pair(i - 1, j);
                if (j + 1 < maxJ && matrix[i][j + 1] == 1 && tracker[i][j + 1] == null) return new Pair(i, j + 1);
                if (i + 1 < maxI && matrix[i + 1][j] == 1 && tracker[i + 1][j] == null) return new Pair(i + 1, j);
                if (j - 1 >= 0 && matrix[i][j - 1] == 1 && tracker[i][j - 1] == null) return new Pair(i, j - 1);
                return null;
            }

        }
    }
}
