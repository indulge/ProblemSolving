package oj.uva.problems.id100;

import java.io.IOException;
import java.util.StringTokenizer;

class ID_100 {
	public static void main(String[] args) {
		Main.main(null);
	}
}


class Main {

	private static java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
	
	static String readLine2() {

		
		
		String s = null;
		try {
			
			s = r.readLine();
			//System.out.println("REady"+r.ready()+" : "+s);
			return s;
				//System.out.println(s);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
//			if (r!=null)
//				try {
//					r.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}	
		}
		return s;

	}

	public static void main(String args[]) // entry point from OS
	{
		Main myWork = new Main(); // create a dinamic instance
		myWork.Begin(); // the true entry point
	}

	void Begin() {
		String input;
		StringTokenizer idata;
		int a, b, min, max, num, n, cycle, cyclemax;

		while (((input = Main.readLine2()) != null) && !input.trim().equals("")) {
			idata = new StringTokenizer(input);
			a = Integer.parseInt(idata.nextToken());
			b = Integer.parseInt(idata.nextToken());
			if (a < b) {
				min = a;
				max = b;
			} else {
				min = b;
				max = a;
			}
			for (cyclemax = -1, num = min; num <= max; num++) {
				for (n = num, cycle = 1; n != 1; cycle++)
					if ((n % 2) != 0)
						n = 3 * n + 1;
					else
						n >>= 1;
				if (cycle > cyclemax)
					cyclemax = cycle;
			}
			System.out.println(a + " " + b + " " + cyclemax);
		}
	}
}