package com.daimens.algorithm.april;

public class SolutionDay08_L0814 {
	
    public TreeNode pruneTree(TreeNode root) {
    	TreeNode ret = null;
    	if (root == null) return ret;
    	if (ok(root)) {
    		ret = new TreeNode(root.val);
    		if (ok(root.left)) ret.left = pruneTree(root.left);
    		if (ok(root.right)) ret.right = pruneTree(root.right);
    	}
    	return ret;
    }
    
    
    public boolean ok(TreeNode root) {
    	if (root == null) return false;
    	
    	boolean valid = root.val == 1 ? true : false;
    	if (root.left != null) {
    		if (root.left.val == 1) valid = true;
    		valid |= ok(root.left);
    	}
    	if (root.right != null) {
    		if (root.right.val == 1) valid |= true;
    		valid |= ok(root.right);
    	}
    	
    	return valid;
    }
	
	public static void main(String[] args) {
		SolutionDay08_L0814 day = new SolutionDay08_L0814();
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(0);
		root.right.left = new TreeNode(0);
		root.right.right = new TreeNode(1);
		System.out.println(day.pruneTree(root));
	}
}
