package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 
 *
 */
public class SolutionDay09_503 {
	
	public int longestConsecutive(TreeNode root) {
		if (root == null) return 0;
		dfs(root);
        return max;
    }
	
	//注意自顶向下和自底向上的区别
	
//	private int dfs(TreeNode root){
//		if(root == null) return 0;
//		
//		int count = 0;
//		
//		//up up
//		if(root.left != null && root.right != null){
//		
//			if (root.left.val + 1 == root.val && root.val + 1 == root.right.val){
//				count = dfs(root.left) + 1 + dfs(root.right);
//			}
//			
//			//up down or down up
//			if (root.left.val + 1 == root.val && root.val - 1 == root.right.val){
//				count = Math.max(dfs(root.left)+1, dfs(root.right)+1);
//			}
//			
//			if (root.left.val - 1 == root.val && root.val + 1 == root.right.val){
//				count = Math.max(dfs(root.left)+1, dfs(root.right)+1);
//			}
//			
//			// down down 
//			if (root.left.val - 1 == root.val && root.val - 1 == root.right.val){
//				count = dfs(root.left) + 1 + dfs(root.right);
//			}
//			
//			count = Math.max(dfs(root.left), dfs(root.right));
//		
//		}else{
//			count = Math.max(dfs(root.left), dfs(root.right));
//		}
//		
//		return count;
//	}
	
	class Result{
		TreeNode node;
		int inc;
		int des;
	}
	
	int max;
	private Result dfs(TreeNode node) {
        if (node == null) return null;
        
        Result left = dfs(node.left);
        Result right = dfs(node.right);
        
        Result curr = new Result();
        curr.node = node;
        curr.inc = 1;
        curr.des = 1;
        
        if (left != null) {
            if (node.val - left.node.val == 1) {
                curr.inc = Math.max(curr.inc, left.inc + 1);
            }
            else if (node.val - left.node.val == -1) {
                curr.des = Math.max(curr.des, left.des + 1);
            }
        }
        
        if (right != null) {
            if (node.val - right.node.val == 1) {
                curr.inc = Math.max(curr.inc, right.inc + 1);
            }
            else if (node.val - right.node.val == -1) {
                curr.des = Math.max(curr.des, right.des + 1);
            }
        }
        
        max = Math.max(max, curr.inc + curr.des - 1);
        
        return curr;
    }
	
	public static void main(String[] args) {
		SolutionDay09_503 day = new SolutionDay09_503();
	}

}
