package sg.archive;
//import java.util.Stack;
//
//public class FindPalindrome {
//	public static boolean isPalinDrome(String str) {
//		LinkedList palinList = new LinkedList(str);
//		return palinList.isPalinDrome();
//	}
//	public static void main(String[] args) {
//		//test cases
//		String[] strs = {null, "","a","ab", "aa", "aba","abba"
//				, "abcdabcd", "aaaabbbb", "aaaaaaaaaa", "abcddcba"};
//		for (String str: strs) {
//			System.out.println("String: \""+str+"\" is palindrome: "+isPalinDrome(str));
//		}
//		
//	}
//}
//
//class LinkedList {
//	private Node root;
//	public LinkedList(String str) {
//		if (str==null) {return;}
//		for (int i=0;i<str.length();i++) {
//			Character c  = str.charAt(i);
//			add(new Node(c,null));
//		}
//	}
//	
//	//adds new node at end of current linked list
//	public void add(Node node) {
//        if (root == null) {
//            root = node;
//            return;
//        }
//        Node temp = root;
//        while (temp.next != null) {
//            temp = temp.next;
//        }
//        temp.next = node;
//        node.next = null;
//    }
//	
//	public boolean isPalinDrome() {
//		//please note that there are 2 while loops, 
//		// but ptr1, will traverse the whole list *only once*
//		//first loop will end when ptr1 is in middle
//		//second will end when prt1 reaches end of list
//		
//		
//		if (root == null) {return false;}
//		if (root.next==null) {return true;}
//		Node ptr1 = root;
//		Node ptr2 = root.next;
//		
//		Stack<Character> stk = new Stack<Character>();
//		
//		//this loop will end when ptr1 is in middle of list
//		while (ptr2!=null) {
//			//push ptr1 to stack
//			stk.push(ptr1.data);
//			ptr2 = ptr2.next;
//			if (ptr2==null) {		//even number of nodes
//				ptr1 = ptr1.next;	//skip to mirror part of palindrome to check against stack
//				break;
//			}
//			ptr2 = ptr2.next;
//			if (ptr2==null) {			//odd number of nodes
//				ptr1 = ptr1.next.next;	//skip to mirror part of palindrome to check against stack
//				break;
//			}
//			
//			ptr1 = ptr1.next;
//		}
//
//		//this loop will check remaining linked list against stack for palindrome property
//		while (ptr1!=null) {
//			
//			Character c = stk.pop();
//			if (!ptr1.data.equals(c)) {
//				return false;
//			}
//			ptr1 = ptr1.next;
//		}
//		return true;
//	}
//	//debug function
//	public void printList(Node n) {	
//		
//        Node temp = n;
//        if (n==null) {
//        	temp = root;
//        }
//        System.out.println("\n---------List----------");
//        while (temp != null) {
//            System.out.print(" -> " + temp.data);
//            temp = temp.next;
//        }
//        System.out.println("\n------------------------");
//    }
//}
//
//
//class Node {
//	//default package visibility
//	Character data;
//	Node next;	
//	
//	public Node(Character data, Node next) {
//		this.data=data;
//		this.next = next;
//	}
//
//	
//	
//}