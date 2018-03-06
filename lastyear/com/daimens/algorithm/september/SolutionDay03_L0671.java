package com.daimens.algorithm.september;

import java.util.TreeSet;

public class SolutionDay03_L0671 {
	
//	public int findSecondMinimumValue(TreeNode root) {
//		if (root == null) return -1;
//		nodes = new ArrayList<>();
//		go(root);
//		Collections.sort(nodes);
//		int min = nodes.get(0);
//		int nxt = -1;
//		for (int i = 1; i < nodes.size(); ++i){
//			if (nodes.get(i) > min){
//				nxt = nodes.get(i);
//				break;
//			}
//		}
//		return nxt;
//    }
//	
//	List<Integer> nodes;
//	public void go(TreeNode root){
//		if (root == null) return;
//		go(root.left);
//		nodes.add(root.val);
//		go(root.right);
//	}

	public int findSecondMinimumValue(TreeNode root) {
		if (root == null) return -1;
		set = new TreeSet<>();
		go(root);
		if (set.size() < 2) return -1;
		else{
			set.pollFirst();
			return set.first();
		}
	}
		
	TreeSet<Integer> set;
	public void go(TreeNode root){
		if (root == null) return;
		go(root.left);
		set.add(root.val);
		go(root.right);
	}

	
	public static void main(String[] args) {
		SolutionDay03_L0671 day = new SolutionDay03_L0671();
	}

}
