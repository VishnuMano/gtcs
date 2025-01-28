/**
 * Your implementation of a CircularSinglyLinkedList without a tail pointer.
 *
 * @author Vishnu Mano
 * @version 1.0
 * @userid vmano3
 * @GTID 903873278
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 * 
 * By typing 'I agree' below, you are agreeing that this is your
 * own work and that you are responsible for all the contents of 
 * this file. If this is left blank, this homework will receive a zero.
 * 
 * Agree Here: I agree
 * 
 */
public class CircularSinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private CircularSinglyLinkedListNode<T> head;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index needs to be greater than 0 and less than size");
        } else if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        CircularSinglyLinkedListNode<T> temp = new CircularSinglyLinkedListNode<>(data);
        CircularSinglyLinkedListNode<T> curr = head;
        if (size == 0) {
            head = temp;
            head.setNext(head);
        } else if (index == 0) {
            temp.setNext(head.getNext());
            temp.setData(head.getData());
            head.setNext(temp);
            head.setData(data);
        } else if (index == size) {
            while (curr.getNext() != head) {
                curr = curr.getNext();
            }
            curr.setNext(temp);
            curr.setNext(head);
        } else {
            for (int i = 0; i < index - 1; i++) {
                curr = curr.getNext();
            }
            temp.setNext(curr.getNext());
            curr.setNext(temp);
        }
        size += 1;
    }

    /**
     * Adds the data to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        } else {
            addAtIndex(0, data);
        }
    }

    /**
     * Adds the data to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        } else {
            addAtIndex(size, data);
        }
    }

    /**
     * Removes and returns the data at the specified index.
     *
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (isEmpty()) {
            return null;
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index needs to be greater than 0 and less than size");
        }
        T temp;
        CircularSinglyLinkedListNode<T> curr = head;
        if (index == 0) {
            temp = head.getData();
            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());
        } else if (index == size) {
            while (curr.getNext().getNext() != head) {
                curr = curr.getNext();
            }
            temp = curr.getNext().getData();
            curr.setNext(head);
        } else {
            for (int i = 1; i < index; i++) {
                curr = curr.getNext();
            }
            temp = curr.getNext().getData();
            curr.setNext(curr.getNext().getNext());
        }
        if (--size == 0) {
            head = null;
        }
        return temp;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        return removeAtIndex(0);
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        return removeAtIndex(size - 1);
    }

    /**
     * Returns the data at the specified index.
     *
     * Should be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index needs to be greater than 0 and less than size");
        } else if (index == 0) {
            return head.getData();
        } else {
            CircularSinglyLinkedListNode<T> temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.getNext();
            }
            return temp.getData();
        }
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can't be null");
        }
        if (size == 0) {
            return null;
        } else {
            CircularSinglyLinkedListNode<T> temp1 = head;
            CircularSinglyLinkedListNode<T> temp2 = null;
            if (head.getData().equals(data)) {
                temp2 = temp1;
            }
            temp1 = temp1.getNext();
            while (temp1 != head && temp1 != null) {
                if (temp1.getNext().getData().equals(data)) {
                    temp2 = temp1;
                }
                temp1 = temp1.getNext();
            }
            if (temp2 != null) {
                temp1 = temp2.getNext();
                temp2.setNext(temp2.getNext().getNext());
                if (--size == 0) {
                    head = null;
                }
            }
            return temp1.getData();
        }
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {
        T[] array = (T[]) new Object[size];
        CircularSinglyLinkedListNode<T> temp = head;
        for (int i = 0; i < size; i++) {
            array[i] = temp.getData();
            temp = temp.getNext();
        }
        return array;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public CircularSinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
