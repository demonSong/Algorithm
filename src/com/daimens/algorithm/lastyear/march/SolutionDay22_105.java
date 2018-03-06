package com.daimens.algorithm.march;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 105.Construct Binary Tree from Preorder and inorder Traversal
 * Given preorder and inorder traversal of a tree,construct the binary tree.
 * 
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 */
public class SolutionDay22_105 {

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		
		if(preorder.length == 0 || inorder.length == 0) return null;
		
		Map<Integer,Integer> map = new HashMap<>();
		for(int i = 0; i < inorder.length; i++){
			map.put(inorder[i], i);
		}
		return build(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, map);
	}
	
	private TreeNode build(int[] preorder, int ps , int pe, int[] inorder,int is, int ie,Map<Integer,Integer> map){
		
		if(pe < ps || ie < is){
			return null;
		}
		
		int first = preorder[ps];
		int pos = map.get(first);
		
		TreeNode root = new TreeNode(first);
		
		//left tree
		root.left = build(preorder, ps+1, ps+pos-is, inorder, is, pos-1, map);
		//right tree
		root.right = build(preorder, ps+pos-is+1, pe, inorder, pos+1, ie, map);
		
		return root;
	}
	

}
