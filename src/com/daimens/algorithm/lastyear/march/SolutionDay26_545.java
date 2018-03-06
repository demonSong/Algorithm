package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SolutionDay26_545 {

	public List<Integer> boundaryOfBinaryTree(TreeNode root) {
		if (root == null)
			return new ArrayList<>();

		LinkedList<Integer> left = new LinkedList<>();
		left.add(root.val);

		if (root.left != null) {
			leftDFS(root.left, left);
			left.remove(left.size() - 1);
		}

		leavesDFS(root.left, left);
		leavesDFS(root.right, left);

		LinkedList<Integer> right = new LinkedList<>();
		rightDFS(root.right, right);

		for (int i = 1; i < right.size(); i++) {
			left.add(right.get(i));
		}

		return left;
	}


	
	private void leftDFS(TreeNode root, LinkedList<Integer> left) {
		left.add(root.val);

		if (root.left == null && root.right == null)
			return;

		if (root.left != null) {
			leftDFS(root.left, left);
		} else if (root.right != null) {
			leftDFS(root.right, left);
		}
	}

	private void rightDFS(TreeNode root, LinkedList<Integer> right) {
		if (root == null)
			return;

		right.addFirst(root.val);

		if (root.left == null && root.right == null)
			return;

		if (root.right != null) {
			rightDFS(root.right, right);
		}

		else if (root.left != null) {
			rightDFS(root.left, right);
		}
	}

	private void leavesDFS(TreeNode root, LinkedList<Integer> leaves) {
		if (root == null)
			return;

		if (root.left == null && root.right == null) {
			leaves.add(root.val);
		}

		leavesDFS(root.left, leaves);
		leavesDFS(root.right, leaves);
	}
	
//	private void leftDfs(TreeNode root, LinkedList<Integer> left) {
//	Stack<TreeNode> stack = new Stack<>();
//	TreeNode curr = root;
//
//	while (!stack.isEmpty() || curr != null) {
//
//		if (curr != null) {
//			stack.push(curr);
//			left.add(curr.val);
//			curr = curr.left;
//		} else {
//			TreeNode node = stack.pop();
//			curr = node.right;
//			if (curr == null) {
//				break;
//			}
//		}
//	}
//}
	

//	private void rightDfs(TreeNode root, LinkedList<Integer> right) {
//
//		Stack<TreeNode> stack = new Stack<>();
//		TreeNode curr = root;
//
//		while (!stack.isEmpty() || curr != null) {
//			if (curr != null) {
//				stack.push(curr);
//				right.addFirst(curr.val);
//				curr = curr.right;
//			} else {
//				TreeNode node = stack.pop();
//				curr = node.left;
//				if (curr == null) {
//					break;
//				}
//			}
//		}
//	}

	public static void main(String[] args) {
		SolutionDay26_545 day = new SolutionDay26_545();

		TreeNode root = new TreeNode(1);

		day.boundaryOfBinaryTree(root);
	}

}
