package sg.iv.flipkart.practice;

import java.util.Stack;

public class CoinChangeProblem {
	//private static int amount = 0;
	private static Stack<Integer> coinList = new Stack<Integer>();
	
	public static int[] coins = {1 };
	public static Boolean[] tracker;
	
	public static void printCoins(int amt) {
		//amount = amt;
		System.out.print(amt+":");
		tracker = new Boolean[amt+1];
		boolean ret = printCoins(amt,0);
		System.out.print(":");
		System.out.println(ret);
	}

	public static void printCoinsDP(int amt) {
		//amount = amt;
		System.out.print(amt+":");
		tracker = new Boolean[amt+1];
		boolean ret = printCoinsDP(amt,0);
		System.out.print(":");
		System.out.println(ret);
	}
	
	private static boolean printCoins(int amt, int coin) {
		
		//System.out.println("incoming:"+coinList);
		if (amt==0) {
//			System.out.print(coinList);
			return true;
		}
		
		if (amt < 0) {
			return false;
		}
		boolean flag1 = false;
		boolean flag2 = false;
		
		
		if (coin < coins.length) {
			coinList.push(coins[coin]);
			flag1 = printCoins(amt - coins[coin], coin);
			coinList.pop();
		}
		
		coin++;
		if (coin < coins.length) {
			coinList.push(coins[coin]);
			flag2 = printCoins(amt - coins[coin], coin);
			coinList.pop();
		}
		
		return (flag1||flag2);
	}
	
private static boolean printCoinsDP(int amt, int coin) {
		
		//System.out.println("incoming:"+coinList);
		if (amt==0) {
			System.out.print(coinList);
			return true;
		}
		
		if (amt < 0) {
			return false;
		}
		
		if (tracker[amt]!=null) return tracker[amt];
		
		boolean flag1 = false;
		boolean flag2 = false;
		
		
		if (coin < coins.length) {
			coinList.push(coins[coin]);
			flag1 = printCoinsDP(amt - coins[coin], coin);
			coinList.pop();
		}
		
		coin++;
		if (coin < coins.length) {
			coinList.push(coins[coin]);
			flag2 = printCoinsDP(amt - coins[coin], coin);
			coinList.pop();
		}
		tracker[amt] = flag1||flag2;
		return (flag1||flag2);
	}
	
	public static void main(String[] args) {
		for (int i = 1; i<=5; i++) {
			printCoins(i);
			printCoinsDP(i);
		}
		
//		printCoins(2);
		
	}
}
