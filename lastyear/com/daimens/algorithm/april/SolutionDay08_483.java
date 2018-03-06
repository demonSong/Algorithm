package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 483.Smallest Good Base
 * For an integer n. we call k>= 2 a good base of n, if all digits of n base k are 1.
 * Now given a string representing n, you should return the smallest good base of n in string format.
 * Example 1:
 * Input:"13"
 * Output: "3"
 * Explanation: 13 base 3 is 111.
 * 
 * Example 2:
 * Input:"4681"
 * Output:"8"
 * Explanation: 4681 base 8 in 11111.
 * 
 * Example 3:
 * Input: "1000000000000000000"
 * Output: "999999999999999999"
 * Explanation: 1000000000000000000 base 999999999999999999 is 11.
 * 
 * Note:
 * 1. The range of n is [3,10^18]
 * 2. The string representing n is always valid and will not have leading zeros.
 *
 */
public class SolutionDay08_483 {
	public String smallestGoodBase(String n) {
		
		long num = Long.parseLong(n);
		
		for (long i = 64; i >= 2; i--){
			long r = (long)(Math.pow(num, 1.0 / i) + 1);
			long index = binarySearch(2, r, i, num);
			if (index != -1)
				return String.valueOf(index);
		}
		
		return String.valueOf(num-1);
		
	}
	
	private long binarySearch(long start, long end, long len, long num){
		
		while (start < end){
			long mid = start + (end - start) / 2;
			
			//用math.pow 一点都不靠谱
			long ans = 0, cur = 1;
            for (int i = 0; i <= len; i++) {
                ans += cur;
                cur *= mid;
            }
			if (ans == num) return mid;
            else if (ans < num){
				start = mid + 1;
			}else{
				end = mid;
			}
		}
		
		return -1;
	}
	
//	public String smallestGoodBase(String n) {
//        long num = 0;
//        for (char c : n.toCharArray()) num = num * 10 + c - '0';
//        
//        long x = 1;
//        for (int p = 64; p >= 1; p--) {
//            if ((x << p) < num) {
//                long k = helper(num, p);
//                if (k != -1) return String.valueOf(k);
//            }
//        }
//        return String.valueOf(num - 1);
//    }
//    
//    private long helper(long num, int p) {
//        long l = 1, r = (long)(Math.pow(num, 1.0/p) + 1);
//        while (l < r) {
//            long mid = l + (r - l) / 2;
//            long sum = 0, cur = 1;
//            for (int i = 0; i <= p; i++) {
//                sum += cur;
//                cur *= mid;
//            }
//            if (sum == num) return mid;
//            else if (sum > num) r = mid;
//            else l = mid + 1;
//        }
//        return -1;
//    }
	
	public static void main(String[] args) {
		SolutionDay08_483 day = new SolutionDay08_483();
		day.smallestGoodBase("3541");
	}
}
