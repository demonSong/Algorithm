package com.daimens.algorithm.august;

import java.util.HashMap;
import java.util.Map;

public class SolutionDay21_L0663 {
	
	public boolean checkEqualTree(TreeNode root) {
		list = new HashMap<>();
        int sum = find(root);
		if (sum % 2 != 0) return false;
		int key = sum / 2;
		return (sum != 0 && list.containsKey(key)) || ( sum == 0 && list.get(sum) >= 2);
	}
	
	Map<Integer, Integer> list;
	public int find(TreeNode root){
		if (root == null) return 0;
		int left = find(root.left);
		int right = find(root.right);
		int sum = left + right + root.val;
		list.put(sum, list.getOrDefault(sum, 0) + 1);
		return sum;
	}
	
	public static void main(String[] args) {
		SolutionDay21_L0663 day = new SolutionDay21_L0663();
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(10);
		root.right = new TreeNode(10);
		root.right.left = new TreeNode(2);
		root.right.right = new TreeNode(3);
		System.out.println(day.checkEqualTree(root));
	}

}
