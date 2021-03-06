package sg.iv.ThoughWorks.gameOfLife.module.input.patttern;

import sg.iv.ThoughWorks.gameOfLife.artifacts.grid.Grid;
import sg.iv.ThoughWorks.gameOfLife.module.input.def.GridInput;
import sg.iv.ThoughWorks.gameOfLife.module.input.def.InputCommand;

public class ToadPatternInput implements GridInput {
	
	@Override
	public Grid getInputGrid() {
		return PatternFactory.getPattern("toad");
	}

	@Override
	public InputCommand getInputCommand() {
		return InputCommand.CMD_CHANGE_GRID;
	}
	
}