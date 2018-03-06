package com.daimens.algorithm.may;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 
 *         60.Permutation Sequence
 * 
 *         The set [1,2,3,…,n] contains a total of n! unique permutations.
 * 
 *         By listing and labeling all of the permutations in order, We get the
 *         following sequence (ie, for n = 3):
 * 
 *         "123" "132" "213" "231" "312" "321" Given n and k, return the kth
 *         permutation sequence.
 * 
 *         Note: Given n will be between 1 and 9 inclusive.
 *
 */
public class SolutionDay23_L0060 {
	
//	public String getPermutation(int n, int k) {
//		int[] nums = new int[n];
//		for (int i = 0; i < n; i++){
//			nums[i] = i + 1;
//		}
////		do{
////			k--;
////			if(k == 0) break;
////		}while(nextPermutation(nums) != null);
//		
//		for (int i = 0; i < k - 1; i++){
//			nextPermutation(nums);
//		}
//		
//		String ans = "";
//		for (int t : nums) ans += t;
//		return ans;
//    }
//	
//	
//	private int[] nextPermutation(int[] nums){
//		int n = nums.length;
//		int i = n - 2;
//		while (i >=0 && nums[i] >= nums[i+1]) i--;
//		if (i < 0) return null;
//		
//		int j = n - 1;
//		while (j > i && nums[j] <= nums[i]) j--;
//		swap(nums, i, j);
//		reverse(nums, i+1, n-1);
//		return nums;
//	}
//	
//	private void swap(int[] nums, int x, int y){
//		int tmp = nums[x];
//		nums[x] = nums[y];
//		nums[y] = tmp;
//	}
//
//	private void reverse(int[] nums, int s, int e){
//		while (s < e){
//			swap(nums, s, e);
//			s++;
//			e--;
//		}
//	}
	
//	public String getPermutation(int n, int k) {
//		List<Integer> set = new ArrayList<>();
//		for (int i = 0; i < n; i++){
//			set.add(i);
//		}
//		backTrack("", set, k);
//		return null;
//	}
//	int cnt = 0;
//	String ans = "";
//	private void backTrack(String tmp, List<Integer> set, int k){
//		if (set.size() == 0){
//			cnt ++;
//			System.out.println(tmp);
//			if(cnt == k){
//				ans = tmp;
//			}
//		}else{
//			for (int i = 0; i < set.size(); i++){
//				tmp += set.get(i)+1;
//				set.remove(i);
//				backTrack(tmp, set, k);
//				set.add(tmp.charAt(tmp.length()-1)-'0',tmp.charAt(tmp.length()-1)-'0');
//				tmp = tmp.substring(0,tmp.length()-1);
//			}
//		}
//	}
	
	public String getPermutation(int n, int k) {
		List<Integer> nums = new ArrayList<>();
		for (int i = 0; i < n; i++){
			nums.add(i+1);
		}
		int[] factories = new int[n+1];
		int sum = 1;
		factories[0] = 1;
		for (int i = 1; i <= n; i++){
			sum *= i;
			factories[i] = sum;
		}
		
		k--;
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++){
			int index = k / factories[n-i];
			sb.append(nums.get(index));
			nums.remove(index);
			k = k - index * factories[n-i];
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		SolutionDay23_L0060 day = new SolutionDay23_L0060();
		day.getPermutation(3, 3);
	}
}
