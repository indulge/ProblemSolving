package sg.iv.ThoughWorks.gameOfLife.rule.impl;

import sg.iv.ThoughWorks.gameOfLife.artifacts.cell.Cell;
import sg.iv.ThoughWorks.gameOfLife.artifacts.cell.CellStatus;
import sg.iv.ThoughWorks.gameOfLife.rule.def.Rule;
import sg.iv.ThoughWorks.gameOfLife.rule.def.RuleConstants;



public class DieOfOverCrowding implements Rule {
	
	@Override
	public Cell getCellStatus(int numberOfNeighbours, Cell cell) {
		if (cell.getStatus() == CellStatus.ALIVE && numberOfNeighbours >= RuleConstants.OVER_CROWD_NUMBER) {
			Cell ret = new Cell(CellStatus.DEAD, cell.getRow(), cell.getCol());
			return ret;
		} 
		return null;
	}
	
}