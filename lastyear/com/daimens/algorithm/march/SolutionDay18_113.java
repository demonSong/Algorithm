package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 113.Path Sum II
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum
 * equals the given sum.
 * For example:
 * 
 * Given the below binary tree and sum = 22.
 * 
 *             5
 *            / \
 *           4   8
 *          /   / \
 *         11  13  4
 *        /  \    / \
 *       7    2  5   1
 * return
 * [
 * 	[5,4,11,2],
 * 	[5,8,4,5]
 * ]
 *
 */
public class SolutionDay18_113 {
	
public List<List<Integer>> pathSum(TreeNode root, int sum) {
	    
	    if(root == null) return new ArrayList<>();
	    
		List<List<Integer>> ans = new ArrayList<>();
		backtrack(ans, new ArrayList<>(), root, sum,new TreeNode(Integer.MIN_VALUE));
		return ans;
    }
	
	//backtrack?
	private void backtrack(List<List<Integer>> ans,List<Integer> paths, TreeNode node, int sum,TreeNode prev){
		
		if(sum == 0 && prev.left == null && prev.right == null){
			ans.add(new ArrayList<Integer>(paths));
			return;
		}
		
	    if(node != null){
	    	
	    	prev = node;
    		paths.add(node.val);
    		
    		if(node.left == null && node.right == null){
    			backtrack(ans, paths, null, sum - node.val,prev);
    		}else{
    			backtrack(ans, paths, node.left, sum - node.val,prev);
        		backtrack(ans, paths, node.right, sum - node.val,prev);
        			
    		}
    		paths.remove(paths.size()-1);
	    }
	}
	
	public static void main(String[] args) {
		SolutionDay18_113 day = new SolutionDay18_113();
		
		TreeNode root = new TreeNode(7);
		
		TreeNode one = new TreeNode(-1);
		TreeNode two = new TreeNode(8);
		
		
		root.left = one;
		root.right = two;
		
		TreeNode three = new TreeNode(1);
		one.left = three;
		
		TreeNode four = new TreeNode(13);
		TreeNode five = new TreeNode(4);
		
		two.left = four;
		two.right = five;
		
		TreeNode six = new TreeNode(-7);
		TreeNode seven = new TreeNode(2);
		
		three.left= six;
		three.right = seven;
		
		TreeNode eight = new TreeNode(5);
		TreeNode nine = new TreeNode(1);
		
		five.left = eight;
		five.right = nine;
		
		day.pathSum(root, 0);
	}

}
