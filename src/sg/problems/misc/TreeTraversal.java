package sg.problems.misc;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeTraversal {
	class Node {
		String data;
		boolean sentinal;
		Node left;
		Node right;

		public Node(String data) {
			this.data = data;
			this.sentinal = false;
		}

		public Node(boolean sentinal) {
			this.data = "SENTINAL_NODE";
			this.sentinal = sentinal;
		}

		@Override
		public String toString() {
			return "["+ data + "]";
		}
		
		
	}

	Node root;

	public void buildTree(String input) { // simple utility function to build
											// the sample tree
		String[] dataArr = input.split(" ");
		for (int i = 0; i < dataArr.length; i++) {
			if (root==null) {
				root = new Node(dataArr[i]);
				
			} else {
//				System.out.println("inserting: " + dataArr[i]);
				insertData(root, dataArr[i]);
			}
		}
	}

	private void insertData(Node currNode, String data) {
		// System.out.println("currNode: "+currNode+" str: "+str);
//		System.out.println("currNode: "+currNode);
		if (data == null || data.length() <= 0 || currNode == null) {
			return;
		}

		if (currNode.left == null) {
			currNode.left = new Node(data);

		} else if (currNode.right == null) {
			currNode.right = new Node(data);

		} else {
			int lr = (int) ((Math.random() * 10));
			if (lr % 2 == 0) {
				insertData(currNode.left, data);
			} else {
				insertData(currNode.right, data);
			}
		}

	}

	public void printTreeLevelOrder() {
		Queue<Node> parseQ = new LinkedList<Node>();
		parseQ.add(root);
		parseQ.add(new Node(true));
		Node node = null;
		while (!parseQ.isEmpty()) {
//			System.out.println("Queue: "+parseQ);
			node = parseQ.poll(); // remove top node;
			//System.out.println("Node: "+node);
			if (node.sentinal) {
				System.out.println("");
				continue;
			}

			System.out.print(node);
			System.out.print(" ");

			if (node.left!=null) {
				parseQ.add(node.left);
			} 
			
			if (node.right!=null) {
				parseQ.add(node.right);
			} 

			Node topNode = parseQ.peek();
			if (topNode.sentinal) {
				parseQ.add(new Node(true));
			}
		}

	}

	public void printTreeAlternateLevelOrder() {
		Queue<Node> parseQ = new LinkedList<Node>();
		Stack<Node> stk = new Stack<Node>();
		parseQ.add(root);
		parseQ.add(new Node(true));
		int sentinalCount = 1;
		Node node = null;
		while (!parseQ.isEmpty()) {
			node = parseQ.poll(); // remove top node;
			if (node.sentinal) {
				
				
				if (sentinalCount % 2 == 0) {	// we have been adding this to stack
					while (!stk.isEmpty()) {
						Node n = stk.pop();
						System.out.print(n);
						System.out.print(" ");
					}
					stk.clear();
				}

				System.out.println("");
				sentinalCount += 1;
				continue;
			}

			if (sentinalCount % 2 == 1) {
				System.out.print(node);
				System.out.print(" ");
			} else {
				stk.push(node);
			}
			
			if (node.left!=null) {
				parseQ.add(node.left);
			} 
			
			if (node.right!=null) {
				parseQ.add(node.right);
			} 
			

			Node topNode = parseQ.peek();
			if (topNode.sentinal) {
				parseQ.add(new Node(true));
			}
		}
	}

	public static void main(String[] args) {
		TreeTraversal test = new TreeTraversal();
		test.buildTree("1 2 3 4 5 6 7 8 9");
		System.out.println("Level Order Traversal: ");
		test.printTreeLevelOrder();
		System.out.println("Alternate reverse Level Order Traversal: ");
		test.printTreeAlternateLevelOrder();

	}

}
