package com.daimens.algorithm.november;

import java.util.ArrayList;
import java.util.List;

public class MyCalendar {
	
	class Interval{
		int s;
		int e;
		
		Interval(int s, int e){
			this.s = s;
			this.e = e;
		}
	}
	
	List<Interval> mem;
	
	public MyCalendar() {
		mem = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        Interval candicate = new Interval(start, end);
        if (overlap(candicate)) {
        	return false;
        }
        else {
        	mem.add(candicate);
        	return true;
        }
    }
    
    public boolean overlap(Interval cand) {
    	for (Interval tmp : mem) {
    		int s = tmp.s;
    		int e = tmp.e;
    		if (!(cand.s >= e || cand.e <= s)) return true;
    	}
    	return false;
    }
}
