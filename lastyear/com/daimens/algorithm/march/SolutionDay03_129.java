package com.daimens.algorithm.march;

/**
 * 
 * @author Demon Song
 * 129.Sum Root to Leaf Numbers
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 * For example,
 *    1
 *   / \
 *  2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Return the sum = 12 + 13 = 25.
 *
 */
public class SolutionDay03_129 {
	public int sumNumbers(TreeNode root) {
		return sum(root, 0);
    }
	
	private int sum(TreeNode node,int sum){
		
		if (node == null)
			return 0;

		if (node.left == null && node.right == null)
			return sum * 10 + node.val;
		
		return sum(node.left, sum * 10 + node.val) + sum(node.right, sum * 10 + node.val);
	}
}

class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x){
		val = x;
	}
	
	@Override
	public String toString() {
		return "["+val+"]";
	}
}
