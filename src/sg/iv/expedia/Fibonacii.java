package sg.iv.expedia;

public class Fibonacii {
	
	public int getSumUptoK(int k) {
		int num1 = 0;
		int num2 = 1;
		int sum = num1 + num2;
		return getSumUptoK(k - 2, num1, num2);
		
	}
	
	private int getSumUptoK(int k, int num1, int num2) {
		System.out.println(" num1: "+num1+ " num2: "+num2+" k: "+k + " sum: "+(2*num1+num2) + " ssf: "+(2*num1+num2));
		
		if (k <= 0) {
			return 2*num1+num2;
		} else {
			
			int temp = num2;
			num2 = num1 + num2;
			num1 = temp;
			
			//sum = sum + num2;
			return getSumUptoK(k-1, num1, num2);
		}
	}
	public static void main(String[] args) {
		Fibonacii fib = new Fibonacii();
		System.out.println("k = 5: " + fib.getSumUptoK(15));
	}
}
