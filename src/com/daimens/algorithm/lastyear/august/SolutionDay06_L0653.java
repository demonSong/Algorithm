package com.daimens.algorithm.august;

import java.util.HashSet;

public class SolutionDay06_L0653 {
	
//	public boolean findTarget(TreeNode root, int k) {
//        if (root == null) return false;
//		nodes = new ArrayList<>();
//        inorder(root);
//        int lf = 0, rt = nodes.size() - 1;
//        while (lf < rt){
//        	int sum = nodes.get(lf) + nodes.get(rt);
//        	if (sum < k){
//        		lf ++;
//        	}
//        	else if (sum > k){
//        		rt --;
//        	}
//        	else{
//        		return true;
//        	}
//        }
//        return false;
//	}
//	
//	List<Integer> nodes;
//	public void inorder(TreeNode root){
//		if (root == null) return;
//		inorder(root.left);
//		nodes.add(root.val);
//		inorder(root.right);
//	}
	
	public boolean findTarget(TreeNode root, int k) {
		return find(root, new HashSet<>(), k);
	}
	
	public boolean find(TreeNode root, HashSet<Integer> mem, int k){
		if (root == null) return false;
		if (mem.contains(k - root.val)) return true;
		mem.add(root.val);
		return find(root.left, mem, k) || find(root.right, mem, k);
	}
	

	public static void main(String[] args) {
		SolutionDay06_L0653 day = new SolutionDay06_L0653();
	}

}
