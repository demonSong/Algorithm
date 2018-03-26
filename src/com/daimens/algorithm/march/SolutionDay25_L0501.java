package com.daimens.algorithm.march;

import java.util.HashSet;
import java.util.Set;

public class SolutionDay25_L0501 {
	
	public int uniqueMorseRepresentations(String[] words) {
		String[] map = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
		Set<String> set = new HashSet<>();
		for (String word : words) {
			StringBuilder sb = new StringBuilder();
			for (char c : word.toCharArray()) {
				sb.append(map[c - 'a']);
			}
			set.add(sb.toString());
		}
		return set.size();
    }
	
	public static void main(String[] args) {
		SolutionDay25_L0501 day = new SolutionDay25_L0501();
	}

}
