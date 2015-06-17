package sg.iv.amazon.june_2015;
import java.util.Arrays;

//solution to coin change problem following topcoder DP tutorial
//Coin change and Increacing subsequence are single dimentional DP problems
//https://www.topcoder.com/community/data-science/data-science-tutorials/dynamic-programming-from-novice-to-advanced/

public class CoinChange1 {
	
	private static class CoinTrack {
		
		final int currCoin;
		final int prevCoinIndex;
		public CoinTrack(int currCoin, int prevCoinIndex) {
			this.currCoin = currCoin;
			this.prevCoinIndex = prevCoinIndex;
		}
		public static void printCoinTrack(CoinTrack[] arr, int start) {
			String track = "";
			CoinTrack coinTrack = arr[start];
			do {
				track = track+" "+coinTrack.currCoin;
				coinTrack = arr[coinTrack.prevCoinIndex];
			} while (coinTrack.prevCoinIndex >= 0);
			System.out.println(track);
		}
		@Override
		public String toString() {
			return "["+currCoin +", "+ prevCoinIndex + "]";
		}
		
	}
	
	public static int minCoinsDP(int[] coins, int sum) {
//		Map<Integer, Integer> minCoinsForSum = new HashMap<>();
		int[] sumArr = new int[sum+1];
		sumArr[0] = 0;
		for (int i = 0; i < sumArr.length; i++) {
			sumArr[i] = -1;
		}
		return minCoinsDP(coins, sum, sumArr);
		
	}
	public static int minCoinsRec(int[] coins, int sum) {
		int mincoins = minCoinsRec(coins, sum, 0);
		return mincoins;
	}
	
	//todo:convert this to map
	//use a triplet in sum array, sum, curr coin, prev coin, this will keep track of coins used to produce the solution.
	public static int minCoinsDP(int[] coins, int sum, int[] sumArr) {
//		List<Integer> coinList = new ArrayList<>();
		
		
		CoinTrack[] coinTracker = new CoinTrack[sum+1];
		for (int i = 0; i < coinTracker.length ; i++) {
			coinTracker[i] = new CoinTrack(0, -1);
		}
		
		
		for (int i = 1; i <= sum; i++) {
			for (int coin : coins) {
				// if (sum < coin) //do nothing
				if (i == coin) {
					sumArr[i] = 1;
					coinTracker[i] = new CoinTrack(coin, -1);
				} else if (i > coin) {
					int coinPrev = sumArr[i - coin];
					// if (coinPrev == -1) //do nothing
					if (coinPrev >= 0) {
						if (sumArr[i] == -1) {
							sumArr[i] = 1 + sumArr[i - coin];	
							coinTracker[i] = new CoinTrack(coin, (i - coin));
						}
						else {
							int min1 = (1 + sumArr[i - coin]);
							int min2 = sumArr[i];
							if (min1 < min2) {
								sumArr[i] = min1; 
								coinTracker[i] = new CoinTrack(coin, (i - coin));
							} else {
								sumArr[i] = min2;
							}							
						}
							
					}

				}

			}
		}
//		System.out.println(Arrays.toString(coinTracker));
		System.out.print("\nCoins:");
		CoinTrack.printCoinTrack(coinTracker, sum);
		System.out.println(Arrays.toString(sumArr));
		return sumArr[sum];
	}
	public static int minCoinsRec(int[] coins, int sum, int coinIndex) {
		if (sum==0) return 0;
		if (sum < 0) return -1; //not possible
		if (coinIndex >= coins.length) return -1;	//not possible
		int min1 = minCoinsRec(coins, sum-coins[coinIndex], coinIndex);
		if (min1 >= 0) min1 = min1 + 1;
		int min2 = minCoinsRec(coins, sum, coinIndex + 1);
		
		int min = 0;
		if ((min1 >= 0) && (min2 >= 0)) {
			min = Math.min(min1, min2);
		} else if (min1 >= 0) min = min1;
		else min = min2;
		
		return min;
	}
	public static void main(String[] args) {
		int[] coins = {2};
		int sum = 22;
		int minCoins = minCoinsRec(coins, sum);
		System.out.println("Min Coins: "+minCoins);
		minCoins = minCoinsDP(coins, sum);
		System.out.println("Min Coins DP: "+minCoins);
		
	}
}
