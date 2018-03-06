package com.daimens.algorithm.january;

/**
 * 
 * @author Demon Song
 * 88.Merge Sorted Array
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * Note:
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) 
 * to hold additional elements from nums2.
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 *
 */
public class SolutionDay12_088 {
	
	//sorted array
	public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m+n;
        while(k-->0){
        	nums1[k] = (n==0 || (m>0 && nums1[m-1] > nums2[n-1])) ? nums1[--m] : nums2[--n];
        }
    }	
}
