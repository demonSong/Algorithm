package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class SolutionDay29_L0502 {
	
	public static List<String> generateParenthesis(int n) {
		List<String> ans = new ArrayList<>();
		helper(n, "", ans);
        return ans;
    }
	
	private static void helper(int len, String tmp, List<String> ans){
		if (tmp.length() == 2 * len){
			if (isValid(tmp))
				ans.add(tmp);
			return;
		}
		
		helper(len, tmp+'(', ans);
		tmp += ')';
		helper(len, tmp, ans);
	}
	
	public static boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++){
        	if (s.charAt(i) == '('){
        		stack.push(s.charAt(i));
        	}
        	else{
				char c = !stack.isEmpty() ? stack.peek() : '.';
        		if (s.charAt(i) == ')' && c == '(') stack.pop();
        		else return false;
        	}
        }
        return stack.isEmpty();
    }
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			List<String> hei = generateParenthesis(n);
			String[] haha = hei.toArray(new String[0]);
			System.out.println(String.join(",", haha));
		}
		in.close();
	}
	

}
