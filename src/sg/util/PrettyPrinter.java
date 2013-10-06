package sg.util;

public class PrettyPrinter {
	public static void print2dArray(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println("");
			for (int j = 0; j < arr.length; j++) {
				System.out.print(String.format("%5s", arr[i][j]));
			}
		}
		System.out.println("");
	}
}
