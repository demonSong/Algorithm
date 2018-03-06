package com.daimens.algorithm.december;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Demon Song
 * 257.Binary Tree Paths
 * Given a binary tree,return all root-to-leaf paths.
 * For example,given the following binary tree:
 * All root-to-leaf paths are:
 * ["1->2->5", "1->3"]
 *
 *
 * 
 */
public class SolutionDay27_257 {
	
	
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> res = new ArrayList<>();
		if(root ==null) return res;
		StringBuilder sb = new StringBuilder();
		rec(root,res,sb);
        return res;
    }
	
	private void rec(TreeNode root,List<String> res,StringBuilder sb){
		if(root.left ==null && root.right ==null){
			sb.append(root.val);
			res.add(sb.toString());
			return;
		}
		sb.append(root.val);
		sb.append("->");
		int oriLen = sb.length();
		if(root.left !=null) rec(root.left,res,sb);
		sb.setLength(oriLen);
		if(root.right !=null) rec(root.right,res,sb);
	}
	
}
