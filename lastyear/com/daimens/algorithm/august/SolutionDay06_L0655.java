package com.daimens.algorithm.august;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolutionDay06_L0655 {
	
//	public List<List<String>> printTree(TreeNode root) {
//        map = new HashMap<>();
//        List<List<String>> ans = new ArrayList<>();
//        if (root == null) return ans;
//        dfs(root, 0);
//        layer(root, 1);
//        int layer = cc;
//        int size = (int) (Math.pow(2, layer) - 1);
//        for (int i = 0; i < layer; ++i) ans.add(new ArrayList<>());
//        for (int i = 0; i < layer; ++i){
//        	for (int j = 0; j < size; ++j){
//        		ans.get(i).add("");
//        	}
//        }
//        int cnt = 0;
//        int pre = 0;
//        for (int key : map.keySet()){
//        	List<Integer> nodes = map.get(key);
//        	cnt ++;
//        	if (cnt == cc + 1) break;
//        	int start = size / (int)(Math.pow(2, cnt));
//        	for (int i = 0; i < nodes.size(); ++i){
//        		if (nodes.get(i) != INF){
//        			ans.get(cnt - 1).set(start, nodes.get(i) + "");
//        			start += (pre + 1);
//        		}
//        		else{
//        			start += (pre + 1);
//        		}
//        	}
//        	pre = size / (int)(Math.pow(2, cnt));
//        }
//        return ans;
//    }
//	
//	Map<Integer, List<Integer>> map;
//	final int INF = 1 << 29;
//	public void dfs(TreeNode root, int layer){
//		if (root == null) return;
//		map.computeIfAbsent(layer, k -> new ArrayList<>()).add(root.val);
//		if (root.left == null){
//			map.computeIfAbsent(layer + 1, k -> new ArrayList<>()).add(INF);
//		}
//		else{
//			dfs(root.left, layer + 1);
//		}
//		if (root.right == null){
//			map.computeIfAbsent(layer + 1, k -> new ArrayList<>()).add(INF);
//		}
//		else{
//			dfs(root.right, layer + 1);
//		}
//	}
//	
//	int cc;
//	public void layer(TreeNode root, int layer){
//		if (root == null) return;
//		cc = Math.max(cc, layer);
//		layer(root.left, layer + 1);
//		layer(root.right, layer + 1);
//	}
	
	public List<List<String>> printTree(TreeNode root) {
		List<List<String>> ans = new ArrayList<>();
		if (root == null) return ans;
		int layer = layer(root);
		int size = (1 << layer) - 1;
		for (int i = 0; i < layer; ++i){
			ans.add(new ArrayList<>());
			for (int j = 0; j < size; ++j){
				ans.get(i).add("");
			}
		}
		dfs(ans, root, 0, 0, size);
		return ans;
	}
	
	public void dfs(List<List<String>> ans, TreeNode root, int layer, int i, int j){
		if (root == null) return;
		int mid = (i + j) / 2;
		ans.get(layer).set(mid, root.val + "");
		dfs(ans,root.left, layer + 1, i, mid - 1);
		dfs(ans,root.right, layer + 1, mid + 1, j);
	}
	
	public int layer(TreeNode root){
		if (root == null) return 0;
		int lf = layer(root.left) + 1;
		int rt = layer(root.right) + 1;
		return Math.max(lf, rt);
	}
	
	int cc;
	public void layer(TreeNode root, int layer){
		if (root == null) return;
		cc = Math.max(cc, layer);
		layer(root.left, layer + 1);
		layer(root.right, layer + 1);
	}
	
	public static void main(String[] args) {
		SolutionDay06_L0655 day = new SolutionDay06_L0655();
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(7);
		root.right.right = new TreeNode(8);
		System.out.println(day.printTree(root));
	}

}
