package com.daimens.algorithm.november;

import java.util.HashMap;

import javax.swing.table.TableModel;


/**
 * 
 * @author Demon Song
 * 169. Majority Element
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 *
 */
public class SolutionDay21 {
	public int majorityElement(int[] nums) {
		//hash map
		HashMap<Integer, Integer> numsMap = new HashMap<Integer,Integer>();
		for (int i = 0 ; i < nums.length; i++){
			//如果这是一个新元素
			if(!numsMap.containsKey(nums[i])){
				int value =0;
				numsMap.put(nums[i], ++value);
			}
			else{ //否则是一老元素
				int value = numsMap.get(nums[i]);
				numsMap.put(nums[i], ++value);
			}
		}
		for (Integer temp : numsMap.keySet()){
			if (numsMap.get(temp).compareTo(nums.length/2) >0){
				return temp;
			}
		}
		
		throw new IllegalArgumentException("no result!");
    }
	
	public static void main(String[] args) {
		int[] nums = {3,2,3};
		SolutionDay21 day21 = new SolutionDay21();
		day21.majorityElement(nums);
	}
}
