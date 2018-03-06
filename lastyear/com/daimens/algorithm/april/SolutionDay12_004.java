package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 4.Median of Two Sorted Arrays
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log(m+n))
 * 
 * Example 1:
 * nums1 = [1,3]
 * nums2 = [2]
 * 
 * The median is 2.0
 * 
 * Example 2:
 * nums1 = [1,2]
 * nums2 = [3,4]
 * 
 * The median is (2 + 3) / 2 = 2.5
 *
 */
public class SolutionDay12_004 {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		
		int[] array = merge(nums1,nums2);
		
		if(array.length == 0) return 0;
		
		int div = array.length / 2;
		
		if (array.length % 2 == 0)
			return (array[div] + array[div-1]) / 2.0;
		else
			return array[div];
    }
	
	
	
	private int[] merge(int[] nums1, int[] nums2){
		
		int[] arrays = new int[nums1.length + nums2.length];
		
		int i = 0, j = 0, k = 0;
		while(i < nums1.length && j < nums2.length){
			if (nums1[i] <= nums2[j]) arrays[k++] = nums1[i++];
			else arrays[k++] = nums2[j++];
		}
		
		while (i < nums1.length) arrays[k++] = nums1[i++];
		while (j < nums2.length) arrays[k++] = nums2[j++];
		
		
		return arrays;
	}
	
//	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//		
//		int a = nums1.length, b = nums2.length;
//		
//		if (a > b)
//			return findMedianSortedArrays(nums2, nums1);
//		
//		if(a == 0){
//			if (b % 2 == 0){
//				int index = findMedian(nums2,0,b-1,false);
//				
//				return (nums2[index] + nums2[index+1]) / 2.0;
//				
//			}else{
//				return findMedian(nums2, 0, b-1, true);
//			}
//		}
//		
//		
//		int a_lf = 0, b_lf = 0, a_rt = a-1, b_rt = b-1;
//		
//		while (a_lf < a_rt && b_lf < b_rt){
//			
//			int a_mid = findMedian(nums1,a_lf,a_rt,false);
//			int b_mid = findMedian(nums2,b_lf,b_rt,false);
//			
//			if (nums1[a_mid] < nums2[b_mid]){
//				a_lf = a_mid;
//				b_rt = b_mid;
//			}else{
//				a_rt = a_mid;
//				b_lf = b_mid;
//			}
//			
//		}
//		
//		//任何数组结束，输出答案
//		int[] ans = new int[3];
//		if (a_lf == a_rt){
//			ans[0] = nums1[a_lf];
//			ans[1] = nums2[b_lf];
//			ans[2] = nums2[b_lf+1];
//		}
//		
//		if (b_lf == b_rt){
//			ans[0] = nums2[b_lf];
//			ans[1] = nums1[a_lf];
//			ans[2] = nums1[a_lf+1];
//		}
//		
//		Arrays.sort(ans);
//		
//		int len = a + b;
//		int mod = len % 2;
//		
//		if (mod != 0){
//			return ans[1];
//		}else{
//			return (ans[0] + ans[1]) / 2.0;
//		}
//
//	}
//	
//	//return index
//	private int findMedian(int[] nums,int start, int end,boolean retMedian){
//		int div = start + (end - start) / 2;
//		
//		if(retMedian)
//			return nums[div];
//		else
//			return div;
//	}
	
//	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//		
//		int N1 = nums1.length;
//		int N2 = nums2.length;
//		
//		if (N1 < N2) return findMedianSortedArrays(nums2, nums1);
//		
//		if (N2 == 0) return ((double)nums1[(N1-1)/2] + (double)nums1[N1/2])/2;
//		
//		int lo = 0, hi = N2 * 2;
//		
//		while (lo <= hi){
//			
//			int mid2 = (lo + hi) /2;
//			int mid1 = N1 + N2 - mid2;
//			
//			double L1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1 - 1)  /2];
//			double L2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2-1)/2];
//			double R1 = (mid1 == N1 * 2) ? Integer.MAX_VALUE : nums1[(mid1)/2];
//	        double R2 = (mid2 == N2 * 2) ? Integer.MAX_VALUE : nums2[(mid2)/2];
//	        
//	        if (L1 > R2) lo = mid2 + 1;		// A1's lower half is too big; need to move C1 left (C2 right)
//	        else if (L2 > R1) hi = mid2 - 1;	// A2's lower half too big; need to move C2 left.
//	        else return (Math.max(L1,L2) + Math.min(R1, R2)) / 2;
//	        
//		}
//		
//		return -1;
//	}

	
//	public double findMedianSortedArrays(int[] nums1, int[] nums2){
//		
//		int N1 = nums1.length;
//		int N2 = nums2.length;
//		
//		if (N1 > N2) return findMedianSortedArrays(nums2, nums1);
//		
//		if (N1 == 0) return (nums2[(N2-1) /2] + nums2[N2/2]) / 2;
//		
//		
//	}
	
	
	//不管是奇数还是偶数都能找到对应的中值
	private double findMedian(int[] nums){
		int N = nums.length;
		return (nums[(N-1)/2] + nums[N/2]) / 2.0;
	}
	
	public static void main(String[] args) {
		SolutionDay12_004 day = new SolutionDay12_004();
		int[] nums = {1,2,3,4,5};
		System.out.println(day.findMedian(nums));
	}
}
