package sg.ds.tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LinkedTries {
	
	private static final int NODE_PRINT_SIZE = 3;
	class TrieNode {
		String data;
		int spaces = 0;
		List<TrieNode> nextNodes = new ArrayList<TrieNode>();
		
		public TrieNode() {
			initTrie("");
		}
		public TrieNode(String data) {
			initTrie(data);
		}
		
		private void initTrie(String data) {
			this.data = data;
			this.nextNodes = new ArrayList<TrieNode>();
		}
		
		@Override
		public String toString() {
			//return "[d: " + data + " s: " + spaces + "]";
			return "[" + data + "]";
		}
	}
	
	TrieNode root;
	
	public LinkedTries() {
		root = new TrieNode("ROOT_NODE");
	}
	
	public void buildTrie(String stringSet) {
		root = new TrieNode("ROOT_NODE");
		String[] strArr = stringSet.split(" ");
		for (int i=0;i<strArr.length;i++) {
			insertString(strArr[i]);
		}
	}
	
	public void printTrie() {
		Queue<TrieNode> parseQ = new LinkedList<TrieNode>();
		parseQ.addAll(root.nextNodes);
		parseQ.add(new TrieNode("NEW_LINE_CODE"));
		boolean lastPrintNewLine = false;
		boolean spacesPrinted = false;
		while(!parseQ.isEmpty()) {
			TrieNode tn = parseQ.poll();
			if(tn.data.equals("NEW_LINE_CODE")) {
				System.out.println(""); 
				lastPrintNewLine = true;
				continue;
			}
			spacesPrinted = false;
			if (lastPrintNewLine) {
				char[] spaceArr = new char[tn.spaces];
				Arrays.fill(spaceArr, ' ');
				String spc = new String(spaceArr,0,tn.spaces);
				System.out.print(spc);
				if (spc.length() > 0) {
					System.out.print(" ");
				}
				lastPrintNewLine = false;
				spacesPrinted = true;
				
			}
			 
			System.out.print(tn);  
			if (!lastPrintNewLine || spacesPrinted) {
				System.out.print(" ");
			}
			
			
			
			parseQ.addAll(tn.nextNodes);
			TrieNode testNewLine = parseQ.peek();
			if (testNewLine.data.equals("NEW_LINE_CODE")) {
				parseQ.add(new TrieNode("NEW_LINE_CODE"));
			}
			
		}
	}
	
	public void insertString(String str) {
		insertString(root, str);
	}
	
//	public boolean searchString(String str) {
//		
//	}
	

	private void insertString(TrieNode currNode, String str) {
//		System.out.println("currNode: "+currNode+" str: "+str);
		if(str==null||str.length()<=0) {
			return;
		}
		List<TrieNode> nextNodes =  currNode.nextNodes;
		for (TrieNode tn:nextNodes) {
			if (tn.data.equals(str.substring(0,1))) {
				insertString(tn, str.substring(1));
				return;
			}
		}
		//none of next nodes have current characters, insert string from here on
		for (int i=0;i<str.length();i++) {
			TrieNode tn = new TrieNode(str.substring(i,i+1));
			currNode.nextNodes.add(tn);
			tn.spaces = (currNode.spaces) +  (currNode.nextNodes.size()-1) * NODE_PRINT_SIZE;
			currNode=tn;
		}
		
	}
	
	public static void main(String[] args) {
		LinkedTries lt = new LinkedTries();
		lt.buildTrie("hello bufallo hello1");
//		lt.buildTrie("hello bufallo cello chalo hello1 hello2 hello3");
//		lt.buildTrie("hello1 hello hello aello1 bello11");
		lt.printTrie();
	}
}
