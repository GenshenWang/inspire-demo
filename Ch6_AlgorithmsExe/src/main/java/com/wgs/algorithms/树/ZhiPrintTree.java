package com.wgs.algorithms.树;

import java.util.ArrayList;
import java.util.List;

public class ZhiPrintTree {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        List<TreeNode> queueList = new ArrayList<>();
        queueList.add(root);
        int nextLevelNodeCount = 1;
        boolean jishu = true;
        while (!queueList.isEmpty()) {
            List<Integer> saveNodeValueTempList = new ArrayList<>(nextLevelNodeCount);
            int count = 0;
            for (int i = 0; i < nextLevelNodeCount; i++) {
                TreeNode node = queueList.remove(0);
                saveNodeValueTempList.add(node.val);

                if (node.left != null) {
                    queueList.add(node.left);
                    count++;
                }
                if (node.right != null) {
                    queueList.add(node.right);
                    count++;
                }

            }
            if (!jishu) {
                saveNodeValueTempList = reverseQueueList(saveNodeValueTempList);
            }
            jishu = !jishu;
            nextLevelNodeCount = count;
            result.add(saveNodeValueTempList);

        }

        return result;
    }

    private List<Integer> reverseQueueList(List<Integer> queueList) {
        List<Integer> result = new ArrayList<>();
        for (int i = queueList.size() - 1; i >= 0; i--) {
            result.add(queueList.get(i));
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

       new ZhiPrintTree().levelOrder(root);
    }

    static class TreeNode {
        private int val;
        private  TreeNode left;
        private  TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int rootVal,  TreeNode left,  TreeNode right) {
            this.val = rootVal;
            this.left = left;
            this.right = right;
        }
    }
}
