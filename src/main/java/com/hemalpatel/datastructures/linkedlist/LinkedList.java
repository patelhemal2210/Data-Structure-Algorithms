package main.java.com.hemalpatel.datastructures.linkedlist;

import java.util.List;

public interface LinkedList<T> {
    int getSize();
    T get(int index);
    List<T> getAll();
    void insert(T data);
    void insertAt(int index, T data);
    boolean remove(T data);
    T removeAt(int index);
}
