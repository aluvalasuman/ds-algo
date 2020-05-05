package com.example;

import java.lang.management.BufferPoolMXBean;

/*
 * Checks if binary tree is a Binary Search Tree or not.
 */
public class IsTreeBst {

    static class BinaryTreeNode {

        public BinaryTreeNode(int data) {
            this.data = data;
        }

        public int data;
        public BinaryTreeNode left;
        public BinaryTreeNode right;
    }

    private static boolean IsNodeBst(BinaryTreeNode node, int min, int max) {
        if (node == null) {
            return true;
        }

        if (node.data < min || node.data > max) {
            return false;
        }

        return (IsNodeBst(node.left, min, node.data - 1) && IsNodeBst(node.right, node.data + 1, max));
    }

    public static boolean IsTreeBst(BinaryTreeNode rootNode) {
        return IsNodeBst(rootNode, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static void main(String[] args) {
        BinaryTreeNode btn = new BinaryTreeNode(10);
        btn.left = new BinaryTreeNode(8);
        btn.right = new BinaryTreeNode(12);
        btn.left.left = new BinaryTreeNode(7);
        btn.left.right = new BinaryTreeNode(9);
        btn.right.left = new BinaryTreeNode(11);
        btn.right.right = new BinaryTreeNode(13);

        System.out.println("Tree is BST: " + IsTreeBst(btn));
    }
}
