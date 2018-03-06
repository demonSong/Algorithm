package com.daimens.algorithm.may;

import java.util.Stack;

/**
 * 
 * @author DemonSong
 * 
 *         150.Evaluate Reverse Polish Notation
 * 
 *         Evaluate the value of an arithmetic expression in Reverse Polish
 *         Notation.
 * 
 *         Valid operators are +, -, *, /. Each operand may be an integer or
 *         another expression.
 * 
 *         Some examples: ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9 ["4",
 *         "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 *
 */
public class SolutionDay27_L0150 {
	public int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < tokens.length; i++){
			if (!isOperator(tokens[i])){
				stack.push(Integer.parseInt(tokens[i]));
			}else{
				int a1 = stack.pop();
				int a2 = stack.pop();
				if (tokens[i].equals("+")){
					stack.push(a2 + a1);
				}
				if (tokens[i].equals("-")){
					stack.push(a2 - a1);
				}
				if (tokens[i].equals("*")){
					stack.push(a2 * a1);
				}
				if (tokens[i].equals("/")){
					stack.push(a2 / a1);
				}
			}
		}
		return stack.peek();
    }
	
	private boolean isOperator(String op){
		return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/");
	}
	
	public static void main(String[] args) {
		SolutionDay27_L0150 day = new SolutionDay27_L0150();
		//String[] tokens = {"4","13","5","/","+"};
		String[] tokens = {"2","1","+","3","*"};
		System.out.println(day.evalRPN(tokens));
	}
}
