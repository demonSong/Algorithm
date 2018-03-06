package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 117.Populating Next Right Pointers in Each Node II
 * Follow up for problem "Populating Next Right Pointers in Each Node"
 * What if the given tree could be any binary tree ? Would your previous solution still work?
 * Note:
 * 1. You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 *        1
 *      /  \
 *     2    3
 *    / \    \
 *   4   5    7
 *
 * After calling your function, the tree should look like:
 *        1 -> NULL
 *      /  \
 *     2 -> 3 -> NULL
 *    / \    \
 *   4-> 5 -> 7 -> NULL
 *
 */
public class SolutionDay28_117 {
	
	
	//layer
//	public void connect(TreeLinkNode root) {
//		if(root == null) return;
//		Map<Integer, List<TreeLinkNode>> map = new HashMap<>();
//		preorder(root, map, 0);
//		for(int num : map.keySet()){
//			List<TreeLinkNode> nodes = map.get(num);
//			
//			TreeLinkNode curr = nodes.get(0);
//			for (int i = 1; i < nodes.size(); i++){
//				curr.next = nodes.get(i);
//				curr = curr.next;
//			}
//		}
//    }
//	
//	
//	private void preorder(TreeLinkNode root, Map<Integer, List<TreeLinkNode>> map,int layer){
//		if(root == null) return;
//		
//		map.put(layer, map.getOrDefault(layer, new ArrayList<>()));
//		
//		if(map.containsKey(layer)){
//			map.get(layer).add(root);
//		}
//		
//		preorder(root.left, map,layer + 1);
//		preorder(root.right,map,layer + 1);
//	}
	
	
	//不需要额外的空间
	public void connect(TreeLinkNode root) {
		TreeLinkNode dummyHead = new TreeLinkNode(0);
		TreeLinkNode pre = dummyHead;
		
		while(root != null){
			
			if(root.left != null){
				pre.next = root.left;
				pre = pre.next;
			}
			
			if(root.right != null){
				pre.next = root.right;
				pre = pre.next;
			}
			
			root = root.next;
			if(root == null){
				pre = dummyHead;
				root = dummyHead.next;
				dummyHead.next = null;
			}
			
		}
	}
	
}
