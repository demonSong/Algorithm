package com.daimens.algorithm.january;

/**
 * 
 * @author Demon Song
 * 190.Reverse Bits
 * Reverse bits of a given 32 bits unsigned integer.
 * For example:
 * Given input 43261596(represented in binary as 00000010100101000001111010011100),
 * return 964176192(represented in binary as 00111001011110000010100101000000)
 *
 */
public class SolutionDay21_190 {
	// 十进制 转 二进制 然后二进制转十进制
	public int reverseBits(int n) {
		int result = 0;
		for (int i = 0; i < 32; i++){
			result += n & 1;
			n >>= 1;
			if(i < 31){
				result <<= 1;
			}
		}
        return result;
    }
}
