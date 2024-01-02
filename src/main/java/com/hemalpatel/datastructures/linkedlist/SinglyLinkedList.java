package main.java.com.hemalpatel.datastructures.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class SinglyLinkedList<T> {

    public SinglyLinkedList() {
    }

    private class Node<T> {

        public Node(T data) {
            this.data = data;
            next = null;
        }

        private Node<T> next;
        private final T data;

        public void setNext(Node<T> node) {
            next = node;
        }

        public Node<T> getNext() {
            return next;
        }

        public T getData() {
            return data;
        }

        /**
         * Designed for better visualization
         *
         * @return Node data as string
         */
        @Override
        public String toString() {
            return "Node{" + data + '}';
        }
    }

    private Node<T> first = null;
    private int size = 0;
    private Node<T> last = null;

    public int getSize() {
        return size;
    }

    /**
     * Designed for better visualization
     *
     * @return List as string
     */
    @Override
    public String toString() {
        if (first == null) {
            return "SinglyLinkedList{" +
                    "head=" + first +
                    ", length=" + size +
                    ", tail=" + last +
                    '}';
        } else {
            StringBuilder nodeString = new StringBuilder();
            nodeString.append(first).append("\n");
            Node<T> node = first.next;
            for (int i = 1; i < size; i++) {
                nodeString.append(node).append("\n");
                node = node.getNext();
            }
            return "SinglyLinkedList{" +
                    "\nLength=" + size + ";\n" +
                    "\nLast=" + last + ";\n" +
                    "Nodes = \n" + nodeString +
                    '}';
        }
    }

    /***
     * Insert the element at the end of the list
     *
     * @param data value for of the element
     */
    public void insert(T data) {
        Node<T> node = new Node<>(data);

        if (first == null) {
            first = node;
        } else {
            last.setNext(node);
        }

        last = node;
        size++;
    }

    /***
     * Insert the element at given index (0-based)
     *
     * @param index index at which element need to be added (0-based)
     * @param data element value
     * @throws IndexOutOfBoundsException if provided index is out of range
     */
    public void insertAt(int index, T data) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        if (index == 0) {
            Node<T> node = new Node<>(data);
            node.setNext(first);
            first = node;
        } else {
            Node<T> node = first;
            for (int i = 1; i < index; i++) {
                node = node.getNext();
            }

            Node<T> newNode = new Node<>(data);
            newNode.setNext(node.getNext());
            node.setNext(newNode);
            if (index == size) {
                last = newNode;
            }
        }

        size++;
    }

    /***
     * Remove an element from given index(0-based)
     *
     * @param index index of an element to be removed
     * @return element value
     * @throws IndexOutOfBoundsException if provided index is out of range
     */
    public T removeAt(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        T result;
        // Removing first element
        if (index == 0) {
            result = first.getData();
            first = first.getNext();

            // If there is only one element in the list, update the last element reference
            if (size == 1) {
                last = first;
            }
        } else {
            Node<T> node = first;
            for (int i = 1; i < index; i++) {
                node = node.getNext();
            }
            result = node.getNext().getData();

            // Update last element reference when last element in the list is deleted
            if (index == (size - 1)) {
                node.setNext(null);
                last = node;
            } else {
                node.setNext(node.getNext().getNext());
            }
        }
        size--;
        return result;
    }

    /***
     * Remove an element based on the value
     * If the duplicate elements are present in the list, first one will be removed
     *
     * @param data element value to be removed
     * @return True if element is removed otherwise false
     */
    public boolean remove(T data) {

        // Check if there is any element in the list
        if (size == 0) {
            return false;
        }

        // Check if first element is getting removed
        if (first.getData().equals(data)) {
            first = first.getNext();
            size--;
            return true;
        } else {
            Node<T> previousNode = first;
            Node<T> node = first.getNext();
            for (int i = 0; i < size - 1; i++) {
                if (node.getData().equals(data)) {
                    previousNode.setNext(node.getNext());

                    // If last element is getting removed
                    if (i == (size - 2)) {
                        last = previousNode;
                    }
                    size--;
                    return true;
                }
                previousNode = node;
                node = node.getNext();
            }
        }

        return false;
    }

    /***
     * Return value at given index (0-based)
     * This does not remove the element from the list
     *
     * @param index index of the element for which value should be returned
     * @return value of an element
     * @throws IndexOutOfBoundsException if provided index is out of range
     */
    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        Node<T> node = first;
        for (int i = 0; i < size; i++) {
            if (index == i) {
                break;
            }
            node = node.getNext();
        }

        return node.getData();
    }

    /***
     * Return all elements in the list as an array
     *
     * @return array list of all elements
     */
    public List<T> getAll() {
        ArrayList<T> result = new ArrayList<>(size);
        Node<T> node = first;
        for(int i = 0; i < size; i++) {
            result.add(node.getData());
            node = node.getNext();
        }

        return result;
    }
}