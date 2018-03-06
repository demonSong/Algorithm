package com.daimens.algorithm.february;

/**
 * 
 * @author Demon Song
 * 238.Product of Array Except self
 * Given an array of n integers where n > 1,nums,return an array output such that output[i] is equal
 * to the product of all the elements of nums except nums[i].
 * Solve it without division and in O(n).
 * For example,given [1,2,3,4],return [24,12,8,6].
 * Follow up:
 * Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
 *
 */
public class SolutionDay01_238 {

	//遇到0元素怎么办
//	public int[] productExceptSelf(int[] nums){
//		int times = 1;
//		int[] result = new int[nums.length];
//		
//		for(int i =0; i < result.length;i++){
//			result[i] =1;
//		}
//		
//		for (int i = 0; i < nums.length; i++){
//			times *= nums[i];
//			
//		}
//		
//		for(int i = 0;i < nums.length; i++){
//			if(nums[i] == 0){
//				for (int j =0 ; j < nums.length; j++){
//					if(i == j) continue;
//					result[i] *= nums[j];
//				}
//			}
//			else{
//				result[i] = times / nums[i];
//			}
//		}
//		return result;
//	}
	
//	public int[] productExceptSelf(int[] nums){
//		int[] result = new int[nums.length];
//		
//		for(int i = 0; i < nums.length; i++){
//			if(nums[i] != 0){
//				result[i] = 0;
//			}else{
//				nums[i] = 1;
//				result[i] = 1;
//			}
//		}
//		
//		int count = 0;
//		for(int i = 0; i < result.length; i++){
//			if(result[i] == 1){
//				count ++;
//			}
//		}
//		
//		if(count == 0){
//			int times = 1;
//			for(int i = 0; i < nums.length; i++){
//				times *= nums[i];
//			}
//			for(int i =0; i < nums.length; i++){
//				result[i] = times/ nums[i]; 
//			}
//			
//			return result;
//		}
//		
//		if(count > 1){
//			return new int[nums.length];
//		}
//	
//		for(int i = 0; i < result.length; i++){
//			if(result[i] == 1){
//				result[i] = allProdcuctExceptZero(nums);
//			}
//		}
//		
//		return result;
//	}
	
//	public int[] productExceptSelf(int[] nums){
//		int count = 0;
//		int times = 1;
//		int[] result = new int[nums.length];
//		for(int i = 0; i < nums.length; i++){
//			
//			if(nums[i] == 0){
//				count ++;
//				nums[i] = 1;
//				result[i] =1;
//			}
//			times *= nums[i];
//			
//			//超过了 就直接返回即可
//			if(count > 1){
//				return result;
//			}
//		}
//		
//		if(count == 1){
//			for (int i = 0; i < result.length; i++){
//				if(result[i] == 1){
//					result[i] = times;
//					return result;
//				}
//			}
//		}
//		
//		for(int i = 0;i < nums.length;i++){
//			result[i] = times/nums[i];
//		}
//		return result;
//	}
	
	//不允许使用除法
	public int[] productExceptSelf(int[] nums){
		int n = nums.length;
		int[] res = new int[nums.length];
		res[0] = 1;
		for(int i = 1; i < n; i++){
			res[i] = res[i-1] * nums[i-1];
		}
		int right =1;
		for(int i = n-1;i >= 0; i--){
			res[i] *= right;
			right *= nums[i];
		}
		return res;
	}
	
	//不含有 0 元素的 producet
	public int ProdcuctExceptZero(int[] nums){
		int times = 1;
		for(int i = 0; i < nums.length; i++){
			times *= nums[i];
		}
		
		return times;
	}
	
	public static void main(String[] args) {
		SolutionDay01_238 day01_238 = new SolutionDay01_238();
		int[] nums = {9,0,-2};
		day01_238.productExceptSelf(nums);
	}
}
