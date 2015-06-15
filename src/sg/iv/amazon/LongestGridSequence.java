package sg.iv.amazon;

import java.util.HashMap;
import java.util.Map;


//Design problem
public class LongestGridSequence {
	
	public static enum Symbol {x, o};
	
	private Symbol grid[][];
	private Map<Symbol, Map<String, Integer>> longest = new HashMap<Symbol, Map<String, Integer>>();
	int longestSequenceX = 0;
	
	private static final String HOR = "H";
	private static final String VERT = "V";
	private static final String DIAG = "D";
	private static final Symbol DEFAULT_SYMBOL = Symbol.o;
	
	public LongestGridSequence(int R, int C) {
		grid = new Symbol[R][C];
		for (Symbol s:Symbol.values()) {
			Map<String, Integer> dirLen = new HashMap<String, Integer>();
			dirLen.put(HOR, 0);	//horiazntal
			dirLen.put(VERT, 0);	//vertical
			dirLen.put(DIAG, 0);	//diagonal
			longest.put(s, dirLen);
		}
		for (int i = 0; i < R; i++) {
            for (int j = 0; j < C ; j++) {
            	mark(i,j,DEFAULT_SYMBOL);
            }
        }
	}
	
	 public void printGrid() {
	        int ROW = grid.length;
	        int COL = grid[0].length;
	        System.out.println("\n\n---start---");
	        for (int i = 0; i < ROW; i++) {
	            for (int j = 0; j < COL ; j++) {
	            //arr[i][j] = i * COL + j;
	                System.out.print(grid[i][j] + " ");
	            }
	            System.out.println("");
	        }
	        System.out.println("---end---\n\n");
	    }
	
	public void markX(int row, int col) {
		mark(row, col, Symbol.x);
	}
	
	public void markO(int row, int col) {
		mark(row, col, Symbol.o);
	}
	
	private void mark(int row, int col, Symbol s) {
		grid[row][col] = s;
		updateLongestRow(row, col, s);
		updateLongestCol(row, col, s);
		updateLongestDiag(row, col, s);
//		System.out.println("Longest X:" + getLongestX());
	}
	
	public int getLongestX() {
		return getLongestForSymbol(Symbol.x);
	}
	
	public int getLongestO() {
		return getLongestForSymbol(Symbol.o);
	}
	
	private int getLongestForSymbol(Symbol s) {
		Map<String, Integer> dirLen = longest.get(s);
		int hor = dirLen.get(HOR);
		int vert = dirLen.get(VERT);
		int diag = dirLen.get(DIAG);
		
		int largest = hor;
		if (vert > largest) {
			largest = vert;
		}
		if (diag > largest) {
			largest = diag;
		}
		return largest;
	}
	
	private void setLongest(Symbol s, String dir, int num) {
		Map<String, Integer> dirLen = longest.get(s);
		int len = dirLen.get(dir);
		if (num > len) {
			dirLen.put(dir, num);
		}
	}
	
	int updateLongestRow(int x, int y, Symbol s) {
		//get symbols to left
		int i = y - 1;
		int len = 1;
		while ( i >= 0) {
			if (grid[x][i] == s) {
				len++;
			} else {
				break;
			}
			i--;
		}
		
		//sym to right
		i = y + 1;
		while ( i < grid[0].length) {
			if (grid[x][i] == s) {
				len++;
			} else {
				break;
			}
			i++;
		}
		setLongest(s, HOR, len);
		return len;
	}

	int updateLongestCol(int x, int y, Symbol s) {
		int i = x - 1;
		int len = 1;
		//Symbol up
		while ( i >= 0) {
			if (grid[i][y] == s) {
				len++;
			} else {
				break;
			}
			i--;
		}
		
		//symbol down
		i = x + 1;
		while ( i < grid.length) {
			if (grid[x][i] == s) {
				len++;
			} else {
				break;
			}
			i++;
		}
		
		setLongest(s, VERT, len);
		return len;
	}

	int updateLongestDiag(int x, int y, Symbol s) {
		int len = 1;
		int i = x-1;
		int j = y-1;
		
		while ( (i >= 0)  && (j >= 0)) {
			if (grid[i][j] == s) {
				len++;
			} else {
				break;
			}
			i--;
			j--;
		}
		
		i = x + 1;
		j = y + 1;
		while ((i < grid.length) && (j < grid[0].length)) {
			if (grid[i][j] == s) {
				len++;
			} else {
				break;
			}
			i++;
			j++;
		}
		
		setLongest(s, DIAG, len);
		return len;
	}
	
	public static void main(String[] args) {
		LongestGridSequence test = new LongestGridSequence(4,4);
//		0 0 x 0
//		x x 0 0
//		0 0 0 x
//		x 0 0 x
//		test.printGrid();
		System.out.println("longest x: " + test.getLongestX());
		test.markX(0,2);
		test.markX(1,0);
		test.markX(1,1);
		test.markX(2,3);
		test.markX(3,0);
		test.markX(3,3);
		test.printGrid();
		System.out.println("longest x: " + test.getLongestX());
		System.out.println("longest o: " + test.getLongestO());
		
	}
}
