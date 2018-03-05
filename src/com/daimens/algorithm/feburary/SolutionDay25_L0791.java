package com.daimens.algorithm.feburary;

import java.util.Arrays;
import java.util.Comparator;

public class SolutionDay25_L0791 {
	
    public String customSortString(String S, String T) {
        Character[] cs = new Character[T.length()];
        for (int i = 0; i < T.length(); ++i) {
        	cs[i] = T.charAt(i);
        }
        Arrays.sort(cs, new Comparator<Character>() {
			@Override
			public int compare(Character o1, Character o2) {
				return S.indexOf(o1) - S.indexOf(o2);
			}
		});
        
        StringBuilder sb = new StringBuilder();
        for (char c : cs) sb.append(c);
        return sb.toString();
    }
    
}
