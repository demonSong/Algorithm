package com.daimens.algorithm.may;

import java.util.Arrays;

/**
 * 
 * @author DemonSong
 * 
 *         135.Candy
 * 
 *         There are N children standing in a line. Each child is assigned a
 *         rating value.
 * 
 *         You are giving candies to these children subjected to the following
 *         requirements:
 * 
 *         Each child must have at least one candy. Children with a higher
 *         rating get more candies than their neighbors. What is the minimum
 *         candies you must give?
 *
 */
public class SolutionDay22_L0135 {
	
	public int candy(int[] ratings) {
		int len = ratings.length;
		int[] candies = new int[len];
		Arrays.fill(candies, 1);
		for (int i = 0; i < len; i++){
			if (i == 0) continue;
			if (ratings[i] > ratings[i-1]){
				candies[i] = candies[i-1] + 1;
			}
		}
		for (int i = len - 1; i >= 0; i--){
			if (i == len -1) continue;
			if (ratings[i] > ratings[i+1]){
				candies[i] = Math.max(candies[i+1] + 1,candies[i]);
			}
		}
		int sum = 0;
		for (int candy : candies){
			sum += candy;
		}
		return sum;
    }
}
