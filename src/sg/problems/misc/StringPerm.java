package sg.problems.misc;

public class StringPerm {
	//private static StringBuilder sb = new StringBuilder();
	
	public static void printPermString(String str) {
		StringBuilder sb = new StringBuilder();
		sb.append(str);
		printPermString(sb,0,sb.length());
		
	}
	
	private static void printPermString(StringBuilder sb, int start, int end) {
//		System.out.println("sb in: "+sb.substring(start, end));
		if (end - start == 1) {
			System.out.println(sb);
			return;
		} else {
			for (int i = start ; i < end; i++ ) {
				char temp = sb.charAt(i);
				sb.setCharAt(i, sb.charAt(start));
				sb.setCharAt(start, temp);
				
//				System.out.println("fixing: "+temp+ " " + sb.substring(start, end));
				printPermString(sb, start + 1, end);
				
				temp = sb.charAt(i);
				sb.setCharAt(i, sb.charAt(start));
				sb.setCharAt(start, temp);

			}
		}
		
	}
	
	public static void main(String[] args) {
		printPermString("abc");
	}
}
