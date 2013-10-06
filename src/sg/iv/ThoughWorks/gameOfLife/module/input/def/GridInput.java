package sg.iv.ThoughWorks.gameOfLife.module.input.def;

import sg.iv.ThoughWorks.gameOfLife.artifacts.grid.Grid;


public interface GridInput {
	public InputCommand getInputCommand();
	//return new input grid in case command indicates a required change
	public Grid getInputGrid();
}
