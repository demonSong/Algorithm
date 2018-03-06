package com.daimens.algorithm.may;

/**
 * 
 * @author DemonSong
 * 
 *         32. Longest Valid Parentheses
 * 
 *         Given a string containing just the characters '(' and ')', find the
 *         length of the longest valid (well-formed) parentheses substring.
 * 
 *         For "(()", the longest valid parentheses substring is "()", which has
 *         length = 2.
 * 
 *         Another example is ")()())", where the longest valid parentheses
 *         substring is "()()", which has length = 4.
 * 
 *         Subscribe to see which companies asked this question.
 * 
 *
 */
public class SolutionDay02_032 {
	
	
	//主要就是为了解决合并的问题
//	public int longestValidParentheses(String s) {
//
//		if (s.isEmpty())
//			return 0;
//
//		char[] c = s.toCharArray();
//		int n = c.length;
//		int[] cut = new int[n];
//		boolean[][] pal = new boolean[n][n];
//
//		int max = 0;
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j <= i; j++) {
//				if (i - j + 1 < 2)
//					continue;
//
//				if (c[j] == '(' && c[i] == ')' && (i - j == 1 || pal[i - 1][j + 1])) {
//					pal[i][j] = true;
//					cut[i] = i-j+1;
//					max = Math.max(max, cut[i]);
//				}
//			}
//		}
//
//		return max;
//	}
	
//	public int longestValidParentheses(String s) {
//		
//		if (s.length() <= 1) return 0;
//		char[] c = s.toCharArray();
//		int curMax = 0;
//		int[] longest = new int[s.length()];
//		
//		for (int i = 1; i < s.length(); i++){
//			if (c[i] == ')'){
//				if (c[i-1] == '('){
//					longest[i] = (i - 2) >= 0 ? (longest[i - 2] + 2) : 2;
//					curMax = Math.max(curMax, longest[i]);
//				}
//				else{
//					if (i-longest[i-1] - 1 >= 0 && c[i-longest[i-1]-1] == '('){
//						longest[i] = longest[i - 1] + 2
//								+ ((i - longest[i - 1] - 2 >= 0) ? longest[i - longest[i - 1] - 2] : 0);
//                        curMax = Math.max(longest[i],curMax);
//					}
//				}
//			}
//		}
//		
//		return curMax;
//	}
	
//	public int longestValidParentheses(String s) {
//		int n = s.length();
//		Stack<Integer> stack = new Stack<>();
//		char[] c = s.toCharArray();
//		for (int i = 0; i < n; i++){
//			if (c[i] == '('){
//				stack.push(i);
//			}else{
//				if (!stack.isEmpty()){
//					if (c[stack.peek()] == '(') stack.pop();
//					else stack.push(i);
//				}else{
//					stack.push(i); //推入非法的下标
//				}
//			}
//		}
//		
//		int max = 0;
//		if (stack.isEmpty()) max = n; 
//		else{
//			int curr = n;
//			int next = 0;
//			while (!stack.isEmpty()){
//				next = stack.pop();
//				max = Math.max(max, curr-next-1);
//				curr = next;
//			}
//			max = Math.max(max, curr);
//		}
//		return max;
//	}
	
	public int longestValidParentheses(String s) {
		int n = s.length();
		int[] dp = new int[n];
		
		char[] c = s.toCharArray();
		int max = 0;
		for (int i = 0; i < n; i++){
			if (c[i] == ')'){
				if (i != 0 && c[i-1] == '('){
					dp[i] = ((i - 2) != -1 ? dp[i-2] : 0) + 2;
				}
				
				if (i != 0 && c[i-1] == ')'){
					if (i - dp[i-1] -1 != -1 && c[i-dp[i-1]-1] == '('){
						dp[i] = dp[i-1]+2 + (i -dp[i-1]-2 != -1 ? dp[i-dp[i-1]-2] : 0);
					}
				}
				
				max = Math.max(max, dp[i]);
			}
		}
		
		return max;
	}

	public static void main(String[] args) {
		SolutionDay02_032 day = new SolutionDay02_032();
		day.longestValidParentheses("(()())");
	}
}
