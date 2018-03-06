package com.daimens.algorithm.december;

/**
 * 
 * @author Demon Song
 * 191.Number of 1 Bits
 * Write a function that takes an unsigned integer and returns the number
 * of '1' bits it has (also known as the Hamming weight).
 * For example, the 32-bit integer '11' has binary representation 00000000000000000000000000001011,
 * so the function should return 3.
 *
 */
public class SolutionDay6_191 {
	
	//计算机体系知识就能解决
	public int hammingWeight(int n){
		//n 还需要转换到unsigned value
		//超过32字节补码表示范围的数，就不在int范围内了。
		int count = 0;
		//循环32为代替比较好
		for (int i =0; i <32;i++){
			if ((n & 0x01) !=0){
				count ++;
			}
			n = n>>1;
		}
		
		//while表达式是有问题的
//		while (n !=0){
//			if ((n & 0x01) !=0){
//				count ++;
//			}
//			n = n>>1;
//		}
		return count;
	}
	
}
