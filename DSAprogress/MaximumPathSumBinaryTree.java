import java.util.*;

public class MaximumPathSumBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private int maxSum;

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        calculateDownPath(root);
        return maxSum;
    }

    private int calculateDownPath(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftGain = Math.max(0, calculateDownPath(node.left));
        int rightGain = Math.max(0, calculateDownPath(node.right));

        int currentPathSum = node.val + leftGain + rightGain;
        maxSum = Math.max(maxSum, currentPathSum);

        return node.val + Math.max(leftGain, rightGain);
    }
}
