package sg.iv.adobe;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class AdjMatrixGraph {
	private int[][] graphMatrix;
	int dimention;
	
	public AdjMatrixGraph(int dim) {
		graphMatrix = new int[dim][dim];
		dimention = dim;
	}
	
	public void setRandom() {
		Random rand = new Random();
		for (int i = 0; i < dimention; i++) {
			for (int j = 0; j < dimention; j++) {
				graphMatrix[i][j] = rand.nextInt(2);	//return 1 or 2
			}
		}
	}
	
	public void setMatrix(int[][] matrix, int dim) {
		this.graphMatrix = matrix;
		this.dimention = dim;
	}
	
	public void setRandomSymmetric() {
		Random rand = new Random();
		for (int i = 0; i < dimention; i++) {
			for (int j = 0; j < dimention; j++) {
				if (i==j) {
					graphMatrix[i][j] = 1;
					continue;
				}
				int val = rand.nextInt(3)%2;	//return 1 or 2
				graphMatrix[i][j] = val;
				graphMatrix[j][i] = val;
			}
		}
	}
	
	public void printMatrix() {
		System.out.println("\n--Begin--");
		System.out.print("  "+"  ");
		for (int i = 0; i < dimention; i++) {
			System.out.print("  "+ (i+1) +"  ");
		}
		System.out.println("");
		for (int i = 0; i < dimention; i++) {
			System.out.print(" "+(i+1)+": ");
			for (int j = 0; j < dimention; j++) {
				System.out.print(" ["+graphMatrix[j][i]+"] ");
			}
			System.out.println("");
		}
		System.out.println("--End--");
	}
	
	public boolean checkConnectivity(int node1, int node2) {
		
		boolean[] connected = new boolean[dimention];
		Arrays.fill(connected, false);
		
		Queue<Integer> q = new LinkedList<Integer>();
		int idx1 = node1 - 1;
		int idx2 = node2 - 1;
		q.add(idx1);
		while (!q.isEmpty()) {
			Integer idx = q.remove();
			connected[idx] = true;
			for (int i = 0; i < dimention; i++) {
				if (i==idx) continue;
				
				int connVal = graphMatrix[idx][i];
				if (!connected[i] && connVal >= 1) {
					q.add(i);
				}
			}
		}
		
		return connected[idx2];
		
	}
	
	public static void main(String[] args) {
		AdjMatrixGraph t = new AdjMatrixGraph(5);
//		t.setRandomSymmetric();
//		t.printMatrix();
		
		int[][] mat = {
				{0,0,1,0,0},
				{0,0,0,0,1},
				{0,0,0,1,1},
				{0,0,0,0,0},
				{0,0,0,0,0}
		};
		
		t.setMatrix(mat, 5);
		t.printMatrix();
		
		System.out.println(t.checkConnectivity(1, 2));
		
		System.out.println(t.checkConnectivity(1, 4));
		
		System.out.println(t.checkConnectivity(3, 5));
		
		System.out.println(t.checkConnectivity(3, 1));
		
	}
}
