package sg.iv.flipkart.july_2015.machine_round.skipped;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Problem8_skipped {

	public static String add1(String a, String b) {
		char[] ac = a.toCharArray();
		char[] bc = b.toCharArray();
		int len = 0;
		if (ac.length > bc.length) len = ac.length;
		else len = bc.length;
		char[] ret = new char[len+1];
		Arrays.fill(ret, '0');
//		List<Integer> ret = new LinkedList<Integer>();
		int carry = 0;
		int sum = 0;
		int k=len;
		for (int i = ac.length-1, j=bc.length-1; (i>=0 || j>=0);k--,i--,j--) {
			if (i<0) {
				sum = (((int)bc[j]-'0') + carry)%10;
				carry = (((int)bc[j]-'0') + carry)/10;
			}
			else if (j<0) {
				sum = (((int)ac[i]-'0') + carry)%10;
				carry = (((int)ac[i]-'0') + carry)/10;
			}
			else {
//				sum = ((int)ac[i] + (int)bc[j] + carry)%10;
				sum = (((int)ac[i]-'0') + ((int)bc[j]-'0') + carry)%10;
				carry = (((int)ac[i]-'0') + ((int)bc[j]-'0') + carry)/10;
				
			}
			System.out.printf("sum:%d, carry:%d\n", sum, carry);
			ret[k]=(char)(sum+'0');
			System.out.println(Arrays.toString(ret));
		}
		if (carry > 0) {
			ret[k]=(char)(carry+'0');
		}
		//System.out.println(Arrays.toString(ret));
		String rets = String.valueOf(ret);
		int prefix0 = 0;
		while (rets.startsWith("0", prefix0)) prefix0++;
		rets = rets.substring(prefix0);
		return rets;
	}
	
	//9-52 = 
	public static String add2(String a, String b) {
		boolean aneg = a.startsWith("-");
		boolean bneg = b.startsWith("-");
		
		if (aneg) a=a.substring(1);
		if (bneg) b=b.substring(1);
		
		char[] ac = a.toCharArray();
		char[] bc = b.toCharArray();
		
		int len = 0;
		if (ac.length > bc.length) len = ac.length;
		else len = bc.length;
		char[] ret = new char[len+2];
		Arrays.fill(ret, '0');

		int carry = 0;
		int sum = 0;
		int ai = 0;
		int bi = 0;
		
		int k=len;
		
		for (int i = ac.length-1, j=bc.length-1; (i>=0 || j>=0);k--,i--,j--) {
			
			if (i<0) {
				ai = 0;
				bi = ((int)bc[j]-'0');
			}
			else if (j<0) {
				ai = ((int)ac[i]-'0');
				bi = 0;
			}
			else {
				ai = ((int)ac[i]-'0');
				bi = ((int)bc[j]-'0');
			}
			if (aneg) ai = ai*-1;
			if (aneg) bi = bi*-1;
			if (aneg ^ bneg) {
				sum = (ai + bi + carry)%10;
				if (sum < 0) {
					
				}
				carry = (ai + bi + carry)/10;
				if ((ai + bi + carry) < 0) {
					carry = carry * -1;
				}
			} else {
				sum = (ai + bi + carry)%10;
				carry = (ai + bi + carry)/10;
			}
			

			System.out.printf("sum:%d, carry:%d\n", sum, carry);
			ret[k]=(char)(sum+'0');
			System.out.println(Arrays.toString(ret));
		}
		if (carry > 0) {
			ret[k]=(char)(carry+'0');
		}
		//System.out.println(Arrays.toString(ret));
		String rets = String.valueOf(ret);
		int prefix0 = 0;
		while (rets.startsWith("0", prefix0)) prefix0++;
		rets = rets.substring(prefix0);
		return rets;
	}
	public static void main(String[] args) {
		String ret = add2("123456789","987654321");
		System.out.println("ret:"+ret+":");
	}
}
