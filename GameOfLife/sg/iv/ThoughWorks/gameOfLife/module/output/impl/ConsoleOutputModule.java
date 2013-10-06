package sg.iv.ThoughWorks.gameOfLife.module.output.impl;

import java.util.concurrent.BlockingQueue;

import sg.iv.ThoughWorks.gameOfLife.artifacts.grid.Grid;
import sg.iv.ThoughWorks.gameOfLife.module.output.def.GridOutput;
import sg.iv.ThoughWorks.gameOfLife.module.output.def.OutputCommand;
import sg.iv.ThoughWorks.gameOfLife.module.output.def.OutputModule;

public class ConsoleOutputModule implements OutputModule {

	private final BlockingQueue<GridOutput> outputQueue;
	private Thread outputThread;
	
	public ConsoleOutputModule(BlockingQueue<GridOutput> outputQueue) {
		this.outputQueue = outputQueue;
	}
	
	@Override
	public synchronized void startModule() {
		outputThread = new Thread(new OutputThread());
		outputThread.start();
	}

	@Override
	public synchronized void shutdownModule() {
		outputThread.interrupt();
	}

	
	private class OutputThread implements Runnable {
		
		@Override
		public void run() {
			while (!Thread.interrupted()) {
				GridOutput gout = null;
				try {
					
					gout = outputQueue.take();
					OutputCommand cmd = gout.getOutputCommand();
					if (cmd == OutputCommand.RENDER_GRID) {
						Grid g = gout.getOutputGrid();
//						System.out.println("Render Grid Command");
						g.printGrid();
						
					} else if (cmd == OutputCommand.EXIT) {
						break;
					}  
					
				} catch (InterruptedException e) {
					System.out.println("Output thread was shutdown");
					//e.printStackTrace();
				}
				
				
			}
			
		}

	}

}
