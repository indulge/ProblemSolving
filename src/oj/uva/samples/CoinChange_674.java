package oj.uva.samples;
//package oj.uva.samples;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.StringTokenizer;
// 
//class Main {
// 
//    // 50-cent, 25-cent, 10-cent, 5-cent, and 1-cent.
//    final static int[] coins = {50, 25, 10, 5, 1};
//    static int[][] memo;
// 
//    public static void main(String[] args) throws IOException {
//        MyScanner sc = new MyScanner();
//        Integer num = 7489 + 1;
// 
//        memo = new int[num + 1][coins.length];
//        for (int i = 0; i < num + 1; i++) {
//            Arrays.fill(memo[i], 0);
//        }
// 
//        for (int i = 0; i < coins.length; i++) {
//            memo[0][i] = 1;
//        }
// 
// 
//        for (int i = 1; i < num + 1; i++) {
//            for (int j = 0; j < coins.length; j++) {
//                if (i - coins[j] >= 0) {
//                    // take coins[j]
//                    memo[i][j] += memo[i - coins[j]][j];
//                }
//                if (j - 1 >= 0) {
//                    // do not take coins[j]
//                    memo[i][j] += memo[i][j-1];
//                }
//            }
//        }
// 
//        while ((num = sc.nextInt()) != null) {
//            System.out.println(memo[num][coins.length - 1]);
//        }
//    }
//}