package sg.ds.linkedlist.problems;

import sg.ds.linkedlist.LinkedList;
import static sg.ds.linkedlist.LinkedList.Node;

public class AddLinkedLists {
    public static LinkedList add(LinkedList list1, LinkedList list2) {
        Node node1 = list1.getRoot();
        Node node2 = list2.getRoot();
        int carry = 0;

        LinkedList sumList = new LinkedList();

        while (node1 != null || node2 != null) {
            int sum = 0;
            if (node1 != null) {
                sum += node1.getData();
                node1 = node1.getNext();
            }

            if (node2 != null) {
                sum += node2.getData();
                node2 = node2.getNext();
            }

            sum += carry;

            if (sum >= 10) {
                carry = sum/10;
                sum = sum%10;
            } else {
                carry = 0;
            }
            sumList.add(sum);
        }

        if (carry > 0) {
            sumList.add(carry);
        }

        return sumList;
    }
}
