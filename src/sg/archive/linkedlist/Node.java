/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.archive.linkedlist;

/**
 *
 * @author sachin
 */
public class Node {

    String nodeData;
    Node next;

    public Node(String data, Node next) {
        nodeData = data;
        this.next = next;
    }

    public Node(String data) {
          nodeData = data;
    }

//    public String getNodeData() {
//        return nodeData;
//    }

    public void setNodeData(String nodeData) {
        this.nodeData = nodeData;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
    @Override
public String toString() {
    return nodeData;
}
}
