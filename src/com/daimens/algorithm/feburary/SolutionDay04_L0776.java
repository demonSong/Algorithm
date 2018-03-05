package com.daimens.algorithm.feburary;

public class SolutionDay04_L0776 {
	
//	public TreeNode[] splitBST(TreeNode root, int V) {
//		if (root == null) return new TreeNode[] {null, null};
//		List<Integer> nodes = new ArrayList<>();
//		Queue<TreeNode> q = new ArrayDeque<>();
//		q.offer(root);
//		while (!q.isEmpty()) {
//			TreeNode now = q.poll();
//			nodes.add(now.val);
//			if (now.left != null) {
//				q.offer(now.left);
//			}
//			if (now.right != null) {
//				q.offer(now.right);
//			}
//		}
//		List<Integer> left = new ArrayList<>();
//		List<Integer> right = new ArrayList<>();
//		for (int val : nodes) {
//			if (val <= V) left.add(val);
//			else right.add(val);
//		}
//		
//		TreeNode leftNode = null;
//		for (int val : left) leftNode = build(leftNode, val);
//		
//		TreeNode rightNode = null;
//		for (int val : right) rightNode = build(rightNode, val);
//		
//		return new TreeNode[] {leftNode, rightNode};
//    }
//	
//	TreeNode build(TreeNode root, int val) {
//		if (root == null) return new TreeNode(val);
//		else {
//			if (val <= root.val) {
//				root.left = build(root.left, val);
//			}
//			else {
//				root.right = build(root.right, val);
//			}
//			return root;
//		}
//	}
	
	public TreeNode[] splitBST(TreeNode root, int V) {
		if (root == null) return new TreeNode[] {null, null};
		if (root.val <= V) {
			TreeNode[] res = splitBST(root.right, V);
			root.right = res[0];
			res[0] = root;
			return res;
		}
		else{
			TreeNode[] res = splitBST(root.left, V);
			root.left = res[1];
			res[1] = root;
			return res;
		}
	}
	
	public static void main(String[] args) {
		SolutionDay04_L0776 day = new SolutionDay04_L0776();
	}

}
