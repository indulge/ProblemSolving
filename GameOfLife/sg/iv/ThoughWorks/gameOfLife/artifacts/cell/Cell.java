package sg.iv.ThoughWorks.gameOfLife.artifacts.cell;


/**
 * @author sachin
 * Simple cell class, Cell is contained within a Grid
 */
public class Cell {

	public static final String ALIVE_SYMBOL = "X";
	public static final String DEAD_SYMBOL = " ";

	private final int row;
	private final int col;

	// can be extended to include other patameters like color, etc.
	private CellStatus status;
	private String symbol;

	public Cell(int row, int col) {
		this(CellStatus.DEAD, row, col);
	}

	public Cell(CellStatus alive, int row, int col) {
		this.status = alive;
		if (alive == CellStatus.ALIVE) {
			this.symbol = ALIVE_SYMBOL;
		} else {
			this.symbol = DEAD_SYMBOL;
		}
		this.row = row;
		this.col = col;
	}

	
	public void copyCellAttributes(Cell cell) {
		this.setStatus(cell.getStatus());
	}

	public CellStatus getStatus() {
		return status;
	}

	public void setStatus(CellStatus status) {
		this.status = status;
		if (status == CellStatus.ALIVE) {
			this.symbol = ALIVE_SYMBOL;
		} else {
			this.symbol = DEAD_SYMBOL;
		}
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void printStatus() {
		System.out.print("[" + symbol + "]");
	}

	@Override
	public String toString() {
		return "Cell [row=" + row + ", col=" + col + ", status=" + status
				+ ", symbol=" + symbol + "]";
	}
	
	

	
}
