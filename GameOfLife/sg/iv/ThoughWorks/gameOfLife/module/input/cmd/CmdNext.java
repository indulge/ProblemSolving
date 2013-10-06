package sg.iv.ThoughWorks.gameOfLife.module.input.cmd;

import sg.iv.ThoughWorks.gameOfLife.artifacts.grid.Grid;
import sg.iv.ThoughWorks.gameOfLife.module.input.def.GridInput;
import sg.iv.ThoughWorks.gameOfLife.module.input.def.InputCommand;

public class CmdNext implements GridInput {

	@Override
	public InputCommand getInputCommand() {
		return InputCommand.CMD_NEXT_GENERATION;
	}

	@Override
	public Grid getInputGrid() {
		return null;
	}

}
