package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 539.Minimum Time Difference
 * Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between
 * any two time points in the list.
 * Example 1:
 * Input["23:59","00:00"]
 * Output: 1
 * 
 * Note:
 * 1. The number of time points in the given list is at lesat 2 and won't exceed 20000.
 * 2. The input time is legal and ranges from 00:00 to 23:59
 *
 */
public class SolutionDay12_501 {

	
//	//space O(n)
//	public int findMinDifference(List<String> timePoints) {
//		
//		int[] times = new int[timePoints.size()];
//		int index = 0;
//		
//		for (String num : timePoints){
//			String[] time = num.split(":");
//			int minutes = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
//			times[index++] = minutes;
//		}
//		
//		Arrays.sort(times);
//		
//		int min = Integer.MAX_VALUE;
//		
//		//该数组的表现形式其实是个loop 所以我们可以由两个指针来记录下来，写在一个循环里面。当然跟loop 和非 loop 也没有多大关系
//		
//		for (int i = 1; i < times.length; i++){
//			if(times[i] - times[i-1] < min){
//				min = times[i] - times[i-1];
//			}
//		}
//		
//		//最后一个数
//		
//		if ((times[0] + 24 * 60 )- times[times.length -1] < min){
//			min = (times[0] + 24 * 60 )- times[times.length -1];
//		}
//		
//		return min;
//	}
	
	
	//my second submit solution within 37ms
//	public int findMinDifference(List<String> timePoints) {
//
//		int[] times = new int[timePoints.size()];
//		int index = 0;
//
//		for (String num : timePoints) {
//			String[] time = num.split(":");
//			int minutes = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
//			times[index++] = minutes;
//		}
//
//		Arrays.sort(times);
//
//		int min = Integer.MAX_VALUE;
//
//		int prev = times[times.length-1];
//		for (int i = 0; i < times.length; i++){
//			int diff = (times[i] - prev);
//			if(diff < 0) diff += 1440;
//			min = Math.min(min, diff);
//			prev = times[i];
//		}
//		
//		return min;
//	}

//	public int findMinDifference(List<String> timePoints) {
//		Collections.sort(timePoints);
//		int minDiff = Integer.MAX_VALUE;
//		String prev = timePoints.get(timePoints.size() - 1);
//		for (String s : timePoints) {
//			int prevMins = Integer.parseInt(prev.split(":")[0]) * 60 + Integer.parseInt(prev.split(":")[1]);
//			int curMins = Integer.parseInt(s.split(":")[0]) * 60 + Integer.parseInt(s.split(":")[1]);
//			int diff = curMins - prevMins;
//			if (diff < 0)
//				diff += 1440;
//			minDiff = Math.min(minDiff, diff);
//			prev = s;
//		}
//		return minDiff;
//	}
	
	public int findMinDifference(List<String> timePoints) {
		
		boolean[] timeSeen = new boolean[1440];

		for (String s : timePoints) {
			int mins = Integer.parseInt(s.split(":")[0]) * 60 + Integer.parseInt(s.split(":")[1]);
			if (timeSeen[mins])
				return 0;
			timeSeen[mins] = true;
		}
		
		int minDiff = Integer.MAX_VALUE,prev = 0;
		
		for (int i = 1439; i >= 0; i--){
			if(timeSeen[i]){
				prev = i;
				break;
			}
		}
		
		for (int i = 0; i < 1440; i++) {
			if(timeSeen[i]){
				int diff = i - prev;
				if(diff <0) diff += 1440;
				minDiff = Math.min(minDiff, diff);
				prev = i;
			}
		}

		return minDiff;
	}
	
	
	
	public static void main(String[] args) {
		SolutionDay12_501 day = new SolutionDay12_501();
		List<String> timePoints = new ArrayList<>();
		timePoints.add("23:59");
		timePoints.add("00:00");
		day.findMinDifference(timePoints);
	}
}
