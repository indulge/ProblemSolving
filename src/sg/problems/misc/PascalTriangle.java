package sg.problems.misc;

public class PascalTriangle {
	
	public static final int MAXN = 10;

	//generates pascals triangle
	public static long[][]  binomial_coefficient(int n, int m)
	
	//int n,m; /* computer n choose m */
	{
	int i,j; /* counters */
	long[][] bc = new long[MAXN][MAXN]; /* table of binomial coefficients */
	for (i=0; i<=n; i++) bc[i][0] = 1;
	for (j=0; j<=n; j++) bc[j][j] = 1;
	for (i=1; i<=n; i++)
	for (j=1; j<i; j++)
	bc[i][j] = bc[i-1][j-1] + bc[i-1][j];
	return( bc );
	}
}
