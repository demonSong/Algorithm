package com.daimens.algorithm.february;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Demon Song
 * 199.Binary Tree Right Side View
 * Given a binary tree, image yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 * For example:
 * Given the following binary tree.
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 * You should return [1,3,4]
 */
public class SolutionDay23_199 {
//	public List<Integer> rightSideView(TreeNode root) {
//		//层序遍历
//		if (root == null) return new ArrayList<>();
//		List<Integer> res = new ArrayList<>();
//		Queue<TreeNode> queue = new LinkedList<>();
//		queue.add(root);
//		res.add(root.val);
//		
//		while (!queue.isEmpty()){
//			List<TreeNode> ans = new ArrayList<>();
//			while (!queue.isEmpty()){
//				TreeNode node = queue.poll();
//				if(node.left != null){
//					ans.add(node.left);
//				}
//				if(node.right != null){
//					ans.add(node.right);
//				}
//			}
//			if(!ans.isEmpty())
//				res.add(ans.get(ans.size()-1).val);
//			
//			for (TreeNode tmp : ans){
//				queue.add(tmp);
//			}
//			
//		}
//		
//        return res;
//    }
	
//	public List<Integer> rightSideView(TreeNode root) {
//        // reverse level traversal
//        List<Integer> result = new ArrayList();
//        Queue<TreeNode> queue = new LinkedList();
//        if (root == null) return result;
//        
//        queue.offer(root);
//        while (queue.size() != 0) {
//            int size = queue.size();
//            for (int i=0; i<size; i++) {
//                TreeNode cur = queue.poll();
//                if (i == 0) result.add(cur.val);
//                if (cur.right != null) queue.offer(cur.right);
//                if (cur.left != null) queue.offer(cur.left);
//            }
//            
//        }
//        return result;
//    }
	
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		rightView(root, res, 0);
		return res;
		
	}
	
	private void rightView(TreeNode curr,List<Integer> result, int currDepth){
		if(curr == null){
			return;
		}
		if(currDepth == result.size()){
			result.add(curr.val);
		}
		
		rightView(curr.right,result,currDepth + 1);
		rightView(curr.left,result,currDepth + 1);
	}
	
	
	public static void main(String[] args) {
		SolutionDay23_199 day = new SolutionDay23_199();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		day.rightSideView(root);
	}
}
