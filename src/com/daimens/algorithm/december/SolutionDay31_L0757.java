package com.daimens.algorithm.december;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolutionDay31_L0757 {
	
	class P implements Comparable<P>{
		int s;
		int e;
		int c;
		
		P(int s, int e){
			this.s = s;
			this.e = e;
			this.c = 0;
		}
		
		@Override
		public int compareTo(P o) {
			return this.e - o.e;
		}
	}
	
    public int intersectionSizeTwo(int[][] intervals) {
    	List<P> ps = new ArrayList<>();
    	int n = intervals.length;
    	for (int i = 0; i < n; ++i) {
    		ps.add(new P(intervals[i][0], intervals[i][1]));
    	}
    	Collections.sort(ps);
    	int ans = 0;
    	for (int i = 0; i < n; ++i) {
    		P inter = ps.get(i);
    		if (inter.c == 0) {
    			int pos = i + 1;
    			while (pos < n && ps.get(pos).s <= inter.e) {
    				ps.get(pos).c ++;
    				if (ps.get(pos).s < inter.e) {
    					ps.get(pos).c ++;
    				}
    				pos ++;
    			}
    			ans += 2;
    		}
    		else if (inter.c == 1){
    			int pos = i + 1;
    			while (pos < n && ps.get(pos).s <= inter.e) {
    				ps.get(pos).c ++;
    				pos ++;
    			}
    			ans ++;
    		}
    	}
    	return ans;
    }
    
	public static void main(String[] args) {
		SolutionDay31_L0757 day = new SolutionDay31_L0757();
	}

}
