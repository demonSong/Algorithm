package com.daimens.algorithm.feburary;

import java.util.ArrayList;
import java.util.List;

public class SolutionDay18_L0784 {
	
    public List<String> letterCasePermutation(String S) {
    	all = new ArrayList<>();
    	backtrack(S.toCharArray(), 0, "");
    	return all;
    }
    
    List<String> all;
	public void backtrack(char[] cs, int pos, String ans) {
		if (pos == cs.length) {
			all.add(ans);
			return;
		}
		else {
			if (Character.isAlphabetic(cs[pos])) {
				char l = cs[pos];
				backtrack(cs, pos + 1, ans + l);
				if (l >= 'a' && l <= 'z') l = (char) (l - 'a' + 'A');
				else if (l >= 'A' && l <= 'Z') l = (char) (l - 'A' + 'a');
				backtrack(cs, pos + 1, ans + l);
			}
			else {
				backtrack(cs, pos + 1, ans + cs[pos]);
			}
		}
	}
    
	public static void main(String[] args) {
		SolutionDay18_L0784 day = new SolutionDay18_L0784();
		System.out.println(day.letterCasePermutation("a1b2"));
	}

}
