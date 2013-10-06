package sg.iv.ThoughWorks.gameOfLife.module.input.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import sg.iv.ThoughWorks.gameOfLife.module.input.cmd.CmdExit;
import sg.iv.ThoughWorks.gameOfLife.module.input.cmd.CmdNext;
import sg.iv.ThoughWorks.gameOfLife.module.input.cmd.CmdSetPattern;
import sg.iv.ThoughWorks.gameOfLife.module.input.def.GridInput;
import sg.iv.ThoughWorks.gameOfLife.module.input.def.InputModule;
import sg.iv.ThoughWorks.gameOfLife.module.input.patttern.PatternFactory;


public class ConsoleInputModule implements InputModule {
	
	private final ReadConsoleSystem console;
	private final BlockingQueue<GridInput> inputQueue;
	private Thread inputThread;
	
	public ConsoleInputModule(BlockingQueue<GridInput> inputQueue) {
		console = new ReadConsoleSystem();
		this.inputQueue = inputQueue;
	}
	
	@Override
	public synchronized void startModule() {
		inputThread = new Thread(new ReaderThread());
		inputThread.start();
	}

	@Override
	public synchronized void shutdownModule() {
		inputThread.interrupt();
	}

	private class ReadConsoleSystem {
		public String readInput(String message) {
			System.out.println(message);
			String s = null;
			try {
			    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			    s = bufferRead.readLine();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			return s;
		 
		  }
		}
	
	private class ReaderThread implements Runnable {
		
		File f = new File("patterns");
		String currPath = f.getAbsolutePath();
		
		@Override
		public void run() {
			while (!Thread.interrupted()) {
				System.out.println();
				List<String> names = PatternFactory.getPatternNames(currPath);
				System.out.println("available patterns:");
				for (String name:names) {
					System.out.println(name);
				}
				System.out.println("Next(N)");
				System.out.println("Stop(S)");
				String s = console.readInput("");
				GridInput inp = null;
				if (s.equalsIgnoreCase("N")) {		//next
					inp = new CmdNext();
					inputQueue.add(inp);
				} else if (s.equalsIgnoreCase("S")) {	//stop
					inp = new CmdExit();
					inputQueue.add(inp);
					break;
				} else {		//new pattern
					if (names.contains(s)) {
						inp = new CmdSetPattern(s);
						inputQueue.add(inp);
					}
					
				}
//				else if (s.equalsIgnoreCase("B")) {
//					inp = new BlinkerPatternInput();
//				} else if (s.equalsIgnoreCase("T")) {
//					inp = new ToadPatternInput();
//				} else if (s.equalsIgnoreCase("BO")) {
//					inp = new BoatPatternInput();
//				} 
//				
//				if (inp!=null) {
//					inputQueue.add(inp);
//				}
			}
			
		}
		
		
		
//		public static void main(String[] args) {
//			
//			
////			System.out.println("currPath: "+currPath);
//			List<String> names = PatternFactory.getPatternNames(currPath);
//			System.out.println("names: "+names);
//			
//			Grid grid = PatternFactory.getPatternFromFile(currPath, "toad");
//			grid.printGrid();
//			
//			System.out.println("\n");
//			System.out.println("Saru Pattern");
//			grid = PatternFactory.getPatternFromFile(currPath, "Saru Pattern");
//			grid.printGrid();
//		}

	}
}
