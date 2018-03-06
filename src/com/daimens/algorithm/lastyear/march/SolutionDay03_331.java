package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 331.Verify pre-order Serialization of a binary Tree
 * One way to serialize a binary tree is to use pre-order traversal.When we encounter a non-null node,we record
 * the node's value. If it is a null node, we record using a sentinel value such as #.
 * 
 *       _9_
 *      /   \
 *     3     2
 *    / \   / \
 *   4   1  #  6
 *  / \ / \   / \
 *  # # # #   # #
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.
 * Given a string of comma separated values, verify whether it is a correct pre-order traversal serialization of a binary tree. 
 * Find an algorithm without reconstructing the tree.
 * Each comma separated value in the string must be either an integer or a character '#' representing null pointer.
 * You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".
 * 
 * Example 1:
 * "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * Return true
 * Example 2:
 * "1,#"
 * Return false
 * Example 3:
 * "9,#,#,1"
 * Return false
 *
 */
public class SolutionDay03_331 {
	public boolean isValidSerialization(String preorder) {
		//位置信息已经给你了，所以理论上是可以构建出一个二叉树的
		String[] nodes = preorder.split(",");
		int diff = 1;
		
		for (String node : nodes){
			if(--diff < 0) return false;  //except root
			if(!node.equals("#")) diff += 2;
		}
        return diff == 0;
    }
}
