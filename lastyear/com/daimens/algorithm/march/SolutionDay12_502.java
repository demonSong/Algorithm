package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 536.Construct Binary Tree from String
 * You need to construct a binary tree from a string consisting of parenthesis and integers.
 * The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of
 * parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with 
 * the same structure.
 * You always start to construct the left child node of the parent first if it exists.
 * 
 * Example:
 * Input: "4(2(3)(1))(6(5))"
 * Output: return the tree root node representing the following tree:
 *       4
 *     /   \
 *    2     6
 *   / \   / 
 *  3   1 5 
 *  
 * Note:
 * 1. There will only be '(', ')', '-' and '0' ~ '9' in the input string.
 *
 */
public class SolutionDay12_502 {

	
//	public TreeNode str2tree(String s) {
//		if(s.length() == 0) return null;
//
//		StringBuilder leftStr = new StringBuilder();
//		StringBuilder rightStr = new StringBuilder();
//		StringBuilder value = new StringBuilder();
//		
//		for (int i = 0; i < s.length(); i++){
//			
//			if(s.charAt(i)== '('){
//				break;
//			}
//			value.append(s.charAt(i));
//		}
//		
//		int rootValue = Integer.parseInt(value.toString());
//		TreeNode root = new TreeNode(rootValue);
//
//		//解析 left
//		
//		int f = 0,l = 0;
//		int isLeft = 0;
//		for (int i = 0; i < s.length(); i++){
//			if(s.charAt(i) == '('){
//				if(isLeft == 0)
//					f = i;
//				isLeft ++;
//			}
//			
//			if(s.charAt(i) == ')'){
//				isLeft --;
//				if(isLeft == 0)
//					l = i;
//				
//			}
//		}
//		
//		for (int i = f+1; i < l; i++){
//			rightStr.append(s.charAt(i));
//		}
//		
//		String tmp = s.substring(0, f);
//		f = 0;
//		l = 0;
//		isLeft = 0;
//		for (int i = 0; i < tmp.length(); i++){
//			if(tmp.charAt(i) == '('){
//				if(isLeft == 0)
//					f = i;
//				isLeft ++;
//			}
//			
//			if(tmp.charAt(i) == ')'){
//				isLeft --;
//				if(isLeft == 0)
//					l = i;
//				
//			}
//		}
//		
//		for (int i = f+1; i < l; i++){
//			leftStr.append(s.charAt(i));
//		}
//		
//		if(leftStr.toString().length() == 0){
//			leftStr = rightStr;
//			rightStr = null;
//		}
//		
//		if(leftStr.length() == 0){
//			leftStr = null;
//		}
//		
//		if(leftStr == null && rightStr == null) return root;
//		
//		//有了 treenode  leftStr 和RightStr
//		if(leftStr != null){
//			root.left = str2tree(leftStr.toString());
//		}
//		
//		if(rightStr != null){
//			root.right = str2tree(rightStr.toString());
//		}
//		
//		return root;
//    }
	
	public TreeNode str2tree(String s) {
		
		if(s.length() == 0) return null;
		
		int val = 0;
		int i = 0, k = 1;
		
		while(i < s.length() && (s.charAt(i) >= '0' && s.charAt(i) <= '9' || s.charAt(i) == '-')){
			if(s.charAt(i) == '-')
				k = -1;
			else
				val = val * 10 + (s.charAt(i)-'0') * k;
			i++;
		}
		
		TreeNode root = new TreeNode(val);
		
		if(i == s.length()) return root;
		
		
		int cnt = 0,j;
		for (j = i; j < s.length();j++){
			if(s.charAt(j) == '(') cnt++;
			if(s.charAt(j) == ')') cnt--;
			if(cnt == 0) break;
		}
		
		root.left = str2tree(s.substring(i+1, j));
		if(j != s.length()-1)
			root.right = str2tree(s.substring(j+2,s.length()-j-3));
		
		return root;
	}
	
	
	
	public static void main(String[] args) {
		SolutionDay12_502 day = new SolutionDay12_502();
		String s = "4(2(3)(1))(6(5))";
		System.out.println(s.substring(0, s.length()));
		day.str2tree(s);
	}
}
