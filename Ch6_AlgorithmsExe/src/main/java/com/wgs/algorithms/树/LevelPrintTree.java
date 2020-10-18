package com.wgs.algorithms.树;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树层序遍历，每层打印
 */
public class LevelPrintTree {

    public List<List<Integer>> levelPrintTree(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        int nextToBePrintCount = 1;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> levelList = new ArrayList<>();
            int count = 0;
            for (int i = 0; i < nextToBePrintCount; i++) {
                TreeNode tempNode = queue.poll();
                levelList.add(tempNode.val);
                
                if (tempNode.left != null) {
                    queue.add(tempNode.left);
                    count++;
                }
                if (tempNode.right != null) {
                    queue.add(tempNode.right);
                    count++;
                }
                
            }

            result.add(levelList);

            nextToBePrintCount = count;
            count = 0;
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);

        root.left = node2;
        root.right = node3;

        node2.left = null;
        node2.right = null;

        node3.left = node4;
        node3.right = node5;

        node4.left = null;
        node4.right = null;
        node5.left = null;
        node5.right = null;

        new LevelPrintTree().levelPrintTree(root);

    }

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
