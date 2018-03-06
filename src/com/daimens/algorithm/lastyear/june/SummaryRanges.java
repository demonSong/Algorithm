package com.daimens.algorithm.june;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * 
 * @author DemonSong
 * 
 *         352.Data Stream as Disjoint Intervals
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
public class SummaryRanges {

//	List<Interval> ans = new ArrayList<>();
//
//	public SummaryRanges() {
//		ans = new ArrayList<>();
//	}
//
//	public void addNum(int val) {
//		Interval newInterval = new Interval(val, val);
//		List<Interval> tmp = new ArrayList<>();
//		if (ans.size() == 0) {
//			tmp.add(newInterval);
//			ans = tmp;
//			return;
//		}
//		int i = 0;
//		while (i < ans.size() && newInterval.start > ans.get(i).end + 1)
//			tmp.add(ans.get(i++));
//
//		while (i < ans.size() && newInterval.end + 1 >= ans.get(i).start) {
//			newInterval = new Interval(Math.min(ans.get(i).start, newInterval.start),
//					Math.max(ans.get(i).end, newInterval.end));
//			i++;
//		}
//		
//		tmp.add(newInterval);
//		while (i < ans.size())
//			tmp.add(ans.get(i++));
//		ans = tmp;
//	}
//	
//	public List<Interval> getIntervals() {
//		return ans;
//	}
	
	// 数据结构
	
	TreeMap<Integer, Interval> tree;
	public SummaryRanges() {
		tree = new TreeMap<>();
	}

	public void addNum(int val) {
		if (tree.containsKey(val)) return;
		
		Integer l = tree.lowerKey(val);
		Integer h = tree.higherKey(val);
		
		if (l != null && h != null && tree.get(l).end + 1 == val && val == h - 1){
			tree.get(l).end = tree.get(h).end;
			tree.remove(h);
		}
		else if (h != null && val == h - 1){
			tree.put(val, new Interval(val,tree.get(h).end));
			tree.remove(h);
		}
		else if (l != null && tree.get(l).end + 1 >= val){
			tree.get(l).end = Math.max(val, tree.get(l).end);
		}
		else {
			tree.put(val, new Interval(val,val));
		}
	}
	
	public List<Interval> getIntervals() {
		return new ArrayList<>(tree.values());
	}

	public static void main(String[] args) {
		SummaryRanges sr = new SummaryRanges();
		sr.addNum(1);
		sr.addNum(3);
		sr.addNum(2);
	}

}

// Set<Integer> nums;
// public SummaryRanges(){
// nums = new HashSet<>();
// }
//
// public void addNum(int val){
// nums.add(val);
// }
//
// public List<Interval> getIntervals(){
// Integer[] num = nums.toArray(new Integer[0]);
// Arrays.sort(num);
// int[] dp = new int[num.length];
//
// for (int i = 1; i < num.length; i++){
// if (num[i] - num[i-1] == 1) dp[i] = dp[i-1] + 1;
// }
// List<Interval> ans = new ArrayList<>();
// for (int i = 0; i < num.length; i++){
// int same = num[i];
// while (i < num.length && num[i] - dp[i] == same) i++;
// if (same == num[i-1]) ans.add(new Interval(same,same));
// else ans.add(new Interval(same, num[i-1]));
// i--;
// }
// return ans;
// }
