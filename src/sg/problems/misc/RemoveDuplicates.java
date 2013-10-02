package sg.problems.misc;

public class RemoveDuplicates {
	
	//o(n2)
	public static void removeDuplicates(char[] str) {
		if (str==null || str.length < 2) {
			return;
		}
		
		int tail = 1;
		int len = str.length;
		for (int i = 1; i < len ; i++) {
			int j=0;
			for (j = 0; j < i; j++) {
				if (str[j] == str[i]) {
					break;
				}
			}
			if (j == i) {
				str[tail] = str[i];
				tail++;
			}
		}
		//str[tail] = 0;
		
		String res = new String (str, 0, tail);
		System.out.println("RemoveDuplicates: "+res);
	}
	
	//o(n)
	public static void removeDuplicates2(char[] str) {
		
		if (str==null || str.length < 2) {
			return;
		}
		
		int tail = 1;
		boolean[] exists = new boolean[256];
		exists[str[0]] = true;
		
		for (int i = 1; i < str.length; i++) {
			if (!exists[str[i]]) {
				exists[str[i]] = true;
				str[tail] = str[i];
				tail ++;
			}
		}
		String res = new String (str, 0, tail);
		System.out.println("removeDuplicates2: "+res);
	}
	public static void main(String[] args) {
		char[] str = {'a', 'b', 'c', 'd'};
		char[] str1 = {'a', 'b', 'c', 'd', 'a', 'b', 'c', 'd'};
		char[] str2 = {'b', 'b', 'b', 'b', 'a', 'a', 'a', 'a'};
		RemoveDuplicates.removeDuplicates(null);
		RemoveDuplicates.removeDuplicates(str);
		RemoveDuplicates.removeDuplicates(str1);
		RemoveDuplicates.removeDuplicates(str2);
		
		RemoveDuplicates.removeDuplicates2(null);
		RemoveDuplicates.removeDuplicates2(str);
		RemoveDuplicates.removeDuplicates2(str1);
		RemoveDuplicates.removeDuplicates2(str2);
	}
}
