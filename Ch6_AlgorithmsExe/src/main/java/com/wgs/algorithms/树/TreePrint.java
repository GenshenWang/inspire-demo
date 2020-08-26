package com.wgs.algorithms.树;

import javax.swing.tree.TreeNode;
import java.util.List;
import java.util.Stack;

public class TreePrint {

    /**
     * 前序遍历
     * @param root
     */
    public void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 中序遍历
     * @param root
     */
    public void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    /**
     * 后序遍历
     * @param root
     */
    public void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    private void inOrder2(TreeNode root, List<Integer> result) {
        Stack<TreeNode> stack = new Stack();
        while (root != null || !stack.isEmpty()) {
            // 先遍历左节点
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                // 左节点为空之后， 就打印，再转向右节点
                TreeNode currentNode = stack.pop();
                result.add(currentNode.val);
                root = currentNode.right;
            }
        }
    }

    private void postOrder2(TreeNode root, List<Integer> result) {
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();

            // 第二个条件表示被访问过了
            if (root.right == null || (!result.isEmpty() && root.right.val == result.get(result.size() - 1))) {
                result.add(root.val);
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }


    public static void main(String[] args) {
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        root.left = node2;
        root.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        TreePrint treePrint = new TreePrint();
        treePrint.preOrder(root);
        System.out.println("====");
        treePrint.inOrder(root);
        System.out.println("====");
        treePrint.postOrder(root);
    }

    static class Node {
        private int val;
        private Node left;
        private Node right;

        public Node(int val) {
            this.val = val;
        }

        public Node(int rootVal, Node left, Node right) {
            this.val = rootVal;
            this.left = left;
            this.right = right;
        }
    }
}
