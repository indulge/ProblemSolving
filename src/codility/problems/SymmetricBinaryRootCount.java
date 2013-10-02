package codility.problems;
import sg.util.BitUtils;


public class SymmetricBinaryRootCount {
	public static int getBinaryRootCount(int num) {
		int count = 0;
		int sqrt = (int) Math.pow(num,(double)1/2);
//		System.out.println(sqrt);
		for (int i=1; i < sqrt; i++) {
			int revbit = BitUtils.bitReverse(i);
//			System.out.println(i+" x "+revbit + " = "+(i * revbit));
			if (i * revbit == num) {
				System.out.println(i+" x "+revbit);
				count++;
			} else if ((num/i) == (int)(num/i)){
				int altnum = num/i;
				revbit = BitUtils.bitReverse(altnum);
//				System.out.println("altnum: "+altnum+" revbit: "+revbit);
				if (altnum * revbit == num) {
					System.out.println(altnum+" x "+revbit);
					count++;
				} 
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		int count = getBinaryRootCount(50);
		System.out.println(count);
	}
}
