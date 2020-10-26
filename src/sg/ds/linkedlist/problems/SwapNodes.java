package sg.ds.linkedlist.problems;

import sg.ds.linkedlist.LinkedList;

public class SwapNodes {

    public static LinkedList swapNodesByData(LinkedList linkedList, int data1, int data2) {
        LinkedList.Node prev1 = null;
        LinkedList.Node prev2 = null;

        LinkedList.Node tmp = linkedList.getRoot();
        while (tmp != null) {
            if (tmp.getData() == data1) {
                break;
            }
            prev1 = tmp;
            tmp = tmp.getNext();
        }

        tmp = linkedList.getRoot();
        while (tmp != null) {
            if (tmp.getData() == data2) {
                break;
            }
            prev2 = tmp;
            tmp = tmp.getNext();
        }

        LinkedList.Node node1 = prev1.getNext();
        LinkedList.Node node2 = prev2.getNext();

        LinkedList.Node node1Next = node1.getNext();

        prev1.setNext(node2);
        prev2.setNext(node1);

        node1Next = node1.getNext();

        node1.setNext(node2.getNext());
        node2.setNext(node1Next);

        return linkedList;

    }
}
