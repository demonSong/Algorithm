package com.daimens.algorithm.october;

public class SolutionDay29_L0717 {
	
	public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        int i = 0;
        for (; i < n - 1;) {
        	if (bits[i] == 1) {
        		i += 2;
        	}
        	else {
        		i += 1;
        	}
        }
        return i == n - 1;
	}
	
	public static void main(String[] args) {
		SolutionDay29_L0717 day = new SolutionDay29_L0717();
	}

}
