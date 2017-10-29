package com.daimens.algorithm.october;

public class SolutionDay22_L0502 {
	
//    public int numSubarrayProductLessThanK(int[] nums, int k) {
//    	int cnt = 0;
//    	int n = nums.length;
//    	
//    	for (int i = 0; i < n; ++i) {
//    		int p = nums[i];
//    		if (p < k) {
//    			int j = i - 1;
//    			for (; j >= 0; --j) {
//        			p *= nums[j];
//        			if (p >= k) break;
//    			}
//    			cnt += i - j;
//    		}
//    		else {
//    		}
//    	}
//    	return cnt;
//    }
	
    public int numSubarrayProductLessThanK(int[] nums, int k) {
    	int cnt = 0;
    	int n = nums.length;
    	
        int p = 1;
        int j = 0;
    	for (int i = 0; i < n; ++i) {
            p *= nums[i];
            if (p < k) {
                cnt += i - j + 1;            
            }
    		else { // p >= k
                for (; j < n; ){
                    p /= nums[j++];
                    if (p < k) break;
                }
                if (p < k) cnt += i - j + 1;
    		}
    	}
    	return cnt;
    }
	
	public static void main(String[] args) {
		SolutionDay22_L0502 day = new SolutionDay22_L0502();
		int[] nums = {10, 5, 2, 6};
		System.out.println(day.numSubarrayProductLessThanK(nums, 100));
	}

}
