package com.daimens.algorithm.january;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolutionDay07_L0759 {
	
	class P implements Comparable<P>{
		int start;
		int end;
		
		P(int start, int end){
			this.start = start;
			this.end   = end;
		}
		
		@Override
		public int compareTo(P o) {
			return this.start == o.start ? this.end - o.end : this.start - o.start;
		}
	
		@Override
		public String toString() {
			return "[" + start + ", " + end + "]";
		}
	}
	
    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
    	List<P> unSorted = new ArrayList<>();
    	for (List<Interval> avail : avails) {
    		for (Interval inter : avail) {
    			unSorted.add(new P(inter.start, inter.end));
    		}
    	}
    	Collections.sort(unSorted);
    	List<Interval> ans = new ArrayList<>();
    	int n = unSorted.size();
    	for (int i = 0; i < n; ++i) {
    		P now = unSorted.get(i);
    		int maxRight = now.end;
    		int j = i + 1;
    		while (j < n && unSorted.get(j).start <= maxRight) {
    			maxRight = Math.max(maxRight, unSorted.get(j).end);
    			j ++;
    		}
    		if (j < n) ans.add(new Interval(maxRight, unSorted.get(j).start));
    		i = j - 1;
    	}
    	return ans;
    }
	
	public static void main(String[] args) {
		SolutionDay07_L0759 day = new SolutionDay07_L0759();
		List<List<Interval>> avails = new ArrayList<>();
		List<Interval> a1 = new ArrayList<>();
		a1.add(new Interval(1, 3));
		a1.add(new Interval(6, 7));
		avails.add(a1);
		List<Interval> a2 = new ArrayList<>();
		a2.add(new Interval(2, 4));
		avails.add(a2);
		List<Interval> a3 = new ArrayList<>();
		a3.add(new Interval(2, 5));
		a3.add(new Interval(9, 12));
		avails.add(a3);
		System.out.println(day.employeeFreeTime(avails));
	}
}

class Interval implements Comparable<Interval>{
	int start;
	int end;
	
	Interval(int start, int end){
		this.start = start;
		this.end   = end;
	}
	
	@Override
	public int compareTo(Interval o) {
		return this.start == o.start ? this.end - o.end : this.start - o.start;
	}
}

