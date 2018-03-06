package com.daimens.algorithm.june;

import java.util.Arrays;

/**
 * 
 * @author DemonSong
 * 
 *         324.Wiggle Sort II
 * 
 *         Given an unsorted array nums, reorder it such that nums[0] < nums[1]
 *         > nums[2] < nums[3]....
 * 
 *         Example: (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is
 *         [1, 4, 1, 5, 1, 6]. (2) Given nums = [1, 3, 2, 2, 3, 1], one possible
 *         answer is [2, 3, 1, 3, 1, 2].
 * 
 *         Note: You may assume all input has valid answer.
 * 
 *         Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra
 *         space?
 *
 */
public class SolutionDay14_L0324 {
	
	// find the nums.length / 2 max number
//	public void wiggleSort(int[] nums) {
//		int n = nums.length;
//		Arrays.sort(nums);
//		int[] aux = new int[n];
//		for (int i = ((n - 1) % 2 == 0 ? n - 1 : n - 2), k = 0; i >= 0; i -= 2){
//			aux[i] = nums[k++];
//		}
//		for (int i = 1, k = n - 1; i < n; i += 2){
//			aux[i] = nums[k--];
//		}
//		for (int i = 0; i < n; i++){
//			nums[i] = aux[i];
//		}
//	}
	
	// map index 1 3 5 7 9
	//           2 4 6 8 10
	//           1 3 5 7 9 11
	//           2 4 6 8 10 
	// 少了奇偶性的判断
	
//	private int findKthLargest(int[] nums, int k){
//		int lo = 0;
//		int hi = nums.length - 1;
//		while (lo < hi){
//			int j = partition(nums, lo, hi);
//			if (j < k){
//				lo = j + 2;
//			}
//			else if (j > k){
//				hi = j - 2;
//			}
//			else{
//				break;
//			}
//		}
//		return nums[k-1];
//	}
//	
//	private int partition(int[] nums, int lo, int hi){
//		int lf = lo + 1;
//		int rt = hi;
//		int num = nums[lo];
//		while (lf < rt){
//			if (nums[rt] >= num){
//				exch(nums,lf, rt);
//				lf++;
//			}
//			if (nums[lf] < num){
//				exch(nums,lf, rt);
//				rt--;
//			}
//		}
//		if (num <= nums[lf]){
//			exch(nums, lo, lf);
//			return lf + 1;
//		}
//		else{ 
//			exch(nums, lo, lf - 1);
//			return lf;
//		}
//	}
//	
//	private void exch(int[] nums, int i, int j){
//		int tmp = nums[i];
//		nums[i] = nums[j];
//		nums[j] = tmp;
//	}
	
//	public void wiggleSort(int[] nums) {
//		int n = nums.length;
//		Arrays.sort(nums);
//		int[] aux = new int[n];
//		for (int i = ((n - 1) % 2 == 0 ? n - 1 : n - 2), k = 0; i >= 0; i -= 2){
//			aux[i] = nums[k++];
//		}
//		for (int i = 1, k = n - 1; i < n; i += 2){
//			aux[i] = nums[k--];
//		}
//		for (int i = 0; i < n; i++){
//			nums[i] = aux[i];
//		}
//	}
	
	public void wiggleSort(int[] nums) {
		int n = nums.length;
		int median = findKthLargest(nums, (nums.length + 1) / 2);
		int i = 0, lf = 0, rt = n - 1;
		while (i <= rt){
			if (nums[map(i,n)] > median){
				exch(nums, map(lf++, n), map(i++,n));
			}
			else if (nums[map(i, n)] < median){
				exch(nums, map(rt--, n), map(i, n));
			}
			else{
				i++;
			}
		}
	}
	
	private int map(int i, int n){
		return (1 + 2 * i) % (n | 1);
	}
	
	public int findKthLargest(int[] nums, int k) {
        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            if(j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

	private int partition(int[] a, int lo, int hi) {

        int i = lo;
        int j = hi + 1;
        while(true) {
            while(i < hi && a[++i] < a[lo]);
            while(j > lo && a[--j] > a[lo]);
            if(i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private void exch(int[] a, int i, int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
	
	public static void main(String[] args) {
		SolutionDay14_L0324 day = new SolutionDay14_L0324();
		int[] nums = {5,1,2,3,4,6,7,8,9};
		System.out.println(day.findKthLargest(nums, 2));
		System.out.println();
	}
}
