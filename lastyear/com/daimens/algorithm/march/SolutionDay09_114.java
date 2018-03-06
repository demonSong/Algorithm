package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 114.Flatten Binary Tree to Linked List
 * Given a binary tree, flatten it to a linked list in-place.
 * For example,
 * Given
 *        1
 *       / \
 *      2   5
 *     / \   \
 *    3   4   6
 *
 * The flattened tree should look like:
 *  1
 *   \
 *    2
 *     \
 *      3
 *       \
 *        4
 *         \
 *          5
 *           \
 *            6
 * Hints:
 * If you notice carefully in the flattened tree,each node's right child points to the next node of a pre-order traversal.
 * 
 */
public class SolutionDay09_114 {
	
//	public void flatten (TreeNode root){
//		if(root == null) return;
//		
//		dfs(root);
//		
//		TreeNode cur = root;
//		for (int i = 1; i < nodes.size(); i++) {
//			TreeNode node = nodes.get(i);
//			cur.right = node;
//			cur = cur.right;
//		}
//	}
//	
//	private List<TreeNode> nodes = new ArrayList<>();
//	
//	private void dfs(TreeNode root){
//		
//		if(root.left == null && root.right == null) return;
//		
//		nodes.add(new TreeNode(root.val));
//		
//		if(root.left != null)
//			dfs(root.left);
//		if(root.right != null)
//			dfs(root.right);
//	}
	
	private TreeNode prev = null;
	
	public void flatten (TreeNode root){
		if(root == null) return;
		
		flatten(root.right);
		flatten(root.left);
		
		root.right = prev;
		root.left = null;
		prev = root;
	}
}
