package com.daimens.algorithm.december;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionDay10_L0742 {
	
	List<Integer>[]  graph;
	List<Integer> leaves;
	int[] dist;
	
	void add(int from, int to) {
		graph[from].add(to);
		graph[to].add(from);
	}
	
	void dfs(TreeNode root) {
		if (root == null) return;
		if (root.left == null && root.right == null) leaves.add(root.val);
		
		if (root.left != null) add(root.val, root.left.val);
		if (root.right != null) add(root.val, root.right.val);
		
		dfs(root.left);
		dfs(root.right);
	}
	
	void efs(int x, int p, int d) {
		dist[x] = d;
		for (int to : graph[x]) {
			if (to != p) efs(to, x, d + 1);
		}
	}
	
	
	public int findClosestLeaf(TreeNode root, int k) {
		int INF = 0x3f3f3f3f;
		graph  = new ArrayList[1016];
		leaves = new ArrayList<>();
		dist   = new int[1016];
		Arrays.fill(dist, INF);
		
		for (int i = 0; i < 1016; ++i) graph[i] = new ArrayList<>();
		
		// 树转无向图，并求得叶子结点
		dfs(root);
		// 无脑k出发到其他所有结点的最短路径
		efs(k, -1, 0);
		
		int min = INF;
		int minNode = -1;
		
		for (int leaf : leaves) {
			if (dist[leaf] < min) {
				min = dist[leaf];
				minNode = leaf;
			}
		}
		
		return minNode;
    }
    

    
	public static void main(String[] args) {
		SolutionDay10_L0742 day = new SolutionDay10_L0742();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(4);
		root.left.left.left = new TreeNode(5);
		root.left.left.left.left = new TreeNode(6);
		root.right = new TreeNode(3);
		System.out.println(day.findClosestLeaf(root, 2));
	}

}
