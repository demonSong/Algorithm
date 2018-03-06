package com.daimens.algorithm.april;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 436.Find Right Interval
 * Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to 
 * the end point of the interval i, which can be called that j is on the "right" of i.
 * For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum start
 * point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i. Finally
 * you need output the stored value of each interval as an array.
 * 
 * Note:
 * 1. You may assume the interval's end point is always bigger than its start point.
 * 2. You may assume none of these intervals have the same start point.
 * 
 * Example 1:
 * Input:[ [1,2] ]
 * Output: [-1]
 * Explanation: There is only one interval in the collection, so it outputs -1.
 * 
 * Example 2:
 * Input: [ [3,4], [2,3], [1,2] ]
 * Output: [-1, 0, 1]
 * Explanation: There is no satisfied "right" interval for [3,4].
 * For [2,3], the interval [3,4] has minimum-"right" start point;
 * For [1,2], the interval [2,3] has minimum-"right" start point.
 * 
 * Example 3:
 * Input: [ [1,4], [2,3], [3,4] ]
 * Output: [-1, 2, -1]
 * Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
 * For [2,3], the interval [3,4] has minimum-"right" start point.
 *
 */
public class SolutionDay06_436 {
	
	public int[] findRightInterval(Interval[] intervals) {
		
		if (intervals.length <= 1) return new int[]{-1};
		
		Interval[] tmp = new Interval[intervals.length];
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < intervals.length; i++){
			tmp[i] = intervals[i];
			map.put(intervals[i].start, i);
		}
		
		
		Arrays.sort(tmp,new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start-o2.start;
			}
		});
		
		
		int[] res = new int[intervals.length];
		for(int i = 0; i < intervals.length; i++){
			int point = binarySearch(tmp, intervals[i].end);
			if(point != -1)
			    res[i] = map.get(tmp[point].start);
			else
			    res[i] = -1;
		}
		
        return res;
    }
	
	// >= key
	private int binarySearch(Interval[] intervals,int key){
		
		int lf = 0, rt = intervals.length-1;
		
		while (lf < rt){
			int mid = lf + (rt - lf) /2;
			
			if(intervals[mid].start < key){
				lf = mid + 1;
			}else{
				rt = mid;
			}
		}
		
		if(intervals[rt].start >= key) return rt;
		
		return -1;
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
		return "["+start+","+end+"]";
	}
	
}
