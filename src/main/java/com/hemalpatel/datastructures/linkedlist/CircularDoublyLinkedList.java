package main.java.com.hemalpatel.datastructures.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class CircularDoublyLinkedList<T> implements LinkedList<T> {

    private class Node {
        private Node previous = null;
        private Node next = null;
        private final T data;

        public Node(T data) {
            this.data = data;
        }

        public Node getPrevious() {
            return previous;
        }

        public Node getNext() {
            return next;
        }

        public T getData() {
            return data;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public void setNext(Node next) {
            this.next = next;
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

    private int size = 0;
    private Node first = null;
    private Node last = null;

    @Override
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
            return "CircularDoublyLinkedList{" +
                    "first=" + first +
                    ", length=" + size +
                    ", last=" + last +
                    '}';
        } else {
            StringBuilder nodeString = new StringBuilder();
            nodeString.append(first).append("\n");
            Node node = first.next;
            for (int i = 1; i < size; i++) {
                nodeString.append(node).append("\n");
                node = node.getNext();
            }
            return "CircularDoublyLinkedList{" +
                    "\nfirst=" + first + ";" +
                    "\nLength=" + size + ";" +
                    "\nLast=" + last + ";\n" +
                    "Nodes = \n" + nodeString +
                    '}';
        }
    }

    /***
     * Return value at given index (0-based)
     * This does not remove the element from the list
     *
     * @param index index of the element for which value should be returned
     * @return value of an element
     * @throws IndexOutOfBoundsException if provided index is out of range
     */
    @Override
    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        Node node = first;
        for (int i = 1; i <= index; i++) {
            node = node.getNext();
        }

        return node.getData();
    }

    /***
     * Return all elements in the list as an array
     *
     * @return array list of all elements
     */
    @Override
    public List<T> getAll() {
        List<T> list = new ArrayList<>();

        Node node = first;
        for (int i = 0; i < size; i++) {
            list.add(node.getData());
            node = node.getNext();
        }

        return list;
    }

    /***
     * Insert the element at the end of the list
     *
     * @param data value for of the element
     */
    @Override
    public void insert(T data) {
        insertAt(size, data);
    }

    /***
     * Insert the element at given index (0-based)
     *
     * @param index index at which element need to be added (0-based)
     * @param data element value
     * @throws IndexOutOfBoundsException if provided index is out of range
     */
    @Override
    public void insertAt(int index, T data) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        Node newNode = new Node(data);
        if (index == 0) {
            if (size == 0) {
                newNode.setNext(newNode);
                newNode.setPrevious(newNode);
                first = newNode;
                last = newNode;
            } else {
                newNode.setNext(first);
                newNode.setPrevious(first.getPrevious());
                first.setPrevious(newNode);
                first = newNode;
                last.setNext(first);
            }
        } else if (index == size) {
            newNode.setPrevious(last);
            newNode.setNext(last.getNext());
            last.setNext(newNode);
            last = newNode;
            first.setPrevious(last);
        } else {
            Node node = first;
            for (int i = 1; i < index; i++) {
                node = node.getNext();
            }

            newNode.setNext(node.getNext());
            newNode.setPrevious(node);
            node.getNext().setPrevious(newNode);
            node.setNext(newNode);
        }
        size++;
    }

    /***
     * Remove an element based on the value
     * If the duplicate elements are present in the list, first one will be removed
     *
     * @param data element value to be removed
     * @return True if element is removed otherwise false
     */

    @Override
    public boolean remove(T data) {
        Node node = first;
        int index = 0;
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (node.getData().equals(data)) {
                index = i;
                found = true;
                break;
            }
            node = node.getNext();
        }

        if (!found) {
            return false;
        }

        removeAt(index);
        return true;
    }

    @Override
    public T removeAt(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        T data;
        if (size == 1) {
            data = first.getData();
            first = null;
            last = null;
        } else if (index == 0) {
            data = first.getData();
            first = first.getNext();
            first.setPrevious(last);
            last.setNext(first);
        } else if (index == (size - 1)) {
            data = last.getData();
            last = last.getPrevious();
            last.setNext(first);
            first.setPrevious(last);
        } else {
            Node node = first;
            for(int i = 1; i <= index; i++) {
                node = node.getNext();
            }

            data = node.getData();
            node.getPrevious().setNext(node.getNext());
            node.getNext().setPrevious(node.getPrevious());
        }

        size--;
        return data;
    }
}