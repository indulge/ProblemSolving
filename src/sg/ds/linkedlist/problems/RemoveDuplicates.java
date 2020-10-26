package sg.ds.linkedlist.problems;

import sg.ds.linkedlist.LinkedList;
import static sg.ds.linkedlist.LinkedList.Node;
import java.util.HashMap;

public class RemoveDuplicates {
    public static LinkedList removeDuplicatesUnsorted(LinkedList list) {
        if (list == null || list.isEmpty()) return null;

        HashMap<LinkedList.Node, Boolean> nodeExists = new HashMap<>();
        Node prev = list.getRoot();

        nodeExists.put(prev, true);
        Node curr = prev.getNext();
        while (curr != null) {
            if (nodeExists.containsKey(curr)) {
                prev.setNext(curr.getNext());
                curr = curr.getNext();
                continue;
            }
            prev = curr;
            curr = curr.getNext();
            nodeExists.put(prev, true);
        }
        return list;
    }

    public static LinkedList removeDuplicatesUnsortedNoHash(LinkedList list) {
        if (list == null || list.isEmpty()) return null;

        Node curr = list.getRoot();

        while (curr != null) {
            Node next = curr.getNext();
            Node innerPrev = curr;
            while (next != null) {
                if (next.getData() == innerPrev.getData()) {
                    innerPrev.setNext(next.getNext());
                    next = next.getNext();
                    continue;
                }
                innerPrev = next;
                next = next.getNext();
            }
            curr = curr.getNext();
        }

        return list;
    }
}
