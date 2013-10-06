package sg.iv.ThoughWorks.gameOfLife.module.output.def;

import sg.iv.ThoughWorks.gameOfLife.artifacts.grid.Grid;

public interface GridOutput {
	public OutputCommand getOutputCommand();
	
	//return new output grid in case command indicates a render operation
	public Grid getOutputGrid();
}
