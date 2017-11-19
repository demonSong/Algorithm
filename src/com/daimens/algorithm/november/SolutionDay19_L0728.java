package com.daimens.algorithm.november;

import java.util.ArrayList;
import java.util.List;

public class SolutionDay19_L0728 {
	
	public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i <= right; ++i) {
        	if (valid(i)) {
        		ans.add(i);
        	}
        }
        return ans;
    }
	
	boolean valid (int num) {
		int n = num;
		while (num != 0) {
			int c = num % 10;
			if (c == 0) return false;
			else {
				if (n % c != 0) return false;
			}
			num /= 10;
		}
		return true;
	}
	
//	boolean valid(int num) {
//		String value = Integer.toString(num);
//		for (char c : value.toCharArray()) {
//			if (c == '0') return false;
//			else {
//				if (num % (c - '0') != 0) return false;
//			}
//		}
//		return true;
//	}
	
	public static void main(String[] args) {
		SolutionDay19_L0728 day = new SolutionDay19_L0728();
	}

}
