package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 98.Validate Binary Search Tree
 * Given a binary tree, determine if it is a valid binary search tree.
 * 
 * Assume a BST is a define as follows:
 * 1. The left subtree of a node contains only nodes with keys less than the node's key.
 * 2. The right subtree of a node contains only nodes with keys greater than the node's key.
 * 3. Both the left and right subtrees must also be binary search tree.
 * 
 * Example 1:
 *    2
 *   / \
 *  1   3
 * Binary tree [2,1,3],return true.
 * 
 * Example 2:
 *    1
 *   / \
 *  2   3
 * Binary tree [1,2,3],return false.
 *  
 */
public class SolutionDay29_098 {
	
	public boolean isValidBST(TreeNode root) {
		if(root == null) return true;
		
        inorder(root);
        return count1 == count2;
    }
	
	TreeNode prev;
	
	int count1 = 0,count2 = 1;
	private void inorder(TreeNode root){
		if(root == null) return;
		
		inorder(root.left);
		
		if(prev != null && root.val > prev.val){
			prev.val = root.val;
			count2++;
		}
		
		prev = root;
		count1 ++;
		inorder(root.right);
	}
}
