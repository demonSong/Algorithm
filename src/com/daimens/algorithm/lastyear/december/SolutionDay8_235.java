package com.daimens.algorithm.december;

/**
 * 
 * @author Demon Song
 * 235.Lowest Common Ancestor of a Binary Search Tree
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two 
 * nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to 
 * be a descendant of itself).”
 * For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of 
 * nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 */
public class SolutionDay8_235 {
	//找寻两个node的公共祖先问题
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || p == null || q ==null) return null;
		if (Math.max(p.val, q.val) < root.val){
			return lowestCommonAncestor(root.left, p, q);
		}else if(Math.min(p.val, q.val) > root.val){
			return lowestCommonAncestor(root.right, p, q);
		}else {
			return root;
		}
    }
}

class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x){
		val =x;
	}
}
