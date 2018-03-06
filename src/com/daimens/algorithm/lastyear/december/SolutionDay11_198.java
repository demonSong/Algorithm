package com.daimens.algorithm.december;

/**
 * 
 * @author Demon Song
 * 198. House Robber
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them
 * is that adjacent houses have security system connected
 * and it will automatically contact the police if two adjacent houses
 * were broken into on the same night.
 * Given a list of non-negative integers representing the amount of money 
 * of each house, determine the maximum amount of money you can rob 
 * tonight without alerting the police.
 *
 */

//待总结
public class SolutionDay11_198 {
	public int rob(int[] num) {
        //   31 23 9 13 49 1 0
        //   0  0 0 0
        if(num==null || num.length==0) return 0;

        int n = num.length;

        int [] b = new int[n]; //include last element;
        int [] d = new int[n]; //exclude last element;

        b[0] = num[0];
        d[0] = 0;

        for(int i=1; i<n; i++) {
            b[i] = d[i-1] + num[i];
            d[i] = Math.max(b[i-1], d[i-1]);
        }

        return Math.max(d[n-1], b[n-1]);
    }
}
