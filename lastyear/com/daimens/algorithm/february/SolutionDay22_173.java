package com.daimens.algorithm.february;

import java.util.Stack;

/**
 * 
 * @author Demon Song
 * 173.Binary Search Tree Iterator
 * Implement an iterator over a binary search tree.Your iterator will be initialized with the
 * root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory,where h is the height of the tree.
 *
 */
public class SolutionDay22_173 {
	
	private Stack<TreeNode> stack = new Stack<TreeNode>();
	
	public SolutionDay22_173(TreeNode root) {
        pushAll(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
    	TreeNode tmpNode = stack.pop();
    	pushAll(tmpNode.right);
    	return tmpNode.val;   
    }
    
    private void pushAll(TreeNode node){
    	for (; node != null; stack.push(node),node = node.left);
    }

}
