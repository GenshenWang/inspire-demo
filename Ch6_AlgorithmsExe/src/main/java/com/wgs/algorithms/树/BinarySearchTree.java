package com.wgs.algorithms.树;

/**
 * 二叉查找树
 */
public class BinarySearchTree {

    private Node root;

    public Node find(int target) {
        Node p = root;
        while (p != null) {
            if (target > p.val) {
                p = p.right;
            } else if (target < p.val) {
                p = p.left;
            } else {
                return p;
            }
        }

        return null;
    }

    /**
     * 插入
     * @param val
     */
    public void insert(int val) {
        if (root == null) {
            root = new Node(val);
            return;
        }

        Node p = root;
        Node insertNode = new Node(val);
        while (p != null) {
            if (val > p.val) {
                if (p.right == null) {
                    p.right = insertNode;
                    return;
                }

                p = p.right;
            } else {
                if (p.left == null) {
                    p.left = insertNode;
                    return;
                }

                p = p.left;
            }
        }

    }

    static class Node {
        private int val;
        private Node left;
        private Node right;

        public Node(int val) {
            this.val = val;
        }
    }
}
