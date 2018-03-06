package com.daimens.algorithm.may;

import java.util.Stack;

/**
 * 
 * @author DemonSong
 * 
 *         20.Valid Parentheses
 * 
 *         Given a string containing just the characters '(', ')', '{', '}', '['
 *         and ']', determine if the input string is valid.
 * 
 *         The brackets must close in the correct order, "()" and "()[]{}" are
 *         all valid but "(]" and "([)]" are not.
 * 
 *
 */
public class SolutionDay10_L0020 {
	
	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++){
        	if (s.charAt(i) == '[' || s.charAt(i) == '{' || s.charAt(i) == '('){
        		stack.push(s.charAt(i));
        	}
        	else{
				char c = !stack.isEmpty() ? stack.peek() : '.';
        		if (s.charAt(i) == ']' && c == '[') stack.pop();
        		else if (s.charAt(i) == '}' && c == '{') stack.pop();
        		else if (s.charAt(i) == ')' && c == '(') stack.pop();
        		else return false;
        	}
        }
        return stack.isEmpty();
    }
	
	public static void main(String[] args) {
		SolutionDay10_L0020 day = new SolutionDay10_L0020();
		day.isValid("(])");
	}

}
