package com.daimens.algorithm.march;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SolutionDay24_L0501 {
	
	
	
	static class Pair{
		int x;
		int y;
		
		public Pair(int x, int y) {
			if (x > y) {
				int tmp = x;
				x = y;
				y = tmp;
			}
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int hashCode() {
			return this.x * 17 + this.y;
		}
		
		@Override
		public boolean equals(Object obj) {
			Pair o = (Pair) obj;
			return this.x == o.x && this.y == o.y;
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
        	int n = in.nextInt();
        	int k = in.nextInt();
        	int[] ele = new int[n];
        	for (int i = 0; i < n; ++i) {
        		ele[i] = in.nextInt();
        	}
        	Arrays.sort(ele);
        	Set<Pair> set = new HashSet<>();
        	for (int i = 0; i < n; ++i) {
        		for (int j = i + 1; j < n; ++j) {
        			if (Math.abs(ele[i] - ele[j]) <= k) {
        				if (Math.abs(ele[i] - ele[j]) == k)
        					set.add(new Pair(ele[i], ele[j]));
        			}
        			else {
        				break;
        			}
        		}
        	}
        	System.out.println(set.size());
        }
        in.close();
	}
}
