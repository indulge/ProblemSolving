package sg.iv.ThoughWorks.gameOfLife.rule.impl;

import sg.iv.ThoughWorks.gameOfLife.artifacts.cell.Cell;
import sg.iv.ThoughWorks.gameOfLife.artifacts.cell.CellStatus;
import sg.iv.ThoughWorks.gameOfLife.rule.def.Rule;
import sg.iv.ThoughWorks.gameOfLife.rule.def.RuleConstants;

public class StayAlive implements Rule {
	
	@Override
	public Cell getCellStatus(int numberOfNeighbours, Cell cell) {
		if (cell.getStatus() == CellStatus.ALIVE && numberOfNeighbours >  RuleConstants.LONLEY_NUMBER && numberOfNeighbours <  RuleConstants.OVER_CROWD_NUMBER) {
			return cell;
		} 
		return null;
	}
	
}
