package com.daimens.algorithm.february;

/**
 * 
 * @author Demon Song
 * 337.House Robber III
 * The thief has found himself a new place for his thievery again.There is only one entrance
 * to his area,called the "root".Besides the root,each house has one and only one parent house.
 * After a tour,the smart thief realized that "all houses in this place forms a binary tree".It
 * will automatically contact the police if two directly-linked houses were broken into on the same
 * night.
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * Example 1:
 *    3
 *   / \
 *  2   3
 *   \   \ 
 *    3   1
 * Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 *     3
 *    / \
 *   4   5
 *  / \   \ 
 * 1   3   1
 * Maximum amount of money the thief can rob = 4 + 5 = 9.
 * 
 */
public class SolutionDay14_337 {
//	public int rob(TreeNode root){
//		if(root == null) return 0;
//		//层序遍历
//		Queue<TreeNode> queue = new LinkedList<>();
//		if(root != null){
//			queue.add(root);
//		}
//		int maxValue = root.val, prep = 0;
//		while(!queue.isEmpty()){
//			int sum = 0;
//			//peek队列中的所有元素
//			List<TreeNode> nodes = new ArrayList<>();
//			while(!queue.isEmpty()){
//				nodes.add(queue.poll());
//			}
//			
//			for (TreeNode node : nodes){
//				if(node.left !=null){
//					sum += node.left.val;
//					queue.add(node.left);
//				}
//				if(node.right !=null){
//					sum += node.right.val;
//					queue.add(node.right);
//				}
//			}
//			
//			int tmp = maxValue;
//			maxValue = Math.max(maxValue, prep+sum); //sum记录了当前状态
//			prep = tmp;
//		}
//		return maxValue;
//	}
	
	//还是遍历每个结点，但状态有所区分
//	public int rob(TreeNode root){
//		if(root == null) return 0;
//		int val = 0;
//		if(root.left != null){
//			val += rob(root.left.left) + rob(root.left.right);
//		}
//		if(root.right != null){
//			val += rob(root.right.left) + rob(root.right.right);
//		}
//		
//		return Math.max(val + root.val, rob(root.left) + rob(root.right));
//	}
	
	
//	public int rob(TreeNode root){
//		return robSub(root,new HashMap<>());
//	}
//	
//	private int robSub(TreeNode root,Map<TreeNode,Integer> map){
//		if(root == null) return 0;
//		if(map.containsKey(root)) return map.get(root);
//		
//		int val = 0;
//		if(root.left != null){
//			val += robSub(root.left.left, map) + robSub(root.left.right, map);
//		}
//		if(root.right != null){
//			val += robSub(root.right.left, map) + robSub(root.right.right,map);
//		}
//		
//		val = Math.max(val + root.val, robSub(root.left, map) + robSub(root.right, map));
//		map.put(root, val);
//		
//		return val;
//	}
	
	public int rob(TreeNode root) {
	    int[] res = robSub(root);
	    return Math.max(res[0], res[1]);
	}

	private int[] robSub(TreeNode root) {
	    if (root == null) return new int[2];
	    
	    int[] left = robSub(root.left);
	    int[] right = robSub(root.right);
	    int[] res = new int[2];

	    res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
	    res[1] = root.val + left[0] + right[0];
	    
	    return res;
	}
	
	
	
}
