package com.daimens.algorithm.may;

/**
 * 
 * @author DemonSong
 * 
 *         572.Subtree of Another Tree
 * 
 *         Given two non-empty binary trees s and t, check whether tree t has
 *         exactly the same structure and node values with a subtree of s. A
 *         subtree of s is a tree consists of a node in s and all of this node's
 *         descendants. The tree s could also be considered as a subtree of
 *         itself.
 * 
 *         Example 1: Given tree s:
 * 
 *         3 / \ 4 5 / \ 1 2 Given tree t: 4 / \ 1 2 Return true, because t has
 *         the same structure and node values with a subtree of s. Example 2:
 *         Given tree s:
 * 
 *         3 / \ 4 5 / \ 1 2 / 0 Given tree t: 4 / \ 1 2 Return false.
 *
 */
public class SolutionDay07_572 {

	// public boolean isSubtree(TreeNode s, TreeNode t) {
	// if (s == null && t == null) return true;
	//
	// if (s == null) return false;
	//
	// List<TreeNode> t1 = new ArrayList<>();
	// List<TreeNode> t2 = new ArrayList<>();
	//
	// preOrder(t, t1);
	// inOrder(t, t2);
	//
	// List<TreeNode> s1 = new ArrayList<>();
	// List<TreeNode> s2 = new ArrayList<>();
	//
	// preOrder(s, s1);
	// inOrder(s, s2);
	//
	// if (t1.size() == s1.size()){
	// for (int i = 0; i < t1.size(); i++){
	// if (t1.get(i).val != s1.get(i).val) return false;
	// }
	//
	// for (int i = 0; i < t1.size(); i++){
	// if (t2.get(i).val != s2.get(i).val) return false;
	// }
	// return true;
	// }
	//
	// return isSubtree(s.left, t) || isSubtree(s.right, t);
	// }
	//
	// private void preOrder(TreeNode root, List<TreeNode> list){
	// if (root == null) return;
	// list.add(root);
	// preOrder(root.left, list);
	// preOrder(root.right, list);
	// }
	//
	// private void inOrder(TreeNode root, List<TreeNode> list){
	// if (root == null) return;
	// inOrder(root.left, list);
	// list.add(root);
	// inOrder(root.right, list);
	// }

	public boolean isSubtree(TreeNode s, TreeNode t) {
		if (t == null)
			return true;
		if (s == null)
			return false;
		return isSame(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
	}

	private boolean isSame(TreeNode s, TreeNode t) {
		if (s == null && t == null)
			return true;
		if (s == null || t == null)
			return false;
		return s.val == t.val && isSame(s.left, t.left) && isSame(s.right, t.right);
	}

	public static void main(String[] args) {
		SolutionDay07_572 day = new SolutionDay07_572();
		TreeNode s = new TreeNode(3);
		s.left = new TreeNode(4);
		s.right = new TreeNode(5);

		s.left.left = new TreeNode(1);
		s.left.right = new TreeNode(2);

		TreeNode t = new TreeNode(4);
		t.left = new TreeNode(1);
		t.right = new TreeNode(2);

		day.isSubtree(s, t);
	}

}
