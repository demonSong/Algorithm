package com.daimens.algorithm.june;

import java.awt.image.RescaleOp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.text.ChangedCharSetException;

import org.omg.CORBA.LongSeqHelper;

/**
 * 
 * @author DemonSong
 * 
 *         327. Count of Range Sum
 * 
 *         Given an integer array nums, return the number of range sums that lie
 *         in [lower, upper] inclusive. Range sum S(i, j) is defined as the sum
 *         of the elements in nums between indices i and j (i ≤ j), inclusive.
 * 
 *         Note: A naive algorithm of O(n2) is trivial. You MUST do better than
 *         that.
 * 
 *         Example: Given nums = [-2, 5, -1], lower = -2, upper = 2, Return 3.
 *         The three ranges are : [0, 0], [2, 2], [0, 2] and their respective
 *         sums are: -2, -1, 2.
 * 
 * 
 *
 */
public class SolutionDay04_L0327 {

	// O(n^2)
//	public int countRangeSum(int[] nums, int lower, int upper) {
//		if (nums.length == 0)
//			return 0;
//
//		long[] sum = new long[nums.length];
//		sum[0] = nums[0];
//		for (int i = 1; i < nums.length; i++) {
//			sum[i] = sum[i - 1] + nums[i];
//		}
//
//		int cnt = 0;
//		for (int i = 0; i < nums.length; i++) {
//			for (int j = i; j < nums.length; j++) {
//				long s = sum[j] - sum[i] + nums[i];
//				if (s >= lower && s <= upper)
//					cnt++;
//			}
//		}
//
//		return cnt;
//	}
	
	class SegmentTreeNode {
        SegmentTreeNode left;
        SegmentTreeNode right;
        int count;
        long min;
        long max;
        public SegmentTreeNode(long min, long max) {
            this.min = min;
            this.max = max;
        }
    }
    private SegmentTreeNode buildSegmentTree(Long[] valArr, int low, int high) {
        if(low > high) return null;
        SegmentTreeNode stn = new SegmentTreeNode(valArr[low], valArr[high]);
        if(low == high) return stn;
        int mid = (low + high)/2;
        stn.left = buildSegmentTree(valArr, low, mid);
        stn.right = buildSegmentTree(valArr, mid+1, high);
        return stn;
    }
    private void updateSegmentTree(SegmentTreeNode stn, Long val) {
        if(stn == null) return;
        if(val >= stn.min && val <= stn.max) {
            stn.count++;
            updateSegmentTree(stn.left, val);
            updateSegmentTree(stn.right, val);
        }
    }
    private int getCount(SegmentTreeNode stn, long min, long max) {
        if(stn == null) return 0;
        if(min > stn.max || max < stn.min) return 0;
        if(min <= stn.min && max >= stn.max) return stn.count;
        return getCount(stn.left, min, max) + getCount(stn.right, min, max);
    }

//    public int countRangeSum(int[] nums, int lower, int upper) {
//
//        if(nums == null || nums.length == 0) return 0;
//        int ans = 0;
//        Set<Long> valSet = new HashSet<Long>();
//        long sum = 0;
//        for(int i = 0; i < nums.length; i++) {
//            sum += (long) nums[i];
//            valSet.add(sum);
//        }
//
//        Long[] valArr = valSet.toArray(new Long[0]);
//
//        Arrays.sort(valArr);
//        SegmentTreeNode root = buildSegmentTree(valArr, 0, valArr.length-1);
//
//        for(int i = nums.length-1; i >=0; i--) {
//            updateSegmentTree(root, sum);
//            sum -= (long) nums[i];
//            ans += getCount(root, (long)lower+sum, (long)upper+sum);
//        }
//        return ans;
//    }
    
//    public int countRangeSum(int[] nums, int lower, int upper) {
//    	if (nums == null || nums.length == 0) return  0;
//    	long[] sums = new long[nums.length+1];
//    	for (int i = 0; i < nums.length; ++i){
//    		sums[i+1] = sums[i] + nums[i];
//    	}
//    	
//    	int cnt = 0;
//    	for (int i = 0; i < sums.length; ++i){
//    		for (int j = i + 1; j < sums.length; ++j){
//    			long s = sums[j] - sums[i];
//    			if (s >= lower && s <= upper) cnt++;
//    		}
//    	}
//    	
//    	return cnt;
//    }
    
//    public int countRangeSum(int[] nums, int lower, int upper) {
//    	if (nums == null || nums.length == 0) return 0;
//    	int ans = 0;
//    	long sum = 0;
//    	Node root = new Node(0);
//    	for (int i : nums){
//    		sum += i;
//    		ans += getBound(sum - lower, root, true) - getBound(sum - upper, root, false);
//    		root = insert(root, sum);
//    	}
//    	return ans;
//    }
//    class Node{
//    	long val;
//    	
//    	int dup;
//    	int small;
//    	
//    	Node left;
//    	Node right;
//    	
//    	public Node(long val){
//    		this.val = val;
//    		dup = 1;
//    	}
//    }
//    
//    private int getBound(long val, Node root, boolean includeSelf){
//    	if (root == null) return 0;
//    	if (root.val == val) return root.small + (includeSelf ? root.dup : 0);
//    	else if (root.val > val){
//    		return getBound(val, root.left, includeSelf);
//    	}else{
//    		return root.small + root.dup + getBound(val, root.right, includeSelf);
//    	}
//    }
//    
//    private Node insert(Node root, long num){
//    	if (root == null){
//    		root = new Node(num);
//    	}else if (root.val == num){
//    		root.dup++;
//    	}else if (root.val < num){
//    		root.right = insert(root.right, num);
//    	}else{
//    		root.small++;
//    		root.left = insert(root.left, num);
//    	}
//    	return root;
//    }
    
//    public int countRangeSum(int[] nums, int lower, int upper) {
//    	int n = nums.length;
//    	long[] sums = new long[n + 1];
//    	for (int i = 0; i < n; ++i){
//    		sums[i+1] = sums[i] + nums[i];
//    	}
//    	return countWhileMergeSort(sums, 0, n, lower, upper);
//    }
//    
//    private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper){
//		if (end < start) return 0;
//		if (end == start) return ((sums[start] >= lower) && (sums[start] <= upper)) ? 1 : 0;
//
//    	int mid = start + (end - start) / 2;
//    	// divide and conquer
//    	int ans = countWhileMergeSort(sums, start, mid, lower, upper)
//    			+ countWhileMergeSort(sums, mid+1, end, lower, upper);
//    	
//    	// merge
//		for (int i = start, j = mid + 1, k = mid + 1; i <= mid; ++i) {
//			while (j <= end && sums[j] - sums[i] < lower) ++j;
//			while (k <= end && sums[k] - sums[i] <= upper) ++k;
//			ans += k - j;
//		}
//		
//		long[] cache = new long[end - start + 1];
//    	for (int i = start, j = mid + 1, k = 0; k < cache.length; ++k){
//    		while (i <= mid && sums[i] < sums[j]) cache[k++] = sums[i++];
//    		cache[k] = sums[j++];
//    	}
//    	
//    	for (int i = start; i <= end; i++) sums[i] = cache[i - start];
//    	return ans;
//    }
    
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; ++i)
            sums[i + 1] = sums[i] + nums[i];
        return countWhileMergeSort(sums, 0, n + 1, lower, upper);
    }

    private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
        if (end - start <= 1) return 0;
        int mid = (start + end) / 2;
        int count = countWhileMergeSort(sums, start, mid, lower, upper) 
                  + countWhileMergeSort(sums, mid, end, lower, upper);
        int j = mid, k = mid, t = mid;
        long[] cache = new long[end - start];
        for (int i = start, r = 0; i < mid; ++i, ++r) {
            while (k < end && sums[k] - sums[i] < lower) k++;
            while (j < end && sums[j] - sums[i] <= upper) j++;
            while (t < end && sums[t] < sums[i]) cache[r++] = sums[t++];
            cache[r] = sums[i];
            count += j - k;
        }
        System.arraycopy(cache, 0, sums, start, t - start);
        return count;
    }
    
//    private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper){
//    	if (end <= end) return 0;
//    	int mid = start + (end - start) / 2;
//    	// divide and conquer
//    	int count = countWhileMergeSort(sums, start, mid, lower, upper) 
//    			+ countWhileMergeSort(sums, mid + 1, end, lower, upper);
//    	
//    	// merge
//    	int j = mid, k = mid, t = mid;
//    	long[] cache = new long[end - start];
//        for (int i = start, r = 0; i < mid; ++i, ++r) {
//            while (k < end && sums[k] - sums[i] < lower) k++;
//            while (j < end && sums[j] - sums[i] <= upper) j++;
//            while (t < end && sums[t] < sums[i]) cache[r++] = sums[t++];
//            cache[r] = sums[i];
//            count += j - k;
//        }
//        System.arraycopy(cache, 0, sums, start, t - start);
//        return count;
//    }

    
}
