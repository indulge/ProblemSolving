package sg.dp;

public class MinNumberOfCoins {

    public static void main(String[] args) {
        System.out.println("Solution: " + Program.minNumberOfCoinsForChange(7, new int[] {3,7 }));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    static class Program {
        static int minCoins = Integer.MAX_VALUE;
        public static int minNumberOfCoinsForChange(int n, int[] denoms) {
            minCoins = Integer.MAX_VALUE;
           int ret =  minNumberOfCoinsForChangeRec(n, denoms, 0, 0);
           if (ret == Integer.MAX_VALUE) return -1;
           return ret;
        }

        public static int minNumberOfCoinsForChangeRec(int sum, int[] coins, int currentCoinIndex, int coinCount) {
            System.out.println("_");
            System.out.println("Sum: " + sum);
            System.out.println("CoinCount: " + coinCount);
            System.out.println("minCoins: " + minCoins);
            System.out.println("currentCoinIndex: " + currentCoinIndex);
            if (currentCoinIndex < coins.length) System.out.println("coin: " + coins[currentCoinIndex]);

            if (currentCoinIndex >= coins.length) return 0;
            int coin = coins[currentCoinIndex];

            if (sum == 0) minCoins = Math.min(minCoins, coinCount);
            else if (sum < 0) return 0;

            //pick a coin.
            minNumberOfCoinsForChangeRec(sum - coin, coins, currentCoinIndex, coinCount + 1);

            //skip a coin
            minNumberOfCoinsForChangeRec(sum, coins, ++currentCoinIndex, coinCount);

            //could not pick any coins.
            return minCoins;
        }
    }

}
