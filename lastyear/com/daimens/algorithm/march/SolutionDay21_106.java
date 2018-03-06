package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 106.Construct Binary Tree from Inorder and Postorder Traversal
 * Given inorder and postorder traversal of a tree,construct the binary tree.
 * 
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 */
public class SolutionDay21_106 {
	
	//两者都是顺序的情况
//	public TreeNode buildTree(int[] inorder, int[] postorder) {
//		
//		if(inorder.length == 0 || postorder.length == 0) return null;
//		
//		if(isAscendant(inorder) && isAscendant(postorder)){
//			TreeNode root = new TreeNode(postorder[postorder.length-1]);
//			
//			TreeNode curr = root;
//			for (int i = postorder.length - 2; i >= 0; i--) {
//				TreeNode tmp = new TreeNode(postorder[i]);
//				curr.left = tmp;
//				curr = curr.left;
//			}
//			
//			return root;
//		}
//		
//		else{
//			List<Integer> io = new ArrayList<>();
//			List<Integer> po = new ArrayList<>();
//			
//			for (int i = 0; i < inorder.length;i++){
//				io.add(inorder[i]);
//				po.add(postorder[i]);
//			}
//			
//	        return build(io, po);
//		}
//    }
//	
//	private boolean isAscendant(int[] arrays){
//		for (int i = 1; i < arrays.length;i++){
//			if(arrays[i] - arrays[i-1] < 0) return false;
//		}
//		return true;
//	}
//	
//	private TreeNode build(List<Integer> inorder, List<Integer> postorder){
//		if(inorder.size() == 0 || postorder.size() == 0) return null;
//		
//		int last = postorder.get(postorder.size()-1);
//		TreeNode root = new TreeNode(last);
//		
//		//数组拆分
//		List<Integer> lfInorder = new ArrayList<>();
//		List<Integer> lfPostorder = new ArrayList<>();
//		
//		
//		
//		//在这里可以提升查找的效率，用hashmap记录下即可
//		
//		int i = 0;
//		for (i = 0; i < inorder.size(); i++){
//			if(inorder.get(i) == last){
//				break;
//			}
//			
//			lfInorder.add(inorder.get(i));
//			lfPostorder.add(postorder.get(i));
//		}
//		
//		List<Integer> rtInorder = new ArrayList<>();
//		List<Integer> rtPostorder = new ArrayList<>();
//		
//		for(int j = i+1; j < inorder.size(); j++){
//			rtInorder.add(inorder.get(j));
//			rtPostorder.add(postorder.get(j-1));
//		}
//		
//		root.left = build(lfInorder, lfPostorder);
//		root.right = build(rtInorder, rtPostorder);
//		
//		return root;
//	}
	
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		if (inorder.length == 0 || postorder.length == 0)
			return null;

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}

		return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
	}
	
	
	private TreeNode build(int[] inorder, int is, int ie, int[] postorder, int ps, int pe, Map<Integer, Integer> map) {
		if (ps > pe || is > ie)
			return null;

		int last = postorder[pe];
		TreeNode root = new TreeNode(last);

		int pos = map.get(last);
		root.left = build(inorder, is, pos - 1, postorder, ps, (pos - 1 -is) + ps, map);  //postOrder的坐标变化是什么意思？
		root.right = build(inorder, pos + 1, ie, postorder, pos + ps-is, pe-1, map); 

		return root;
	}
	
	
	
	
	

	public static void main(String[] args) {
		SolutionDay21_106 day = new SolutionDay21_106();
		
//		
//		int[] inorder = {6,2,1,8,4,9};
//		int[] postorder = {6,2,8,9,4,1};
		
		int[] inorder = {1,2};
		int[] postorder = {2,1};
		
		day.buildTree(inorder, postorder);
	}
}
