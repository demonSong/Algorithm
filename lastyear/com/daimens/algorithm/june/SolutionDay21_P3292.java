package com.daimens.algorithm.june;

import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         3292.Semi-prime H-numbers
 * 
 *         Description
 * 
 *         This problem is based on an exercise of David Hilbert, who
 *         pedagogically suggested that one study the theory of 4n+1 numbers.
 *         Here, we do only a bit of that.
 * 
 *         An H-number is a positive number which is one more than a multiple of
 *         four: 1, 5, 9, 13, 17, 21,... are the H-numbers. For this problem we
 *         pretend that these are the only numbers. The H-numbers are closed
 *         under multiplication.
 * 
 *         As with regular integers, we partition the H-numbers into units,
 *         H-primes, and H-composites. 1 is the only unit. An H-number h is
 *         H-prime if it is not the unit, and is the product of two H-numbers in
 *         only one way: 1 × h. The rest of the numbers are H-composite.
 * 
 *         For examples, the first few H-composites are: 5 × 5 = 25, 5 × 9 = 45,
 *         5 × 13 = 65, 9 × 9 = 81, 5 × 17 = 85.
 * 
 *         Your task is to count the number of H-semi-primes. An H-semi-prime is
 *         an H-number which is the product of exactly two H-primes. The two
 *         H-primes may be equal or different. In the example above, all five
 *         numbers are H-semi-primes. 125 = 5 × 5 × 5 is not an H-semi-prime,
 *         because it's the product of three H-primes.
 * 
 *         Input
 * 
 *         Each line of input contains an H-number ≤ 1,000,001. The last line of
 *         input contains 0 and this line should not be processed.
 * 
 *         Output
 * 
 *         For each inputted H-number h, print a line stating h and the number
 *         of H-semi-primes between 1 and h inclusive, separated by one space in
 *         the format shown in the sample.
 * 
 *         Sample Input
 * 
 *         21 85 789 0 Sample Output
 * 
 *         21 0 85 5 789 62
 *
 */
public class SolutionDay21_P3292 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		seive();
		while (true){
			int h = in.nextInt();
			if (h == 0) break;
			System.out.println(h + " " + accumalte[h]);
		}
		in.close();
	}
	
	static int MAX = 1000001 + 16;
	static boolean[]  hPrime;
	static long[] prime;
	static boolean[] hSemiPrime;
	static int[] accumalte;
	
	public static void seive(){
		hPrime = new boolean[MAX];
		prime = new long[MAX];
		
		for (int i = 0; i < MAX; ++i){
			if (i % 4 != 1) hPrime[i] = false;
			else hPrime[i] = true;
		}
		
		int k = 0;
		for (int i = 5; i < MAX; ++i){
			if (hPrime[i]){
				prime[k++] = i;
				for (int j = 2 * i; j < MAX; j += i){
					hPrime[j] = false; 
				}
			}
		}
		
		hSemiPrime = new boolean[MAX+1];

		for (int i = 0; i < k; ++i){
			for (int j = i; j < k; ++j){
				if (prime[i] * prime[j] > MAX) break;
				hSemiPrime[(int)(prime[i] * prime[j])] = true;
			}
		}
		
		accumalte = new int[MAX+1];
		for (int i = 1; i < accumalte.length; ++i){
			if (hSemiPrime[i]) accumalte[i] = accumalte[i-1] + 1;
			else accumalte[i] = accumalte[i-1];
		}
	}
	
	public static int solve(int h){
		int cnt = 0;
		for (int i = 0; i <= h && prime[i] * prime[i] <= h; ++i){
			int rt = h;
			while (rt >= i && prime[i] * prime[rt] > h) rt--;
			cnt += (rt - i + 1);
		}
		return cnt;
	}
	
}
