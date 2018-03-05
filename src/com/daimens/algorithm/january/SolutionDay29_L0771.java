package com.daimens.algorithm.january;

public class SolutionDay29_L0771 {
	
    public int numJewelsInStones(String J, String S) {
        int[] count = new int[64];
        for (char c : J.toCharArray()) {
        	count[c - 'A']++;
        }
        int ans = 0;
        for (char c : S.toCharArray()) {
        	if (count[c - 'A'] >= 1) ans ++;
        }
    	return ans;
    }
	
    public static void main(String[] args) {
    	SolutionDay29_L0771 day = new SolutionDay29_L0771();
    	System.out.println(day.numJewelsInStones("sasa", "sakhsakjh"));
	}
}
