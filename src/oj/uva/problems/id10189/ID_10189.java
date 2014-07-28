package oj.uva.problems.id10189;

import java.io.IOException;
import java.util.StringTokenizer;

class ID_10189 {

}

class Token {
	public int[] ia;
	public String sa[];
}

enum TokenType {
	INT2, STRING2, INT1, STRING1, INT1_STRING, STRING_INT

}

class UVAUtil {

	private static java.io.BufferedReader r = new java.io.BufferedReader(
			new java.io.InputStreamReader(System.in));
	Token token;

	static Token readToken(TokenType type) {
		return null;
	}
	
	static void printCharArray(char[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j<arr[0].length;j++) {
				System.out.print(Character.toString(arr[i][j]));
			}
			System.out.println("");
		}
	}
	
	static String readLn () {
		return readLn(1024);
	}
	static String readLn (int maxLg)  // utility function to read from stdin
    {
        byte lin[] = new byte [maxLg];
        int lg = 0, car = -1;
        String line = "";

        try
        {
            while (lg < maxLg)
            {
                car = System.in.read();
                if ((car < 0) || (car == '\n')) break;
                lin [lg++] += car;
            }
        }
        catch (IOException e)
        {
            return (null);
        }

        if ((car < 0) && (lg == 0)) return (null);  // eof
        line = (new String (lin, 0, lg));
        return line;
    }

	static String readLine() {

		String s = null;
		try {

			s = r.readLine();
			// System.out.println("REady"+r.ready()+" : "+s);
			return s;
			// System.out.println(s);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// we should not close System.in
		}
		return s;
	}
}

class Main {
	
	public static void main(String[] args) {
		String input;
		StringTokenizer idata;
		int count = 0;
		while (((input = UVAUtil.readLine()) != null) && !input.trim().equals("")) {
			count++;
//			System.out.println("count ~: "+count);
			idata = new StringTokenizer(input);
			if (input.trim().equals("")) System.exit(0);
//			System.out.println("Input: "+input);
			int rows = Integer.parseInt(idata.nextToken());
			int cols = Integer.parseInt(idata.nextToken());
			if (rows == 0 || cols == 0) System.exit(0);

			char [][] ip = new char[rows][cols];
			for (int i = 0; i < rows; i++) {
				input = UVAUtil.readLine();
				idata = new StringTokenizer(input);
				String mineRow = idata.nextToken();
				for (int j = 0; j< cols;j++) {
					char c = mineRow.charAt(j);
					ip[i][j]=c;
				}
			}
			char [][] op = new char[rows][cols];
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j< cols;j++) {
					char c = ip[i][j];
					if (c=='*')
						op[i][j]=c;
					else {
						//check 8 elems around
						op[i][j]='0';
						
						if (i>0 && j>0 && ip[i-1][j-1] == '*') {
							op[i][j] += 1;
						}
						if (i>0 && ip[i-1][j] == '*') {
							op[i][j] += 1;
						}
						if (i>0 && j<(cols-1) && ip[i-1][j+1] == '*') {
							op[i][j] += 1;
						}
						
						if (j > 0 && ip[i][j-1] == '*') {
							op[i][j] += 1;
						}
						if (j < (cols - 1) && ip[i][j+1] == '*') {
							op[i][j] += 1;
						}
						if (i < (rows - 1) && j > 0 && ip[i+1][j-1] == '*') {
							op[i][j] += 1;
						}
						if (i < (rows - 1) && ip[i+1][j] == '*') {
							op[i][j] += 1;
						}
						if (j < (cols - 1) && i < (rows -1) && ip[i+1][j+1] == '*') {
							op[i][j] += 1;
						}
					}
					
				}
			}
			if (count==1) 
				System.out.format("Field #%d:\n",count);
			else
				System.out.format("\nField #%d:\n",count);
			UVAUtil.printCharArray(op);
//			System.out.println("count =: "+count);
		}
	}
}
