package sg.iv.ThoughWorks.gameOfLife.artifacts.grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sg.iv.ThoughWorks.gameOfLife.artifacts.cell.Cell;
import sg.iv.ThoughWorks.gameOfLife.artifacts.cell.CellStatus;
import sg.iv.ThoughWorks.gameOfLife.rule.def.Rule;


/**
 * @author sachin
 * Grid structure holds cells in defined set of states
 * It implements logic to apply a rule to all the cells in grid.
 * 
 */
/**
 * @author sachin
 *
 */
public class Grid {

	private final int maxRows;
	private final int maxColumns;

	// Grid has cells
	private final Cell[][] cells;
	private final List<Cell> pendingChanges;

	public Grid(int m, int n) {
		maxRows = m;
		maxColumns = n;
		cells = new Cell[maxRows][maxColumns];
		pendingChanges = new ArrayList<Cell>();
		initializeCells();
	}

	private void initializeCells() {
		for (int i = 0; i < maxRows; i++) {
			for (int j = 0; j < maxColumns; j++) {
				cells[i][j] = new Cell(i, j);
			}

		}
	}

	public void setCellStatus(CellStatus status, int row, int col) {
		cells[row][col].setStatus(status);
	}

	public void discardPendingChanges() {
		pendingChanges.clear();
	}
	
	public void applyPendingChanges() {
		for(Cell cell:pendingChanges) {
			int row = cell.getRow();
			int col = cell.getCol();
//			System.out.println(" row, col: "+row+", "+col + " status: "+cell.getStatus());
			cells[row][col].copyCellAttributes(cell);
		}
		discardPendingChanges();
	}
	
	public void printPendingChanges() {
		System.out.println("Pending Changes: ");
		for(Cell cell:pendingChanges) {
			int row = cell.getRow();
			int col = cell.getCol();
			System.out.println(" row, col: "+row+", "+col+" status: "+cell);
		}
		System.out.println(" ");
	}
	
	public void applyRule(Rule rule) {
		for (int i = 0; i < maxRows; i++) {
			for (int j = 0; j < maxColumns; j++) {
				int num = getAliveNeighbours(i, j);
				Cell changedCell = rule.getCellStatus(num, cells[i][j]);
//				System.out.println("i,j : "+i+", "+j);
//				System.out.println("num: "+num);
//				System.out.println("Changed Cell: "+changedCell);
				
				if (changedCell != null) {
					pendingChanges.add(changedCell);
				}
			}
		}
	}
	
	public void printGrid() {
		System.out.println("");
//		System.out.println("Grid--");
		for (int i = 0; i < maxRows; i++) {
			System.out.println("");
			for (int j = 0; j < maxColumns; j++) {
				cells[i][j].printStatus(); System.out.print(" ");
			}
		}
//		System.out.println("\n--Grid End");
		System.out.println("");
	}

	
	@Override
	public String toString() {
		return "Grid [maxRows=" + maxRows + ", maxColumns=" + maxColumns
				+ ", cells=" + Arrays.toString(cells) + "]";
	}

	
	/**
	 * @param row
	 * @param col
	 * @return number of alive cells in eight neighboring cells of current cell. values include 0 to 8 both inclusive
	 * 
	 */
	private int getAliveNeighbours(int row, int col) {
		// test eight directions here
		int ret = 0;

		// cases: top row, first column, others
		int prevRow = row - 1;
		int prevCol = col - 1;

		// cases: bottom row, last column, others
		int nextRow = row + 1;
		int nextCol = col + 1;
		
//		System.out.println("---------");
//		System.out.println("row, col: "+ row+" , "+ col);
//		System.out.println("prev row, col: " + prevRow+", "+prevCol);
//		System.out.println("next row, col: " +nextRow+", "+nextCol+" ");
		// eight positions to check:
		// top left, top up, top right -- case 1
		// left, right
		// bottom left, bottom down, bottom right
		if (prevRow >= 0) {
			if (prevCol >= 0) { // top left
				if (cells[prevRow][prevCol].getStatus() == CellStatus.ALIVE) {
					ret++; 
//					System.out.println("top left");
				} else {
//					 System.out.println("! top left");
				}
			}

			// top up
			if (cells[prevRow][col].getStatus() == CellStatus.ALIVE) {
//				System.out.println("top up");
				ret++;
			} else {
//				 System.out.println("! top up");
			}

			// top right
			if (nextCol < maxColumns) {
				if (cells[prevRow][nextCol].getStatus() == CellStatus.ALIVE) {
//					System.out.println("top right");
					ret++;
				} else {
//					 System.out.println("! top right");
				}
			}

		} // end of (prevRow > 0)

		if (prevCol >= 0) {

			// check left
			if (cells[row][prevCol].getStatus() == CellStatus.ALIVE) {
//				System.out.println(" left");
				ret++;
			} else {
//				 System.out.println("! left");
			}

			// bottom left
			if (nextRow < maxRows) {
				if (cells[nextRow][prevCol].getStatus() == CellStatus.ALIVE) {
//					System.out.println("bottom left");
					ret++;
				} else {
//					 System.out.println("! bottom left");
				}
			} // end of (prevCol > 0)
		}

		// bottom
		if (nextRow < maxRows) {
			if (cells[nextRow][col].getStatus() == CellStatus.ALIVE) {
//				System.out.println("bottom");
				ret++;
			} else {
//				 System.out.println("! bottom");
			}

		}

		if (nextCol < maxColumns) {
			// bottom right
			if (nextRow < maxRows) {
				if (cells[nextRow][nextCol].getStatus() == CellStatus.ALIVE) {
//					System.out.println("bottom right");
					ret++;
				} else {
//					 System.out.println("! bottom right");
				}
			}

			// right
			if (cells[row][nextCol].getStatus() == CellStatus.ALIVE) {
//				System.out.println("right");
				ret++;
			} else {
//				 System.out.println("! right");
			}

		}

		return ret;
	}
}
