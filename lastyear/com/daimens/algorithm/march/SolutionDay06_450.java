package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 450.Delete Node in a BST
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST.
 * Return the root node reference(possibly update) of the BST.
 * Basically, the deletion can be divided into two stages:
 * 1. Search for a node to remove.
 * 2. If the node is found, delete the node.
 * Note: Time complexity should be O(height of tree).
 * Example:
 * root = [5,3,6,2,4,null,7]
 * key = 3
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 * Given key to delete is 3. So we find the node with value 3 and delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
 *     5
 *    / \
 *   4   6
 *  /     \
 * 2       7
 * Another valid answer is [5,2,6,null,4,null,7].
 *    5
 *   / \
 *  2   6
 *   \   \
 *    4   7
 *
 */
public class SolutionDay06_450 {
	
	public TreeNode deleteNode(TreeNode root, int key) {
		TreeNode cur = root;
		TreeNode pre = null;
		//双指针
		while (cur != null && cur.val != key){
			pre = cur;
			if(key < cur.val){
				cur = cur.left;
			}else if (key > cur.val){
				cur = cur.right;
			}
		}
		
		if(pre == null){
			return deleteRootNode(cur);
		}
		if(pre.left == cur){
			pre.left = deleteRootNode(cur);
		}else{
			pre.right = deleteRootNode(cur);
		}
		return root;
    }
	
	private TreeNode deleteRootNode(TreeNode root){
		if(root == null){
			return null;
		}
		if(root.left == null){
			return root.right;
		}
		if(root.right == null){
			return root.left;
		}
		
		TreeNode next = root.right;
		TreeNode pre = null;
		
		for (;next.left != null; pre = next,next = next.left);
		next.left = root.left;
		if(root.right != next){
			pre.left = next.right;
			next.right = root.right;
		}
		return next;
	}
}
