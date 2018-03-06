package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 201.Bitwise AND of Numbers Range
 * Given a range [m,n] where 0 <= m <= n <= 2147483647,return the bitwise AND of all numbers in this range,inclusive.
 * For example,given the range[5,7],you should return 4.
 * 
 *
 */
public class SolutionDay13_201 {
//	public int rangeBitwiseAnd(int m, int n) {
//
//		int mask = 0x80000000;
//		int res = 0;
//
//		for (int i = 0; i <= 31; i++){
//			int tmp = 0xffffffff;
//			for (int j = m; j <= n; j++){
//				tmp &= (j & mask);
//			}
//			mask >>>= 1;
//			res |= tmp;
//		}
//		
//        return res;
//        
//    }
	
//	public int rangeBitwiseAnd(int m, int n) {
//		int tmp = 0xffffffff;
//		for (int j = m; j <= n; j++){
//			tmp &= j;
//		}
//		return tmp;
//	}
	
	public int rangeBitwiseAnd(int m, int n) {
		if(m == 0) return 0;
		
		int moveFactor = 1;
		while( m != n){  //只操作第一个元素和最后一个元素，中间元素完全可以不去考虑！！！+++
			m >>= 1;
			n >>= 1;
			moveFactor <<= 1;
		}
		return m * moveFactor;
	}
	
	public static void main(String[] args) {
		SolutionDay13_201 day = new SolutionDay13_201();
		day.rangeBitwiseAnd(0, 1);
	}
}
