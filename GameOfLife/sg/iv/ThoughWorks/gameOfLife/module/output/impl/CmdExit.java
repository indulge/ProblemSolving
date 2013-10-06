package sg.iv.ThoughWorks.gameOfLife.module.output.impl;

import sg.iv.ThoughWorks.gameOfLife.artifacts.grid.Grid;
import sg.iv.ThoughWorks.gameOfLife.module.output.def.GridOutput;
import sg.iv.ThoughWorks.gameOfLife.module.output.def.OutputCommand;

public class CmdExit implements GridOutput {

	@Override
	public OutputCommand getOutputCommand() {
		return OutputCommand.EXIT;
	}

	@Override
	public Grid getOutputGrid() {
		return null;
	}

}
