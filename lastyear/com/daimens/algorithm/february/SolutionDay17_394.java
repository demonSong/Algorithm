package com.daimens.algorithm.february;

import java.util.Stack;

/**
 * 
 * @author Demon Song
 * 394.Decode String
 * Given an encoded string,return it's decoded string.
 * The encoding rule is : k[encoded_string],where the encoded_string inside the square brackets is 
 * being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid;No extra white spaces,square brackets are well-formed,etc.
 * Furthermore,you may assume that the original data does not contain any digits and that digits are only for those repeated
 * numbers,k.For example,there won't be input like 3a or 2[4]
 * 
 * Example:
 * s = "3[a]2[bc]",return "aaabcbc".
 * s = "3[a2[c]]",return "accaccacc".
 * s = "2[abc]3[cd]ef",return "abcabccdcdcdef"
 *
 */
public class SolutionDay17_394 {
	
//	public String decodeString(String s){
//		//遇到]就开始重复呗
//		StringBuilder sb = new StringBuilder();
//		Stack<Character> st = new Stack<>();
//		for (int i = 0; i < s.length(); i++){
//			StringBuilder tmpb = new StringBuilder();
//			if(s.charAt(i) == ']'){
//				
//				while(!st.empty()){
//					String tmp = "";
//					StringBuilder res = new StringBuilder();
//					while (!st.peek().equals('[')){
//						tmp += st.pop();
//					}
//					st.pop(); 
//					char num = st.pop(); //弹出数字
//					int nums = Character.getNumericValue(num);
//					for (int j = 0; j < nums; j++){
//						res.append(tmp);
//					}
//					tmpb.append(res);
//				}
//			}
//			else{
//				st.push(s.charAt(i));
//			}
//			
//			sb.append(tmpb.reverse().toString());
//		}
//		return sb.toString();
//	}
	
	//优美的解决方案 思路很奇特
	public String decodeString(String s){
		Stack<Integer> intStack = new Stack<>();
		Stack<StringBuilder> strStack = new Stack<>();
		
		StringBuilder cur = new StringBuilder();
		int k = 0;
		for (char ch : s.toCharArray()){
			if(Character.isDigit(ch)){
				k = k * 10 + ch - '0';
			}
			else if (ch == '['){
				intStack.push(k);
				strStack.push(cur);
				cur = new StringBuilder();
				k = 0;
			}else if (ch == ']'){
				StringBuilder tmp = cur;
				cur = strStack.pop();
				for (k = intStack.pop(); k > 0; --k) cur.append(tmp);
			}else{
				cur.append(ch);
			}
		}
		return cur.toString();
	}
	
	public static void main(String[] args) {
		SolutionDay17_394 day = new SolutionDay17_394();
		System.out.println(day.decodeString("3[a2[c]]"));
	}

}
