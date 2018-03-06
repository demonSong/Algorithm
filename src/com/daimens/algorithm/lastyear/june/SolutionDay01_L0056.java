package com.daimens.algorithm.june;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 
 *         56.Merge Intervals
 * 
 *         Given a collection of intervals, merge all overlapping intervals.
 * 
 *         For example, Given [1,3],[2,6],[8,10],[15,18], return
 *         [1,6],[8,10],[15,18].
 *
 */
public class SolutionDay01_L0056 {
	
	public List<Interval> merge(List<Interval> intervals) {
		if (intervals.size() == 0) return new ArrayList<>();
		Collections.sort(intervals, (a,b) -> a.start != b.start ? a.start - b.start : a.end - b.end);
		List<Interval> ans = new ArrayList<>();
		int start = intervals.get(0).start;
		int end = intervals.get(0).end;
		for (Interval inter : intervals){
			if (end >= inter.start){
				end = Math.max(end, inter.end);
			}else{
				ans.add(new Interval(start, end));
				start = inter.start;
				end = inter.end;
			}
		}
		ans.add(new Interval(start,end));
		return ans;
    }

}

class Interval{
	int start;
	int end;
	Interval(){
		start = 0;
		end = 0;
	}
	Interval(int s, int e){
		start = s;
		end = e;
	}
	@Override
	public String toString() {
		return "Interval [start=" + start + ", end=" + end + "]";
	}
}
