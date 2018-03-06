package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         233.Number of Digit One
 * 
 *         Given an integer n, count the total number of digit 1 appearing in
 *         all non-negative integers less than or equal to n.
 * 
 *         For example: Given n = 13, Return 6, because digit 1 occurred in the
 *         following numbers: 1, 10, 11, 12, 13.
 *
 */
public class SolutionDay08_L0233 {
	
	static long[] cnt = {0, 1, 20, 300, 4000, 50000, 600000, 7000000, 80000000, 900000000, 1000000000};
	public int countDigitOne(int n) {
		if (n <= 0) return 0;
		if (n < 10) return 1;
		String num = Integer.toString(n);
		char[] bit = num.toCharArray();
		int m = bit.length;
		int ans = 0;
		if (bit[0] == '1')
			ans += cnt[m-1] + Integer.parseInt(num.substring(1)) + 1;
		else{
			ans += cnt[m-1] * (bit[0] - '0') + Math.pow(10, m -1);
		}
		ans += countDigitOne(Integer.parseInt(num.substring(1)));
		return ans;
    }
	
//	public int countDigitOne(int n) {
//		int count = 0;
//		for (long k = 1; k <= n; k *= 10) {
//			long r = n / k, m = n % k;
//			// sum up the count of ones on every place k
//			count += (r + 8) / 10 * k + (r % 10 == 1 ? m + 1 : 0);
//		}
//		return count;
//	}
	
	public static void main(String[] args) {
		SolutionDay08_L0233 day = new SolutionDay08_L0233();
		System.out.println(day.countDigitOne(569882136));
	}
}
