package com.daimens.algorithm.december;

/**
 * 
 * @author Demon Song
 * 70. Climbing Stairs
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps.
 * In how many distinct ways can you climb to the top?
 *
 */
public class Solution70_5 {

	public int climbStairs(int n){
		int stepOne =0;
		int stepTwo =1;
		int sum =0;
		
		for(int i =0; i<n ;i++){
			sum = stepOne+stepTwo;
			stepOne = stepTwo;
			stepTwo = sum;
		}
		return sum;
	}
}
