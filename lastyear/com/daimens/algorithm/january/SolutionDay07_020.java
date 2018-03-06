package com.daimens.algorithm.january;

import java.util.Stack;

/**
 * 
 * @author Demon Song
 * 20.Valid Parentheses
 * Given a string containing just the characters'(', ')', '{', '}', '[' and ']', 
 * determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" 
 * are all valid but "(]" and "([)]" are not.
 *
 */
public class SolutionDay07_020 {
	public boolean isValid(String s){
		int length = s.length();
		Stack<Character> left_char_stack = new Stack<Character>();
		char[] sarrays = s.toCharArray();
		for (int i = 0; i < length; i++) {
			if(sarrays[i] =='(' || sarrays[i]=='[' || sarrays[i]=='{'){
				left_char_stack.push(sarrays[i]);
			}else{
				if(left_char_stack.isEmpty()){
					return false;
				}
				char tmp = left_char_stack.peek();
				if( (tmp=='('&& sarrays[i]==')') || (tmp=='['&& sarrays[i]==']') || (tmp=='{'&& sarrays[i]=='}') ){  
                    left_char_stack.pop();  
                }else{  
                    return false;  
                }  
			}
		}
		if(left_char_stack.empty())  
            return true;  
        else  
            return false;  
	}
}
