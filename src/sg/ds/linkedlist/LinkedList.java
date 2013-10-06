package sg.ds.linkedlist;

public class LinkedList {
	class Node {
		int data;
		Node next;

		public Node() {

		}

		public Node(int data) {
			this.data = data;
		}

		@Override
		public String toString() {
			return "[" + data + "]";
		}
	}

	private Node root;

	public Node buildRandomList(int numNodes) {
		Node ret = new Node();
		root = ret;
		ret.data = (int) (Math.random() * 100) % 100;
		for (int i = 1; i < numNodes; i++) {
			ret.next = new Node();
			ret.next.data = (int) (Math.random() * 100) % 100;
			ret = ret.next;
		}

		return ret;
	}

	public void printList() {
		Node tmp = root;
		// System.out.println("");
		while (tmp != null) {
			System.out.print(tmp);
			if (tmp.next != null) {
				System.out.print(" -> ");
			}
			tmp = tmp.next;
		}
		System.out.println("");
	}

	public void buildListFromString(String values) {
		String[] valArr = values.split(" ");
		Node ret = new Node(Integer.parseInt(valArr[0]));
		root = ret;
		for (int i = 1; i < valArr.length; i++) {
			ret.next = new Node(Integer.parseInt(valArr[i]));
			ret = ret.next;
		}
	}

	public void reverseRec() {
		reverseRec(null, root);
	}

	private void reverseRec(Node prev, Node curr) {
		if (curr == null && prev != null) {
			root = prev;
			return;
		}

		Node temp = curr.next;
		curr.next = prev;
		prev = curr;
		curr = temp;

		reverseRec(prev, curr);

	}

	public void reverse() {

		Node prev = null;
		Node node = root;

		while (node != null) {
			Node temp = node.next;
			node.next = prev;
			prev = node;
			node = temp;
		}

		root = prev;
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

	public void swapNodesByData(int data1, int data2) {
		Node prev1 = null;
		Node prev2 = null;

		Node tmp = root;
		while (tmp != null) {
			if (tmp.data == data1) {
				break;
			}
			prev1 = tmp;
			tmp = tmp.next;
		}

		tmp = root;
		while (tmp != null) {
			if (tmp.data == data2) {
				break;
			}
			prev2 = tmp;
			tmp = tmp.next;
		}

		Node node1 = prev1.next;
		Node node2 = prev2.next;

		Node node1Next = node1.next;
		System.out.println("node1Next: " + node1Next);

		prev1.next = node2;
		prev2.next = node1;

		node1Next = node1.next;
		System.out.println("node1Next: " + node1Next);

		node1.next = node2.next;
		node2.next = node1Next;

	}

	public static void main(String[] args) {
		LinkedList list1 = new LinkedList();
		list1.buildRandomList(5);
		list1.printList();

		list1.reverse();
		list1.printList();

		list1.reverseRec();
		list1.printList();

		// list1.buildListFromString("1 2 3 4 5");
		// list1.printList();
		//
		// list1.swapNodesByData(2, 4);
		// list1.printList();
		//
		// list1.buildListFromString("1 2 3 4 5");
		// list1.swapNodesByData(2, 3);
		// list1.printList();
		//
		// list1.buildListFromString("1 2 3 4 5");
		// list1.swapNodesByData(2, 2);
		// list1.printList();

	}
}
