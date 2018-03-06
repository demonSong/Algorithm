package com.daimens.algorithm.april;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * @author DemonSong 
 * 352.Data Stream as Disjoint Intervals.
 * 
 *         Given a data stream input of non-negative integers a1, a2, ..., an,
 *         ..., summarize the numbers seen so far as a list of disjoint
 *         intervals.
 * 
 *         For example, suppose the integers from the data stream are 1, 3, 7,
 *         2, 6, ..., then the summary will be:
 * 
 *         [1, 1] [1, 1], [3, 3] [1, 1], [3, 3], [7, 7] [1, 3], [7, 7] [1, 3],
 *         [6, 7] Follow up: What if there are lots of merges and the number of
 *         disjoint intervals are small compared to the data stream's size?
 *
 */
public class SolutionDay20_352 {
	
//	Queue<Integer> queue;
//	List<Interval> ans;
//	public SolutionDay20_352() {
//        queue = new LinkedList<>();
//        ans = new ArrayList<>();
//    }
//    
//    public void addNum(int val) {
//        queue.offer(val);
//    }
//    
//    public List<Interval> getIntervals() {
//    	while (!queue.isEmpty()){
//    		Interval it = new Interval(queue.peek(), queue.peek());
//    		ans.add(it);
//    		queue.poll();
//    	}
//    	Interval[] inter = ans.toArray(new Interval[0]);
//    	ans.clear();
//    	Arrays.sort(inter,new Comparator<Interval>() {
//			@Override
//			public int compare(Interval o1, Interval o2) {
//				return o1.start - o2.start != 0 ? o1.start - o2.start : o1.end - o2.end;
//			}
//		});
//    	
//    	ans.add(inter[0]);
//    	
//    	for (int i = 1; i < inter.length; i++){
//    		if (inter[i].start - inter[i-1].end <= 1){ // 合并
//    			Interval ii = ans.get(ans.size()-1);
//    			ans.remove(ans.size()-1);
//    			ans.add(new Interval(ii.start,Math.max(ii.end, inter[i].end)));
//    		}else{
//    			ans.add(inter[i]);
//    		}
//    	}
//        return ans;
//    }
	
	public SolutionDay20_352() {
	}
	
	public void addNum(int val){
	}
	
	public List<Interval> getIntervals() {
		return null;
	}
    
    public static void main(String[] args) {
    	SolutionDay20_352 day = new SolutionDay20_352();
    	day.addNum(1);
    	day.getIntervals();
    	day.addNum(3);
    	day.getIntervals();
    	day.addNum(7);
    	day.getIntervals();
    	day.addNum(2);
    	day.addNum(6);
    	day.getIntervals();
	}

}
