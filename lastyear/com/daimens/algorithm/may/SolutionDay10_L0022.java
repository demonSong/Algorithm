package com.daimens.algorithm.may;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 
 *         22. Generate Parentheses
 * 
 *         Given n pairs of parentheses, write a function to generate all
 *         combinations of well-formed parentheses.
 * 
 *         For example, given n = 3, a solution set is:
 * 
 *         [ "((()))", "(()())", "(())()", "()(())", "()()()" ]
 * 
 *
 */
public class SolutionDay10_L0022 {
	
//	public List<String> generateParenthesis(int n) {
//		List<String> ans = new ArrayList<>();
//		helper(n, "", ans);
	
//        return ans;
//    }
//	
//	private void helper(int len, String tmp, List<String> ans){
//		if (tmp.length() == 2 * len){
//			if (isValid(tmp))
//				ans.add(new String(tmp));
//			return;
//		}
//		
//		tmp += '(';
//		helper(len, tmp, ans);
//		tmp = tmp.substring(0, tmp.length()-1);
//		tmp += ')';
//		helper(len, tmp, ans);
//		tmp = tmp.substring(0, tmp.length()-1);
//	}
//	
//	public boolean isValid(String s) {
//		Stack<Character> stack = new Stack<Character>();
//        for (int i = 0; i < s.length(); i++){
//        	if (s.charAt(i) == '[' || s.charAt(i) == '{' || s.charAt(i) == '('){
//        		stack.push(s.charAt(i));
//        	}
//        	else{
//				char c = !stack.isEmpty() ? stack.peek() : '.';
//        		if (s.charAt(i) == ']' && c == '[') stack.pop();
//        		else if (s.charAt(i) == '}' && c == '{') stack.pop();
//        		else if (s.charAt(i) == ')' && c == '(') stack.pop();
//        		else return false;
//        	}
//        }
//        return stack.isEmpty();
//    }
	
	public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
    }
    
    public void backtrack(List<String> list, String str, int open, int close, int max){
        if(str.length() == max*2){
            list.add(str);
            return;
        }
        
        if(open < max)
            backtrack(list, str+"(", open+1, close, max);
        if(close < open)
            backtrack(list, str+")", open, close+1, max);
    }
	
	public static void main(String[] args) {
		System.out.println(""+'a');
		SolutionDay10_L0022 day = new SolutionDay10_L0022();
		day.generateParenthesis(3);
	}

}
