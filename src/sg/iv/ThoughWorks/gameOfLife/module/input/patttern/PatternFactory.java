package sg.iv.ThoughWorks.gameOfLife.module.input.patttern;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sg.iv.ThoughWorks.gameOfLife.artifacts.cell.Cell;
import sg.iv.ThoughWorks.gameOfLife.artifacts.cell.CellStatus;
import sg.iv.ThoughWorks.gameOfLife.artifacts.grid.Grid;

public class PatternFactory {

	private static String inputFileName = "patterns.inp";
	private static String inputFile = inputFileName;
	private static ReadFile fileReader = new ReadFile();

	private PatternFactory() {
 
	}

	public static List<String> getPatternNames(String path) {
		inputFile = path + File.separatorChar + inputFileName;
		fileReader.parseFile();
		return fileReader.getPatternNames();
	}

	public static Grid getPatternFromFile(String path, String patternName) {
		inputFile = path + File.separatorChar + inputFileName;
		fileReader.parseFile();
		return fileReader.getGridPattern(patternName);
		 
	}
	
//	public static Grid getPatternFromFile(String patternName) {
//		fileReader.parseFile();
//		return fileReader.getGridPattern(patternName);
//		 
//	}

	public static Grid getPattern(String patternName) {

		Grid grid = null;

		if (patternName.equalsIgnoreCase("toad")) {

			grid = new Grid(4, 4);
			grid.setCellStatus(CellStatus.ALIVE, 1, 1);
			grid.setCellStatus(CellStatus.ALIVE, 1, 2);
			grid.setCellStatus(CellStatus.ALIVE, 1, 3);
			grid.setCellStatus(CellStatus.ALIVE, 2, 0);
			grid.setCellStatus(CellStatus.ALIVE, 2, 1);
			grid.setCellStatus(CellStatus.ALIVE, 2, 2);

		} else if (patternName.equalsIgnoreCase("boat")) {

			grid = new Grid(3, 3);
			grid.setCellStatus(CellStatus.ALIVE, 0, 0);
			grid.setCellStatus(CellStatus.ALIVE, 0, 1);
			grid.setCellStatus(CellStatus.ALIVE, 1, 0);
			grid.setCellStatus(CellStatus.ALIVE, 1, 2);
			grid.setCellStatus(CellStatus.ALIVE, 2, 1);

		} else if (patternName.equalsIgnoreCase("blinker")) {

			grid = new Grid(3, 3);
			grid.setCellStatus(CellStatus.ALIVE, 0, 1);
			grid.setCellStatus(CellStatus.ALIVE, 1, 1);
			grid.setCellStatus(CellStatus.ALIVE, 2, 1);

		}

		return grid;
	}

	private static class ReadFile {
		
		private List<String> patternNames = new ArrayList<String>();
		private Map<String, Grid> gridPatterns = new HashMap<String, Grid>();
		
		public void parseFile() {
			FileInputStream fis = null;
			DataInputStream dis =null;
			BufferedReader br = null;
			try {
				System.out.println("inputFile: "+inputFile);
				fis = new FileInputStream(inputFile);
				dis = new DataInputStream(fis);
				br = new BufferedReader(new InputStreamReader(dis));
				patternNames.clear();
				gridPatterns.clear();
				loadPatternNames(br);
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			} finally {
				try {
					
					br.close();
					dis.close();
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		private void loadPatternNames(BufferedReader br) throws IOException {
			String str;
			str = br.readLine();
			
			while (str != null) {
				if (str.toUpperCase().startsWith("NAME")) {
					String[] nameSplit = str.split(":");
					String name = nameSplit[1];
					patternNames.add(name);
					
					str = br.readLine();
					int rows = Integer.parseInt(str);
					str = br.readLine();
					int cols = Integer.parseInt(str);
					Grid grid = loadGrid(br, rows, cols);
					gridPatterns.put(name, grid);
				}
				str = br.readLine();
			}
		}
		
		private Grid loadGrid(BufferedReader br, int row, int col) throws IOException {
			Grid grid = new Grid(row, col);
			String str;
			
			
			for (int i = 0; i< row; i++) {
				str = br.readLine();
				for (int j = 0; j< col; j++) {
					String ch = str.substring(j, j+1);
					if (ch.toUpperCase().equals(Cell.ALIVE_SYMBOL.toUpperCase())) {
//						System.out.println("Alive Cell Found");
						grid.setCellStatus(CellStatus.ALIVE, i, j);
					}
				}
			}
//			grid.printGrid();
			return grid;
		}

		public List<String> getPatternNames() {
			return patternNames;
		}

		

		public Map<String, Grid> getGridPatterns() {
			return gridPatterns;
		}

		public Grid getGridPattern(String name) {
			return gridPatterns.get(name);
		}
		
		
	}
	
	
}
