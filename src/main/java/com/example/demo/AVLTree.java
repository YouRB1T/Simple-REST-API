package com.example.demo;

import java.util.ArrayList;

public class AVLTree<T> {

    private class Node {
        T data;
        int key;
        int height;
        Node left, right;

        Node(int key, T data) {
            this.key = key;
            this.data = data;
            this.height = 1;
        }
    }

    private Node root;

    public void insert(int key, T data) {
        root = insert(root, key, data);
    }

    private Node insert(Node node, int key, T data) {
        if (node == null) return new Node(key, data);

        if (key < node.key) {
            node.left = insert(node.left, key, data);
        } else if (key > node.key) {
            node.right = insert(node.right, key, data);
        } else {
            node.data = data;
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    public T get(int key) {
        Node node = get(root, key);
        return node != null ? node.data : null;
    }

    private Node get(Node node, int key) {
        if (node == null) return null;
        if (key < node.key) return get(node.left, key);
        else if (key > node.key) return get(node.right, key);
        else return node;
    }

    private class DeleteResult {
        Node newRoot;
        Node deletedNode;

        DeleteResult(Node newRoot, Node deletedNode) {
            this.newRoot = newRoot;
            this.deletedNode = deletedNode;
        }
    }

    public T delete(int key) {
        DeleteResult result = delete(root, key);
        root = result.newRoot;
        return result.deletedNode != null ? result.deletedNode.data : null;
    }

    private DeleteResult delete(Node node, int key) {
        if (node == null) return new DeleteResult(null, null);

        Node deletedNode;

        if (key < node.key) {
            DeleteResult result = delete(node.left, key);
            node.left = result.newRoot;
            deletedNode = result.deletedNode;
        } else if (key > node.key) {
            DeleteResult result = delete(node.right, key);
            node.right = result.newRoot;
            deletedNode = result.deletedNode;
        } else {
            deletedNode = node;
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                Node minNode = minValueNode(node.right);
                node.key = minNode.key;
                node.data = minNode.data;
                node.right = delete(node.right, minNode.key).newRoot;
            }
        }

        if (node == null) return new DeleteResult(null, deletedNode);

        node.height = 1 + Math.max(height(node.left), height(node.right));
        return new DeleteResult(balance(node), deletedNode);
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) current = current.left;
        return current;
    }

    public ArrayList<T> toList() {
        ArrayList<T> result = new ArrayList<>();
        toList(root, result);
        return result;
    }

    private void toList(Node node, ArrayList<T> result) {
        if (node != null) {
            toList(node.left, result);
            result.add(node.data);
            toList(node.right, result);
        }
    }

    private int height(Node node) { return node == null ? 0 : node.height; }

    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private Node balance(Node node) {
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0) return rightRotate(node);
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) return leftRotate(node);
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }
}

