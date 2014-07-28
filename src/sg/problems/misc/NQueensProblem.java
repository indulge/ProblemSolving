package sg.problems.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NQueensProblem {
	private final int totalQueens;
	private final int[] currentConfiguration;

	// private int numQueens = 0;
	Map<Integer, List<Integer>> skipMap = new HashMap<Integer, List<Integer>>();

	private class Ret {
		public String reason;
		public boolean ret;

		@Override
		public String toString() {

			return "[ret=" + ret + ", reason=" + reason + "]";
		}
	}

	public NQueensProblem(int num) {
		totalQueens = num;
		currentConfiguration = new int[num];
		Arrays.fill(currentConfiguration, 0);
		// initialStartColumn = 0;
	}

	public NQueensProblem(int num, int init) {
		totalQueens = num;
		currentConfiguration = new int[num];
		Arrays.fill(currentConfiguration, 0);
		// initialStartColumn = init;
		List<Integer> triedCols = new ArrayList<Integer>();
		skipMap.put(new Integer(1), triedCols);
		for (int i = 0; i < totalQueens; i++) {
			if ((i + 1) != init)
				triedCols.add(new Integer(i + 1));
		}

	}

	private Ret canPlaceQueen(int x, int y) {
//		System.out.println("Can place in " + x + " " + y + " " + skipMap);
		// System.out.println(Arrays.toString(Arrays.copyOf(currentConfiguration,
		// x)));

		String reason = "";
		boolean ret = true;

		if (x > totalQueens) {
			reason = " TOTAL ";
			ret = false;
		}

		// if (x==1 && initialStartColumn > 0) {
		// if (y == initialStartColumn) {
		// ret = true;
		// reason = "";
		// } else {
		// ret = false;
		// reason = "";
		// }
		//
		// }
		List<Integer> triedCols = skipMap.get(x);
		if (triedCols == null) {
			triedCols = new ArrayList<Integer>();
			skipMap.put(x, triedCols);
		}
		
		// if (currentConfiguration[x-1] > 0) return false; //row

		if (ret)
			for (int i = 0; i < x; i++) { // columns
				if ((currentConfiguration[i] == y) || triedCols.contains(y)) {
					reason = " COLUMN ";
					ret = false;
				}
			}

		if (ret)
			for (int i = 0; i < x; i++) { // diagonals
				if (Math.abs(x - (i + 1)) == Math.abs(y - currentConfiguration[i])) {
					reason = " DIAGONAL ";
					ret = false;
				}

			}

		if (ret) triedCols.add(y);
		Ret rv = new Ret();
		rv.reason = reason;
		rv.ret = ret;
		return rv;

	}

	public void getRandomConfig() {
		for (int i = 0; i < totalQueens; i++) {
			currentConfiguration[i] = ((int) (Math.random() * (totalQueens + 1)))
					% totalQueens + 1;
			// numQueens++;
		}
		System.out.println(Arrays.toString(currentConfiguration));
	}

	public void printBoard(int rows) {
		System.out.println("");
		for (int i = 0; i < rows; i++) {
			String[] row = new String[totalQueens];
			Arrays.fill(row, " ");
			if (currentConfiguration[i] > 0) {
				row[currentConfiguration[i] - 1] = "Q";
				System.out.println(Arrays.toString(row) + " "
						+ canPlaceQueen(i + 1, currentConfiguration[i]));
			} else {
				System.out.println(Arrays.toString(row) + " [Row Empty] ");
			}

		}
		System.out.println("");
	}

	public void printBoard() {
		printBoard(totalQueens);
	}

	public boolean solve() {
		// try to place queen in current in current row, if it can be placed,
		// then, recurse for next row
		// else, try placing previous row.
		// for (int i = 0; i < totalQueens; i++) {
		// if (solve(i + 1))
		// }
		return solve(1);

	}

	private boolean solve(int x) {
		System.out.println("Solving Row: " + x);
		if (x <= 1 && currentConfiguration[x - 1] >= totalQueens) {
			System.out.println("No Solution");
			return false;
		} else if (x > totalQueens) {
			return true;
		}

		int i = 0;
		if ((x == 1) && (currentConfiguration[x - 1] > 0))
			i = currentConfiguration[x - 1];
		for (; i < totalQueens; i++) {
			Ret ret = canPlaceQueen(x, i + 1);
			if (ret.ret) {
				currentConfiguration[x - 1] = i + 1;
				System.out.println("solved upto: " + x);
				printBoard(x);
				solve(x + 1);
				return true;
			}
		}
		for (Integer colKeys : skipMap.keySet()) {
			if (colKeys >= x)
				skipMap.put(colKeys, null);
		}
		return solve(x - 1);
	}

	public static void main(String[] args) {
		NQueensProblem board1 = new NQueensProblem(6);
		// board1.getRandomConfig();
		board1.printBoard();
		System.out.println(board1.solve());
	}
}
