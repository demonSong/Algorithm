package com.daimens.algorithm.may;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 
 *         301.Remove Invalid Parentheses
 * 
 *         Remove the minimum number of invalid parentheses in order to make the
 *         input string valid. Return all possible results.
 * 
 *         Note: The input string may contain letters other than the parentheses
 *         ( and ).
 * 
 *         Examples: "()())()" -> ["()()()", "(())()"] "(a)())()" -> ["(a)()()",
 *         "(a())()"] ")(" -> [""]
 *
 */
public class SolutionDay10_L0301 {

//	public List<String> removeInvalidParentheses(String s) {
//		List<String> ans = new ArrayList<>();
//		
//		if (s.length() == 0){
//			ans.add("");
//			return ans;
//		}
//		
//		int lf = 0;
//		int rt = 0;
//		char[] c = s.toCharArray();
//		for (int i = 0; i < s.length(); i++){
//			if (c[i] == '(') lf ++;
//			if (c[i] == ')') rt ++;
//			
//			//非法
//			if (rt > lf){
//				List<String> tmp = new ArrayList<>();
//				
//				if (lf == 0) tmp.add(s.substring(0,i));
//				
//				for (int j = 0; j < i; j++){
//					if (c[j] == ')'){
//						tmp.add(s.substring(0, j) + s.substring(j+1,i+1));
//					}
//				}
//				List<String> right = removeInvalidParentheses(s.substring(i+1));
//				if (right.size() == 0) return tmp;
//				
//				for (String s1 : tmp){
//					for (String s2: right){
//						ans.add(s1+s2);
//					}
//				}
//				
//				return ans;
//			}
//		}
//		if (ans.size() == 0 && rt == lf){
//			ans.add(s);
//		}else{
//			ans.add("");
//		}
//		return ans;
//    }
	
	public List<String> removeInvalidParentheses(String s) {
		List<String> ans = new ArrayList<>();
		remove(s, ans, 0, 0, new char[]{'(',')'});
		return ans;
	}
	
	// last_i 记录了第一个非法的位置
	private void remove(String s, List<String> ans, int last_i, int last_j, char[] par){
		for (int stack = 0, i = last_i; i < s.length(); ++i){
			if (s.charAt(i) == par[0]) stack++;
			if (s.charAt(i) == par[1]) stack--;
			if (stack >= 0) continue;
			for (int j = last_j; j <= i; ++j){
				if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j-1) != par[1])){ //去重
					remove(s.substring(0, j) + s.substring(j+1, s.length()), ans, i, j, par);
				}
			}
			return;
		}
		String reversed = new StringBuilder(s).reverse().toString();
		if (par[0] == '('){
			 remove(reversed, ans, 0, 0, new char[]{')', '('});
		}else{
			ans.add(reversed);
		}
	}
	
	public static void main(String[] args) {
		SolutionDay10_L0301 day = new SolutionDay10_L0301();
		day.removeInvalidParentheses("(((()");
	}
}
