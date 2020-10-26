package sg.ds.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class LinkedListAE {
    // Feel free to add new properties and methods to the class.
    static
    class Program {
        static class DoublyLinkedList {
            public Node head;
            public Node tail;

            private Node detachNode(Node node) {
                assert node != null;
                System.out.println("Detach node: " + node);
                if (node == tail) {
                    tail = node.prev;
                }
                if (node == head) {
                    head = node.next;
                }

                //detach node;
                if (node.prev != null) {
                    node.prev.next = node.next;

                }

                if (node.next != null) {
                    node.next.prev = node.prev;

                }

                node.next = null;
                node.prev = null;
                return node;
            }

            public void setHead(Node node) {
                System.out.println("Set Head: " + node);
                System.out.println("Current Head: " + head);
                node = detachNode(node);
                System.out.println("Detached: " + node);

                node.next = head;
                if (node.next != null) node.next.prev = node;
                head = node;
                if (tail == null) tail = node;
            }


            // public void setHead(Node node) {
            // if (head == null) {
            // 			 head = node;
            // 			 tail = node;
            // 		 }
            // 		insertBefore(head, node);
            // }



            public void setTail(Node node) {
                System.out.println("Set Tail: " + node);
                System.out.println("Current Tail: " + tail);
                node = detachNode(node);
                System.out.println("Detached: " + node);

                node.prev = tail;
                System.out.println("Node: " + node);
                System.out.println("Node.prev: " + node.prev);
                if (node.prev != null) {
                    System.out.println("Node.prev.next: " + node.prev.next);
                    node.prev.next = node;
                    System.out.println("Node.prev.next: " + node.prev.next);
                }

                tail = node;
                System.out.println("New Tail: " + tail);
                if (head == null) head = node;
            }

            public void insertBefore(Node node, Node nodeToInsert) {
                assert nodeToInsert != null;
                assert node != null;

                nodeToInsert = detachNode(nodeToInsert);
                if (node == head) {
                    head = nodeToInsert;
                }

                nodeToInsert.next = node;
                nodeToInsert.prev = node.prev;
                if (node.prev != null) node.prev.next = nodeToInsert;
                node.prev = nodeToInsert;
            }

            public void insertAfter(Node node, Node nodeToInsert) {
                System.out.println("insertAfter: " + node + " to Insert: " + nodeToInsert);
                assert nodeToInsert != null;
                assert node != null;

                nodeToInsert = detachNode(nodeToInsert);
                if (node == tail) {
                    tail = nodeToInsert;
                }

                nodeToInsert.next = node.next;
                nodeToInsert.prev = node;
                if (node.next != null) node.next.prev = nodeToInsert;
                node.next = nodeToInsert;
            }

            public void insertAtPosition(int position, Node nodeToInsert) {
                assert position >= 1;

                int counter = 1;
                Node curr = head;
                while (counter < position && curr != null) {
                    counter++;
                    curr = curr.next;
                }
                if (curr == head) {
                    setHead(nodeToInsert);
                } else if (curr == null) {
                    setTail(nodeToInsert);
                } else {
                    insertBefore(curr, nodeToInsert);
                }
            }

            public void removeNodesWithValue(int value) {
                assert head != null;

                System.out.println("Remove value:" + value);
                List<Node> nodesToRemove = new ArrayList<>();
                Node curr = head;
                while (curr != null) {

                    if (curr.value == value) {
                        Node nodeToRemove = curr;
                        curr = curr.next;
                        remove(nodeToRemove);
                    } else {
                        curr = curr.next;
                    }

                }

            }

            public void remove(Node node) {
                System.out.println("Remove:" + node);
                detachNode(node);
            }

            public String printList() {
                StringBuilder list = new StringBuilder();
                list.append("\n~B~\n");
                list.append("[");
                if (head == null) {
                    list.append("Empty List");
                }
                Node curr = head;
                while (curr != null) {
                    list.append(curr.value);
                    if (curr.next != null && curr.next.prev != null) {
                        list.append("<->");
                    } else if (curr.next != null) {
                        list.append("->");
                    }
                    curr = curr.next;
                }
                list.append("]");
                if (head != null)
                    list.append("[H:").append(head.value).append("]");
                else
                    list.append("[H:NULL]");

                if (tail != null)
                    list.append("[T:").append(tail.value).append("]");
                else
                    list.append("[T:NULL]");
                list.append("\n~E~\n");

                System.out.println(list.toString());
                return list.toString();

            }


            public boolean containsNodeWithValue(int value) {
                Node curr = head;
                while (curr != null) {
                    if (curr.value == value) return true;
                    curr = curr.next;
                }
                return false;
            }
        }

        // Do not edit the class below.
        static class Node {
            public int value;
            public Node prev;
            public Node next;

            public Node(int value) {
                this.value = value;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "value=" + value +
                        '}';
            }
        }
    } //end of program
}
