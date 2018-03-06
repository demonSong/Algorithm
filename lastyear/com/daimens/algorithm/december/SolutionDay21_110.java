package com.daimens.algorithm.december;

/**
 * 
 * @author Demon Song
 * 110.Balanced Binary Tree
 * Given a binary tree,determine if it is height-balanced.
 * For this problem,a height-balanced binary tree is defined as a binary tree 
 * in which the depth of the two subtrees of every node never differ by more than 1.
 * 
 * 2016.12.21 找寻题目之间解的相似性
 *
 */
public class SolutionDay21_110 {
	
	//判断是否为平衡树
	public boolean isBalanced(TreeNode root){
		if(root ==null) return true;
		int l = cntHeight(root.left);  
	    int r = cntHeight(root.right);  
	    if(l < 0 || r < 0 || Math.abs(l-r) > 1) return false;  
	    else return true; 
	}
	
	public int cntHeight(TreeNode root){
		if(root ==null) return 0;
		int l = cntHeight(root.left);
		int r = cntHeight(root.right);
		if (l < 0 || r < 0 || Math.abs(l-r) >1) return -1;
		else return Math.max(l,r)+1;
	}

}
