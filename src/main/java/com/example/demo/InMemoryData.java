package com.example.demo;

import com.sun.source.tree.Tree;

public class InMemoryData {
    private AVLTree avlTree = new AVLTree();

    public InMemoryData() {
        this.avlTree = avlTree;
    }

    public AVLTree getAvlTree() {
        return avlTree;
    }

    public void setAvlTree(AVLTree avlTree) {
        this.avlTree = avlTree;
    }

    @Override
    public String toString() {
        return "InMemoryData{" +
                "avlTree=" + avlTree +
                '}';
    }
}
