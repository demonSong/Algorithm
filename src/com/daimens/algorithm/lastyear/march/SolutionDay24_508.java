package com.daimens.algorithm.march;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 508.Most Frequent Subtree Sum
 * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as
 * the sum of all the node values formed by the subtree rooted at that node (including the node itself.)　So
 * what is the most frequent subtree sum value ? If there is a tie, return all the values with the highest frequency in any order.
 * 
 * Example 1:
 * Input:
 *    5
 *  /  \
 * 2   -3
 * 
 * return [2,-3,4],since all the values happen only once, return all of them in any order.
 * Example 2:
 *    5
 *  /  \
 * 2   -5
 * 
 * return [2],since 2 happens twice, however -5 only occur once.
 * 
 * Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
 * 
 *
 */
public class SolutionDay24_508 {
	
	public int[] findFrequentTreeSum(TreeNode root) {
		
		Map<Integer,Integer> map = new HashMap<>(); 
		helper(root, map);
		
		int max = Integer.MIN_VALUE;
		for (int num : map.values()){
			if(num > max){
				max = num;
			}
		}
		
		int count = 0;
		for (int key : map.keySet()){
			if(map.get(key) == max){
				count++;
			}
		}
		
		int[] ans = new int[count];
		int i = 0;
		for (int key : map.keySet()){
			if(map.get(key) == max){
				ans[i++] = key;
			}
		}
		
        return ans;
    }
	
	
	private int helper(TreeNode root, Map<Integer,Integer> map){
		
		if(root == null) return 0;
		
		
//		if(root.left != null && root.right == null){
//			return root.left.val;
//		}
//		
//		if(root.right != null && root.left == null){
//			return root.right.val;
//		}
//		
//		if(root.left != null && root.right != null){
//			return root.left.val + root.right.val;
//		}
		
//		if(root.left == null && root.right == null){
//			return root.val;
//		}
		
		int leftSum = helper(root.left, map);
		int rightSum = helper(root.right, map);
		
		int sum = leftSum + rightSum + root.val;
		map.put(sum,map.getOrDefault(sum,0)+1);
		
		return leftSum + rightSum + root.val;
	}
	
	public static void main(String[] args) {
		SolutionDay24_508 day = new SolutionDay24_508();
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(2);
		root.right = new TreeNode(-3);
		
		day.findFrequentTreeSum(root);
	}

}
