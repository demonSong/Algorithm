package com.daimens.algorithm.february;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 * @author Demon Song
 * 449.Serialize and Deserialize BST
 * Serialize is the process of converting a data structure or object into a sequence of bits so
 * that it can be stored in a file or memory buffer,or transmitted across a network connection link
 * to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary search tree.Three is no restruction on
 * how your serialization/deserialization algorithm should work.You just need to ensure that a binary
 * search tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * The encoded string should be as compact as possible.
 * Note :
 * Do not use class member/global/static variables to store states.Your serialize and deserialize algorithms 
 * should be stateless.
 *
 */
public class SolutionDay14_449 {
	
	private static final String SEP = ",";
	private static final String NULL = "null";
	
	public String serialize(TreeNode root){
		StringBuilder sb = new StringBuilder();
		if(root == null) return NULL;
		
		Stack<TreeNode> st = new Stack<>();
		st.push(root);
		while(!st.empty()){
			root = st.pop();
			sb.append(root.val).append(SEP);
			if(root.right != null) st.push(root.right);
			if(root.left != null) st.push(root.left);
		}
		
		return sb.toString();
		
	}
	
	public TreeNode deserialize(String data){
		if(data.equals(NULL)) return null;
		String[] strs = data.split(SEP);
		Queue<Integer> q = new LinkedList<>();
		for (String e : strs){
			q.offer(Integer.parseInt(e));
		}
		return getNode(q);
	}
	
	private TreeNode getNode(Queue<Integer> q){
		if(q.isEmpty()) return null;
		TreeNode root = new TreeNode(q.poll());
		Queue<Integer> smallerQUeue = new LinkedList<>();
		while(!q.isEmpty() && q.peek() < root.val){
			smallerQUeue.offer(q.poll());
		}
		root.left = getNode(smallerQUeue);
		root.right = getNode(q);
		return root;
	}

}
