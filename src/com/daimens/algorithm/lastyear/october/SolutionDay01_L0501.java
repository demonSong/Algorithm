package com.daimens.algorithm.october;

public class SolutionDay01_L0501 {
	
    public int longestUnivaluePath(TreeNode root) {
		if (root == null) return 0;
		int val = root.val;
		int res = 0;
		if (root.left != null && root.left.val == val) {
			res = 1 + dfs(root.left);
		}
		if (root.right != null && root.right.val == val) {
			res += 1 + dfs(root.right);
		}
		return Math.max(res, Math.max(longestUnivaluePath(root.left), longestUnivaluePath(root.right)));
    }
    
    public int dfs(TreeNode root) {
    	if (root == null) return 0;
    	int res = 0;
    	int val = root.val;
    	
    	if (root.left != null && root.left.val == val) {
    		res = 1 + dfs(root.left);
    	}
    	
    	if (root.right != null && root.right.val == val) {
    		res = Math.max(res, 1 + dfs(root.right));
    	}
    	return res;
    }
    
    
//	public int dfs(TreeNode root) {
//		if (root == null) return 0;
//		int val = root.val;
//		int res = 0;
//		if (root.left != null && root.left.val == val) {
//			int lf = dfs(root.left);
//			res = 1 + lf;
//		}
//		
//		if (root.right != null && root.right.val == val) {
//			int rt = dfs(root.right);
//            res += 1 + rt;
//        }
//		return res;
//	}

	
	public static void main(String[] args) {
		SolutionDay01_L0501 day = new SolutionDay01_L0501();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(4);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(5);
		System.out.println(day.longestUnivaluePath(root));
	}
	
}
