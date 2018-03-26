package com.daimens.algorithm.march;

public class SolutionDay25_L0500 {
	
    public int[] numberOfLines(int[] widths, String S) {
    	int line = 1;
    	int prev = 0;
    	int width = 0;
    	for (char c : S.toCharArray()) {
    		int w = widths[c - 'a'];
    		if (width + w > 100) {
    			prev = width;
    			line ++;
    			width = 0;
    		}
    		else {
    		}
    		width += w;
    	}
    	prev = width;
    	return new int[] {line, prev};
    }
    
	
	public static void main(String[] args) {
		SolutionDay25_L0500 day = new SolutionDay25_L0500();
		int[] widths = {10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
		System.out.println(day.numberOfLines(widths, "abcdefghijklmnopqrstuvwxyz"));
	}

}
