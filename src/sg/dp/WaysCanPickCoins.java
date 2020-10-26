package sg.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WaysCanPickCoins {
    public static void main(String[] args) {
        System.out.println("Solution: " + Program.numberOfWaysToMakeChange1(3, new int[] {1, 2}));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Solution Perfect: " + Program.numberOfWaysToMakeChange2(3, new int[] {1, 2}));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Solution Perfect: " + Program.numberOfWaysToMakeChange3(3, new int[] {1, 2}));
    }

    static class Program {
        private static final Map<Integer, Integer> waysForSum = new HashMap<>();
        static int execCount = 0;
        public static int numberOfWaysToMakeChange1(int n, int[] denoms) {
            // Write your code here.
            int ret = countWays(n, denoms, 0);
            return ret;

        }

        public static int numberOfWaysToMakeChange2(int n, int[] denoms) {
            int[] numWays = new int[n+1];
            numWays[0] = 1;
            for (int i = 1; i <= n; i++) {
                for (int coin:denoms) {
                    if (coin <= i)
                    numWays[i] += numWays[i-coin] ;
                    System.out.println("Sum: " + i+" , coin: "+coin);
                    System.out.println("numWays: "+ Arrays.toString(numWays));

                }
            }
            return numWays[n];
        }

        public static int numberOfWaysToMakeChange3(int n, int[] denoms) {
            int[] numWays = new int[n+1];
            numWays[0] = 1;
            for (int coin:denoms) {
                for (int i = 1; i <= n; i++) {
                    if (coin <= i)
                        numWays[i] += numWays[i-coin];
                    System.out.println("Sum: " + i+" , coin: "+coin);
                    System.out.println("numWays: "+ Arrays.toString(numWays));

                }
            }
            return numWays[n];
        }

        public static int countWays(int sum, int[] coins, int coinIndex) {
            System.out.println("Exec Count ~~~: " + ++execCount + "~~~");
            System.out.println("sum: " + sum);
            if (coinIndex < coins.length) System.out.println("coin: " + coins[coinIndex]);

//            if (waysForSum.get(sum) != null && waysForSum.get(sum) > 0) return 1;
            if (sum == 0) return 1;
            if (coinIndex >= coins.length) return 0;
            int coin = coins[coinIndex];
            System.out.println("coin: "+coin);
            if (coin > sum)  return 0;

            System.out.println("pick coin: "+coin);
            int pick = countWays(sum - coin, coins, coinIndex);

//            if (waysForSum.get(sum) != null) waysForSum.put(sum, waysForSum.get(sum) + pick);
//            else waysForSum.put(sum, pick);

            System.out.println("skip coin: "+coin);
            int skip = countWays(sum, coins, ++coinIndex);

//            if (waysForSum.get(sum) != null) waysForSum.put(sum, waysForSum.get(sum) + skip);
//            else waysForSum.put(sum, skip);

            waysForSum.put(sum, pick + skip);
            System.out.println("waysForSum: " + waysForSum);

            return pick + skip;
        }
    }
}


