package com.daimens.algorithm.may;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         3262.Protecting the Flowers
 * 
 *         Description
 * 
 *         Farmer John went to cut some wood and left N (2 ≤ N ≤ 100,000) cows
 *         eating the grass, as usual. When he returned, he found to his horror
 *         that the cluster of cows was in his garden eating his beautiful
 *         flowers. Wanting to minimize the subsequent damage, FJ decided to
 *         take immediate action and transport each cow back to its own barn.
 * 
 *         Each cow i is at a location that is Ti minutes (1 ≤ Ti ≤ 2,000,000)
 *         away from its own barn. Furthermore, while waiting for transport, she
 *         destroys Di (1 ≤ Di ≤ 100) flowers per minute. No matter how hard he
 *         tries, FJ can only transport one cow at a time back to her barn.
 *         Moving cow i to its barn requires 2 × Ti minutes (Ti to get there and
 *         Ti to return). FJ starts at the flower patch, transports the cow to
 *         its barn, and then walks back to the flowers, taking no extra time to
 *         get to the next cow that needs transport.
 * 
 *         Write a program to determine the order in which FJ should pick up the
 *         cows so that the total number of flowers destroyed is minimized.
 * 
 *         Input
 * 
 *         Line 1: A single integer N Lines 2..N+1: Each line contains two
 *         space-separated integers, Ti and Di, that describe a single cow's
 *         characteristics Output
 * 
 *         Line 1: A single integer that is the minimum number of destroyed
 *         flowers Sample Input
 * 
 *         6 3 1 2 5 2 3 3 2 4 1 1 6 Sample Output
 * 
 *         86 Hint
 * 
 *         FJ returns the cows in the following order: 6, 2, 3, 4, 1, 5. While
 *         he is transporting cow 6 to the barn, the others destroy 24 flowers;
 *         next he will take cow 2, losing 28 more of his beautiful flora. For
 *         the cows 3, 4, 1 he loses 16, 12, and 6 flowers respectively. When he
 *         picks cow 5 there are no more cows damaging the flowers, so the loss
 *         for that cow is zero. The total flowers lost this way is 24 + 28 + 16
 *         + 12 + 6 = 86.
 *
 */
public class SolutionDay24_P3262 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[] T = new int[N];
		int[] D = new int[N];
		for (int i = 0; i < N; i++){
			T[i] = in.nextInt();
			D[i] = in.nextInt();
		}
		System.out.println(solve(T, D));
		in.close();
	}

	private static class Pair{
		int t;
		int d;
		Pair(int t, int d){
			this.t = t;
			this.d = d;
		}
	}
	
	private static long solve(int[] T, int[] D){
		Pair[] p = new Pair[T.length];
		int damage = 0;
		for (int i = 0; i < T.length; i++){
			damage += D[i];
			p[i] = new Pair(T[i],D[i]);
		}
		Arrays.sort(p, new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				return o2.d * o1.t - o1.d * o2.t;
			}
		});
		
		long total = 0;
		for (int i = 0; i < T.length; i++){
			damage -= p[i].d;
			total += damage * 2 * p[i].t;
		}
		return total;
	}
}
