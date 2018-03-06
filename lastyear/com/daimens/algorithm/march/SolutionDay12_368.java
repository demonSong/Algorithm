package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 368.Largest Divisible Subset
 * Given a set of distinct positive integers,find the largest subset such that every pair(Si,Sj) of elements
 * in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
 * If there are multiple solutions, return any subset is fine.
 * Example 1:
 * nums :[1,2,3]
 * Result : [1,2] (of course,[1,3] will also be ok)
 * 
 * Example 2:
 * nums:[1,2,4,8]
 * Result: [1,2,4,8]
 *
 */
public class SolutionDay12_368 {
	public List<Integer> largestDivisibleSubset(int[] nums) {
		int n = nums.length;
		int[] count = new int[n];
		int[] pre = new int[n];
		
		Arrays.sort(nums);
		int max = 0, index = -1;
		for (int i = 0; i < n; i++){ //遍历 nums 数组
			count[i] = 1; //遍历过程中 会被 改变
			pre[i] = -1; //同上
			
			//永远比较复杂的内循环
			for (int j = i - 1; j >= 0; j--) { //for 循环的作用 规定第一个元素是否作用 显然 i=0 的情况 无法进入循环  需 i >= 1  j 来遍历 所有从0 到 i-1 的元素，是需要 对应到nums上去的
				if (nums[i] % nums[j] == 0) { //对应的所有pair 符合 上述条件后，进行下标更新 排序后 只需要判断一次即可！！！
					if (1 + count[j] > count[i]) { // count 的作用是什么？ pre 的作用
						count[i] = count[j] + 1; //更新  外循环的i值
						pre[i] = j;
					}
				}
			}
			
			if(count[i] > max){
				max = count[i];
				index = i;
			}
		}
		
		List<Integer> res = new ArrayList<>();
		while(index != -1){
			res.add(nums[index]);
			index = pre[index];  //有点 union find 的味道
		}
		
		return res;
    }
}
