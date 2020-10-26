package sg.ds.linkedlist.problems;

import sg.ds.linkedlist.LinkedList;

public class ReverseLinkedList {

    public static LinkedList reverseRec(LinkedList linkedList) {
        return reverseRec(null, linkedList.getRoot(), linkedList);
    }

    private static LinkedList reverseRec(LinkedList.Node prev, LinkedList.Node curr, LinkedList linkedList) {
        if (curr == null && prev != null) {
            linkedList.setRoot(prev);
            return linkedList;
        }

        LinkedList.Node temp = curr.getNext();
        curr.setNext(prev);
        prev = curr;
        curr = temp;

        return reverseRec(prev, curr, linkedList);
    }

    public static LinkedList reverse(LinkedList linkedList) {

        LinkedList.Node prev = null;
        LinkedList.Node node = linkedList.getRoot();

        while (node != null) {
            LinkedList.Node temp = node.getNext();
            node.setNext(prev);
            prev = node;
            node = temp;
        }

        linkedList.setRoot(prev);
        return linkedList;
    }
}
