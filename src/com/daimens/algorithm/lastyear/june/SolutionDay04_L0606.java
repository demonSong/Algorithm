package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         606.Construct String From Binary Tree
 * 
 *         You need to construct a string consists of parenthesis and integers
 *         from a binary tree with the preorder traversing way.
 * 
 *         The null node needs to be represented by empty parenthesis pair "()".
 *         And you need to omit all the empty parenthesis pairs that don't
 *         affect the one-to-one mapping relationship between the string and the
 *         original binary tree.
 * 
 *         Example 1: Input: Binary tree: [1,2,3,4] 1 / \ 2 3 / 4
 * 
 *         Output: "1(2(4))(3)"
 * 
 *         Explanation: Originallay it needs to be "1(2(4)())(3()())", but you
 *         need to omit all the unnecessary empty parenthesis pairs. And it will
 *         be "1(2(4))(3)". Example 2: Input: Binary tree: [1,2,3,null,4] 1 / \
 *         2 3 \ 4
 * 
 *         Output: "1(2()(4))(3)"
 * 
 *         Explanation: Almost the same as the first example, except we can't
 *         omit the first parenthesis pair to break the one-to-one mapping
 *         relationship between the input and the output.
 *
 */
public class SolutionDay04_L0606 {

	public String tree2str(TreeNode t) {
		if (t == null)
			return "";

		String ans = t.val + "";

		if (t.left != null) {
			ans += "(" + tree2str(t.left) + ")";
		}

		if (t.left == null && t.right != null) {
			ans += "()";
		}

		if (t.right != null) {
			ans += "(" + tree2str(t.right) + ")";
		}
		return ans;
	}

	public static void main(String[] args) {
		SolutionDay04_L0606 day = new SolutionDay04_L0606();
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
	
	@Override
	public String toString() {
		return "["+val+"]";
	}
}