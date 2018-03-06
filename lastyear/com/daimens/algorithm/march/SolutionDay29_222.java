package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 222.Count Complete Tree Nodes
 * Given a complete binary tree, count the number of nodes.
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree level, except possibly the last, is completely filled, and all nodes in the last level are as far
 * left as possible.
 * It can have between 1 and 2^h nodes inclusive at the last level h.
 *
 */
public class SolutionDay29_222 {
	
	//time limit
//	int count = 0;
//	private void dfs(TreeNode root){
//		if(root == null){
//			return;
//		}
//		
//		count ++;
//	
//		dfs(root.left);
//		dfs(root.right);
//	}
	
//	int count = 0;
//	public int countNodes(TreeNode root) {
//		if(root == null) return 0;
//		
//		dfs(root);
//		if(layer == 0) return 1;
//		
//		count = (int) Math.pow(2, layer+1) - 1;
//		
//		
//		TreeNode curr = root;
//		Stack<TreeNode> stack = new Stack<>();
//		
//		while(!stack.isEmpty() || curr != null){
//			
//			if(curr != null){
//				stack.push(curr);
//				curr = curr.right;
//			}else{
//				TreeNode node = stack.pop();
//				if(node.left == null){
//					count -= 2;
//				}else if(node.left != null){
//					count -= 1;
//					break;
//				}
//			}
//		}
//		
//		
//		return count;
//    }
//	
//	int layer = 0;
//	private void dfs(TreeNode root){
//		if(root.left == null) return;
//		layer ++;
//		if(root.left != null)
//			dfs(root.left);
//	}
	
	
	
	
	private int height(TreeNode root){
		if (root == null) return -1;
		return 1+height(root.left);
	}
	
	public int countNodes(TreeNode root) {
		int h = height(root);
		return h < 0 ? 0
				: height(root.right) == h - 1 ? (1 << h) + countNodes(root.right)
						: (1 << h - 1) + countNodes(root.left);
	}
	
	
	public static void main(String[] args) {
		SolutionDay29_222 day = new SolutionDay29_222();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		day.countNodes(root);
	}
}
