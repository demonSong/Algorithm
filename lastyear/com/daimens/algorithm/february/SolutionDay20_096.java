package com.daimens.algorithm.february;

/**
 * 
 * @author Demon Song
 * 96.Unique Binary Search Trees.
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 * For example,
 * Given n = 3,there are a total of 5 unique BST's.
 *  1         3     3      2      1
 *   \       /     /      / \      \
 *    3     2     1      1   3      2
 *   /     /       \                 \
 *  2     1         2                 3
 *
 */
public class SolutionDay20_096 {
	
	//dp 有一个最大的毛病在于 需要递归计算 前n-1次的dp值，否则符合推得第n个值，所以说它不是一步到位的解法.
	public int numTrees(int n){
		int[] dp = new int[n+1];
		dp[0] = dp[1] = 1;
		//外层循环累加每种状态，便于计算后一种状态
		for (int i = 2; i <= n; ++i){
			//内层循环计算G(n)的值
			for (int j = 1; j <=i; ++j){
				dp[i] += dp[j-1] * dp[i-1];
			}
		}
		return dp[n];
	}
	
//	The tricky part is that we could consider the number of unique BST out of sequence [1,2] as G(2), and the number of of unique BST out of sequence [4, 5, 6, 7] as G(4). Therefore, F(3,7) = G(2) * G(4).
//
//			i.e.
//
//			F(i, n) = G(i-1) * G(n-i)	1 <= i <= n 
	
	// 4 5 6 7 和 1 2 3 4的G(4)是等价，所以无非算法 F(i,n)之间中间差了多少颗数。

}
