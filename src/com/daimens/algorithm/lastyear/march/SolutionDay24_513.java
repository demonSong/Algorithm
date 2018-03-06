package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 513.Find Bottom Left Tree Value
 * Given a binary tree,find the leftmost value in the last row of the tree.
 * 
 * Example 1:
 * Input:
 *
 *    2
 *   / \
 *  1   3
 * 
 * Output:
 * 1
 * 
 * Example 2:
 * Input:
 * 
 *       1
 *      / \
 *     2   3
 *    /   / \
 *   4   5   6
 *      /
 *     7
 *
 * Output:
 * 7
 * 
 * Note: You may assume the tree (i.e, the given root node) is not NULL
 * 
 *
 */
public class SolutionDay24_513 {
//	public int findBottomLeftValue(TreeNode root) {
//		
//		List<Integer> ans = new ArrayList<>();
//		dfs(root, 0, ans);
//        return ans.get(ans.size()-1);
//    }
//	
//	
//	private void dfs(TreeNode root, int layer, List<Integer> lists){
//		
//		if(root == null) return;
//		
//		if(layer == lists.size()){
//			lists.add(root.val);
//		}
//		
//		dfs(root.left, layer+1, lists);	
//		dfs(root.right, layer+1, lists);
//		
//	}
	
	
	public int findBottomLeftValue(TreeNode root) {
		
		return dfs(root, 0, ans);
	}
	
	int[] ans = new int[]{-1,0}; // 表示位置 和 值
	
//	private void dfs(TreeNode root, int layer, int[] ans){
//		
//		if(root == null) return;
//		
//		if(ans[0] < layer){
//			ans[0] = layer;
//			ans[1] = root.val;
//		}
//		
//		dfs(root.left, layer+1, ans);	
//		dfs(root.right, layer+1, ans);
//		
//	}
	
	private int dfs(TreeNode root, int layer, int[] ans){
		
		if(root == null) return -1; //无效返回
		
		if(ans[0] < layer){
			ans[0] = layer;
			ans[1] = root.val;
		}
		
		dfs(root.left, layer+1, ans);	
		dfs(root.right, layer+1, ans);
		
		//一定是遍历完了所有的节点，才会到这里来，我们关注最终状态
		
		return ans[1];
		
	}
	
	
	
	
}
