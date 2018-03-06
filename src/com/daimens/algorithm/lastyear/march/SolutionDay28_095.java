package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 95.Unique Binary Search Trees II
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 * For example,
 * Given n = 3, your program should return all 5 unique BST's shown below.
 *  1         3     3      2      1
 *   \       /     /      / \      \
 *    3     2     1      1   3      2
 *   /     /       \                 \
 *  2     1         2                 3
 * 
 *
 */
public class SolutionDay28_095 {
	
	
//	//会出现重复的情况,关键还是个dp问题(构建过程是存在状态的，且有重复问题，需要用dp来解决)
//	public List<TreeNode> generateTrees(int n) {
//		List<TreeNode> ans = new ArrayList<>();
//		List<List<Integer>> trees = new ArrayList<>();
//		backtrack(trees, new ArrayList<>(), n);
//		
//		for(List<Integer> tree : trees){
//			TreeNode root = null;
//			for (int num : tree){
//			    root = buildTree(root,num);
//			}
//			ans.add(root);
//		}
//        return ans;
//    }
//	
//	
//	private void backtrack(List<List<Integer>> ans, List<Integer> path, int n){
//		if(path.size() == n){
//			ans.add(new ArrayList<>(path));
//			return;
//		}
//		
//		for(int i = 1; i <= n; i++){
//			if(path.contains(i)) continue;
//			path.add(i);
//			backtrack(ans, path, n);
//			path.remove(path.size()-1);
//		}
//	}
//	
//	private TreeNode buildTree(TreeNode root,int n){
//		if (root == null) return new TreeNode(n);
//		
//		if(n < root.val){
//			root.left = buildTree(root.left, n);
//		}else{
//			root.right = buildTree(root.right, n);
//		}
//		return root;
//	}
	
	
	//一个很神奇的解决方案，根据前n个状态能够生成n+1状态下的树
	public List<TreeNode> generateTrees(int n){
		
		List<TreeNode>[] result = new List[n+1];
		result[0] = new ArrayList<>();
		
		if(n == 0){
			return result[0];
		}
		result[0].add(null);
		
		for (int len = 1; len <= n; len++){  //状态的变更
			result[len] = new ArrayList<TreeNode>();
			for (int j = 0; j < len; j++){  //所有子结点
				//一个状态下的所有树
				for (TreeNode nodeL : result[j]){
					for(TreeNode nodeR : result[len-j-1]){
						TreeNode node = new TreeNode(j+1);
						node.left = nodeL;
						node.right = clone(nodeR, j+1);
						result[len].add(node);
					}
				}
			}
		}
		return result[n];
	}
	
	private TreeNode clone(TreeNode n, int offSet){
		if(n == null) return null;
		TreeNode root = new TreeNode(n.val+offSet);
		
		root.left = clone(n.left, offSet);
		root.right = clone(n.right, offSet);
		
		return root;
	}
	
	
	
	
	public static void main(String[] args) {
		SolutionDay28_095 day = new SolutionDay28_095();
		day.generateTrees(3);
	}
}
