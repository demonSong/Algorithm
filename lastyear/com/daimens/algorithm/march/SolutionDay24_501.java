package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 501.Find Mode in Binary Search Tree
 * Given a binary search tree(BST) with duplicates,find all the mode(s) (the most frequently occurred element) in
 * the given BST.
 * 
 * Assume a BST is defined as follows:
 * 1. The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * 2. The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * 3. Both the left and right subtrees must also be binary search trees.
 * For example:
 * Given BST [1,null,2,2]
 *   1
 *    \
 *     2
 *    /
 *   2
 * return [2].
 * Note: If a tree has more than one mode,you can return them in any order.
 * Follow up: Could you do that without using any extra space?(Assume that the implicit stack space incurred due
 * to recursion does not count.)
 * 
 * 
 *
 */
public class SolutionDay24_501 {
	
	public int[] findMode(TreeNode root) {
		
		if(root == null) return new int[]{};
		Map<Integer,Integer> map = new HashMap<>();
		dfs(root, map);
		
		List<Integer> ans = new ArrayList<>();
		for(int key : map.keySet()){
			if(map.get(key) == max){
				ans.add(key);
			}
		}
		
		int[] res = new int[ans.size()];
		
		for(int i = 0; i < ans.size(); i++){
			res[i] = ans.get(i);
		}
		
		return res;
    }
	
	int max = Integer.MIN_VALUE;
	private void dfs(TreeNode root,Map<Integer,Integer> map){
		
		if(root == null) return;
		
		map.put(root.val, map.getOrDefault(root.val, 0)+1);
		max = Math.max(max, map.get(root.val));
		
		dfs(root.left, map);
		dfs(root.right, map);
		
	}
	
	public static void main(String[] args) {
		SolutionDay24_501 day = new SolutionDay24_501();
		day.findMode(new TreeNode(2147483647));
	}
	
	
	
}
