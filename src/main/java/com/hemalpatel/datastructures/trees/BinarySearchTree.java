package main.java.com.hemalpatel.datastructures.trees;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree implements BinaryTree {

    public BinarySearchTree(int rootNodeValue) {
        this.root = new Node(rootNodeValue);
    }

    private class Node {
        private int data;
        private Node left = null;
        private Node right = null;

        public Node(int nodeValue) {
            data = nodeValue;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public boolean isLeaf() {
            return this.left == null && this.right == null;
        }
    }

    private final Node root;

    private boolean addRecursive(Node root, int value) {
        if(root == null) {
            return false;
        }
        Node newRoot;
        if(value < root.getData()) {
            if(root.getLeft() == null) {
                root.setLeft(new Node(value));
                return true;
            }
            newRoot = root.getLeft();
        }
        else {
            if(root.getRight() == null) {
                root.setRight(new Node(value));
                return true;
            }
            newRoot = root.getRight();
        }

        return addRecursive(newRoot, value);
    }

    @Override
    public boolean insert(int value) {
        return addRecursive(root, value);
    }

    private Node findRecursive(Node node, int value) {
        if(value == node.getData()) {
            return node;
        } else if(value < node.getData()) {
            if(node.getLeft() == null) return null;
            return findRecursive(node.getLeft(), value);
        } else {
            if(node.getRight() == null) return null;
            return findRecursive(node.getRight(), value);
        }
    }

    @Override
    public boolean has(int value) {
        return findRecursive(root, value) != null;
    }

    private Node findLargest(Node node) {
        if(node == null) return null;

        return node.getRight() != null ? findLargest(node.getRight()) : node;
    }

    private Node findSmallest(Node node) {

        return node.getLeft() != null ? findSmallest(node.getLeft()) : node;
    }

    // Used for deleting a node
    // replace with predecessor (Largest element from the left side of the tree)
    private Node replaceWithPredecessorRecursive(Node node, int value) {
        if(node == null) {
            return null;
        }

        // Find the element
        if(value == node.getData()) {
            // delete the node
            if(node.isLeaf()) {
                return null;
            }
            if(node.getLeft() == null) {
                return node.getRight();
            }
            if(node.getRight() == null) {
                return node.getLeft();
            }

            Node largestNode = findLargest(node.getLeft());
            node.setData(largestNode.getData());
            node.setLeft(replaceWithPredecessorRecursive(node.getLeft(), largestNode.getData()));
        }
        else if(value < node.getData()) {
            Node ret = replaceWithPredecessorRecursive(node.getLeft(), value);
            node.setLeft(ret);
        }
        else {
            Node ret = replaceWithPredecessorRecursive(node.getRight(), value);
            node.setRight(ret);
        }

        return node;
    }

    // Used for deleting a node
    // replace with successor (Smallest element from the right side of the tree)
    private Node replaceWithSuccessorRecursive(Node node, int value) {
        if(node == null) {
            return null;
        }

        // Find the element
        if(value == node.getData()) {
            // delete the node
            if(node.isLeaf()) {
                return null;
            }
            if(node.getLeft() == null) {
                return node.getRight();
            }
            if(node.getRight() == null) {
                return node.getLeft();
            }

            Node smallestNode = findSmallest(node.getRight());
            node.setData(smallestNode.getData());
            node.setRight(replaceWithSuccessorRecursive(node.getRight(), smallestNode.getData()));
        }
        else if(value < node.getData()) {
            // it's on left side
            Node ret = replaceWithSuccessorRecursive(node.getLeft(), value);
            node.setLeft(ret);
        }
        else {
            // it's on right side
            Node ret = replaceWithSuccessorRecursive(node.getRight(), value);
            node.setRight(ret);
        }

        return node;

    }

    @Override
    public boolean delete(int value) {
        replaceWithPredecessorRecursive(root, value);
//        replaceWithSuccessorRecursive(root, value);
        return true;
    }

    private List<Integer> inOrderListRecursive(Node node) {
        List<Integer> result = new ArrayList<>();

        // Left, Root, Right
        if(node != null) {
            result.addAll(inOrderListRecursive(node.getLeft()));
            result.add(node.getData());
            result.addAll(inOrderListRecursive(node.getRight()));
        }
        return result;
    }

    @Override
    public List<Integer> traverseInOrder() {
        // Left, Root, Right
        return inOrderListRecursive(root);
    }

    private List<Integer> preOrderListRecursive(Node node) {
        List<Integer> result = new ArrayList<>();

        // Root, Left, Right
        if(node != null) {
            result.add(node.getData());
            result.addAll(preOrderListRecursive(node.getLeft()));
            result.addAll(preOrderListRecursive(node.getRight()));
        }
        return result;
    }

    @Override
    public List<Integer> traversePreOrder() {
        // Root, Left, Right
        return preOrderListRecursive(root);
    }

    private List<Integer> postOrderListRecursive(Node node) {
        List<Integer> result = new ArrayList<>();

        // Left, Right, Root
        if(node != null) {
            result.addAll(postOrderListRecursive(node.getLeft()));
            result.addAll(postOrderListRecursive(node.getRight()));
            result.add(node.getData());
        }
        return result;
    }

    @Override
    public List<Integer> traversePostOrder() {
        // Left, Right, Root
        return postOrderListRecursive(root);
    }

    private List<Integer> levelOrderListRecursive(List<Node> nodeList) {
        List<Integer> result = new ArrayList<>();
        if(nodeList.isEmpty()) {
            return result;
        }
        List<Node> nextNodeList = new ArrayList<>();
        for(Node node : nodeList) {
            result.add(node.getData());
            if(node.getLeft() != null) nextNodeList.add(node.getLeft());
            if(node.getRight() != null) nextNodeList.add(node.getRight());
        }
        result.addAll(levelOrderListRecursive(nextNodeList));
        return result;
    }

    @Override
    public List<Integer> traverseLeverOrder() {
        // From top to bottom level
        List<Node> nodeList = new ArrayList<>();
        nodeList.add(root);
        return levelOrderListRecursive(nodeList);
    }
}
