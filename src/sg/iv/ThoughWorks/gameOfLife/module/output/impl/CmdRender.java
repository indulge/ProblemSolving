package sg.iv.ThoughWorks.gameOfLife.module.output.impl;

import sg.iv.ThoughWorks.gameOfLife.artifacts.grid.Grid;
import sg.iv.ThoughWorks.gameOfLife.module.output.def.GridOutput;
import sg.iv.ThoughWorks.gameOfLife.module.output.def.OutputCommand;

public class CmdRender implements GridOutput {

	private final Grid out;
	
	public CmdRender(Grid out) {
		this.out = out;
	}
	
	@Override
	public OutputCommand getOutputCommand() {
		return  OutputCommand.RENDER_GRID;
	}

	@Override
	public Grid getOutputGrid() {
		return out;
	}

}
