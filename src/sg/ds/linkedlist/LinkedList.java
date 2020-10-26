package sg.ds.linkedlist;

import sg.ds.linkedlist.problems.AddLinkedLists;
import sg.ds.linkedlist.problems.RemoveDuplicates;
import sg.ds.linkedlist.problems.ReverseLinkedList;
import sg.ds.linkedlist.problems.SwapNodes;

import java.util.Objects;

public class LinkedList {
	public static class Node {
		private Integer data;
		private Node next;

		public Node(Integer data) {
			this.data = data;
		}

		public Node(Integer data, Node next) {
			this.data = data;
			this.next = next;
		}

		public Integer getData() {
			return data;
		}

		public Node getNext() {
			return next;
		}

		public void setData(Integer data) {
			this.data = data;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Node node = (Node) o;
			return data.equals(node.data);
		}

		@Override
		public int hashCode() {
			return Objects.hash(data);
		}

		@Override
		public String toString() {
			return "[" + data + "]";
		}
	}

	private Node root;

	public boolean isEmpty() {
		return (root == null);
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public static LinkedList buildRandomList(int numNodes) {
		LinkedList linkedList = new LinkedList();
		Node node = new Node((int) (Math.random() * 100) % 100);
		linkedList.setRoot(node);

		for (int i = 1; i < numNodes; i++) {
			node.next = new Node((int) (Math.random() * 100) % 100);
			node = node.next;
		}

		return linkedList;
	}

	public static LinkedList buildListFromString(String values) {
		String[] valArr = values.split(" ");
		LinkedList linkedList = new LinkedList();
		Node ret = new Node(Integer.parseInt(valArr[0]));
		linkedList.root = ret;
		for (int i = 1; i < valArr.length; i++) {
			ret.next = new Node(Integer.parseInt(valArr[i]));
			ret = ret.next;
		}
		return linkedList;
	}

	public void printList() {
		Node tmp = this.root;
		while (tmp != null) {
			System.out.print(tmp);
			if (tmp.next != null) {
				System.out.print(" -> ");
			}
			tmp = tmp.next;
		}
		System.out.println();
	}

	public void add(int data) {
		Node node = new Node(data);
		if (root == null) {
			root = node;
			return;
		}
		Node curr = root;
		while (curr.next != null) {
			curr = curr.next;
		}
		curr.setNext(node);
	}

	public void insertSorted(int data) {

		Node node = new Node(data);
		if (root == null) {
			root = node;
			return;
		}

		Node prev = null;
		Node curr = root;

		while (curr != null) {
			if (curr.data >= node.data) {
				if (prev == null) {
					node.next = root;
					root = node;
				} else {
					prev.next = node;
					node.next = curr;
				}
				return;
			}
			prev = curr;
			curr = curr.next;
		}
		prev.next = node;
		node.next = null;
	}

	public static void main(String[] args) {
		LinkedList list1;
		LinkedList list2;

		System.out.println("Reverse list functions");
		list1 = LinkedList.buildRandomList(5);
		list1.printList();

		ReverseLinkedList.reverse(list1);
		list1.printList();

		list1 = ReverseLinkedList.reverseRec(list1);
		list1.printList();

		System.out.println();

		 list1 = LinkedList.buildListFromString("1 2 3 4 5");
		 list1.printList();

		 SwapNodes.swapNodesByData(list1, 2, 4);
		 list1.printList();

		list1 = LinkedList.buildListFromString("1 2 3 4 5");
		SwapNodes.swapNodesByData(list1,2, 3);
		list1.printList();

		list1 = LinkedList.buildListFromString("1 2 3 4 5");
		SwapNodes.swapNodesByData(list1,2, 2);
		list1.printList();

		System.out.println("Remove Duplicates");
		list1 = LinkedList.buildListFromString("1 2 2 4 5");
		list1.printList();
		list1 = RemoveDuplicates.removeDuplicatesUnsorted(list1);
		list1.printList();

		System.out.println("Remove Duplicates");
		list1 = LinkedList.buildListFromString("1 2 2 2 2");
		list1.printList();
		list1 = RemoveDuplicates.removeDuplicatesUnsorted(list1);
		list1.printList();

		System.out.println("Remove Duplicates");
		list1 = LinkedList.buildListFromString("2 2 2 2 2 1");
		list1.printList();
		list1 = RemoveDuplicates.removeDuplicatesUnsorted(list1);
		list1.printList();

		System.out.println("Remove Duplicates");
		list1 = LinkedList.buildListFromString("1 2 2 4 5");
		list1.printList();
		list1 = RemoveDuplicates.removeDuplicatesUnsortedNoHash(list1);
		list1.printList();

		System.out.println("Remove Duplicates");
		list1 = LinkedList.buildListFromString("1 2 2 2 2");
		list1.printList();
		list1 = RemoveDuplicates.removeDuplicatesUnsortedNoHash(list1);
		list1.printList();

		System.out.println("Remove Duplicates");
		list1 = LinkedList.buildListFromString("2 2 2 2 2 1");
		list1.printList();
		list1 = RemoveDuplicates.removeDuplicatesUnsortedNoHash(list1);
		list1.printList();


		System.out.println("Add lists");
		list1 = LinkedList.buildListFromString("2 2 2 2 2 1");
		list2 = LinkedList.buildListFromString("2 2 2 2 2 1");
		list1.printList(); list2.printList();
		AddLinkedLists.add(list1, list2).printList();

		list1 = LinkedList.buildListFromString("1 9 9");
		list2 = LinkedList.buildListFromString("9 9");
		list1.printList(); list2.printList();
		AddLinkedLists.add(list1, list2).printList();
	}
}
