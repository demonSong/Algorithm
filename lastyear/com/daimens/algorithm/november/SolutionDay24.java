package com.daimens.algorithm.november;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author Demon Song
 * 350. Intersection of Two Arrays II
 * Given two arrays, write a function to compute their intersection
 * Example:
 * Given nums1 = [1,2,2,1],nums2=[2,2]
 * return [2,2]
 *
 */
public class SolutionDay24 {
	//思考一个问题，为什么排序后的数组能够提升效率？本质原因在于什么
	public int[] intersect(int[] nums1,int[] nums2){
		// 排序算法
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		ArrayList<Integer> result = new ArrayList<Integer>();
		// for 循环第二个分号中的内容本质上表示 true or false
		for (int i =0 ,j =0; i<nums1.length && j < nums2.length;){
			if(nums1[i] < nums2[j]){
				i++;
			}
			else if (nums1[i] > nums2[j]){
				j++;
			}else{
				result.add(nums1[i]);
				i++;
				j++;
			}
		}
		
		int[] res = new int[result.size()];
	    for (int i = 0; i < result.size(); i++){
	        res[i] = (int) result.get(i);
	    }
	    return res;
	}
}
