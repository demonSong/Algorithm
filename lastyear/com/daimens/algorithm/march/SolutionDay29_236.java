package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 236.Lowest Common Ancestor of a Binary Tree
 * Given a binary tree, find the lowest common ancestor(LCA) of two given nodes in the tree.
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor is defined between two nodes v and w as the lowest
 * node in T that has both v and w as descendants (where we allow a node to be a descendant of itself.)"
 *        ______3______
 *       /              \
 *    ___5__          ___1__
 *   /      \        /      \
 *   6      _2_      0       8
 *         /   \
 *         7    4
 * 
 * For example,the lowest common ancestor(LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node
 * can be a descendant of itself according to the LCA definition.
 *
 */
public class SolutionDay29_236 {
	
	
//	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//		if(root == null) return null;
//		boolean isLCA = false;
//		dfs(p, q, isLCA);
//		if(isLCA) return p;
//		
//		isLCA = false;
//		dfs(q, p, isLCA);
//		if(isLCA) return q;
//		
//		return null;
//    }
//	
//	private void dfs(TreeNode parent , TreeNode target, boolean isLCA){
//		
//		if(parent == null) return;
//		
//		if(parent.val == target.val){
//			//LCA
//			isLCA = true;
//		}
//		
//		dfs(parent.left,target,isLCA);
//		dfs(parent.right, target,isLCA);
//	}
	
//	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//		if(root == null) return null;
//		
//		if (isLCA(root.left, p) && isLCA(root.right, q))
//			return root;
//		else if (isLCA(root, p) && isLCA(root.left, q) || isLCA(root, q) && isLCA(root.left, p)) {
//			
//			if(root.left.val == p.val){
//				return p;
//			}
//			
//			if(root.left.val == q.val)
//				return q;
//			
//			return lowestCommonAncestor(root.left, p, q);
//		} else if (isLCA(root, p) && isLCA(root.right, q) || isLCA(root, q) && isLCA(root.right, p)) {
//			
//			if(root.right.val == p.val){
//				return p;
//			}
//			
//			if(root.right.val == q.val){
//				return q;
//			}
//			
//			return lowestCommonAncestor(root.right, p, q);
//		} else {
//			return null;
//		}
//	}
//	
//	
//	private boolean isLCA(TreeNode root, TreeNode p){
//		
//		if(root == null) return false;
//
//		if(root.val == p.val) return true;
//		
//		if(root.left != null)
//			return isLCA(root.left, p);
//		
//		if(root.right != null)
//			return isLCA(root.right, p);
//		
//		return false;
//	}
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		
		if(root == null || root == p || root == q) return root;
		
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		
		if(left != null && right != null) return root;

		return left != null ? left : right;
	}
	
	
	public static void main(String[] args) {
		SolutionDay29_236 day = new SolutionDay29_236();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		
		day.lowestCommonAncestor(root, root, root.left);
	}
	
}
