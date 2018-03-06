package com.daimens.algorithm.may;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 
 *         214.Different Ways to Add Parentheses
 * 
 *         Given a string of numbers and operators, return all possible results
 *         from computing all the different possible ways to group numbers and
 *         operators. The valid operators are +, - and *.
 * 
 * 
 *         Example 1 Input: "2-1-1".
 * 
 *         ((2-1)-1) = 0 (2-(1-1)) = 2 Output: [0, 2]
 * 
 * 
 *         Example 2 Input: "2*3-4*5"
 * 
 *         (2*(3-(4*5))) = -34 ((2*3)-(4*5)) = -14 ((2*(3-4))*5) = -10
 *         (2*((3-4)*5)) = -10 (((2*3)-4)*5) = 10 Output: [-34, -14, -10, -10,
 *         10]
 *
 */
public class SolutionDay10_L0241 {

	public List<Integer> diffWaysToCompute(String input) {
		List<Integer> ans = new ArrayList<>();
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '*' || input.charAt(i) == '-' || input.charAt(i) == '+') {
				List<Integer> left = diffWaysToCompute(input.substring(0, i));
				List<Integer> right = diffWaysToCompute(input.substring(i + 1));

				switch (input.charAt(i)) {
				case '*': {
					for (int a1 : left) {
						for (int a2 : right) {
							ans.add(a1 * a2);
						}
					}
				}
					break;
				case '-': {
					for (int a1 : left) {
						for (int a2 : right) {
							ans.add(a1 - a2);
						}
					}
				}
					break;

				case '+': {
					for (int a1 : left) {
						for (int a2 : right) {
							ans.add(a1 + a2);
						}
					}
				}
					break;
				default:
					break;
				}
			}
		}
		
		if (ans.size() == 0){
			ans.add(Integer.valueOf(input));
		}
		
		return ans;
	}

	public static void main(String[] args) {
		SolutionDay10_L0241 day = new SolutionDay10_L0241();
		day.diffWaysToCompute("2*3-4*5");
	}

}
