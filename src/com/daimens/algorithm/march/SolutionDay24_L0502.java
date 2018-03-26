package com.daimens.algorithm.march;

import java.util.Arrays;
import java.util.Scanner;

public class SolutionDay24_L0502 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] lines = new String[n];
        for (int i = 0; i < n; ++i) {
        	lines[i] = in.nextLine().trim();
        }
        
        // 0
        char[][] zero = new char[5][5];
        for (int i = 0; i < 5; ++i) Arrays.fill(zero[i], '.');
        
        
        
        // 1
        char[][] one = new char[5][5];
        for (int i = 0; i < 5; ++i) Arrays.fill(one[i], '.');
        for (int i = 0; i < 5; ++i) one[4][i] = '6';
        
        // 2
        char[][] two = new char[5][5];
        for (int i = 0; i < 5; ++i) Arrays.fill(one[i], '.');
        Arrays.fill(two[0], '6');
        two[1][4] = '6';
        two[3][0] = '6';
        Arrays.fill(two[2], '6');
        Arrays.fill(two[4], '6');
        
        in.close();
	}

}
