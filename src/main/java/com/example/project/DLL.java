package com.example.project;

public class DLL<T> {
	private DLLNode<T> head;
	private DLLNode<T> current;

    public DLL() {
        head = current = null;
    }
    public boolean empty() {
        return head == null;
    }
    public boolean last() {
        return current.next == null;
    }
    public boolean first() {
        return current.previous == null;
    }
    public boolean full() {
        return false;
    }
    public void findFirst() {
        current = head;
    }
    public void findNext() {
        current = current.next;
    }
    public void findPrevious() {
        current = current.previous;
    }
    public T retrieve() {
        return current.data;
    }
    public void update(T val) {
        current.data = val;
    }
    public void insert(T val) {
        DLLNode<T> tmp = new DLLNode<T>(val);
        if(empty()) {
            current = head = tmp;
        }
        else {
            tmp.next = current.next;
            tmp.previous = current;
            if(current.next != null)
                current.next.previous = tmp;
            current.next = tmp;
            current = tmp;
        }
    }
    public void remove() {
        if(current == head) {
            head = head.next;
            if(head != null)
               head.previous = null;
        }
        else {
            current.previous.next = current.next;
            if(current.next != null)
               current.next.previous = current.previous;
        }
        if(current.next == null)
            current = head;
        else
            current = current.next;
    }
   public void removeBetween(T e1, T e2) {
    if (empty()) {
        return; // If the list is empty, do nothing.
    }

    DLLNode<T> node1 = null; // Node containing e1
    DLLNode<T> node2 = null; // Node containing e2

    // Search for node1 (containing e1) and node2 (containing e2)
    DLLNode<T> temp = head;
    while (temp != null) {
        if (temp.data.equals(e1)) {
            node1 = temp;
        } else if (temp.data.equals(e2)) {
            node2 = temp;
        }

        if (node1 != null && node2 != null) {
            break; // Found both e1 and e2
        }

        temp = temp.next;
    }

    // If either e1 or e2 is not found, or if node1 comes after node2, do nothing
    if (node1 == null || node2 == null || node1.next == node2) {
        return;
    }

    // Remove nodes between node1 and node2
    DLLNode<T> current = node1.next;
    while (current != node2) {
        DLLNode<T> nextNode = current.next; // Save the next node

        // Unlink the current node
        if (current.previous != null) {
            current.previous.next = current.next;
        }
        if (current.next != null) {
            current.next.previous = current.previous;
        }

        current = nextNode; // Move to the next node
    }

    // Link node1 to node2 directly
    node1.next = node2;
    node2.previous = node1;

    // Move current pointer to head after successful removal
    current = head;
}}
