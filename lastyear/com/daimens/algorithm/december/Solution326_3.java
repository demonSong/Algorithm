package com.daimens.algorithm.december;

/**
 * 
 * @author Demon Song
 * 326.Power of Three
 * Given an integer,write a function to determine if it is a power of three.
 *
 */
public class Solution326_3 {
	
	//补码表示 32位表示范围 
	public static final int MIN_VALUE = 0X80000000;
	
	//2进制是计算机中表达的方式，但3的power可以用3进制表示，再用2进制研究是寻找不出有价值的规律的
	public boolean isPowerOfThree(int n){
		if (n == 0)	return false;
//		while(n !=1){
//			if (n % 3 ==0){
//				n = n/3;
//			}else{
//				return false;
//			}
//		}
//		
//		return true;
		//可以整合在一起
		while (n % 3 ==0){
			n =n/3;
		}
		
		return n==1;
	}
	
//	// 转换成3进制表示方法
//	public String toString(int n,int radix){
//		if (radix < 2 || radix >36){
//			radix = 10;
//		}
//		
//		
//		
//		
//	}
//	
//	public String toString(int i){
//		
//	}
}


