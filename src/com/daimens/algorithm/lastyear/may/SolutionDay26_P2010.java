package com.daimens.algorithm.may;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         2010.Moo University - Financial Aid
 * 
 *         Description
 * 
 *         Bessie noted that although humans have many universities they can
 *         attend, cows have none. To remedy this problem, she and her fellow
 *         cows formed a new university called The University of
 *         Wisconsin-Farmside,"Moo U" for short.
 * 
 *         Not wishing to admit dumber-than-average cows, the founders created
 *         an incredibly precise admission exam called the Cow Scholastic
 *         Aptitude Test (CSAT) that yields scores in the range
 *         1..2,000,000,000.
 * 
 *         Moo U is very expensive to attend; not all calves can afford it.In
 *         fact, most calves need some sort of financial aid (0 <= aid
 *         <=100,000). The government does not provide scholarships to calves,so
 *         all the money must come from the university's limited fund (whose
 *         total money is F, 0 <= F <= 2,000,000,000).
 * 
 *         Worse still, Moo U only has classrooms for an odd number N (1 <= N <=
 *         19,999) of the C (N <= C <= 100,000) calves who have applied.Bessie
 *         wants to admit exactly N calves in order to maximize educational
 *         opportunity. She still wants the median CSAT score of the admitted
 *         calves to be as high as possible.
 * 
 *         Recall that the median of a set of integers whose size is odd is the
 *         middle value when they are sorted. For example, the median of the set
 *         {3, 8, 9, 7, 5} is 7, as there are exactly two values above 7 and
 *         exactly two values below it.
 * 
 *         Given the score and required financial aid for each calf that
 *         applies, the total number of calves to accept, and the total amount
 *         of money Bessie has for financial aid, determine the maximum median
 *         score Bessie can obtain by carefully admitting an optimal set of
 *         calves.
 * 
 *         Input
 * 
 *         Line 1: Three space-separated integers N, C, and F
 * 
 *         Lines 2..C+1: Two space-separated integers per line. The first is the
 *         calf's CSAT score; the second integer is the required amount of
 *         financial aid the calf needs Output
 * 
 *         Line 1: A single integer, the maximum median score that Bessie can
 *         achieve. If there is insufficient money to admit N calves,output -1.
 *         Sample Input
 * 
 *         3 5 70 30 25 50 21 20 20 5 18 35 30 Sample Output
 * 
 *
 */
public class SolutionDay26_P2010 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int C = in.nextInt();
		int F = in.nextInt();
		int[][] cast = new int[C][2];
		for (int i = 0; i < C; i++){
			cast[i][0] = in.nextInt();
			cast[i][1] = in.nextInt();
		}
		System.out.println(solve(cast, N, F));
		in.close();
	}
	
	private static int solve(int[][] cast, int N, int F){
		Arrays.sort(cast, (a, b) -> (a[0] - b[0]));
		
		int[] lower = new int[cast.length];
		int[] upper = new int[cast.length];
		Arrays.fill(lower, 1<<30);
		Arrays.fill(upper, 1<<30);
		int half = N / 2;
		{
			Queue<Integer> queue = new PriorityQueue<>((a,b) -> (b-a));
			int total = 0;
			for (int i = 0; i < cast.length; i++){
				if (queue.size() == half){
					lower[i] = total;
					queue.offer(cast[i][1]);
					total += cast[i][1];
					total -= queue.poll();
				}else{
					total += cast[i][1];
					queue.offer(cast[i][1]);
				}
				
			}
		}
		
		{
			Queue<Integer> queue = new PriorityQueue<>((a,b) -> (b-a));
			int total = 0;
			for (int i = cast.length - 1; i >=0; i--){
				if (queue.size() == half){
					upper[i] = total;
					queue.offer(cast[i][1]);
					total += cast[i][1];
					total -= queue.poll();
				}else{
					total += cast[i][1];
					queue.offer(cast[i][1]);
				}
				
			}
		}
		
		int res = -1;
		for (int i = cast.length-1; i >= 0; i--){
			if (lower[i] + cast[i][1] + upper[i] <= F){
				res = cast[i][0];
				break;
			}
		}
		return res;
	}
}
