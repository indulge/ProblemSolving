package sg.iv.expedia;
import java.util.Random;


public class MathProblems {

	public static int checkMultipleOf7(int num) {
		
		if (num < 0) {
			return checkMultipleOf7(-1*num);
		} 
		
		if (num == 0 || num == 7) {
			return 1;
		} else if (num < 10) {
			return -1;
		}
//		System.out.println(" check for: "+ (num/10 - 2 * (num - ((int)(num/10))*10)));
		return checkMultipleOf7(num/10 - 2 * (num - ((int)(num/10))*10));
	
	}
	
	static int add(int x, int y)
	{
	    // Iterate till there is no carry  
	    while (y != 0)
	    {
	        // carry now contains common set bits of x and y
	        int carry = x & y;  
	 
	        // Sum of bits of x and y where at least one of the bits is not set
	        x = x ^ y; 
	 
	        // Carry is shifted by one so that adding it to x gives the required sum
	        y = carry << 1;
	    }
	    return x;
	}
	
	static int multiply(int x, int y) {
	
		
		if (x < 0 && y < 0) {
			return multiply(-1 * x, -1 * y);
		} else if (y < 0) {
			return -1 * multiply(x, -1 * y);
		} else if (x < 0) {
			return -1 * multiply(-1 * x, y);
		} 
		
		int result  = 0;
		
		while (y > 0) {
			if ((y & 1) > 0) {
				result = result + x;
			}
			x = x << 1;
			y = y >> 1;
		}
		
		return result;
	}
	
	
	public static void main(String[] args) {
//		for (int i=0;i<100;i++) {
//			if (checkMultipleOf7(i) > 0) {
//				System.out.println(" num: "+i+" divisible");
//			}
//		}
		
		Random rand = new Random();
		for (int i=0;i<100;i++) {
			int num1 = -1 * rand.nextInt(10);
			int num2 = rand.nextInt(10);
			int mult = multiply(num1, num2);
			System.out.println(" num1: "+ num1 +" num2: " + num2 + " mult: " + mult);
			
		}
	}
}
