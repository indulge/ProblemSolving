package sg.iv.ThoughWorks.gameOfLife.rule.def;

import sg.iv.ThoughWorks.gameOfLife.artifacts.cell.Cell;


public interface Rule {
	//return new cell with parameters set OR null if rule does not apply
	public Cell getCellStatus(int numberOfNeighbours, Cell cell);
}
