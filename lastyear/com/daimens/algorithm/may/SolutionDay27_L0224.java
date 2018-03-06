package com.daimens.algorithm.may;

import java.util.Stack;

/**
 * 
 * @author DemonSong
 * 
 *         224.Basic Calculator
 * 
 *         Implement a basic calculator to evaluate a simple expression string.
 * 
 *         The expression string may contain open ( and closing parentheses ),
 *         the plus + or minus sign -, non-negative integers and empty spaces .
 * 
 *         You may assume that the given expression is always valid.
 * 
 *         Some examples: "1 + 1" = 2 " 2-1 + 2 " = 3 "(1+(4+5+2)-3)+(6+8)" = 23
 *         Note: Do not use the eval built-in library function.
 *
 */
public class SolutionDay27_L0224 {
	
//	public int calculate(String s) {
//		if (s == null || s.length() == 0) return 0;
//		char[] cs = s.toCharArray();
//		Stack<StringBuilder> stack = new Stack<>();
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < cs.length; i++){
//			if (cs[i] == '('){
//				stack.push(sb);
//				sb = new StringBuilder();
//			}
//			else if (cs[i] == ')'){
//				int ans = calculateNoparentheses(validExpression(sb.toString()));
//				sb = stack.pop();
//				sb.append(ans);
//			}else{
//				sb.append(cs[i]);
//			}
//		}
//        return calculateNoparentheses(validExpression(sb.toString()));
//    }
//	
//	private String validExpression(String s){
//		return s.replaceAll("-\\s*-", "+").replaceAll("\\+\\s*-", "-");
//	}
//	
//	private int calculateNoparentheses(String s){
//		if (s.length() == 0 || s == null) return 0;
//		Stack<Integer> stack = new Stack<>();
//		char opera = '+';
//		char[] cs = s.toCharArray();
//		for (int i = 0, num = 0; i < s.length(); i++){
//			if (Character.isDigit(cs[i])){
//				num = num * 10 + cs[i] - '0';
//			}
//			if (i == s.length() - 1 || !Character.isDigit(cs[i]) && cs[i] != ' '){
//				if (opera == '+'){
//					stack.push(num);
//				}
//				
//				if (opera == '-'){
//					stack.push(-num);
//				}
//				opera = cs[i];
//				num = 0;
//			}
//		}
//		int res = 0;
//		for (int num : stack){
//			res += num;
//		}
//		return res;
//	}
	
	public int calculate(String s) {
		Stack<Integer> stack = new Stack<>();
		char[] cs = s.toCharArray();
		int res = 0, sign = 1;
		int num = 0;
		for (int i = 0; i < cs.length; i++){
			if (Character.isDigit(cs[i])){
				num = num * 10 + cs[i] - '0';
			}
			else if (cs[i] == '+'){
				res += sign * num;
				num = 0;
				sign = 1;
			}
			else if (cs[i] == '-'){
				res += sign * num;
				num = 0;
				sign = -1;
			}
			else if (cs[i] == '('){
				stack.push(res);
				stack.push(sign);
				res = 0;
				sign = 1;
			}
			else if (cs[i] == ')'){
				res += sign * num;
				num = 0;
				res *= stack.pop();
				res += stack.pop();
			}
		}
		if (num != 0) res += sign * num;
		return res;
	}
	
	public static void main(String[] args) {
		SolutionDay27_L0224 day = new SolutionDay27_L0224();
		//System.out.println(day.calculate("4+(1+(4+5+2)-3)+(6+8)"));
		System.out.println(day.calculate("(2 - (5-6))"));
	}
}