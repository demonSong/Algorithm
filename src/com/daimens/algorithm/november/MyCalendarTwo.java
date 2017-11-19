package com.daimens.algorithm.november;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyCalendarTwo {
	
	class Interval implements Comparable<Interval>{
		int s;
		int e;
		Interval(int s, int e){
			this.s = s;
			this.e = e;
		}
		
		@Override
		public int compareTo(Interval o) {
			return this.s == o.s ? this.e - o.e : this.s - o.s;
		}
		
		@Override
		public String toString() {
			return "[" + s + ", " + e +"]";
		}
	}
	
	List<Interval> mem;
	
	public MyCalendarTwo() {
        mem = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
    	Interval candicate = new Interval(start, end);
        if (tripleOverlap(candicate)) {
        	return false;
        }
        else {
        	mem.add(candicate);
        	return true;
        }
    }
    
    // 在 它的范围内不能出现 overlap
    boolean tripleOverlap(Interval candicate) {
    	Collections.sort(mem);
        int n = mem.size();
        int left = 0;
        int right = n - 1;
        while (left < n && candicate.s >= mem.get(left).e) left ++;
        while (right >= 0 && candicate.e <= mem.get(right).s) right --;
        for (int i = left; i <= right; ++i) {
            for (int j = left; j < i; ++j){
                Interval ans = new Interval(-1, -1);
                if (overlap(mem.get(j), mem.get(i), ans)) {
                    if (overlap(ans, candicate, new Interval(-1, -1))) return true;
                }
            }
    	}
    	return false;
    }
    
    boolean overlap(Interval a, Interval b, Interval ans) {
    	if (b.s >= a.s && b.s < a.e || b.e <= a.e && b.e > a.s || b.s <= a.s && b.e >= a.e) {
    		ans.s = Math.max(b.s, a.s);
    		ans.e = Math.min(a.e, b.e);
    		return true;
    	}
    	return false;
    }
    
    public static void main(String[] args) {
    	MyCalendarTwo MyCalendar = new MyCalendarTwo();
    	System.out.println(MyCalendar.book(10, 20));
    	System.out.println(MyCalendar.book(50, 60));
    	System.out.println(MyCalendar.book(10, 40));
    	System.out.println(MyCalendar.book(5, 15));
    	System.out.println(MyCalendar.book(5, 10));
    	System.out.println(MyCalendar.book(25, 55));
	}
    
}
