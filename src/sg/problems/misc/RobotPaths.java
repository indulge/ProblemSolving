package sg.problems.misc;
import java.util.Set;
import java.util.TreeSet;

public class RobotPaths {

//	public static void PrintAllPossiblePathsWithObstacles(int M, int N,
//			Set<Pair<Integer>> obstacles, String path) {
//		if (obstacles.contains(new Pair<Integer>(M, N)))
//			return;
//		else {
//			if (M == 0) {
//				for (int i = 0; i < N; ++i)
//					path = "DOWN " + path;
//				System.out.println(path);
//			} else if (N == 0) {
//				for (int i = 0; i < M; ++i)
//					path = "RIGHT " + path;
//				System.out.println(path);
//			} else {
//				PrintAllPossiblePathsWithObstacles(M - 1, N, obstacles,
//						"RIGHT " + path);
//				PrintAllPossiblePathsWithObstacles(M, N - 1, obstacles, "DOWN "
//						+ path);
//			}
//		}
//	}
	
	public static void main(String[] args) {
		Set<IntPair> obstacles = new TreeSet<IntPair>();
		PrintAllPossiblePathsWithObstacles(2,2,obstacles,"");
		System.out.println(getNumberOfPathsWithObstacles(2,2,obstacles));
	}
	
	public static void PrintAllPossiblePathsWithObstacles(int M, int N, Set<IntPair> obstacles, String path) {
		if (obstacles.contains(new IntPair(M, N)))
			return;
		else {
			if (M == 0) {
				for (int i = 0; i < N; ++i)
					path = "DOWN " + path;
				System.out.println(path);
			} else if (N == 0) {
				for (int i = 0; i < M; ++i)
					path = "RIGHT " + path;
				System.out.println(path);
			} else {
				PrintAllPossiblePathsWithObstacles(M - 1, N, obstacles,
						"RIGHT " + path);
				PrintAllPossiblePathsWithObstacles(M, N - 1, obstacles, "DOWN "
						+ path);
			}
		}
	}
	
	
	public static int getNumberOfPathsWithObstacles(int M, int N,
			Set<IntPair> obstacles) {
		if (obstacles.contains(new IntPair(M, N)))
			return 0;
		else {
			if (M == 0) {
				return 1;
			} else if (N == 0) {
				return 1;
			} else {
				return  getNumberOfPathsWithObstacles(M - 1, N, obstacles)
					   	+ getNumberOfPathsWithObstacles(M, N - 1, obstacles);
			}
		}
	}
}

class IntPair implements Comparable<IntPair> {
	final int x;
	final int y;
	IntPair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntPair other = (IntPair) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
//	@Override
//	public int compareTo(Object obj) {
//		if (this == obj)
//			return 0;
//		if (obj == null)
//			throw new NullPointerException();
//		if (getClass() != obj.getClass())
//			throw new RuntimeException();
//		
//		IntPair other = (IntPair) obj;
//		
//		return ((this.x * this.x + this.y * this.y) - (other.x * other.x + other.y * other.y));
//		
//	}
	@Override
	public int compareTo(IntPair other) {
		return ((this.x * this.x + this.y * this.y) - (other.x * other.x + other.y * other.y));
	}
	
	
	
}