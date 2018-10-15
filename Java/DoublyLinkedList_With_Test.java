
package data_structure;

/**
 *
 * @author Ahmed Elafifi
 * date :15/10/2018
 * category: DoublyLinkedList (Data Structure) with java
 */

 public class DoublyLinkedList_With_Test {
     public static void main(String[] args) {
        DoublyLinkedList theList = new DoublyLinkedList();
        theList.insertFirst(22); // insert at front
        theList.insertFirst(44);
        theList.insertFirst(66);
        theList.insertLast(11); // insert at rear
        theList.insertLast(33);
        theList.insertLast(55);
        theList.displayForward(); // display list forward
        theList.displayBackward(); // display list backward
        theList.deleteFirst(); // delete first item
        theList.deleteLast(); // delete last item
        theList.deleteKey(11); // delete item with key 11
        theList.displayForward(); // display list forward
        theList.insertAfter(22, 77); // insert 77 after 22
        theList.insertAfter(33, 88); // insert 88 after 33
        theList.displayForward(); // display list forward
     }
 }
 class DoublyLinkedList {

    private Node head;     // reference to the first element in the list
    private Node tail;      //referent to the last element in the list

    /**
     * constructor to instantiate the list and initialise the first and last ref
     * to null
     *
     * note: there is no need for this constructor as first and last already
     * initialised with null but i make class more clear
     */
    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    /**
     * function boolean to check if list is empty or not
     *
     * @return the boolean value to tell me if list is empty or not
     */
    boolean isEmpty() {
        return head == null;
    }

    public void insertFirst(long data) {
        Node newNode = new Node(data);      // create a new node

        if (isEmpty()) {
            tail = newNode;     // newLink <-- last
        } else {
            head.previous = newNode;  //newLink <-- old head
        }
        newNode.next = head;    //newLink --> old first
        head = newNode;     // head --> newLink
    }

    /**
     * function to insert new node at the end of list
     *
     * @param data the data the be stored in new last node
     */
    public void insertLast(long data) {
        Node newNode = new Node(data); // create a new node with data passed

        if (isEmpty()) {
            head = newNode;         // head --> new Node
        } else {
            tail.next = newNode;    // old last --> newNode
        }
        newNode.previous = tail;     //old last --> newNode
        tail = newNode;             //last --> newNode

    }

    /**
     * function to delete the first element in list
     *
     * @return the deleted element
     */
    public Node deleteFirst() {
        Node temp = head;

        // note that there is no need for this condition as if list is empty
        //the temp  = null and will return null also without this condition but 
        // I Want to make class more clear
        if (isEmpty()) // return null if the list is already empty
        {
            return null;
        }
        if (tail == head) {
            tail = null;
            head = null;
        } else {
            head.next.previous = null;
            head = head.next;
        }
        return temp;
    }

    public Node deleteLast() {
        Node temp = tail;

        // note that there is no need for this condition as if list is empty
        //the temp  = null and will return null also without this condition but 
        // I Want to make class more clear
        if (isEmpty()) // return null if the list is already empty
        {
            return null;
        }
        if (tail == head) {
            head = null;        //head --> null
            tail = null;        //tail --> null
        } else {
            tail.previous.next = null;  //prev tail --> null
            tail = tail.previous;       //old tail --> prev tail
        }
        return temp;
    }

    /**
     * function to insert element after node has required key
     *
     * @param key
     * @param data
     * @return
     */
    public boolean insertAfter(long key, long data) {
        Node newNode = new Node(data);      // create a new node with new data
        Node curNode = head;
        // search for the node has the key
        while (curNode != null && curNode.data != key) {
            curNode = curNode.next;
        }
        if (curNode == null) // if key not found return false
        {
            return false;
        }
        if (tail == curNode) {
            newNode.next = null;    // not required as this next is already null
            tail = curNode;
        } else {
            curNode.next.previous = newNode;
            newNode.next = curNode.next;
        }
        curNode.next = newNode;
        newNode.previous = curNode;
        return true;    //found it and did insertion
    }

    /**
     * function to delete node the passed key
     *
     * @param key the key that its node will be deleted
     * @return the deleted node with key
     */
    public Node deleteKey(long key) {
        Node curNode = head;
        while (curNode != null && curNode.data != key) {
            curNode = curNode.next;
        }
        if (curNode == null) // if the key is not found return null 
        {
            return null;
        }
        Node temp = curNode;
        if (tail == head) {  // if the list has one node only
            tail = null;
            head = null;
        } else if (curNode == head) { // if the node with key is the head
            curNode.next.previous = null;
            head = curNode.next;
        } else if (curNode == tail) { // if node with the key is tail 
            curNode.previous.next = null;
            tail = curNode.previous;
        } else {             // if the node with key in the middle if the list
            curNode.previous.next = curNode.next;
            curNode.next.previous = curNode.previous;
        }
        return temp;        // found the key and did deletion
    }
    /**
     * function to display the list from first to last
     */
    public void displayForward() {
        System.out.println("List(first --> last):");
        Node curNode = head;
        while(curNode != null) {
            curNode.displayNode();
            curNode = curNode.next;
        }
        System.out.println("");
    }
    /**
     * function to display the list element from last to first
     */
    public void displayBackward() {
        System.out.println("List(first --> last):");
        Node curNode = tail;
        while(curNode != null) {
            curNode.displayNode();
            curNode = curNode.previous;
        }
        System.out.println("");
    }
}

/**
 * {@Node } this is class to represent the node and its information we can use {@Node
 * } later to build doubly linked list
 */
class Node {

    public long data;       // data item 
    public Node next;       // reference to nextLink in list
    public Node previous;   // previous link in list

    /**
     * constructor to instantiate the new node
     *
     * @param data information passed to stored in new node
     */
    public Node(long data) {
        this.data = data;
    }

    /**
     * function to display the data in the current node
     */
    public void displayNode() {
        System.out.print(data + " ");
    }

}
