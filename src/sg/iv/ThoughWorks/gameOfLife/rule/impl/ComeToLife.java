package sg.iv.ThoughWorks.gameOfLife.rule.impl;

import sg.iv.ThoughWorks.gameOfLife.artifacts.cell.Cell;
import sg.iv.ThoughWorks.gameOfLife.artifacts.cell.CellStatus;
import sg.iv.ThoughWorks.gameOfLife.rule.def.Rule;
import sg.iv.ThoughWorks.gameOfLife.rule.def.RuleConstants;

public class ComeToLife implements Rule {

	@Override
	public Cell getCellStatus(int numberOfNeighbours, Cell cell) {
		if (cell.getStatus() == CellStatus.DEAD && numberOfNeighbours == RuleConstants.LIFE_NUMBER) {
			return new Cell(CellStatus.ALIVE, cell.getRow(), cell.getCol());
		} 
		return null;
	}
	
}
