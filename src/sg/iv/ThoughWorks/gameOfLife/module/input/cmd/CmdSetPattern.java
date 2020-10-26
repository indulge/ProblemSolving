package sg.iv.ThoughWorks.gameOfLife.module.input.cmd;

import java.io.File;

import sg.iv.ThoughWorks.gameOfLife.artifacts.grid.Grid;
import sg.iv.ThoughWorks.gameOfLife.module.input.def.GridInput;
import sg.iv.ThoughWorks.gameOfLife.module.input.def.InputCommand;
import sg.iv.ThoughWorks.gameOfLife.module.input.patttern.PatternFactory;

public class CmdSetPattern  implements GridInput {

	private Grid grid;
	
	public CmdSetPattern(String patternName) {
		setGrid(patternName);
	}
	
	@Override
	public InputCommand getInputCommand() {
		return InputCommand.CMD_CHANGE_GRID;
	}

	@Override
	public Grid getInputGrid() {
		return grid;
	}
	
	private void setGrid(String patternName) {
		File f = new File("patterns");
		String currPath = f.getAbsolutePath();
		this.grid = PatternFactory.getPatternFromFile(currPath, patternName);
	}

}