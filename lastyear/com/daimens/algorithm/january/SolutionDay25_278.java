package com.daimens.algorithm.january;

/**
 * 
 * @author Demon Song
 * 278.First Bad Version
 * You are a product manager and currently leading a team to develop a new product. 
 * Unfortunately, the latest version of your product fails the quality check. 
 * Since each version is developed based on the previous version, 
 * all the versions after a bad version are also bad.
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, 
 * which causes all the following ones to be bad.You are given an API bool isBadVersion(version) 
 * which will return whether version is bad. Implement a function to find the first bad version. 
 * You should minimize the number of calls to the API.
 *
 */
public class SolutionDay25_278 {
	/*public int firstBadVersion(int n) {
		//从第一个 开始：
		int start = 1;
		int end = n;
		int mid = (start + end) /2;
		if(isBadVersion(start)) return start;
		if(!isBadVersion(end)) return -1;
		
		while(Math.abs(start - end) > 2){
			//从左边开始
			if(isBadVersion(mid)){
				end = mid;
				mid = (start + end)/2;
			}
			else{//从右边开始
				start = mid;
				mid = (start + end)/2;
			}
			
		}
		
		for (int i = start; i <= end; i++){
			if(isBadVersion(i)){
				return i;
			}
		}
		return -1;
    }*/
	
	public int firstBadVersion(int n) {
		int low = 1,high = n;
		while(low < high){
			int mid = low +(high - low) /2;
			if(isBadVersion(mid)){
				high = mid;
			}else{
				low = mid +1;
			}
		}
		return low;
    }
	
	private boolean isBadVersion(int n){
		return false;
	}
}
