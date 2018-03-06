package com.daimens.algorithm.codeforce.round23;

import java.util.Scanner;
import java.util.Stack;

public class SolutionDay26_C6253 {
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		int N = Integer.parseInt(s);
		Stack<Integer> stack = new Stack<>();
		int cnt = 0;
		int now = 1;
		boolean first = true;
		for (int i = 0; i < 2 * N; ++i){
			s = in.nextLine();
			String[] command = s.trim().split(" ");
			if (command[0].equals("add")){
				first = true;
				stack.push(Integer.parseInt(command[1]));
			}
			else{
				int x = stack.peek();
				if (x != now || !first){
					stack.pop();
					if (first){
						cnt++;
						first = false;
					}
					now++;
				}
				else{
					first = true;
					now++;
					stack.pop();
				}
			}
		}
		System.out.println(cnt);
		in.close();
	}
}
