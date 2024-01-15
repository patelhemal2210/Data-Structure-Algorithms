package main.java.com.hemalpatel.datastructures.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class CircularLinkedList<T> implements LinkedList<T> {

    private class Node {
        private Node next = null;
        private final T data;

        public Node(T data) {
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
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

    private int size;
    private Node first;
    private Node last;

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
            return "CircularLinkedList{" +
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
            return "CircularLinkedList{" +
                    "\nLength=" + size + ";\n" +
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
            newNode.setNext(first == null ? newNode : first);
            first = newNode;
            if (last != null) {
                last.setNext(first);
            } else {
                last = newNode;
            }

        } else if (index == size) {
            newNode.setNext(last.getNext());
            last.setNext(newNode);
            last = newNode;
        } else {
            Node node = first;
            for (int i = 0; i < index - 1; i++) {
                node = node.getNext();
            }
            newNode.setNext(node.getNext());
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
        int index = 0;
        Node node = first;
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (node.getData().equals(data)) {
                found = true;
                index  = i;
                break;
            }
            node = node.getNext();
        }
        if(!found) {
            return false;
        }

        removeAt(index);
        return true;
    }

    /***
     * Remove an element from given index(0-based)
     *
     * @param index index of an element to be removed
     * @return element value
     * @throws IndexOutOfBoundsException if provided index is out of range
     */
    @Override
    public T removeAt(int index) {
        if(index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        T data = null;
        if(index == 0) {
            data = first.getData();
            first = first.getNext();
            last.setNext(first);
        } else {
            Node node = first;
            for(int i = 0; i < index - 1; i++) {
                node = node.getNext();
            }

            if(index == (size - 1)) {
                last = node;
            }
            node.setNext(node.getNext().getNext());
        }

        size--;
        return data;
    }
}