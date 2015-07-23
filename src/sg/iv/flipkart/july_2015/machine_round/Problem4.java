package sg.iv.flipkart.july_2015.machine_round;

import java.util.Arrays;

import sg.util.ArrayUtil;

//4 -Given a lane where there are various houses each containing a fixed amount of gold. 
//Now a robber has to rob the houses such that when he robs a house the adjacent one cannot be robbed. 
//Calculate the maximum amount of gold collected by him.
public class Problem4 {
	
	private static class IntPair  {
		public final int X;
		public final int Y;
		public IntPair(int x, int y) {
			super();
			X = x;
			Y = y;
		}	
	}

	public static void main(String[] args) {
		int[] houseVal = 
				ArrayUtil.getRandomIntArray(50, 1000);
//				ArrayUtil.buildIntArrayFromString("1 1 1 9000 8000");
				
		ArrayUtil.printIntArray(houseVal);
		int max = 0;
		int[] dpBuffer = new int[houseVal.length];
		Arrays.fill(dpBuffer,-1);
		
//		max = getMaxGold(houseVal, 0);
//		System.out.println("max rec:"+max);

		
		max = getMaxGoldDP(houseVal, 0, dpBuffer);
		System.out.println("dpBuffer: "+Arrays.toString(dpBuffer));
		System.out.println("max dp:"+max);
	}

	public static int getMaxGold(int[] houseVal, int canRob) {
		if (canRob >= houseVal.length) return 0;
		int max1 = getMaxGold(houseVal,canRob+1);
		int max2 = houseVal[canRob]+getMaxGold(houseVal,canRob+2);
		if (max1>max2) return max1;
		else return max2;
	}
	
	public static int getMaxGoldDP(int[] houseVal, int canRob, int[] maxSofar) {
//		System.out.println("maxSofar: "+Arrays.toString(maxSofar));
		if (canRob >= houseVal.length) return 0;
		if (maxSofar[canRob] >= 0) return maxSofar[canRob];
		int max1 = getMaxGoldDP(houseVal,canRob+1,maxSofar);
		int max2 = houseVal[canRob]+getMaxGoldDP(houseVal,canRob+2,maxSofar);
		if (max1>max2) {
			maxSofar[canRob] = max1;
		} else {
			maxSofar[canRob] = max2;
		}
		return maxSofar[canRob];
	}

}
