package main.java.com.hemalpatel.datastructures.trees;

import java.util.List;

public interface BinaryTree {
    boolean insert(int value);

    boolean has(int value);

    boolean delete(int value);

    List<Integer> traverseInOrder();

    List<Integer> traversePreOrder();

    List<Integer> traversePostOrder();

    List<Integer> traverseLeverOrder();

    int countOfNodes();

    int sumOfNodes();

    int height();

    int diameter();

    boolean hasSubTree(BinarySearchTree tree);
}
