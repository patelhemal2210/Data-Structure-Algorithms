package main.java.com.hemalpatel.datastructures.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class DoublyLinkedList<T> {

    private class Node<T> {

        private final T data;
        private Node<T> previous;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.previous = null;
            this.next = null;
        }

        public Node<T> getNext() {
            return next;
        }

        public Node<T> getPrevious() {
            return previous;
        }

        public T getData() {
            return data;
        }

        public void setPrevious(Node<T> previous) {
            this.previous = previous;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private Node<T> first;
    private Node<T> last;
    private int size;

    public DoublyLinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "DoublyLinkedList{" +
                    "first=" + first +
                    ", size=" + size +
                    ", last=" + last +
                    '}';
        } else {
            StringBuilder nodeString = new StringBuilder();
            nodeString.append("previous : ")
                    .append(first.previous)
                    .append(" ;current : ")
                    .append(first)
                    .append(" ;next : ")
                    .append(first.next)
                    .append("\n");
            Node<T> node = first.next;
            for (int i = 1; i < size; i++) {
                nodeString.append("previous : ")
                        .append(node.previous)
                        .append(" ;current : ")
                        .append(node)
                        .append(" ;next : ")
                        .append(node.next)
                        .append("\n");
                node = node.getNext();
            }
            return "DoublyLinkedList{" +
                    "\nLength=" + size + ";\n" +
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

        if (size == 0) {
            this.first = node;
        } else {
            node.setPrevious(this.last);
            this.last.setNext(node);
        }

        this.last = node;
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

        // Adding at the end
        if (index == size) {
            insert(data);
            return;
        } else {
            Node<T> newNode = new Node<>(data);

            if (index == 0) { // adding at the beginning
                newNode.setNext(this.first);
                this.first.setPrevious(newNode);
                this.first = newNode;
            } else { // insert in-between
                if (index <= (int) Math.floor((double) size / 2)) { // traverse from front
                    Node<T> node = this.first;
                    for (int i = 1; i < index; i++) {
                        node = node.getNext();
                    }

                    newNode.setNext(node.getNext());
                    newNode.setPrevious(node);
                    node.getNext().setPrevious(newNode);
                    node.setNext(newNode);
                } else { // traverse from back
                    Node<T> node = this.last;
                    for (int i = size - 1; i > index; i--) {
                        node = node.getPrevious();
                    }

                    newNode.setNext(node);
                    newNode.setPrevious(node.getPrevious());
                    node.getPrevious().setNext(newNode);
                    node.setPrevious(newNode);
                }
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
        if(index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        if(index == 0) {
            T data = this.first.getData();
            this.first = this.first.getNext();
            this.first.setPrevious(null);
            size--;
            return data;
        }

        if(index == (size - 1)) {
            T data = this.last.getData();
            this.last = this.last.getPrevious();
            this.last.setNext(null);
            size--;
            return data;
        }

        Node<T> node = this.first.getNext();
        for(int i = 1; i < size - 1; i++) {
            if(i == index) break;
            node = node.getNext();
        }

        node.getPrevious().setNext(node.getNext());
        node.getNext().setPrevious(node.getPrevious());
        size--;
        return node.getData();
    }

    /***
     * Remove an element based on the value
     * If the duplicate elements are present in the list, first one will be removed
     *
     * @param data element value to be removed
     * @return True if element is removed otherwise false
     */
    public boolean remove(T data) {
        if(size == 0) {
            return false;
        }

        if(this.first.getData().equals(data)) {
            this.first = this.first.getNext();
            this.first.setPrevious(null);
            size--;
            return true;
        }

        if(this.last.getData().equals(data)) {
            this.last = this.last.getPrevious();
            this.last.setNext(null);
            size--;
            return true;
        }

        Node<T> node = this.first.getNext();
        for(int i = 1; i < size; i++) {
            if(node.getData().equals(data)) {
                node.getPrevious().setNext(node.getNext());
                node.getNext().setPrevious(node.getPrevious());
                size--;
                return true;
            }
            node = node.getNext();
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
        if(index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        if (index <= (int) Math.floor((double) size / 2)) { // traverse from front
            Node<T> node = this.first;
            for (int i = 0; i < index; i++) {
                node = node.getNext();
            }
            return node.getData();
        } else { // traverse from back
            Node<T> node = this.last;
            for (int i = size - 1; i > index; i--) {
                node = node.getPrevious();
            }

            return node.getData();
        }
    }

    /***
     * Return all elements in the list as an array
     *
     * @return array list of all elements
     */
    public List<T> getAll() {
        List<T> list = new ArrayList<>();
        Node<T> node = first;
        for(int i = 0; i < size; i++) {
            list.add(node.getData());
            node = node.getNext();
        }
        return list;
    }
}