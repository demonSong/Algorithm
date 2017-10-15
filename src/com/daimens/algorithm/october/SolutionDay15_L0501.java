package com.daimens.algorithm.october;

import java.util.HashMap;
import java.util.Map;

public class SolutionDay15_L0501 {
	
    public int countBinarySubstrings(String s) {
    	int n = s.length();
    	char[] cs = s.toCharArray();
    	int[] num = new int[n];
    	for (int i = 0; i < n; ++i) {
    		num[i] = cs[i] == '0' ? - 1 : 1;
    	}
    	
    	int[] sum = new int[n + 1];
    	for (int i = 0; i < n; ++i) {
    		sum[i + 1] = sum[i] + num[i];
    	}
    	
    	Map<Integer, Integer> map = new HashMap<>();
    	map.put(0, 0);
    	int cnt = 0;
    	for (int i = 1; i <= n; ++i) {
    		if (map.containsKey(sum[i])) {
    			if (group(cs, map.get(sum[i]), i - 1)) cnt++;
    			
    		}
    		map.put(sum[i], i);
    	}
    	return cnt;
    }
    
    boolean group(char[] cs, int i, int j) {
    	int prev = cs[i] - '0';
    	int n = j - i + 1;
    	for (int k = 1; k < n / 2; ++k) {
    		if (prev != cs[k + i] - '0') return false; 
    	}
    	return true;
    }
	
	public static void main(String[] args) {
		SolutionDay15_L0501 day = new SolutionDay15_L0501();
		System.out.println(day.countBinarySubstrings("00110011"));
	}
}
