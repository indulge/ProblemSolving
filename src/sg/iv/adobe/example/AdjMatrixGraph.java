package sg.iv.adobe.example;
import java.util.LinkedList;
import java.util.Queue;


public class AdjMatrixGraph {
	
	public static boolean[] BFS(boolean[][] adjacencyMatrix, int vertexCount, int givenVertex){
	      // Result array.
	      boolean[] mark = new boolean[vertexCount];

	      Queue<Integer> queue = new LinkedList<Integer>();
	      queue.add(givenVertex);
	      mark[givenVertex] = true;

	      while (!queue.isEmpty())
	      {
	        Integer current = queue.remove();

	        for (int i = 0; i < vertexCount; ++i)
	            if (adjacencyMatrix[current][i] && !mark[i])
	            {
	                mark[i] = true;
	                queue.add(i);
	            }
	      }

	      return mark;
	  }


	  public static void main(String[] args) {
	      // Given adjacencyMatrix[x][y] if and only if there is a path between x and y.
	      boolean[][] adjacencyMatrix = new boolean[][]
	              {
	                      {false,true,false,false,false},
	                      {true,false,false,true,true},
	                      {false,false,false,false,false},
	                      {true,false,false,false,false},
	                      {true,false,false,false,false}
	              };
	      // Mark[i] is true if and only if i belongs to the same connected component as givenVertex vertex does.
	      boolean[] mark = BFS(adjacencyMatrix, 5, 0);

	      for (int i = 0; i < 5; ++i)
	          System.out.println(i+": "+mark[i]);
	  }
}
