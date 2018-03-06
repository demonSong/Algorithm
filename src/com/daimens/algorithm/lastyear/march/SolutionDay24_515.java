package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author DemonSong
 * 515.Find Largest Value in Each Tree Row
 * You need to find the largest value in each row of a binary tree.
 * 
 * Example:
 * Input:
 *         1
 *        / \
 *       3   2
 *      / \   \  
 *     5   3   9
 * Output: [1,3,9] 
 *
 */
public class SolutionDay24_515 {
	
//	public List<Integer> largestValues(TreeNode root) {
//		
//		if(root == null) return new ArrayList<Integer>();
//		int layer = 0;
//		Map<Integer,Integer> map = new HashMap<>();
//		dfs(root, layer, map);
//		
//        return new ArrayList<>(map.values());
//    }
	
	//深度
	
//	private void dfs(TreeNode root, int layer,Map<Integer,Integer> ans){
//		
//		if(root == null) return;
//		
//		if(ans.get(layer) == null)
//			ans.put(layer,root.val);
//		else{
//			int num = ans.get(layer);
//			if(root.val > num){
//				ans.put(layer,root.val);
//			}
//		}
//		
//		layer = layer + 1;
//		dfs(root.left, layer,ans);
//		dfs(root.right, layer,ans);
//		
//	}
	
	public List<Integer> largestValues(TreeNode root) {
		
		if(root == null) return new ArrayList<>();
		
		List<Integer> ans = new ArrayList<>();
		dfs(root, 0, ans);
		return ans;
	}
	
	private void dfs(TreeNode root, int layer, List<Integer> ans){
		if(root == null) return;
		
		if(ans.size() == layer){
			ans.add(root.val);
		}else{
//			if(ans.get(layer) < root.val){
//				ans.set(layer, root.val);
//			}
			ans.set(layer, Math.max(ans.get(layer), root.val));
		}
		
		dfs(root.left, layer+1, ans);
		dfs(root.right, layer+1, ans);
	}
	
	public static void main(String[] args) {
		SolutionDay24_515 day = new SolutionDay24_515();
		
//		SolutionDay21_106 build = new SolutionDay21_106();
//		int[] inorder = {6,2,1,8,4,9};
//		int[] postorder = {6,2,8,9,4,1};
//		
//		TreeNode root = build.buildTree(inorder, postorder);
//		
//		day.largestValues(root);

		
		List<Integer> ans = new ArrayList<>();
		for(int i = 0; i < 10; i++){
			//插入到当前的位置
			ans.add(0, i);
		}
	}
	
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		if (inorder.length == 0 || postorder.length == 0)
			return null;

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}

		return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
	}
	
	
	private TreeNode build(int[] inorder, int is, int ie, int[] postorder, int ps, int pe, Map<Integer, Integer> map) {
		if (ps > pe || is > ie)
			return null;

		int last = postorder[pe];
		TreeNode root = new TreeNode(last);

		int pos = map.get(last);
		root.left = build(inorder, is, pos - 1, postorder, ps, (pos - 1 -is) + ps, map);  //postOrder的坐标变化是什么意思？
		root.right = build(inorder, pos + 1, ie, postorder, pos + ps-is, pe-1, map); 

		return root;
	}
	
		
}
