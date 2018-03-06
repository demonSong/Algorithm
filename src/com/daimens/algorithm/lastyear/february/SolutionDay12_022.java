package com.daimens.algorithm.february;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Demon Song
 * 22.Generate Parentheses
 * Given n pairs of parentheses,write a function to generate all combinations of well-formed parentheses.
 * For example,given n = 3, a solution set is:
 * [
 *  "((()))",
 *  "(()())",
 *  "(())()",
 *  "()(())",
 *  "()()()"
 * ]
 * 
 *
 */
public class SolutionDay12_022 {

	public List<String> generateParenthesis(int n) {
		List<String> list = new ArrayList<>();
		backtrack(list,"",0,0,n);
        return list;
    }
	
	private void backtrack(List<String> list, String str,int open,int close,int max){
		if(str.length() == max * 2){
			list.add(str);
			return;
		}
		if(open < max){
			backtrack(list,str+"(",open + 1, close,max);
		}
		if(close < open){
			backtrack(list,str+")",open, close + 1,max);
		}
	}
	
	public static void main(String[] args) {
		SolutionDay12_022 day = new SolutionDay12_022();
		day.generateParenthesis(4);
	}
}
