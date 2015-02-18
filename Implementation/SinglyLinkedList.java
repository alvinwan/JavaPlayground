public class SinglyLinkedList implements List {
    public SNode sentinel;
    
    public SinglyLinkedList(int[] array) {
        sentinel = new SNode(0);
        fromArray(array);
    }
    
    /**
     * Converts an array of ints into a SinglyLinkedList
     * of ints.
     * @param array : array of ints
     */
    public void fromArray(int[] array) {
        SNode pointer = sentinel;
        for (int i=0;i<array.length;i++) {
            sentinel.head += 1;
            pointer.next = new SNode(array[i]);
            pointer = pointer.next;
        }
    }

    /**
     * Get element at index i, or get the last element if
     * the index is beyond the length of the list.
     * @param i
     * @return
     */
    public SNode get(int i) {
        SNode pointer = sentinel;
        while (i > 0 && pointer.next != null) {
            pointer = pointer.next;
            i -= 1;
        }
        return pointer;
    }

    /**
     * Inserts a given value into the singly-linked list:
     *  - inserts at the end if the index exceeds array length
     * @param i: int
     * @param value: int
     */
    public void insert(int i, int value) {
        SNode pointer = get(i);
        SNode insert = new SNode(value, pointer.next);
        pointer.next = insert;
        sentinel.head += 1;
    }

    /**
     * Removes, from the singly-linked list, the element at
     * the specified index, or terminate if the index given
     * is beyond the scope of the linked-list.
     * @param i: int
     */
    public void remove(int i) {
        if (i < 0 || i >= sentinel.head) {
            return;
        }
        SNode pointer = get(i-1);
        pointer.next = pointer.next.next;
        sentinel.head-= 1;
    }
    
    public String toString() {
        SNode pointer = sentinel.next;
        StringBuilder str = new StringBuilder("[");
        while (pointer != null) {
            str.append(pointer.head+" ");
            pointer = pointer.next;
        }
        return str.append("]").toString();
    }
    
    public void insertFront(int value) {
        insert(0, value);
    }
    
    public void insertBack(int value) {
        insert(sentinel.head, value);
    }

    public static void main(String[] args) {
        SinglyLinkedList s = new SinglyLinkedList(new int[]{1, 2, 3, 4});
        System.out.println("Original:       "+s.toString());
        
        s.insert(0, 0);
        System.out.println("Insert at i=0:  "+s.toString());
        
        s.remove(0);
        System.out.println("Remove at i=0:  "+s.toString());
        
        s.insertFront(0);
        System.out.println("InsertFront:    "+s.toString());
        
        s.insertBack(5);
        System.out.println("InsertBack:     "+s.toString());
    }
}

class SNode {
    public int head;
    public SNode next;
    
    public SNode(int _head) {
        head = _head;
    }
    
    public SNode(int _head, SNode _next) {
        head = _head;
        next = _next;
    }
}