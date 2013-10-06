/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.archive.linkedlist;

/**
 *
 * @author sachin
 */
public class LinkedListOld {

    private Node firstNode = null;

	public void build(int n) {
        deleteList();
            Node node = new Node("node:" + n);
            add(node);
        }

    

    public void buildLoop(int n) {
        deleteList();
        Node node = null;
        for (int i = 0; i < n; i++) {
            node = new Node("node:" + i);
            add(node);
        }
        node.next = getFirstNode();

    }

    public int length() {
        Node temp = getFirstNode();
        int len = 0;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        return len;
    }

    public void push(Node node) {
        node.next = getFirstNode();
        setFirstNode(node);
    }

    public void printList() {
        Node temp = getFirstNode();
        System.out.println("\n---------List----------");
        while (temp != null) {
            System.out.print(" -> " + temp.nodeData);
            temp = temp.next;
        }
        System.out.println("\n------------------------");
    }

    public void add(Node node) {
        if (getFirstNode() == null) {
        	setFirstNode(node);
            return;
        }
        Node temp = getFirstNode();
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
        node.next = null;
    }

    public int countData(String nodeData) {
        Node temp = getFirstNode();
        int count = 0;
        while (temp != null) {
            if (nodeData.equals(temp.nodeData)) {
                count++;
            }
            temp = temp.next;
        }
        return count;
    }

    public Node getNth(int n) {
        Node temp = getFirstNode();
        int count = 0;
        while (temp != null) {
            if (count == n) {
                return temp;
            }
            count++;
            temp = temp.next;
        }
        return null;
    }

    public void deleteList() {
    	setFirstNode(null); //list available for GC
	}

    public Node pop() {
        if (getFirstNode() == null) {
            return null;    //this is an error
        }
        Node temp = getFirstNode();
        setFirstNode(getFirstNode().next);
        return temp;

    }

    public void insertSorted(String nodeData) {

        Node prev_node = null;
        Node temp = getFirstNode();
        int count = 0;

        while (temp != null) {

            if (temp.nodeData.compareTo(nodeData) >= 0) {
                Node node = new Node(nodeData);
                if (prev_node != null) {
                    System.out.println("inserting in middle position");
                    prev_node.next = node;
                } else {
                    System.out.println("inserting in first position");
                    setFirstNode(node);
                }
                node.next = temp;
                return;
            }
            prev_node = temp;
            temp = temp.next;
            count++;
        }

        if (prev_node != null) {
            System.out.println("inserting in last position");
            prev_node.next = new Node(nodeData);
            prev_node.next.next = null;
        } else {
            System.out.println("inserting first node");
            setFirstNode(new Node(nodeData));
            firstNode.next = null;
        }



    }

    public boolean isLoop() {
        Node temp = getFirstNode();
        Node fastPointer = null;
        //initialize fast pointer
        if (getFirstNode() != null && getFirstNode().next != null) {
            fastPointer = getFirstNode().next;
        } else {
            return false;
        }

        while (temp != null) {
            System.out.println("slow:" + temp);
            System.out.println("fastPointer:" + fastPointer);
            temp = temp.next;
            if (fastPointer != null && fastPointer.next != null) {
                fastPointer = fastPointer.next.next;
            } else {
                return false;
            }
            if (temp == fastPointer) {
                return true;
            }

        }
        return false;
    }

    public void sort() {

        LinkedListOld sortedList = new LinkedListOld();
        Node temp = getFirstNode();
        while (temp != null) {
            Node node = pop();
            if (node != null) {
                sortedList.insertSorted(node.nodeData);
                temp = temp.next;
            }
        }
        this.setFirstNode(sortedList.getFirstNode());
    }

    public Node findFromLast(int pos) {
        Node n1 = null;
        Node n2 = null;

        n1 = getFirstNode();
        n2 = getFirstNode();

        while (pos > 0) {
            if (n2 == null) {
                return null;
            }
            n2 = n2.next;
            pos--;
        }
        while (n2 != null) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    public Node deleteNode(int index) {
        Node prev_node = null;
        Node temp = getFirstNode();
        int count = 0;

        while (temp != null) {

            if (count == index) {


                if (prev_node != null) {
                    System.out.println("deleting middle node:" + temp);
                    prev_node.next = temp.next;
                    
                } else {
                    System.out.println("deleting first node:" + temp);
                    setFirstNode(temp.next);
                }
                return temp;
            }
            prev_node = temp;
            temp = temp.next;
            count++;
        }
        return null;
    }

    public void swapAdjacentNodes() {
        //int index1=1;
        if (getFirstNode() == null) {
            return;
        }
        int index = 1;
        Node temp = getFirstNode().next;
        while (temp != null) {

            Node node = deleteNode(index);
            System.out.println("deleted node:"+node);
            if (node != null) {
                insert(node.nodeData, index - 1);
                System.out.println("temp:"+temp);
                
                temp = temp.next;
            }
            index += 2;
            if (temp==null) return;
            temp = temp.next;
            if (temp != null) {
                temp = temp.next;
            } 



        }

    }

    public void moveNode(int index1, int index2) {
        Node node = deleteNode(index1);
        if (node != null) {
            insert(node.nodeData, index2);
        }
    }

    public void insert(String nodeData, int index) {
        System.out.println("inserting... :"+nodeData+" at:"+index);
        insert(nodeData, null, null, 0, index);
    }

    public void insert(String nodeData, Node prevNode, Node startNode, int startIndex, int index) {
        Node prev_node = null;
        if (prevNode != null) {
            prev_node = prevNode;
        }
        Node temp = getFirstNode();
        if (startNode != null) {
            temp = startNode;
        }

        int count = 0;
        if (startIndex > 0) {
            count = startIndex;
        }
        while (temp != null) {

            if (count == index) {
                System.out.println("inserting in middle position");
                Node node = new Node(nodeData);

                if (prev_node != null) {
                    prev_node.next = node;
                } else {
                	setFirstNode(node);
                }
                node.next = temp;
                return;
            }
            prev_node = temp;
            temp = temp.next;
            count++;
        }
        if (count == index) {

            if (count == 0) {
                System.out.println("inserting first position");
                setFirstNode(new Node(nodeData));
                firstNode.next = null;
                return;
            }
            System.out.println("inserting last position");
            prev_node.next = new Node(nodeData);
            prev_node.next.next = null;
        }
    }

    public void readBackwards() {
        System.out.println("\n-------readBackwards-----------");
        readBackwards(getFirstNode());
        System.out.println("\n-------------------------------");
    }

    private void readBackwards(Node node) {
        if (node == null) {
            return;
        }
        readBackwards(node.next);
        System.out.print("->" + node);
    }

   

    public void reverse() {
        Node prev;
        Node curr;
        Node next;

        prev = null;
        curr = getFirstNode();

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        setFirstNode(prev);
    }

    public static void main(String[] args) {
        LinkedListOld list = new LinkedListOld();
        list.build(10);
        list.printList();
        System.out.println("Length:" + list.length());
        System.out.println("Count Data:" + list.countData("node:4"));
        System.out.println("get nth:" + list.getNth(9));
        list.deleteList();
        list.insert("inserted:" + 0, 0);
        list.insert("inserted:" + 1, 1);
        for (int i = 0; i < 10; i++) {
            list.insert("inserted:" + i, i);
        }
        //list.insert("inserted:" + 11);

        list.sort();
        list.printList();
        list.build(10);
        list.moveNode(0, 9);
        list.printList();

        list.build(5);
        list.printList();
        list.reverse();
        list.printList();
        //list.buildLoop(2);
        System.out.println("isLoop:" + list.isLoop());
        //findFromLast
        list.build(5);
        System.out.println("findFromLast: 1:" + list.findFromLast(1));
        System.out.println("findFromLast: 2:" + list.findFromLast(2));
        System.out.println("findFromLast: 3:" + list.findFromLast(3));
        list.build(6);
        list.printList();
        list.swapAdjacentNodes();
        list.printList();
       //list.readBackwards();
    }

    /**
     * @return the first_node
     */
    public Node getFirstNode() {
        return firstNode;
    }

    /**
     * @param first_node the first_node to set
     */
    public void setFirstNode(Node firstNode) {
        this.firstNode = firstNode;
    }
}
