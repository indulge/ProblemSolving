package sg.ds.graphs;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class AdjMatrixGraph {
	private final int[][] graphMatrix;
	final int dimension;
	
	public AdjMatrixGraph(int dim) {
		graphMatrix = new int[dim][dim];
		dimension = dim;
	}

	public AdjMatrixGraph(int[][] matrix) {
		this.graphMatrix = matrix;
		this.dimension = matrix.length;
	}
	
	public void setRandom() {
		Random rand = new Random();
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				graphMatrix[i][j] = rand.nextInt(3)%2;	//return 0 or 1
			}
		}
	}
	
	public void setRandomSymmetric() {
		Random rand = new Random();
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				if (i==j) {
					graphMatrix[i][j] = 1;
					continue;
				}
				int val = rand.nextInt(3)%2;	//return 0 or 1
				graphMatrix[i][j] = val;
				graphMatrix[j][i] = val;
			}
		}
	}
	
	public void printMatrix() {
		System.out.println("\n--Begin--");
		System.out.print("  "+"  ");
		for (int i = 0; i < dimension; i++) {
			System.out.print("  "+ (i+1) +"  ");
		}
		System.out.println("");
		for (int i = 0; i < dimension; i++) {
			System.out.print(" "+(i+1)+": ");
			for (int j = 0; j < dimension; j++) {
				System.out.print(" ["+graphMatrix[j][i]+"] ");
			}
			System.out.println("");
		}
		System.out.println("--End--");
	}
	
//	public int[] dijkstra(int source_row, int source_col) {
//		int[] dist = new int[dimention * dimention];
//		int [] pred = new int [dimention * dimention];
//		boolean [] visited = new boolean [dimention * dimention];
//		
//		Arrays.fill(visited, false);
//		Arrays.fill(dist, Integer.MAX_VALUE);
//		Arrays.fill(pred, -1);
//		
//	}
	//takes indexes
	public boolean checkConnectivity(int node1, int node2) {
		
		boolean[] connected = new boolean[dimension];
		Arrays.fill(connected, false);
		
		Queue<Integer> q = new LinkedList<Integer>();
		int idx1 = node1 - 1;
		int idx2 = node2 - 1;
		q.add(idx1);
		while (!q.isEmpty()) {
			Integer idx = q.remove();
			connected[idx] = true;
			for (int i = 0; i < dimension; i++) {
				if (i==idx) continue;
				
				int connVal = graphMatrix[idx][i];
				if (!connected[i] && connVal >= 1) {
					q.add(i);
				}
			}
		}
		
		return connected[idx2];
		
	}
	
//	public static int[][] buildKnightGraph(int startx, int starty) {
//		int BOARD_DIM  = 8;
//		int[][] board = new int[BOARD_DIM][BOARD_DIM];
//		
//		
//		
//	}
	
	public static void main(String[] args) {
//		t.setRandomSymmetric();
//		t.printMatrix();
		
		int[][] mat = {
				{0,0,1,0,0},
				{0,0,0,0,1},
				{0,0,0,1,1},
				{0,0,0,0,0},
				{0,0,0,0,0}
		};

		AdjMatrixGraph t = new AdjMatrixGraph(mat);
		t.printMatrix();
		
		System.out.println(t.checkConnectivity(1, 2));
		
		System.out.println(t.checkConnectivity(1, 4));
		
		System.out.println(t.checkConnectivity(3, 5));
		
		System.out.println(t.checkConnectivity(3, 1));
		
	}
}
