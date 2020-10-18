package com.wgs.algorithms.树;

import java.util.*;

/**
 * 从上到下打印二叉树
 */
public class TopBottomPrintTree {

    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int nextToBePrint = 1;
        while (!queue.isEmpty()) {

            int count = 0;
            for (int i = 0; i < nextToBePrint; i++) {
                TreeNode tempNode = queue.poll();
                list.add(tempNode.val);

                if (tempNode.left != null) {
                    count++;
                    queue.add(tempNode.left);
                }

                if (tempNode.right != null) {
                    count++;
                    queue.add(tempNode.right);
                }
            }
            nextToBePrint = count;
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
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
