package sg.iv.amazon.june_2015;

public class VisitMaze {

}


/*

Suppose you are given a puzzle that is represented as a matrix with 0s and 1s, where a 0 indicates youre allowed to move into that position and 1 means youre not allowed to move in that position. Write a function that given a start position and an end position, returns a boolean value indicating if there exists a path from start to end. you are only allowed to move up, left, right and down. Diagonal movement is not allowed.

Example #1 
Input 
0 0 1 0 1 
0 0 0 0 0 
0 1 1 1 1 
0 1 1 0 0 

start: 4,1 
end 0,3 

Output - true 

Example #2 
Input 
0 0 1 1 1 
0 1 0 0 0 
1 1 1 1 1 
0 0 0 0 1 

start: 0,0 
end: 1,2 

Output - false
*/

/*
We can solve it by using a simple flood fill:

public final class Maze {
	private static boolean[][] m_visited;
	private static int[][] m_maze;
	
	public static boolean hasPath(int[][] maze, int startRow, int startCol, int destRow, int destCol){
		if(maze == null || maze.length == 0){
			return false;
		}
		m_visited = new boolean[maze.length][maze[0].length];
		m_maze = maze;
		visit(startRow,startCol);
		if(! m_visited[destRow][destCol]){
			return false;
		}
		return true;
	}

	
	private static void visit(int i, int j) {
		if(i < 0 || i >= m_visited.length || j < 0 || j >= m_visited[0].length || m_maze[i][j] == 1){
			return;
		}
		if(!m_visited[i][j]){
			m_visited[i][j] = true;
			visit(i-1, j); // Move left
			visit(i+1, j); // Move Right
			visit(i, j-1); //Move top
			visit(i, j+1); //Move bottom
		}
	}

	public static void main(String[] args) {
		int[][] maze = {{0, 0, 1, 1, 1},{0, 1, 0, 0, 0}, {1, 1, 1, 1, 1 },{0, 0, 0, 0, 1}};
		System.out.println(hasPath(maze, 0,0,1,2));
	}
}
*/