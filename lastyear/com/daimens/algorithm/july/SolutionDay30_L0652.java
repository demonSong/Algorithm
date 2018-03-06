package com.daimens.algorithm.july;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SolutionDay30_L0652 {
	
//	Map<String, TreeNode> mem;
//	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
//		mem = new HashMap<>();
//		tt = new ArrayList<>();
//		if (root == null) return new ArrayList<>();
//		dfs(root);
//		for (int i = 0; i < tt.size(); ++i){
//			for (int j = i + 1; j < tt.size(); ++j){
//				if (hasSame(tt.get(i), tt.get(j))){
//					mem.put(seriazle(tt.get(i)), tt.get(i));
//				}
//			}
//		}
//		return new ArrayList<>(mem.values());
//	}
//	
//	List<TreeNode> tt;
//	public void dfs(TreeNode root){
//		if (root == null) return;
//		tt.add(root);
//		dfs(root.left);
//		dfs(root.right);
//	}
//	
//	public String seriazle(TreeNode node){
//		if (node == null) return "";
//		String ans = "";
//		ans += node.val + "";
//		if (node.left != null){
//			ans += seriazle(node.left);
//		}
//		else{
//			ans += "null" + "";
//		}
//		if (node.right != null){
//			ans += seriazle(node.right);
//		}
//		else{
//			ans += "null" + "";
//		}
//		return ans;
//	}
	
//	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
//		ll = new ArrayList<>();
//		if (root == null) return new ArrayList<>();
//		dfs(root);
//		List<TreeNode> ans = new ArrayList<>();
//		for (TreeNode node : ll){
//			if (hasSubtrees(node, root)){
//				ans.add(node);
//			}
//		}
//		return ans;
//	}
//	
//	public boolean hasSubtrees(TreeNode left, TreeNode right){
//		List<TreeNode> tt = dfs(right, 0);
//		for (TreeNode t : tt){
//			if (hasSame(t, left) && t != left) return true;
//		}
//		return false;
//	}
//	
//	public List<TreeNode> dfs(TreeNode root, int type){
//		if (root == null) return new ArrayList<>();
//		List<TreeNode> ans = new ArrayList<>();
//		ans.add(root);
//		if (root.left != null){
//			ans.addAll(dfs(root.left, 0));
//		}
//		if (root.right != null){
//			ans.addAll(dfs(root.right, 0));
//		}
//		return ans;
//	}
//	
//	List<TreeNode> ll;
//	public void dfs(TreeNode root){
//		if (root == null) return;
//		
//		ll.add(root);
//		
//		dfs(root.left);
//		dfs(root.right);
//	}
//	
//	public boolean hasSame(TreeNode l, TreeNode r){
//		if (l == null && r == null) return true;
//		if (l == null && r != null) return false;
//		if (l != null && r == null) return false;
//		if (l.val == r.val){
//			return hasSame(l.left, r.left) && hasSame(l.right, r.right);
//		}
//		return false;
//	}
	
	
	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		List<TreeNode> res = new ArrayList<>();
		Map<String, Integer> mem = new HashMap<>();
		if(root == null) return res;
		seriazle(root, mem, res);
		return res;
	}
	
	public String seriazle(TreeNode root, Map<String, Integer> mem, List<TreeNode> res){
		if (root == null) return "";
		String ans = "";
		ans += root.val + ",";
		ans += root.left != null ? seriazle(root.left, mem, res) : "#";
		ans += root.right != null ? seriazle(root.right, mem, res) : "#";
		mem.put(ans, mem.getOrDefault(ans, 0) + 1);
		if (mem.get(ans) == 2) res.add(root);
		return ans;
	}
	
	
	public static void main(String[] args) {
		SolutionDay30_L0652 day = new SolutionDay30_L0652();
		TreeNode root = new TreeNode(0);
		root.left = new TreeNode(0);
		root.left.left = new TreeNode(0);
		root.right = new TreeNode(0);
		root.right.right = new TreeNode(0);
		root.right.right.left = new TreeNode(0);
		root.right.right.right = new TreeNode(0);
//		System.out.println(day.seriazle(root));
		System.out.println(day.findDuplicateSubtrees(root));
	}
}
