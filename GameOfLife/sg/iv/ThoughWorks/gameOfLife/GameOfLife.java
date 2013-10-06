package sg.iv.ThoughWorks.gameOfLife;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import sg.iv.ThoughWorks.gameOfLife.artifacts.grid.Grid;
import sg.iv.ThoughWorks.gameOfLife.module.input.def.GridInput;
import sg.iv.ThoughWorks.gameOfLife.module.input.def.InputCommand;
import sg.iv.ThoughWorks.gameOfLife.module.input.def.InputModule;
import sg.iv.ThoughWorks.gameOfLife.module.input.impl.ConsoleInputModule;
import sg.iv.ThoughWorks.gameOfLife.module.output.def.GridOutput;
import sg.iv.ThoughWorks.gameOfLife.module.output.def.OutputModule;
import sg.iv.ThoughWorks.gameOfLife.module.output.impl.CmdExit;
import sg.iv.ThoughWorks.gameOfLife.module.output.impl.CmdRender;
import sg.iv.ThoughWorks.gameOfLife.module.output.impl.ConsoleOutputModule;
import sg.iv.ThoughWorks.gameOfLife.rule.def.Rule;
import sg.iv.ThoughWorks.gameOfLife.rule.impl.ComeToLife;
import sg.iv.ThoughWorks.gameOfLife.rule.impl.DieOfLonliness;
import sg.iv.ThoughWorks.gameOfLife.rule.impl.DieOfOverCrowding;
import sg.iv.ThoughWorks.gameOfLife.rule.impl.StayAlive;



/**
 * @author sachin
 * This class acts as a controller
 */
public class GameOfLife {

	//Throttle I/O rate for input and output modules
	private static final int INPUT_QUEUE_SIZE = 100;
	private static final int OUTPUT_QUEUE_SIZE = 100;

	//Rules of game are defined in terms of logic which can be applied to a cell given 
	//Current state and number of alive cells around it
	private final List<Rule> rulesOfGame = new ArrayList<Rule>();
	
	//Grid holds cells in a pattern, it has a MxN structure
	private Grid gameGrid;

	//Blocking queue is used to implement throttled I/O in a producer consumer pattern between 
	//Controller and Input/Output
	private final BlockingQueue<GridInput> inputQueue = new ArrayBlockingQueue<GridInput>(INPUT_QUEUE_SIZE);
	private final BlockingQueue<GridOutput> outputQueue = new ArrayBlockingQueue<GridOutput>(OUTPUT_QUEUE_SIZE);

	//Input and output modules, Implemented using console for this exercise
	private final InputModule input;
	private final OutputModule output;

	//thread for this controller thread
	private Thread gameController;

	public GameOfLife() {

		// construct rules of game
		Rule rule1 = new DieOfLonliness();
		Rule rule2 = new DieOfOverCrowding();
		Rule rule3 = new StayAlive();
		Rule rule4 = new ComeToLife();

		rulesOfGame.add(rule1);
		rulesOfGame.add(rule2);
		rulesOfGame.add(rule3);
		rulesOfGame.add(rule4);

		//construct input and output modules, with shared IO pipe
		input = new ConsoleInputModule(inputQueue);
		output = new ConsoleOutputModule(outputQueue);
		
		//game grid will be populated with selected pattern
		gameGrid = null;
		
		//Controller thread creation
		gameController = new Thread(new InputConsumer());

	}

	//starts the application
	public void startGame() {
		input.startModule();
		output.startModule();
		gameController.start();
	}

	//consumes the input and queues output commands for Output Module
	class InputConsumer implements Runnable {

		@Override
		public void run() {
			while (true) {
				//GridInput is a transfer object used for input commands
				GridInput inp = null;
				try {
					//read next command
					inp = inputQueue.take();
					
					//transfer object contains Input command
					InputCommand cmd = inp.getInputCommand();
//					System.out.println("input command received: "+inp);
					
					//choose action based on command
					if (cmd == InputCommand.CMD_CHANGE_GRID) {
						Grid g = inp.getInputGrid();
						gameGrid = g;
						
						//create transfer object to send command and data to output module
						GridOutput gout = new CmdRender(gameGrid);
						outputQueue.add(gout);
						
					} else if (cmd == InputCommand.CMD_EXIT) {
						
						input.shutdownModule();
						output.shutdownModule();
						GridOutput gout = new CmdExit();
						outputQueue.add(gout);
						System.out.println("** Game Ended **");
						break;
						
					} else if (cmd == InputCommand.CMD_NEXT_GENERATION) {
						if (gameGrid == null) {
							System.out.println("\nPlease select a starting pattern first.");
						} else {
							gameGrid.discardPendingChanges();
							for (Rule rule : rulesOfGame) {
								// System.out.println("Apply Rule: "+rule.getClass());
								gameGrid.applyRule(rule);
								// gameGrid.printGrid();
								// System.out.println("\n --- ");
								// gameGrid.printPendingChanges();
							}
							// gameGrid.printPendingChanges();
							gameGrid.applyPendingChanges();
							GridOutput gout = new CmdRender(gameGrid);
							outputQueue.add(gout);
						}
						
					}
				} catch (InterruptedException e) {
					System.out.println("Game shutdown");
				}
			}
		}

	}

	public static void main(String[] args) {
		GameOfLife test = new GameOfLife();
		test.startGame();
	}
}
