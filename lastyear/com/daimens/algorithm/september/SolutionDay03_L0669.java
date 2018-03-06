package com.daimens.algorithm.september;

public class SolutionDay03_L0669 {
	
	public TreeNode trimBST(TreeNode root, int L, int R) {
		if (root == null) return null;
		if (root.val < L){
			return trimBST(root.right, L, R);
		}
		else if (root.val > R){
			return trimBST(root.left, L, R);
		}
		else {
			TreeNode ans = new TreeNode(root.val);
			ans.left = trimBST(root.left, L, R);
			ans.right = trimBST(root.right, L, R);
			return ans;
		}
    }
	
	public static void main(String[] args) {
		SolutionDay03_L0669 day = new SolutionDay03_L0669();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(0);
		root.right = new TreeNode(2);
		
	}

}
