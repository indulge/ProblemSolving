package sg.util;

public class BitUtils {
	public static int nextSmallestPower2(int num) {
		if (num <= 1) return 0;
		int i = 1;
		while (i < num) {
			i = i << 1;
			//System.out.println(i);
		}
		return i;
	}
	
	public static int bitFlip(int num) {
		int all1 =  nextSmallestPower2(num) - 1;
		StringBuilder sbr = new StringBuilder();
		while (num > 0) {
			sbr.append(num&1);
			num >>= 1; 
		}
		System.out.println(sbr.reverse());
		return num^all1;
	}
	
	public static int countSetBits(int n)
	{
	  int count = 0;
	  while(n > 0)
	  {
	    count += n & 1;
	    n >>= 1;
	  }
	  return count;
	}
	
	public static int bitReverse(int num) {
		
		int ret = 0;
		while (num > 0) {
			if ( (num & 1) > 0) {
				ret = (ret << 1) + 1;
			} else {
				ret <<= 1;
			}
			num >>= 1;
		}
		
		return ret;
	}
	
	public static void printBits(int num) {
		StringBuilder sbr = new StringBuilder();
		while (num > 0) {
			sbr.append(num&1);
			num >>= 1; 
		}
		System.out.println(sbr.reverse());
		
	}
	
	public static void main(String[] args) {
//		System.out.println(nextSmallestPower2(2));
//		System.out.println(nextSmallestPower2(3));
//		System.out.println(nextSmallestPower2(4));
//		System.out.println(nextSmallestPower2(5));
		int num = 22;
		printBits(num);
		int br = bitReverse(num);
		printBits(br);
		System.out.println(br);
		printBits(31);
		int xor = 26^31;
		
		printBits(xor);
		System.out.println(xor);
		//System.out.println(printBits(26)); //11 //11 13
		//System.out.println(printBits(11));
		
		
//		System.out.println(bitFlip(1));
//		System.out.println(1<<1);
//		System.out.println(1<<2);
//		System.out.println(1<<3);
	}
}
