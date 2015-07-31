package sg.iv.flipkart.july_2015.machine_round;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//13 -Write a running code in any language to implement the famous tic-tac-toe game.
//Design this game as per following:
//1) Game has 3 modes: Human Vs Human, Human Vs Computer and Computer Vs Computer.
//2) Initially start with 3X3 grid, but it can be generalized to NXN grid. So don’t hardcode any variable.
//3) Minimize Code Redundancy and try to make it as modular as possible.
//4) Try to use abstraction and expose lesser number of functions(APIs) to outside world.
//5) Try to cover maximum number of edge cases, like when to abort the game, draw condition, win condition, overwriting existing value in grid etc)

public class Problem13 {
	public static void main(String[] args) {
		TicTacToe test = new TicTacToe(3); 		
		while (!test.isCompleted()) {
			test.playNextMove();
		}
	}
}
class TicTacToe {
	public final static int HUMAN_VS_HUMAN = 1; 
	
	private final Board gameBoard; 
	private final Player player1; 
	private final Player player2; 
	
	private Player currentPlayer; 
	private Player winner; 
	
	public TicTacToe(int boardSize) {
		player1 = new HumanPlayer("player1", Marker.CROSS); 
		player2 = new HumanPlayer("player2", Marker.NOUGHT); 
		gameBoard = new Board(boardSize); 
		currentPlayer = player1; 
	}
	
	public static enum Marker {
		CROSS, 
		NOUGHT 
	}
	
	public boolean isCompleted() {
		return this.gameBoard.isGameComplete(); 
	}
	
	public boolean playNextMove() {	
		if (gameBoard.isGameComplete()) {
			gameBoard.printBoard();
			System.out.println("Game Finished");
			return false;
		}
		
		Move next = currentPlayer.nextMove(gameBoard);
		boolean cellMarked = gameBoard.markCell(next); 
		
		if (gameBoard.isGameComplete()) {
			winner = currentPlayer;
			gameBoard.printBoard();
			System.out.println("Game Finished");
		}
		
		if (cellMarked) {
			setNextPlayer();
			return true;
		} 
		return false;
	}
	
	private void setNextPlayer() {
		if (currentPlayer == null) {
			currentPlayer = player1;
		} else if (currentPlayer == player1) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}
	}
	
	public void printCurrentState() {
		this.gameBoard.printBoard();
	}
	
	
	public Player getWinner() {
		return winner;
	}


}

class Board {
		
	private int boardSize=3;
	private TicTacToe.Marker[][] boardGrid;
	
	private boolean gameComplete = false;
	
	public Board() {
		boardGrid = new TicTacToe.Marker[boardSize][boardSize];
		initBoard();
	}
	public Board(int size) {
		this.boardSize = size;
		boardGrid = new TicTacToe.Marker[boardSize][boardSize];
		initBoard();
	}
	
	private void initBoard() {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				boardGrid[i][j] = null;
			}
		}
	}
	
	public void printBoard() {
		System.out.println("");
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (boardGrid[i][j] == TicTacToe.Marker.CROSS) System.out.print("[X]" + " ");
				else if (boardGrid[i][j] == TicTacToe.Marker.NOUGHT) System.out.print("[O]" + " ");
				else System.out.print("[" + ((i) * boardSize  + (j + 1)) + "]" + " "); 
			}
			System.out.println("");
		}
		
	}
	
	public boolean isGameComplete() {
		return gameComplete;
	}
	public int getBoardSize() {
		return boardSize;
	}
	private boolean checkComplete(int row, int col, TicTacToe.Marker marker) {
		boolean complete = false;
		//check row
		for (int i = 0; i < boardSize; i++) {
			if (boardGrid[i][col] == marker) {
				complete = true;
			} else {
				complete = false;
				break;
			}
		} 
		//check column
		if (!complete) {
			for (int i = 0; i < boardSize; i++) {
				if (boardGrid[row][i] == marker) {
					complete = true;
				} else {
					complete = false;
					break;
				}
			} 
		} 
		//check main diag
		if (!complete) {
			for (int i=0; i<boardSize; i++) {
				if (boardGrid[i][i] == marker) {
					complete = true;
				} else {
					complete = false;
					break;
				}
			} 
		} 
		//check reverse diag
		if (!complete) {
			for (int i=0; i < boardSize; i++) {
				if (boardGrid[i][(boardSize-1) - i] == marker) {
					complete = true;
				} else {
					complete = false;
					break;
				}
			} 
		} 
		
		return complete;
		
	}
	public boolean markCell(Move move) {
		if (move==null) {
			System.out.println("Invalid Input");
			return false;
		} else if (move.end) {		
				gameComplete = true;
				System.out.println("Thank You !"); 
		}
		return markCell(move.row, move.col, move.marker);
	}
	private boolean markCell(int row, int col, TicTacToe.Marker marker) {
		boolean cellMarked=false;
		if (!gameComplete) {
			if (boardGrid[row][col]==null) {
				boardGrid[row][col] = marker;
				cellMarked = true;
				gameComplete = checkComplete(row, col, marker);
			}
		}
		return cellMarked;
	}
}

interface Player {
	public Move nextMove(Board board);
}

class HumanPlayer implements Player {

	private final String name;
	private final TicTacToe.Marker marker;
	
	public  HumanPlayer(String name, TicTacToe.Marker marker) {
		this.name = name;
		this.marker = marker;
	}

	public String getName() {
		return name;
	}

	@Override
	public Move nextMove(Board board) {
		board.printBoard();
		String cellSel = ReadConsoleSystem.readInput("select cell:");
		if (cellSel != null && cellSel.equalsIgnoreCase("q")) {
			Move next = Move.getEndMove();
			return next;
		}
		int cell = 0;
		try {
			cell = Integer.parseInt(cellSel);
		} catch (Exception e) {
			return null;
		}
		int row = (cell-1)/board.getBoardSize();
		int col = (cell-1)%board.getBoardSize();
		Move next = Move.getMove(row, col, marker);
		return next;
	}

	@Override
	public String toString() {
		return "HumanPlayer [name=" + name + ", marker=" + marker + "]";
	}
			
}

class Move {
	
	TicTacToe.Marker marker;
	int row;
	int col;
	boolean end = false;
	
	public static Move getMove(int row, int col, TicTacToe.Marker marker) {
		return new Move(row, col, marker);
	}
	
	public static Move getEndMove() {
		Move move = new Move(-1, -1, null);
		move.end = true;
		return move;
	}
	
	private Move(int row, int col, TicTacToe.Marker marker) {
		this.row = row;
		this.col = col;
		this.marker = marker;
	}

	
}

class ReadConsoleSystem {
	 
	public static String readInput(String message) {
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
