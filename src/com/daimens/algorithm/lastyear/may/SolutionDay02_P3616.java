package com.daimens.algorithm.may;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         3616.Milking Time
 * 
 *         Description
 * 
 *         Bessie is such a hard-working cow. In fact, she is so focused on
 *         maximizing her productivity that she decides to schedule her next N
 *         (1 ≤ N ≤ 1,000,000) hours (conveniently labeled 0..N-1) so that she
 *         produces as much milk as possible.
 * 
 *         Farmer John has a list of M (1 ≤ M ≤ 1,000) possibly overlapping
 *         intervals in which he is available for milking. Each interval i has a
 *         starting hour (0 ≤ starting_houri ≤ N), an ending hour
 *         (starting_houri < ending_houri ≤ N), and a corresponding efficiency
 *         (1 ≤ efficiencyi ≤ 1,000,000) which indicates how many gallons of
 *         milk that he can get out of Bessie in that interval. Farmer John
 *         starts and stops milking at the beginning of the starting hour and
 *         ending hour, respectively. When being milked, Bessie must be milked
 *         through an entire interval.
 * 
 *         Even Bessie has her limitations, though. After being milked during
 *         any interval, she must rest R (1 ≤ R ≤ N) hours before she can start
 *         milking again. Given Farmer Johns list of intervals, determine the
 *         maximum amount of milk that Bessie can produce in the N hours.
 * 
 *         Input
 * 
 *         Line 1: Three space-separated integers: N, M, and R Lines 2..M+1:
 *         Line i+1 describes FJ's ith milking interval withthree
 *         space-separated integers: starting_houri , ending_houri , and
 *         efficiencyi
 * 
 *         Output
 * 
 *         Line 1: The maximum number of gallons of milk that Bessie can product
 *         in the N hours
 * 
 *         Sample Input
 * 
 *         12 4 2 1 2 8 10 12 19 3 6 24 7 10 31 Sample Output
 * 
 *         43
 *
 */
public class SolutionDay02_P3616 {
	static int MAX_M = 1000;
	static int[] dp = new int[MAX_M];
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int M = in.nextInt();
		int R = in.nextInt();
		
		Interval[] intervals = new Interval[M];
		for (int i = 0; i < M; i++){
			int start = in.nextInt();
			int end = in.nextInt();
			int eff = in.nextInt();
			intervals[i] = new Interval(start, end, eff);
		}
		
//		int M = 4;
//		int R = 2;
//		Interval[] intervals = new Interval[M];
//		intervals[0] = new Interval(1, 2, 8);
//		intervals[1] = new Interval(10, 12, 19);
//		intervals[2] = new Interval(3, 6, 24);
//		intervals[3] = new Interval(7, 10, 31);
		
		Arrays.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start != o2.start ? o1.start-o2.start : o1.end - o2.end;
			}
		});
		
		for (int i = 0; i < M; i++){
			dp[i] = intervals[i].efficiency;
			for (int j = 0; j < i; j++){
				if (intervals[j].end + R <= intervals[i].start){
					dp[i] = Math.max(dp[i],intervals[i].efficiency+dp[j]);
				}
			}
		}
		
		int max = 0;
		for (int i = 0; i < M; i++){
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
		in.close();
	}
}

class Interval{
	int start;
	int end;
	int efficiency;
	
	Interval(int start,int end,int efficiency){
		this.start = start;
		this.end = end;
		this.efficiency = efficiency;
	}
}
