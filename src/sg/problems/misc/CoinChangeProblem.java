package sg.problems.misc;

import java.util.HashMap;
import java.util.Map;

public class CoinChangeProblem {
	private static Map<Integer, Integer> OptChange = new HashMap<Integer, Integer>();
	public static int getMinNumberOfCoins(int[] coins, int sum) {
		return getMinNumberOfCoins(coins, coins.length, sum);
	}

	private static int getMinNumberOfCoins(int[] coins, int arrlen, int sum) {
		// c(p) = min (c(p - vi)) + 1
		if (sum == 0) {
			return 0;
		}
		if (sum < coins[0]) {
//			System.out.println(" sum < coins[0]");
			return -1;
		}
		// int[] stepSum = new int[coins.length];
		// int toatalNumCoins = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < arrlen; i++) {
//			System.out.println(" sum: "+sum + " sub_sum: "+ (sum - coins[arrlen - (i + 1)]) + " coin: " + coins[arrlen - (i + 1)]);
			int ret;
			if (OptChange.get(sum) != null) {
				ret = OptChange.get(sum);
			} else {
				ret  = getMinNumberOfCoins(coins, arrlen, sum - coins[arrlen - (i + 1)]);
			}
			
			
//			System.out.println(" ret: "+ret );
			
			if (ret < 0) {
//				if (min == Integer.MAX_VALUE) {
//					min = -1;
//				}
				continue;
			}
			
			int numCoins = 1 + ret;
//			System.out.println(" numCoins: "+numCoins );
			if (numCoins < min) {
				min = numCoins;
			}
				
		}
		if (min == Integer.MAX_VALUE){
			min = -1;
		}
		OptChange.put(sum, min);
		return min;
	}

	public static void main(String[] args) {
		int coins[] = { 1, 2, 4 };
		int sum = 3;
		int ret = getMinNumberOfCoins(coins, sum);
		if (ret < 0) {
			System.out.println("not possible");
		} else  {
			System.out.println("min coins: " + ret);
		}
		
	}
}
