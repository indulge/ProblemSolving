package sg.iv.ThoughWorks.gameOfLife.module.input.cmd;

import sg.iv.ThoughWorks.gameOfLife.artifacts.grid.Grid;
import sg.iv.ThoughWorks.gameOfLife.module.input.def.GridInput;
import sg.iv.ThoughWorks.gameOfLife.module.input.def.InputCommand;

/**
 * @author sachin
 * Implemts exit command as transfer object
 */
public class CmdExit  implements GridInput {
	@Override
	public InputCommand getInputCommand() {
		return InputCommand.CMD_EXIT;
	}

	@Override
	public Grid getInputGrid() {
		return null;
	}
}
