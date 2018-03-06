package com.daimens.algorithm.december;

/**
 * 
 * @author Demon Song
 * 101.Symmetric Tree
 * Given a binary tree,check whether it is a minor of itself(ie,symmetric around its center)
 * For example,this binary tree([1,2,2,3,4,4,3])is symmetric
 * But the following[1,2,2,null,3,null,3] is not
 *
 */
public class SolutionDay15_101 {
	
	public boolean isSymmetric(TreeNode root){
		return isMirror(root, root);
	}
	
	//递归的思想
	public boolean isMirror(TreeNode t1,TreeNode t2){
		if(t1 ==null && t2 ==null) return true;
		if(t1 ==null || t2 ==null) return false;
		return (t1.val == t2.val)
			&& isMirror(t1.right, t2.left)
			&& isMirror(t1.left, t2.right);
	}
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(4);
		SolutionDay15_101 day15_101 = new SolutionDay15_101();
		day15_101.isSymmetric(root);
	}
}
