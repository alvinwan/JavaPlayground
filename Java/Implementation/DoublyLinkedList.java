/**
 * DOUBLY LINKED LIST
 * All methods are written in linear time, of O(n)
 * where n is the number of elements in the list. 
 *
 * @author Alvin Wan
 * @website alvinwan.com
 * Note that this has been written millions of times before;
 * there's nothing special about my version.
 */

public class DoublyLinkedList implements List {
    public DNode sentinel;

    public DoublyLinkedList(int[] array) {
        sentinel = new DNode(0);
        fromArray(array);
    }

    /**
     * Converts an array of ints into a DoublyLinkedList
     * of ints.
     * @param array : array of ints
     */
    public void fromArray(int[] array) {
        DNode pointer = sentinel;
        for (int i=0;i<array.length;i++) {
            sentinel.head += 1;
            pointer.next = new DNode(pointer, array[i], null);
            pointer = pointer.next;
        }
    }

    /**
     * Get element at index i, or get the last element if
     * the index is beyond the length of the list.
     * @param i
     * @return
     */
    public DNode get(int i) {
        DNode pointer = sentinel;
        while (i > 0 && pointer.next != null) {
            pointer = pointer.next;
            i -= 1;
        }
        return pointer;
    }

    /**
     * Inserts a given value into the doubly-linked list:
     *  - inserts at the end if the index exceeds array length
     *  - inserts at 0th index if index if negative
     * @param i: int
     * @param value: int
     */
    public void insert(int i, int value) {
        DNode pointer = get(i);
        DNode insert = new DNode(pointer, value, pointer.next);
        if (pointer.next != null)
            pointer.next.prev = insert;
        pointer.next = insert;
        sentinel.head += 1;
    }

    /**
     * Removes, from the doubly-linked list, the element at
     * the specified index, or terminate if the index given
     * is invalid.
     * @param i: int
     */
    public void remove(int i) {
        if (i < 0 || i >= sentinel.head) return;
        DNode pointer = get(i-1);
        if (pointer.next.next != null)
            pointer.next.next.prev = pointer;
        pointer.next = pointer.next.next;
        sentinel.head-= 1;
    }

    /**
     * Convert doubly-linked list into a string representation
     * for testing use.
     * @return String
     */
    public String toString() {
        DNode pointer = sentinel.next;
        StringBuilder str = new StringBuilder("[");
        while (pointer != null) {
            str.append(pointer.head+" ");
            pointer = pointer.next;
        }
        return str.append("]").toString();
    }

    /* Making insertFront easier with general insert function */
    public void insertFront(int value) {
        insert(0, value);
    }

    /* Making insertBack easier with insert and sentinel (size) */
    public void insertBack(int value) {
        insert(sentinel.head, value);
    }

    public static void main(String[] args) {
        DoublyLinkedList s = new DoublyLinkedList(new int[]{1, 2, 3, 4});
        System.out.println("Original:       "+s.toString());

        s.insert(0, 0);
        System.out.println("Insert at i=0:  "+s.toString());

        s.remove(0);
        System.out.println("Remove at i=0:  "+s.toString());

        s.insertFront(0);
        System.out.println("InsertFront:    "+s.toString());

        s.insertBack(5);
        System.out.println("InsertBack:     "+s.toString());

        // special cases
        s.insert(-1, 0);
        System.out.println("Insert at i=-1: "+s.toString());

        s.remove(-1);
        System.out.println("No remove i=-1: "+s.toString());
    }
}

class DNode {
    public int head;
    public DNode prev;
    public DNode next;

    public DNode(int _head) {
        head = _head;
    }

    public DNode(DNode _prev, int _head, DNode _next) {
        prev = _prev;
        head = _head;
        next = _next;
    }
}