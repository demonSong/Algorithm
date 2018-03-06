package com.daimens.algorithm.march;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author DemonSong
 * 297.Serialize and Deserialize Binary Tree
 * Serialize is the process of converting a data structure or object into a sequence of bits so that it can be stored in
 * a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm
 * should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the
 * original tree structure.
 * For example,You may serialize the following tree
 *   1
 *  / \
 * 2   3
 *    / \
 *   4   5
 * as "[1,2,3,null,null,4,5]",ust the same as how LeetCode OJ serializes a binary tree. 
 * You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 *
 */
public class SolutionDay27_297 {
	
	// Encodes a tree to a single string.
    public String serialize(TreeNode root) {
    	if(root == null) return "";
    	
    	StringBuilder sb = new StringBuilder();
    	
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.add(root);
    	sb.append(root.val+",");
    	
    	while(!queue.isEmpty()){
    		
    		TreeNode node = queue.poll();
    		if(node.left != null){
    			sb.append(node.left.val+",");
    			queue.add(node.left);
    		}else{
    			sb.append("null"+",");
    		}
    		
    		if(node.right != null){
    			sb.append(node.right.val+",");
    			queue.add(node.right);
    		}else{
    			sb.append("null"+",");
    		}
    		
    	}
    	return sb.toString().substring(0,sb.length()-1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
    	if(data.length() == 0) return null;

		Queue<TreeNode> queue = new LinkedList<>();
		String[] nums = data.split(",");
		TreeNode root = new TreeNode(Integer.parseInt(nums[0]));
		queue.add(root);
		
		int index = 1;
		while(index < nums.length && !queue.isEmpty()){
			TreeNode node = queue.poll();
			
			if(!nums[index].equals("null")){
				node.left = new TreeNode(Integer.parseInt(nums[index++]));
				queue.add(node.left);
			}
			else {
				node.left = null;
				index++;
			}
			if(!nums[index].equals("null")){
				node.right = new TreeNode(Integer.parseInt(nums[index++]));
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
