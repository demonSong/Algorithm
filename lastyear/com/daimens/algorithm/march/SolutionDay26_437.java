package com.daimens.algorithm.march;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 437.Path Sum III
 * You are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (iterating only from parent nodes to child nodes).
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000
 * 
 * Example:
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 * 
 * Return 3. The paths that sum to 8 are:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 *
 */
public class SolutionDay26_437 {
	
	//backtrack
//	public int pathSum(TreeNode root, int sum) {
//		List<List<Integer>> ans = new ArrayList<>();
//		dfs(root, ans, sum);
//		return ans.size();
//	}
//	
//	//路径重复，路径没有被全部访问
//	private void backtrack(List<List<Integer>> ans, List<Integer> path, TreeNode root,int sum){
//		if(root == null) return;
//		
//		if(sum == 0){
//			ans.add(new ArrayList<>(path));
//		}
//		
//		path.add(root.val);
//		backtrack(ans, path, root.left,sum-root.val);
//		backtrack(ans,path,root.right,sum-root.val);
//		
//		path.remove(path.size()-1);
//	}
//	
//	private void dfs(TreeNode root,List<List<Integer>> ans,int sum){
//		if(root == null){
//			return;
//		}
//		
//		backtrack(ans, new ArrayList<>(), root, sum);
//		
//		dfs(root.left, ans, sum);
//		dfs(root.right, ans, sum);
//	}
	
//	public int pathSum(TreeNode root, int sum) {
//			
//		if(root == null) return 0;
//		Map<Integer,Integer> preSum = new HashMap<>();
//		preorder(root,preSum);
//		preorder(root,sum,preSum);
//		return count;
//	}
//	
//	
//	
//	//它的路径还是不好遍历
//	int sum = 0;
//	private void preorder(TreeNode root, Map<Integer,Integer> map){
//		
//		if(root == null){
//			return;
//		}
//		
//		sum += root.val;
//		map.put(sum, map.getOrDefault(sum, 0)+1);
//		
//		preorder(root.left,map);
//		preorder(root.right,map);
//		
//	}
//	
//	int count = 0;
//	private void preorder(TreeNode root, int target,Map<Integer,Integer> map){
//		if(root == null){
//			return;
//		}
//		
//		target += root.val;
//		
//		if(map.containsKey(target)){
//			count += map.get(target);
//		}
//		preorder(root.left,target,map);
//		preorder(root.right,target,map);
//	}
	
	public int pathSum(TreeNode root, int sum) {
		HashMap<Integer, Integer> preSum = new HashMap();
        preSum.put(0,1);
        helper(root, 0, sum, preSum);
        return count;
	}
	
	int count = 0;
	private void helper(TreeNode root, int currSum, int target, Map<Integer,Integer> preSum){
		if(root == null) return;
		
		currSum += root.val;
		if(preSum.containsKey(currSum-target)){
			count += preSum.get(currSum-target);
		}
		
		preSum.put(currSum, preSum.getOrDefault(currSum, 0)+1);
		
		helper(root.left, currSum, target, preSum);
		helper(root.right, currSum, target, preSum);
		
		//backtrack 路径总是从上到下
		preSum.put(currSum, preSum.get(currSum) - 1);
	}
	
	
	
	
	public static void main(String[] args) {
		SolutionDay26_437 day = new SolutionDay26_437();
		
//		int[] nums ={10,5,-3,3,2,100,11,3,-2,100,1};
		int[] nums = {1,-2,-3};
		TreeNode root = TreeHelper.buildTree(nums, 100);
		day.pathSum(root, 8);
		
	}
	
}
