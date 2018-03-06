package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 538.Convert BST to Greater Tree
 * Given a binary search tree(BST), convert it to a greater tree such that every key of the original BST is 
 * changed to the original key plus sum of all keys greater than the original key in BST.
 * 
 * Example:
 * Input:The root of a Binary Search Tree like this:
 *             5
 *           /   \
 *          2     13
 * 
 * Output: The root of a Greater Tree like this:
 *            18
 *           /   \
 *         20     13
 *
 */
public class SolutionDay19_501 {
	
//	public TreeNode convertBST(TreeNode root) {
//        
//		
//		if(root == null) return null;
//		
//		convertBST(root.right);
//		convertBST(root.left);
//
//		//root 做操作
//		max = Integer.MIN_VALUE;
//		maxDFS(root.right);
//		root.val = root.val + ((max == Integer.MIN_VALUE) ? 0 : max); //要找最大的元素？
//		
//		//left 操作
//		if(root.left != null)
//			DFS(root.left, root.val);
//		
//		return root;
//    }
//	
//	private void  DFS(TreeNode node,int val){
//		if(node == null) return;
//		
//		node.val = node.val + val;
//		
//		DFS(node.left,val);
//		DFS(node.right,val);
//	}
//	
//	int max = Integer.MIN_VALUE;
//	private void maxDFS(TreeNode node){
//		if(node == null) {
//			return;
//		}
//		
//		max = Math.max(max, node.val);
//		
//		maxDFS(node.left);
//		maxDFS(node.right);
//		
//	}
	
	public TreeNode convertBST(TreeNode root) {
		if(root == null) return null;
		sum = 0;
		DFS(root);
		return root;
	}
	
	//为什么这种组装得来的结果是无比正确的？
	
	int sum;
	private void DFS(TreeNode root){
		
		if(root == null) return;
		
		DFS(root.right);   //子问题 1
		
		root.val = sum + root.val;
	    sum = root.val;
		
		DFS(root.left);   //子问题 2
		
	}
	
	public static void main(String[] args) {
		SolutionDay19_501 day = new SolutionDay19_501();
		
		TreeNode root = new TreeNode(2);
		TreeNode left = new TreeNode(5);
		TreeNode right = new TreeNode(13);
		
		root.left  = left;
		root.right = right;
		
		day.convertBST(root);
	}

}
