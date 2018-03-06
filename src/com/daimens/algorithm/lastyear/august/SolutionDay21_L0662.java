package com.daimens.algorithm.august;

import java.util.HashMap;
import java.util.Map;

public class SolutionDay21_L0662 {
	
//	public int widthOfBinaryTree(TreeNode root) {
//        if (root == null) return 0;
//        map = new HashMap<>();
//        dfs(root, 0);
//        int max = 0;
//        for (int key : map.keySet()){
//        	List<String> list = map.get(key);
//        	int j = list.size() - 1;
//        	while (j >= 0 && list.get(j).equals("#")){
//        		j --;
//        	}
//        	max = Math.max(max, j + 1);
//        }
//        return max;
//	}
//	
//	Map<Integer, List<String>> map;
//	public void dfs(TreeNode root, int layer){
//		if (root == null) return;
//		map.computeIfAbsent(layer, k -> new ArrayList<>()).add(root.val + "");
//		if (root.left != null){
//			dfs(root.left, layer + 1);
//		}
//		else{
//			map.computeIfAbsent(layer + 1, k -> new ArrayList<>()).add("#");
//		}
//		
//		if (root.right != null){
//			dfs(root.right, layer + 1);
//		}
//		else{
//			map.computeIfAbsent(layer + 1, k -> new ArrayList<>()).add("#");
//		}
//	}
	
//	class Pair{
//		TreeNode node;
//		String val;
//		public Pair(TreeNode node, String val){
//			this.node = node;
//			this.val = val;
//		}
//	}
//	
//	public int widthOfBinaryTree(TreeNode root) {
//		if (root == null) return 0;
//		Queue<Pair> queue = new LinkedList<>();
//		queue.offer(new Pair(root, root.val + ""));
//		int max = 0;
//		Map<Integer, List<String>> map = new HashMap<>();
//		int layer = 0;
//		
//		while (!queue.isEmpty()){
//			int size = queue.size();
//			boolean end = true;
//			for (int i = 0; i < size; ++i){
//				Pair now = queue.poll();
//				if (now.val.equals("#")){ //空节点
//					map.computeIfAbsent(layer, k -> new ArrayList<>()).add("#");
//					queue.offer(new Pair(null, "#"));
//					queue.offer(new Pair(null, "#"));
//				}
//				else{
//					end = false;
//					map.computeIfAbsent(layer, k -> new ArrayList<>()).add(now.val + "");
//					if (now.node.left != null){
//						queue.offer(new Pair(now.node.left, now.node.left.val + ""));
//					}
//					else{
//						queue.offer(new Pair(null, "#"));
//					}
//					if (now.node.right != null){
//						queue.offer(new Pair(now.node.right, now.node.right.val + ""));
//					}
//					else{
//						queue.offer(new Pair(null, "#"));
//					}
//				}
//			}
//			if (end) break;
//			layer ++;
//		}
//		for (int key : map.keySet()) {
//			List<String> list = map.get(key);
//			int j = list.size() - 1;
//			while (j >= 0 && list.get(j).equals("#")) {
//				j--;
//			}
//			int i = 0;
//			while (i < list.size() && list.get(i).equals("#")){
//				i++;
//			}
//			max = Math.max(max, j - i + 1);
//		}
//		
//		return max;
//	}
	
//	public int widthOfBinaryTree(TreeNode root) {
//		if (root == null) return 0;
//		list = new ArrayList<>();
//		dfs(root);
//		int max = 0;
//		for (TreeNode node : list){
//			left = new HashMap<>();
//			right = new HashMap<>();
//			left(node, 0, 0);
//			right(node, 0, 0);
//			for (int key : left.keySet()){
//				int i = left.get(key);
//				if (right.containsKey(key)){
//					int j = right.get(key);
//					max = Math.max(max, j - i + 1);
//				}
//				else{
//					max = Math.max(max, 1);
//				}
//			}
//		}
//		return max;
//	}
//	
//	List<TreeNode> list;
//	public void dfs(TreeNode root){
//		if (root == null) return;
//		list.add(root);
//		dfs(root.left);
//		dfs(root.right);
//	}
//	
//	Map<Integer, Integer> left;
//	Map<Integer, Integer> right;
//	public void left(TreeNode root, int layer, int pos){
//		if (root == null) return;
//		left.put(layer, pos);
//		if (root.left != null){
//			left(root.left, layer + 1, pos * 2);
//		}
//		else if (root.right != null){
//			left (root.right, layer + 1, pos * 2 + 1);
//		}
//	}
//	
//	public void right(TreeNode root, int layer, int pos){
//		if (root == null) return;
//		right.put(layer, pos);
//		int nxt = ((1 << (layer + 1)) - (((1 << layer) - pos - 1) * 2)) - 1;
//		if (root.right != null){
//			right(root.right, layer + 1, nxt);
//		}
//		else if (root.left != null){
//			right(root.left, layer + 1, nxt - 1);
//		}
//	}
	
	public int widthOfBinaryTree(TreeNode root) {
		if (root == null) return 0;
		map = new HashMap<>();
		dfs(root, 0, 0);
		int max = 0;
		for (int key : map.keySet()){
			Pair p = map.get(key);
			max = Math.max(max, p.max - p.min + 1);
		}
		return max;
	}
	
	class Pair{
		int min;
		int max;
		public Pair(){
			this.min = Integer.MAX_VALUE;
			this.max = Integer.MIN_VALUE;
		}
	}
	
	Map<Integer, Pair> map;
	public void dfs(TreeNode root, int layer, int pos){
		if (root == null) return;
		if (!map.containsKey(layer)) map.put(layer, new Pair());
		Pair p = map.get(layer);
		p.min = Math.min(p.min, pos);
		p.max = Math.max(p.max, pos);
		dfs(root.left, layer + 1, pos * 2);
		dfs(root.right, layer + 1, pos * 2 + 1);
	}
	
	public static void main(String[] args) {
		SolutionDay21_L0662 day = new SolutionDay21_L0662();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(3);
		root.right = new TreeNode(2);
		root.right.right = new TreeNode(9);
		root.left.left = new TreeNode(5);
		day.widthOfBinaryTree(root);
	}
	
	
}
