package com.daimens.algorithm.may;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 * 		   3614.Sunscreen
 * 
 *         Description
 * 
 *         To avoid unsightly burns while tanning, each of the C (1 ≤ C ≤ 2500)
 *         cows must cover her hide with sunscreen when they're at the beach.
 *         Cow i has a minimum and maximum SPF rating (1 ≤ minSPFi ≤ 1,000;
 *         minSPFi ≤ maxSPFi ≤ 1,000) that will work. If the SPF rating is too
 *         low, the cow suffers sunburn; if the SPF rating is too high, the cow
 *         doesn't tan at all........
 * 
 *         The cows have a picnic basket with L (1 ≤ L ≤ 2500) bottles of
 *         sunscreen lotion, each bottle i with an SPF rating SPFi (1 ≤ SPFi ≤
 *         1,000). Lotion bottle i can cover coveri cows with lotion. A cow may
 *         lotion from only one bottle.
 * 
 *         What is the maximum number of cows that can protect themselves while
 *         tanning given the available lotions?
 * 
 *         Input
 * 
 *         Line 1: Two space-separated integers: C and L Lines 2..C+1: Line i
 *         describes cow i's lotion requires with two integers: minSPFi and
 *         maxSPFi Lines C+2..C+L+1: Line i+C+1 describes a sunscreen lotion
 *         bottle i with space-separated integers: SPFi and coveri
 * 
 *         Output
 * 
 *         A single line with an integer that is the maximum number of cows that
 *         can be protected while tanning
 * 
 *         Sample Input
 * 
 *         3 2 3 10 2 5 1 5 6 2 4 1 Sample Output
 * 
 *         2
 *
 */
public class SolutionDay26_P3614 {
	
	public static void main(String[] args) {
		Scanner in= new Scanner(System.in);
		int C = in.nextInt();
		int L = in.nextInt();
		int[][] cows = new int[C][2];
		int[][] lotions = new int[L][2];
		for (int i = 0; i < C; i++){
			cows[i][0] = in.nextInt();
			cows[i][1] = in.nextInt();
		}
		for (int i = 0; i < L; i++){
			lotions[i][0] = in.nextInt();
			lotions[i][1] = in.nextInt();
		}
		System.out.println(solve(cows, lotions));
		in.close();
	}
	
	private static int solve(int[][] cows, int[][] lotions){
		Arrays.sort(cows, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] !=  o2[0] ? o1[0] - o2[0] : o1[1] - o2[1];
			}
		});
		Arrays.sort(lotions, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		
		int cur = 0;
		int res = 0;
		for (int i = 0; i < lotions.length; i++){
			while (cur < cows.length && cows[cur][0] <= lotions[i][0]){
				queue.offer(cows[cur]);
				cur ++;
			}
			while (!queue.isEmpty() && lotions[i][1] != 0){
				int max = queue.peek()[1]; queue.poll();
				if (max >= lotions[i][0]){
					res ++;
					lotions[i][1]--;
				}
			}
		}
		return res;
	}
}

