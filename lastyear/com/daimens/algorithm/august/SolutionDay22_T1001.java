package com.daimens.algorithm.august;

public class SolutionDay22_T1001 {
	
	// w u b a
	public int solve(String str){
		char[] cs = str.toCharArray();
		int n = cs.length;
		int min = n + 1;
		int[] map = new int[32];
		int i = 0, j = 0;
		int len = 0;
		for (;;){
			while (j < n && len != 4){
				if ((cs[j] == 'w' || cs[j] == 'a' || cs[j] == 'u' || cs[j] == 'b')){
					map[cs[j] - 'a'] ++;
					if (map[cs[j] - 'a'] == 1) len ++;
				}
				j ++;
			}
			if (len < 4) break;
			min = Math.min(min, j - i);
			map[cs[i] - 'a'] --;
			if (map['w' - 'a'] == 0){
				len --;
			}
			if (map['u' - 'a'] == 0){
				len --;
			}
			if (map['b' - 'a'] == 0){
				len --;
			}
			if (map['a' - 'a'] == 0){
				len --;
			}
			i ++;
		}
		
		if (min == n + 1) return -1;
		else return min;
	}
	
	
	public static void main(String[] args) {
		SolutionDay22_T1001 day = new SolutionDay22_T1001();
		System.out.println(day.solve("happywahubcc"));
	}
}
