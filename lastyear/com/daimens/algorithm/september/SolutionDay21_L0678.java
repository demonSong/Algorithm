package com.daimens.algorithm.september;

public class SolutionDay21_L0678 {
	public boolean checkValidString(String s) {
		return robot(s.toCharArray(), 0, 0, 0);
	}

	boolean robot(char[] cs, int i, int left, int right) {
		if (i >= cs.length) {
			return left == right;
		}
		for (int j = i; j < cs.length; ++j) {
			if (cs[j] == '(')
				left++;
			else if (cs[j] == ')') {
				right++;
				if (right > left) return false;
			} 
			else {
				return robot(cs, j + 1, left, right) || robot(cs, j + 1, left + 1, right)
						|| robot(cs, j + 1, left, right + 1);
			}
		}
		return left == right;
	}
}
