package sg.archive;

public class RecursiveFibonacci {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = recfib(5);
		System.out.println(num+" ");

	}
	public static int recfib(int n) {
		if (n<=1) {
			//System.out.println(n+" ");
			return 1;
		}
		int num = recfib(n-1) + recfib(n-2);
		//System.out.println(num+" ");
		return num;
	}

}
