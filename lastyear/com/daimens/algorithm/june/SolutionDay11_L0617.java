package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         617. Merge Two Binary Trees
 * 
 *         Given two binary trees and imagine that when you put one of them to
 *         cover the other, some nodes of the two trees are overlapped while the
 *         others are not.
 * 
 *         You need to merge them into a new binary tree. The merge rule is that
 *         if two nodes overlap, then sum node values up as the new value of the
 *         merged node. Otherwise, the NOT null node will be used as the node of
 *         new tree.
 *
 */
public class SolutionDay11_L0617 {

//	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
//		if (t1 == null && t2 == null) return null;
//		TreeNode root = null;
//		if (t1 != null && t2 != null) {
//			root = new TreeNode(t1.val + t2.val);
//			root.left = mergeTrees(t1.left, t2.left);
//			root.right = mergeTrees(t1.right, t2.right);
//		} else if (t1 == null) {
//			root = t2;
//		} else if (t2 == null) {
//			root = t1;
//		}
//		return root;
//	}
	
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null) return null;
		TreeNode ret = new TreeNode((t1 != null ? t1.val : 0) + (t2 != null ? t2.val : 0));
		ret.left = mergeTrees(t1 != null ? t1.left : null, t2 != null ? t2.left : null);
		ret.right = mergeTrees(t1 != null ? t1.right : null, t2 != null ? t2.right : null);
		return ret;
	}

	public static void main(String[] args) {
		SolutionDay11_L0617 day = new SolutionDay11_L0617();
		TreeNode t1 = new TreeNode(1);
		t1.left = new TreeNode(3);
		t1.right = new TreeNode(2);

		TreeNode t2 = new TreeNode(1);
		t2.left = new TreeNode(3);

		day.mergeTrees(t1, t2);

	}

}
