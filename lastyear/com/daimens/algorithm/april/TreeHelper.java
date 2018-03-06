package com.daimens.algorithm.april;

import java.util.LinkedList;
import java.util.Queue;

public class TreeHelper {
	
	public static TreeNode buildTree(int[] nums,int nul){
		
		if(nums.length == 0) return null;

		Queue<TreeNode> queue = new LinkedList<>();
		TreeNode root = new TreeNode(nums[0]);
		queue.add(root);
		
		int index = 1;
		while(index < nums.length && !queue.isEmpty()){
			TreeNode node = queue.poll();
			
			if(nums[index] != nul){
				node.left = new TreeNode(nums[index++]);
				queue.add(node.left);
			}
			else {
				node.left = null;
				index++;
			}
			if(nums[index] != nul){
				node.right = new TreeNode(nums[index++]);
				queue.add(node.right);
			}
			else {
				node.right = null;
				index++;
			}
		}
			
		return root;
	}

}
