package com.daimens.algorithm.feburary;

public class SolutionDay11_L0783 {
	
    public int minDiffInBST(TreeNode root) {
    	min = 0x3f3f3f3f;
    	prv = -1;
    	solve(root);
        return min;
    }
    int min = 0x3f3f3f3f;
    int prv = -1;
    
    void solve(TreeNode root) {
    	if (root == null) return;
    	solve(root.left);
    	if (prv != -1) {
    		min = Math.min(min, root.val - prv);
    	}
    	prv = root.val;
    	solve(root.right);
    }
	
	public static void main(String[] args) {
		SolutionDay11_L0783 day = new SolutionDay11_L0783();
	}

}
