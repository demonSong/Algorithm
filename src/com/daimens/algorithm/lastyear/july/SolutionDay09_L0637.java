package com.daimens.algorithm.july;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SolutionDay09_L0637 {
	
//	Map<Integer, List<Integer>> map = new HashMap<>();
//	public List<Double> averageOfLevels(TreeNode root) {
//		List<Double> ans = new ArrayList<>();
//		if (root == null) return ans;
//		dfs(root, 0);
//		
//		for (Integer key : map.keySet()){
//			List<Integer> tmp = map.get(key);
//			long sum = 0;
//			for (int n : tmp) sum += n;
//			ans.add(sum / (1.0 * tmp.size()));
//		}
//		return ans;
//    }
	
	public List<Double> averageOfLevels(TreeNode root) {
		
		List<Double> ans = new ArrayList<>();
		if (root == null) return ans;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()){
			int size = queue.size();
			long sum = 0;
			for (int i = 0; i < size; ++i){
				TreeNode node = queue.poll();
				sum += node.val;
				if (node.left != null) queue.offer(node.left);
				if (node.right != null) queue.offer(node.right);
			}
			ans.add(sum / (1.0 * size));
		}
		
		return ans;
	}
	
//	public void dfs(TreeNode root, int layer){
//		if (root == null) return;
//		map.computeIfAbsent(layer, k -> new ArrayList<>()).add(root.val);
//		dfs(root.left, layer + 1);
//		dfs(root.right, layer + 1);
//	}
	
	public static void main(String[] args) {
		SolutionDay09_L0637 day = new SolutionDay09_L0637();
	}

}
