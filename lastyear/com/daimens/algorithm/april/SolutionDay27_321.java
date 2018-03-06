package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 
 *         321.Create Maximum Number
 * 
 *         Given two arrays of length m and n with digits 0-9 representing two
 *         numbers. Create the maximum number of length k <= m + n from digits
 *         of the two. The relative order of the digits from the same array must
 *         be preserved. Return an array of the k digits. You should try to
 *         optimize your time and space complexity.
 * 
 *         Example 1: nums1 = [3, 4, 6, 5] nums2 = [9, 1, 2, 5, 8, 3] k = 5
 *         return [9, 8, 6, 5, 3]
 * 
 *         Example 2: nums1 = [6, 7] nums2 = [6, 0, 4] k = 5 return [6, 7, 6, 0,
 *         4]
 * 
 *         Example 3: nums1 = [3, 9] nums2 = [8, 9] k = 3 return [9, 8, 9]
 *
 */
public class SolutionDay27_321 {

//	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
//		List<Integer> ans =dfs(nums1, 0, nums2, 0, k);
//		return null;
//	}
//	
//	private List<Integer> dfs(int[] nums1, int pos1,int[] nums2, int pos2, int k){
//		if (nums1.length - pos1 + nums2.length - pos2 == k){
//			List<Integer> ans = new ArrayList<>();
//			int index1 = pos1;
//			int index2 = pos2;
//			for (int i = 0; i < k; i++) {
//				if (nums1[index1] > nums2[index2]){
//					ans.add(nums1[index1]);
//					index1++;
//					if (index1 == nums1.length) break;
//				}else{
//					ans.add(nums2[index2]);
//					index2++;
//					if (index2 == nums2.length) break;
//				}
//			}
//			
//			while (index1 < nums1.length) ans.add(nums1[index1++]);
//			while (index2 < nums2.length) ans.add(nums2[index2++]);
//			
//			return ans;
//		}
//		
//		
//		List<Integer> ans = new ArrayList<>();
//		
//		int n = nums1.length;
//		int m = nums2.length;
//		for(int i = 0; i <= k-1; i++){
//			
//			int up1 = nums1.length - i;
//			int up2 = nums2.length - (k-1-i);
//			
//			if (up1 < 0 | up2 < 0) continue;
//			
//			int max1 = nums1[0];
//			int id1 = 0;
//			for (int j = 0; j < up1; j++){
//				if (max1 < nums1[j]){
//					max1 = nums1[j];
//					id1 = j;
//				}
//			}
//			
//			int max2 = nums2[0];
//			int id2 = 0;
//			for (int j = 0; j < up2; j++){
//				if (max2 < nums2[j]){
//					max2 = nums2[j];
//					id2 = j;
//				}
//			}
//			
//			if (max1 > max2){
//				List<Integer> tmp = dfs(nums1, id1+1, nums2, pos2, k-1);
//				ans.add(max1);
//				for (int num : tmp){
//					ans.add(num);
//				}
//			}
//			else if (max2 > max1){
//				ans.add(max2);
//				List<Integer> tmp = dfs(nums1, pos1, nums2, id2+1, k-1);
//				for (int num : tmp){
//					ans.add(num);
//				}
//			}
//			else{
//				
//			}
//			
//		}
//		
//		
//		return ans;
//	}
	
	
	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int get_from_nums1 = Math.min(nums1.length, k);
        int[] ans = new int[k];
        for (int i = Math.max(k - nums2.length, 0); i <= get_from_nums1; i++) {
            int[] res1 = new int[i];
            int[] res2 = new int[k - i];
            int[] res = new int[k];
            res1 = solve(nums1, i);
            res2 = solve(nums2, k - i);
            int pos1 = 0, pos2 = 0, tpos = 0;
            
            while (res1.length > 0 && res2.length > 0 && pos1 < res1.length && pos2 < res2.length) {
                if (compare(res1, pos1, res2, pos2))
                    res[tpos++] = res1[pos1++];
                else
                    res[tpos++] = res2[pos2++];
            }
            while (pos1 < res1.length)
                res[tpos++] = res1[pos1++];
            while (pos2 < res2.length)
                res[tpos++] = res2[pos2++];

            if (!compare(ans, 0, res, 0))
                ans = res;
        }

        return ans;
    }
	
	
	//相等则直到比出结果 nums1 > nums2 return true
	private boolean compare (int[] nums1, int start1, int[] nums2, int start2){
		for (; start1 < nums1.length && start2 < nums2.length; start1++, start2++){
			if (nums1[start1] > nums2[start2]) return true;
			if (nums1[start1] < nums2[start2]) return false;
		}
		return start1 != nums1.length;
	}
	
	private int[] solve(int[] nums, int k){
		int[] res = new int[k];
		int len = 0;
		for (int i = 0; i < nums.length; i++){
			while (len > 0 && nums.length - i > k - len && res[len-1] < nums[i]){
				len--;
			}
			if (len < k)
				res[len++] = nums[i];
		}
		return res;
	}
	
	private int[] dfs(int[] nums, int start, int k){
		if (nums.length - start == k){
			int[] ans = new int[k];
			for (int i = start; i < nums.length; i++){
				ans[i-start] = nums[i];
			}
			return ans;
		}
		if (k == 1){
			int max = 0;
			for (int i = start; i < nums.length; i++){
				max = Math.max(max, nums[i]);
			}
			return new int[]{max};
		}
		
		int[] res = new int[k];
		int maxId = 0;
		res[0] = nums[start];
		for (int i = start; i < nums.length-k+1; i++){
			if (res[0] < nums[i]){
				res[0] = nums[i];
				maxId = i;
			}
		}
		
		int[] tmp = dfs(nums,maxId+1,k-1);
		int index = 1;
		for (int tt : tmp){
			res[index++] = tt;
		}
		return res;
	}
	
	public static void main(String[] args) {
		SolutionDay27_321 day = new SolutionDay27_321();
		int[] nums1 = {3,4,6,5};
		int[] nums2 = {9,1,2,5,8,3};
		
		day.maxNumber(nums1, nums2, 6);
		day.solve(nums2, 0);
		
//		for (int i = 1; i <= nums1.length; i++){
//			int[] ans1 = day.dfs(nums2, 0, i);
//			int[] ans2 = day.solve(nums2, i);
//			boolean isSame = true;
//			for (int j = 0; j < ans1.length; j++){
//				if (ans1[j] != ans2[j]) isSame = false;
//			}
//			System.out.println(i+" is Same " + isSame);
//		}
	}

}
