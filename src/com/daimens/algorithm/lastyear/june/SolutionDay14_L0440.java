package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 * 440. K-th Smallest in Lexicographical Order
 * 
 *         Given integers n and k, find the lexicographically k-th smallest
 *         integer in the range from 1 to n.
 * 
 *         Note: 1 ≤ k ≤ n ≤ 109.
 * 
 *         Example:
 * 
 *         Input: n: 13 k: 2
 * 
 *         Output: 10
 * 
 *         Explanation: The lexicographical order is [1, 10, 11, 12, 13, 2, 3,
 *         4, 5, 6, 7, 8, 9], so the second smallest number is 10.
 *
 */
public class SolutionDay14_L0440 {
	public int findKthNumber(int n, int k) {
		int cur = 1;
		k = k - 1;
		while (k > 0){
			int steps = calStep(n, cur, cur + 1);
			if (steps <= k){
				cur = cur + 1;
				k -= steps;
			}
			else{
				cur *= 10;
				k -= 1;
			}
		}
		return cur;
    }
	
	private int calStep(int n, long n1, long n2){
		int step = 0;
		while (n1 <= n){
			step += Math.min(n + 1, n2) - n1;
			n1 *= 10;
			n2 *= 10;
		}
		return step;
	}
}
