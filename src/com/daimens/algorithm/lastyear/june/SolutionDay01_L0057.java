package com.daimens.algorithm.june;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 
 *         57.Insert Interval
 * 
 *         Given a set of non-overlapping intervals, insert a new interval into
 *         the intervals (merge if necessary).
 * 
 *         You may assume that the intervals were initially sorted according to
 *         their start times.
 * 
 *         Example 1: Given intervals [1,3],[6,9], insert and merge [2,5] in as
 *         [1,5],[6,9].
 * 
 *         Example 2: Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge
 *         [4,9] in as [1,2],[3,10],[12,16].
 * 
 *         This is because the new interval [4,9] overlaps with
 *         [3,5],[6,7],[8,10].
 *
 */
public class SolutionDay01_L0057 {
	
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		if (intervals.size() == 0) return Collections.singletonList(newInterval);
		List<Interval> ans = new ArrayList<>();
		int i = 0;
		while (i < intervals.size() && newInterval.start > intervals.get(i).end) ans.add(intervals.get(i++));
		
		while (i < intervals.size() && intervals.get(i).start <= newInterval.end){
			newInterval = new Interval(
					Math.min(newInterval.start, intervals.get(i).start),
	                Math.max(newInterval.end, intervals.get(i).end));
			i++;
		}
		ans.add(newInterval);
		
		while (i < intervals.size()) ans.add(intervals.get(i++));
		return ans;
    }
	
//	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
//		if (intervals.size() == 0) return Collections.singletonList(newInterval);
//		List<Interval> ans = new ArrayList<>();
//		int i = 0;
//		while (i < intervals.size() && newInterval.start > intervals.get(i).start) i++;
//		int target = i - 1 == -1 ? 0 : i - 1;
//		
//		for (int j = 0; j < target; j++){
//			ans.add(intervals.get(j));
//		}
//		intervals.add(i, newInterval);
//		int start = intervals.get(target).start;
//		int end = intervals.get(target).end;
//		for (int j = target; j < intervals.size(); j++){
//			if (end >= intervals.get(j).start){
//				end = Math.max(end, intervals.get(j).end);
//			}else{
//				ans.add(new Interval(start,end));
//				start = intervals.get(j).start;
//				end = intervals.get(j).end;
//			}
//		}
//		ans.add(new Interval(start,end));
//	}
	
	public static void main(String[] args) {
		SolutionDay01_L0057 day = new SolutionDay01_L0057();
		List<Interval> intervals = new ArrayList<>();
		intervals.add(new Interval(1,5));
		Interval newInterval = new Interval(2,3);
		day.insert(intervals, newInterval);
	}

}
