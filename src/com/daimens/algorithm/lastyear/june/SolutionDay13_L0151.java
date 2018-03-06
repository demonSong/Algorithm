package com.daimens.algorithm.june;

import java.util.Stack;

/**
 * 
 * @author DemonSong
 * 
 *         151. Reverse Words in a String
 * 
 *         Given an input string, reverse the string word by word.
 * 
 *         For example, Given s = "the sky is blue", return "blue is sky the".
 *
 */
public class SolutionDay13_L0151 {
	
	public String reverseWords(String s) {
		String[] words = s.trim().split(" +");
		Stack<String> stack = new Stack<>();
		for (String word : words){
			stack.push(word);
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()){
			sb.append(stack.pop() + " ");
		}
        return sb.toString().trim();
    }
}
