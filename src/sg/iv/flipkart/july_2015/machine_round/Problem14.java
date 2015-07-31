package sg.iv.flipkart.july_2015.machine_round;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

//14 -Input :
//List of edges are given in the format (source, destination) –> (s1,d1) (s2,d2)…
//There are some error codes with priority.
//1 –Loop
//2 –Multiple roots
//3 –More than two children
//19
//Sample
//Questions
//Personal Interview I / Machine Coding Round Questions :-
//Output :
//Indicate the error (considering the priority) in case of any error and exit the program.
//Print the tree structure in the bracket notation in case of no errors.
//(e.g. (A(B(D)(E))(C(F)(G))) .. Here A is root. B and C are children of A. D and E are children of B. F and G are children of C.)


//todo: check loop, check more than 2 children, pring bfs, multiple roots

public class Problem14 {
	private static class Node<T> {
		public final T data;
		public final List<Node<T>> nextNodes = new ArrayList<>();

		public Node(T data) {
			super();
			this.data = data;
		}
		
		public Node<T> getLeft() {
			if (nextNodes.size()>=1) 
				return nextNodes.get(0);
			else 
				return null;
		}
		
		public Node<T> getRight() {
			if (nextNodes.size()>=2) 
				return nextNodes.get(1);
			else 
				return null;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((data == null) ? 0 : data.hashCode());
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
			Node<?> other = (Node<?>) obj;
			if (data == null) {
				if (other.data != null)
					return false;
			} else if (!data.equals(other.data))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Node [" + data + "]";
		}

		// @Override
		// public int compareTo(String data) {
		// //if (data==null && this.data==null) return 0;
		// return data.compareTo((String) this.data);
		// }

	}

	public static List<Node<String>> buildDirectedGraphFromStatements() {

		Set<String> statements = new HashSet<>();
		
//		statements.add("E0 is taller that E1");
//		statements.add("E0 is taller that E2");
//		statements.add("E0 is taller that E5");
//		statements.add("E1 is taller that E4");
//		statements.add("E3 is taller that E2");
//		statements.add("E3 is taller that E4");
//		statements.add("E3 is taller that E5");
//		statements.add("E3 is taller that E6");
//		statements.add("E5 is taller that E2");
//		statements.add("E6 is taller that E0");
//		statements.add("E5 is taller that E4");
		
		statements.add("A,B");
		statements.add("A,C");
		statements.add("B,D");
		statements.add("B,E");
		statements.add("C,F");
		statements.add("C,G");

//		statements.add("A,B");
//		statements.add("A,C");
		
		String forward = ",";
		String backward = "|";
		int entity1Length = 1;
		int entity2Length = 1;
		
		
		
		// build a dag
		List<Node<String>> adjList = new ArrayList<>();
		for (String st : statements) {
			String node1Name = st.substring(0, entity1Length);
			String node2Name = st.substring(st.length()-entity2Length, st.length());

			Node<String> node1 = new Node<>(node1Name);
			Node<String> node2 = new Node<>(node2Name);
			
			if (!adjList.contains(node1)) {
				adjList.add(node1);
			} else {
				node1 = adjList.get(adjList.indexOf(node1));
			}
			if (!adjList.contains(node2)) {
				adjList.add(node2);
			} else {
				node2 = adjList.get(adjList.indexOf(node2));
			}

			if (st.contains(forward)) {
				node1.nextNodes.add(node2);
			} else if (st.contains(backward)) {
				node2.nextNodes.add(node1);
			} else {
				throw new RuntimeException("");
			}
		}
		return adjList;

	}
	
	
	
	public static <T> void printFullPar(Node<T> root) {
		if (root == null) {
			return;
		}
		System.out.print("("+root.data);
		printFullPar(root.getLeft());
		printFullPar(root.getRight());
		System.out.print(")");
	}
	public static <T> void sortTopological(List<Node<T>> adjList, Stack<Node<T>> sorted) {
		Map<Node<?>, Integer> track = new HashMap<>();
		
		for (Node<T> node:adjList) {
			if (track.get(node) == null) {
				sortTopologicalRec(node, track, sorted);
				// track.put(node, 1);
				// System.out.print(node+"->");

			}
		}
		
	}
	private static <T> void sortTopologicalRec(Node<T> node, Map<Node<?>, Integer> track, Stack<Node<T>> sorted) {
		
			if (track.get(node) == null) {
				track.put(node, 1);
				//if (node.nextNodes.isEmpty()) System.out.println("");
				for(Node<T> node1:node.nextNodes) {
					if (track.get(node1) == null) {
						sortTopologicalRec(node1, track, sorted);
					}
				}
			}
			//sorted.add(node);
			sorted.push(node);
			//System.out.print(node + "->");		
	}
	
	public static void main(String[] args) {
		List<Node<String>> adjList = buildDirectedGraphFromStatements();
		Stack<Node<String>> sorted = new Stack<>();
		sortTopological(adjList, sorted);
		System.out.println("");
		System.out.println("sorted:"+sorted);
		Node<String> root = sorted.pop();
		System.out.println("top:"+root);
		
		
		System.out.println("");
		printFullPar(root);
//		(A(B(D))(E))(C(F))(G))
		System.out.println("");
		
		BTreePrinter.printNode(root);
	}
	
	
	private static class BTreePrinter {

		public static <T> void printNode(Node<T> root) {
			int maxLevel = BTreePrinter.maxLevel(root);

			printNodeInternal(Collections.singletonList(root), 1, maxLevel);
		}

		private static <T> void printNodeInternal(List<Node<T>> nodes, int level, int maxLevel) {
			if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
				return;

			int floor = maxLevel - level;
			int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
			int firstSpaces = (int) Math.pow(2, (floor)) - 1;
			int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

			BTreePrinter.printWhitespaces(firstSpaces);

			List<Node<T>> newNodes = new ArrayList<Node<T>>();
			for (Node<T> node : nodes) {
				if (node != null) {
					System.out.print(node.data);
					newNodes.add(node.getLeft());
					newNodes.add(node.getRight());
				} else {
					newNodes.add(null);
					newNodes.add(null);
					System.out.print(" ");
				}

				BTreePrinter.printWhitespaces(betweenSpaces);
			}
			System.out.println("");

			for (int i = 1; i <= endgeLines; i++) {
				for (int j = 0; j < nodes.size(); j++) {
					BTreePrinter.printWhitespaces(firstSpaces - i);
					if (nodes.get(j) == null) {
						BTreePrinter.printWhitespaces(endgeLines + endgeLines + i
								+ 1);
						continue;
					}

					if (nodes.get(j).getLeft() != null)
						System.out.print("/");
					else
						BTreePrinter.printWhitespaces(1);

					BTreePrinter.printWhitespaces(i + i - 1);

					if (nodes.get(j).getRight() != null)
						System.out.print("\\");
					else
						BTreePrinter.printWhitespaces(1);

					BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
				}

				System.out.println("");
			}

			printNodeInternal(newNodes, level + 1, maxLevel);
		}

		private static void printWhitespaces(int count) {
			for (int i = 0; i < count; i++)
				System.out.print(" ");
		}

		private static <T> int maxLevel(Node<T> node) {
			if (node == null)
				return 0;

			return Math.max(BTreePrinter.maxLevel(node.getLeft()),
					BTreePrinter.maxLevel(node.getRight())) + 1;
		}

		private static <T> boolean isAllElementsNull(List<T> list) {
			for (Object object : list) {
				if (object != null)
					return false;
			}

			return true;
		}

	}
}
