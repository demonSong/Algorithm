package com.daimens.algorithm.may;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         2376.Cleaning Shifts
 * 
 *         Description
 * 
 *         Farmer John is assigning some of his N (1 <= N <= 25,000) cows to do
 *         some cleaning chores around the barn. He always wants to have one cow
 *         working on cleaning things up and has divided the day into T shifts
 *         (1 <= T <= 1,000,000), the first being shift 1 and the last being
 *         shift T.
 * 
 *         Each cow is only available at some interval of times during the day
 *         for work on cleaning. Any cow that is selected for cleaning duty will
 *         work for the entirety of her interval.
 * 
 *         Your job is to help Farmer John assign some cows to shifts so that
 *         (i) every shift has at least one cow assigned to it, and (ii) as few
 *         cows as possible are involved in cleaning. If it is not possible to
 *         assign a cow to each shift, print -1. Input
 * 
 *         Line 1: Two space-separated integers: N and T
 * 
 *         Lines 2..N+1: Each line contains the start and end times of the
 *         interval during which a cow can work. A cow starts work at the start
 *         time and finishes after the end time. Output
 * 
 *         Line 1: The minimum number of cows Farmer John needs to hire or -1 if
 *         it is not possible to assign a cow to each shift. Sample Input
 * 
 *         3 10 1 7 3 6 6 10 Sample Output
 * 
 *         2
 * 
 *
 */
public class SolutionDay23_P2376 {
	
	private static class Pair{
		int s; 
		int e;
		Pair(int s, int e){
			this.s = s;
			this.e = e;
		}
		
		@Override
		public String toString() {
			return "["+s+","+e+"]";
		};
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int T = in.nextInt();
		Pair[] pairs = new Pair[N];
		for (int i = 0; i < N; i++){
			int s = in.nextInt();
			int e = in.nextInt();
			pairs[i] = new Pair(s,e);
		}
		System.out.println(solve(pairs,T));
		in.close();
	}
	
	private static int solve(Pair[] pairs, int T){
		Arrays.sort(pairs, new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				return o1.s != o2.s ? o1.s - o2.s : o1.e - o2.e ;
			}
		});
		
		int pos = 0;
		int end = 0;
		int step = 0;
		int begin = 0;
		while (end < T){
			begin = end + 1;
			for (int i = pos; i < pairs.length; i++){
				if (pairs[i].s <= begin){
					end = Math.max(end, pairs[i].e);
				}else{
					pos = i;
					break;
				}
			}
			
			if (begin > end){
				return -1;
			}
			else{
				step ++;
			}
		}
		return step;
	}

}
