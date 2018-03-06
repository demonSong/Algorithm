package com.daimens.algorithm.january;

import java.util.Arrays;

/**
 * 
 * @author Demon Song
 * 475.Heaters
 * Winter is coming! Your first job during the contest is to design a standard heater
 * with fixed warm radius to warm all the houses.
 * Now,you are given  positions of houses and heaters on a horizontal line,find out
 * minimum radius of heaters so that all houses could be covered by those heaters.
 * So,your input will be the positions of houses and heaters seperately,and your expected
 * output will be the minimum radius standard of heaters.
 * 
 * Note:
 * 1.Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
 * 2.Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
 * 3.As long as a house is in the heaters' warm radius range, it can be warmed.
 * 4.All the heaters follow your radius standard and the warm radius will the same.
 * 
 * Example 1:
 * Input:[1,2,3],[2]
 * Output: 1
 * Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, 
 * then all the houses can be warmed.
 * 
 * Example 2:
 * Input: [1,2,3,4],[1,4]
 * Output:1
 * Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, 
 * then all the houses can be warmed.
 *
 */
public class SolutionDay20_475 {
	public int findRadius(int[] houses, int[] heaters) {
		Arrays.sort(houses);
		Arrays.sort(heaters);
		int result = 0;
		int temp = 0;
		int j = 0;
		for(int i = 0; i < houses.length; i++){
			//找到heaters 大于等于houses 的 j
			while(j < heaters.length && heaters[j] < houses[i]){
				j ++;
			}
			if (j == heaters.length){
				temp = houses[i] - heaters[j-1];
			}
			else if(j == 0){
				temp = heaters[j] - houses[i];
			}
			else if (heaters[j] > houses[i]){
				temp = Math.min(heaters[j]- houses[i], houses[i]-heaters[j-1]);
			}
			if(temp > result){
				result = temp;
			}
			
		}
		return result;
    }
}
