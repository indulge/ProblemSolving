package sg.iv.flipkart.july_2015.machine_round;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

//5-Given 1000 elephant ,none of whom exact heights are known
//, there are statements given which will be of two forms
//3.i-E_i is taller than E_jOR3.ii-E_i is smaller than E_j
//Calculate the ascending order of the elephants(in terms of height).

//6 -Topologically sort the DAG(excluding forest arrangement) given if the source is not known.
//For Ex: if edges are 1->2,1->3,2->4,3->4 .

public class Problem5_6 {
	private static class Node<T> {
		public final T data;
		public final List<Node<T>> nextNodes = new ArrayList<>();
		public Node(T data) {
			super();
			this.data = data;
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
		
//		@Override
//		public int compareTo(String data) {
//			//if (data==null && this.data==null) return 0;
//			return  data.compareTo((String) this.data); 
//		}
		
	}
	public static void main(String[] args) {
		Set<String> statements = new HashSet<>();
//		statements.add("E1 is taller that E3");
//		statements.add("E3 is taller that E2");
//		statements.add("E2 is taller that E1");
		
//		statements.add("E1 is taller that E2");
//		statements.add("E2 is taller that E3");
//		statements.add("E3 is taller that E4");
//		statements.add("E5 is taller that E1");
		
		statements.add("E0 is taller that E1");
		statements.add("E0 is taller that E2");
		statements.add("E0 is taller that E5");
		statements.add("E1 is taller that E4");
		statements.add("E3 is taller that E2");
		statements.add("E3 is taller that E4");
		statements.add("E3 is taller that E5");
		statements.add("E3 is taller that E6");
		statements.add("E5 is taller that E2");
		statements.add("E6 is taller that E0");
		statements.add("E5 is taller that E4");

		//build a dag
		List<Node<String>> adjList = new ArrayList<>();
		for (String st:statements) {
			String node1Name = st.substring(0,2);
			int i2 = st.lastIndexOf("E");
			String node2Name = st.substring(i2,i2+2);
			
			Node<String> node1=new Node<>(node1Name);
			Node<String> node2=new Node<>(node2Name);
			if (!adjList.contains(node1)) {
				adjList.add(node1);
			} else {
				node1=adjList.get(adjList.indexOf(node1));
			}
			if (!adjList.contains(node2)) {
				adjList.add(node2);
			} else {
				node2=adjList.get(adjList.indexOf(node2));
			}
			
			
			if (st.contains("taller")) {
				node1.nextNodes.add(node2);
			} else if (st.contains("smaller")) {
				node2.nextNodes.add(node1);
			} else {
				throw new RuntimeException("");
			}
		}
		
		printDFS(adjList);
		System.out.println("--------------");
		printDFSStack(adjList);
//		printTopologicalSort(adjList);
		System.out.println("------Topological sort rec--------");
		printTopologicalRec(adjList);
		
	}
	
//	private static  <T> void printTopologicalSort(List<Node<T>> adjList) {
//		//1=in process, 2=processed
//		Map<Node<?>, Integer> track = new HashMap<>();
//		Stack<Node<?>> stk = new Stack<>();
//		int i = 0;
//		for (Node<T> node:adjList) {
//			if (node.nextNodes.size() > 0) {
//				Node<?> nodei = node.nextNodes.get(0);
//				while (track.get(nodei) == null) {
//					
//				}
//			}
//			track.put(node, 1);
//			stk.push(node);
//			System.out.println("");
//		}
//		
////		do {
////			Node<?> node = adjList.get(i);
////			
////			if (track.get(node) == null) track.put(node, 1);
////			else {
////				i++;
////				continue;
////			}
////			
////			
////			if (!node.nextNodes.isEmpty()) {
////				for(Node<?> nodei:node.nextNodes) {
////					if (track.get(nodei) == null) {
////						track.put(nodei, 1);
////						stk.push(nodei);
////					} else {
////						continue;
////					}
////				}
////			}
////			stk.push(node);
////			i++;
////		} while (i < adjList.size());
//		System.out.println("\nSorted items:");
//		System.out.println(stk);
//	}
	
	
//	private static <T> void printTopologicalSort(List<Node<T>> adjList) {
//		Stack<Node<?>> stk = new Stack<>();
//		Stack<Node<?>> sortedStack = new Stack<>();
//
//		for (Node<T> node : adjList) {
//			Map<Node<?>, Integer> track = new HashMap<>();
////			System.out.print(node + "->");
////			track.put(node, 2);
//			// if (track.get(node) == null) {
//			// track.put(node, 1);
//			do {
//				for (Node<?> nodej : node.nextNodes) {
//					if (track.get(nodej) == null) {
//						track.put(nodej, 1);
//						stk.push(nodej);
//					}
//				}
//			} while (!stk.isEmpty());
//			
//			stk.push(node);
//			while (!stk.isEmpty()) {
//				Node<?> nodei = stk.pop();
////				if (track.get(nodei) < 2) {
////					System.out.print(nodei + "->");
////					track.put(nodei, 2);
////				}
//				for (Node<?> nodej : nodei.nextNodes) {
//					if (track.get(nodej) == null) {
//						track.put(nodej, 1);
//						stk.push(nodej);
//					}
//				}
//				track.put(nodei, 2);
//				sortedStack.push(nodei);
//				System.out.print(nodei + "->");
//			}
//			System.out.println("");
//		}
//	}
	
	
	private static <T> void printDFSStack(List<Node<T>> adjList) {
		Stack<Node<?>> stk = new Stack<>();
		
		for (Node<T> node:adjList) {
			Map<Node<?>, Integer> track = new HashMap<>();
			
			System.out.print(node + "->");
			track.put(node, 2);
//			if (track.get(node) == null) {
//				track.put(node, 1);
				stk.push(node);
				while (!stk.isEmpty()) {
					Node<?> nodei = stk.pop();
					if (track.get(nodei) < 2) {
						System.out.print(nodei + "->");
						track.put(nodei, 2);
					}
					for (Node<?> nodej:nodei.nextNodes) {
						if (track.get(nodej) == null) {
							track.put(nodej, 1);
							stk.push(nodej);
						}
					}
				}
//			}
			
			
			System.out.println("");
		}
		
	}
	
	private static <T> void printDFS(List<Node<T>> adjList) {
	
		Map<Node<?>, Integer> track = new HashMap<>();
		for (Node<T> node:adjList) {
			System.out.print(node + "->");
//			printDFSNode(node, track);
			if (track.get(node) == null) {
				track.put(node, 1);
				if (node.nextNodes.isEmpty()) System.out.println("");
				printDFS(node.nextNodes);
			}
			
//			if (track.get(node) == null) {
////				track.put(node, 1);
////				System.out.print(node+"->");
//				
//			
//			}
			
		}
	}
	
	public static <T> void printTopologicalRec(List<Node<T>> adjList) {
		Map<Node<?>, Integer> track = new HashMap<>();
		
		for (Node<T> node:adjList) {
			if (track.get(node) == null) {
				printTopologicalRec(node, track);
				// track.put(node, 1);
				// System.out.print(node+"->");

			}
		}
	}
	private static <T> void printTopologicalRec(Node<T> node, Map<Node<?>, Integer> track) {
		
			if (track.get(node) == null) {
				track.put(node, 1);
				if (node.nextNodes.isEmpty()) System.out.println("");
				for(Node<T> node1:node.nextNodes) {
					if (track.get(node1) == null) {
						printTopologicalRec(node1, track);
					}
				}
			}
			System.out.print(node + "->");		
	}
	

//	private static <T> void printDFSNode(Node<?> node, Map<Node<?>, Integer> track) {
//		if (track.get(node) == null) {
//			track.put(node, 1);
//			System.out.print(node + "->");
//			if (!node.nextNodes.isEmpty()) {
//				printDFSNode(node.nextNodes.get(0), track);
//			}
//		}
//	}
}
