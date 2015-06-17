package sg.iv.amazon.june_2015;

import java.util.LinkedList;
import java.util.List;

//given a set of coins and a sum return minimum number of coins required.
public class CoinChangeProblem {
	


	public static int getMinCoinsDP(int[] coins, int sum) {
		numSolutios = 0;
		int min = getMinCoinsDP(coins, sum, 0, 0);
		System.out.println("MinCoins: "+minCoins);
		System.out.println("Num Solutions: "+numSolutios);
		System.out.println(solutionCoins);
//		System.out.println(solutionCoins.subList(0, solutionIndex));
		return min;
	}
	
	private static int getMinCoinsDP(int[] coins, int sum, int pos, int numCoins) {
		if (table.get(sum, pos) != null) return table.get(sum, pos);
		
		//this is a solution
				if (sum == 0) {
					if (minCoins < 0)
						minCoins = numCoins;
					else if (minCoins > numCoins)
						minCoins = numCoins;
					
					System.out.println(solutionCoins);
					table.put(sum, pos, minCoins);
					numSolutios++;
					return minCoins;
				}
				
				//if not 
				//pick each coin once, if none of the coins reach <= sum then there is no solution
				//there is no further solution if adding any of coins lead to greater than sum required.
				
				if (pos >= coins.length) return -1;
				if (sum < 0) return -1;
				
				getMinCoinsDP(coins, sum, pos + 1, numCoins);
				
				solutionCoins.add(coins[pos]); //solutionIndex++;
				getMinCoinsDP(coins, sum - coins[pos], pos, numCoins + 1);
				solutionCoins.remove(new Integer(coins[pos]));
				
				return -1;
		
	}

	
	// Returns the count of ways we can sum  S[0...m-1] coins to get sum n
	public static int countExample( int S[], int m, int n )
	{
	    // If n is 0 then there is 1 solution (do not include any coin)
	    if (n == 0)
	        return 1;
	     
	    // If n is less than 0 then no solution exists
	    if (n < 0)
	        return 0;
	 
	    // If there are no coins and n is greater than 0, then no solution exist
	    if (m <=0 && n >= 1)
	        return 0;
	 
	    // count is sum of solutions (i) including S[m-1] (ii) excluding S[m-1]
	    return countExample( S, m - 1, n ) + countExample( S, m, n-S[m-1] );
	}
	
	public static int getMinCoins(int[] coins, int sum) {
		numSolutios = 0;
		int min = getMinCoins(coins, sum, 0, 0);

//		System.out.println(solutionCoins.subList(0, solutionIndex));
		return min;
	}
	
	static Table<Integer> table = new Table<>();
	private static int minCoins = -1;
	private static int numSolutios = 0;
	private static List<Integer> solutionCoins = new LinkedList<Integer>();
//	private static int solutionIndex = 0;
	private static int getMinCoins(int[] coins, int sum, int pos, int numCoins) {
		
		//this is a solution
		if (sum == 0) {
			if (minCoins < 0)
				minCoins = numCoins;
			else if (minCoins > numCoins)
				minCoins = numCoins;
			
			System.out.println(solutionCoins);
//			
//			System.out.println(solutionCoins.subList(0, solutionIndex));
//			solutionIndex=0;
//			solutionCoins.clear();
			numSolutios++;
			return minCoins;
		}
		
		//if not 
		//pick each coin once, if none of the coins reach <= sum then there is no solution
		//there is no further solution if adding any of coins lead to greater than sum required.
		
		if (pos >= coins.length) return 0;
		if (sum < 0) return 0;
		
		
		//for each coin further, use, drop or resue and continue
//		for (int i = pos; i < coins.length; i++) {
//				int ret = 0;
				
				
				// drop this coin
				int ret1 = getMinCoins(coins, sum, pos + 1, numCoins);
				// use this coin and move to next
				
				
				
//				int ret2 = getMinCoins(coins, sum - coins[pos], pos + 1, numCoins + 1);
				
//				if (ret < 0) solutionCoins.remove(solutionCoins.size()-1); 
//				if (ret < 0) solutionIndex--;
				
				// use this coin and stay on this coin for reuse
				solutionCoins.add(coins[pos]); //solutionIndex++;
				int ret3 = getMinCoins(coins, sum - coins[pos], pos, numCoins + 1);
				solutionCoins.remove(new Integer(coins[pos]));
//				if (ret < 0) solutionCoins.remove(solutionCoins.size()-1);
//				if (ret3 < 0) solutionCoins.remove(new Integer(coins[pos]));
				
//				if ((ret1 + ret2 + ret3) < 0 ) 
				
			
//		}
		return minCoins;
	}
	
	public static void main(String[] args) {
		int[] coins = {50,25,10,5,1};
		int sum = 26;
		//50-cent, 25-cent, 10-cent, 5-cent, and 1-cent.
		getMinCoins(coins, sum);
		System.out.println("MinCoins: "+minCoins);
		System.out.println("Num Solutions: "+numSolutios);
		System.out.println("MinCoins Example: "+
		countExample(coins, 4, sum)
				);
		
//		System.out.println("----------DP Run---------");
//		getMinCoinsDP(coins, sum);
//		System.out.println("MinCoinsDP: "+minCoins);
//		System.out.println("Num SolutionsDP: "+numSolutios);

		//System.out.println(" Result: " + result);
	}
}
