package com.daimens.algorithm.november;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolutionDay13_L0503 {
	
	class Window implements Comparable<Window>{
		int idx;
		int len;
		String s;
		Window(int idx, int len, String s){
			this.idx = idx;
			this.len = len;
			this.s   = s;
		}
		
		@Override
		public int compareTo(Window o) {
			return this.len == o.len ? this.idx - o.idx : this.len - o.len;
		}
	}
	
    public String minWindow(String S, String T) {
    	char[] cs = S.toCharArray();
    	int lf = 0;
    	int rt = 0;
    	int n = cs.length;
    	
    	List<Window> windows = new ArrayList<>();
    	for (;;) {
    		String s = new String(cs).substring(lf, rt + 1);
    		while (rt < n && !valid(s, T)) {
    			rt ++;
    			if (rt >= n) break;
    			s = new String(cs).substring(lf, rt + 1);
    		}
    		
    		if (rt >= n && !valid(s, T)) break;
    		windows.add(new Window(lf, s.length(), s));
    		lf ++;
    	}
    	
    	if (windows.size() == 0) return "";
    	
    	Collections.sort(windows);
    	return windows.get(0).s;
    }
    
    boolean valid(String s, String t) {
    	char[] cs = s.toCharArray();
    	int n = cs.length;
    	int j = 0;
    	for (int i = 0; i < n && j < t.length(); ++i) {
    		if (cs[i] == t.charAt(j)) {
    			j++;
    		}
    	}
    	return j == t.length();
    }
    
    public static void main(String[] args) {
    	SolutionDay13_L0503 day = new SolutionDay13_L0503();
    	System.out.println(day.minWindow("abcdebdde", "bde"));
    }
}
