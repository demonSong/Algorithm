package com.daimens.algorithm.may;

import java.util.Stack;

/**
 * 
 * @author DemonSong
 * 
 *         227.Basic Calculator II
 * 
 *         Implement a basic calculator to evaluate a simple expression string.
 * 
 *         The expression string contains only non-negative integers, +, -, *, /
 *         operators and empty spaces . The integer division should truncate
 *         toward zero.
 * 
 *         You may assume that the given expression is always valid.
 * 
 *         Some examples: "3+2*2" = 7 " 3/2 " = 1 " 3+5 / 2 " = 5 Note: Do not
 *         use the eval built-in library function.
 *
 */
public class SolutionDay27_L0227 {
	
//	public int calculate(String s) {
//		String ss = s.replace("+","#+").replace("-", "#-").replace("/", "#/").replace("*", "#*");
//		String[] values = ss.split("#");
//		int[] nums = new int[values.length];
//		char[] operators = new char[values.length-1];
//		
//		int cnt1 = 0;
//		int cnt2 = 0;
//		for (int i = 0; i < values.length; i++){
//			if (values[i].toCharArray()[0] == '+' ||
//					values[i].toCharArray()[0] == '-' ||
//						values[i].toCharArray()[0] == '*' ||
//							 values[i].toCharArray()[0] == '/'){
//				operators[cnt2++] = values[i].toCharArray()[0];
//				nums[cnt1++] = Integer.parseInt(values[i].substring(1).trim());
//			}else{
//				nums[cnt1++] = Integer.parseInt(values[i].trim());
//			}
//		}
//		
//		Deque<Integer> numStack = new ArrayDeque<>();
//		Deque<Character> operatorStack = new ArrayDeque<>();
//		numStack.push(nums[0]);
//		for (int i = 0; i < operators.length; i++){
//			if (operators[i] == '*' || operators[i] == '/'){
//				numStack.push(nums[i+1]);
//				int a1 = numStack.pollFirst();
//				int a2 = numStack.pollFirst();
//				switch (operators[i]) {
//				case '*':{
//					numStack.push(a2 * a1);
//				}
//					break;
//				case '/':{
//					numStack.push(a2 / a1);
//				}
//					break;
//				default:
//					break;
//				}
//			}else{
//				operatorStack.push(operators[i]);
//				numStack.push(nums[i+1]);
//			}
//		}
//		
//		while (!operatorStack.isEmpty()){
//			char opera = operatorStack.pollLast();
//			int a1 = numStack.pollLast();
//			int a2 = numStack.pollLast();
//			switch (opera) {
//			case '+':{
//				numStack.addLast(a1 + a2);;
//			}
//				break;
//			case '-':{
//				numStack.addLast(a1 - a2);
//			}
//				break;
//			default:
//				break;
//			}
//		}
//		
//		return numStack.peek();
//    }
	
	public int calculate(String s) {
		if (s == null || s.length() == 0) return 0;
		char[] cs = s.toCharArray();
		Stack<Integer> stack = new Stack<>();
		char sign = '+'; //开始的数字一定为正
		for (int i = 0, num = 0; i < cs.length; i++){
			if (Character.isDigit(cs[i])){
				num = num * 10 + cs[i] - '0';
			}
			if (i == cs.length -1 || !Character.isDigit(cs[i]) && cs[i] != ' '){ //操作符的情况
				if (sign == '+'){
					stack.push(num);
				}
				if (sign == '-'){
					stack.push(-num);
				}
				if (sign == '*'){
					stack.push(stack.pop() * num);
				}
				if (sign == '/'){
					stack.push(stack.pop() / num);
				}
				sign = cs[i];
				num = 0;
			}
		}
		int res = 0;
		for (int i : stack){
			res += i;
		}
		return res;
	}
	
	public static void main(String[] args) {
		SolutionDay27_L0227 day = new SolutionDay27_L0227();
		System.out.println(day.calculate("2+3*2/4+5-2*2"));
		//System.out.println(day.calculate(" 30  + 2"));
	}
	

}
