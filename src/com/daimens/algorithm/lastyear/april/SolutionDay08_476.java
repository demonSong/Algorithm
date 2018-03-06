package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 476.Number Complement
 * Given a positive integer,output its complement number. The complement
 * strategy is to flip the bits of its binary representation.
 * Note:
 * 1. The given integer is guaranteed to fit within the range of a 32-bit signed integer.
 * 2. You could assume no leading zero bit in the integer's binary representation.
 * 
 * Example 1:
 * Input: 5
 * Output: 2
 * Explanation: The binary representation of 5 is 101 (no leading zero bits),and its complement is 010. So you need to output 2.
 * 
 * Example 2:
 * Input: 1
 * Output: 0
 * Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
 * 
 * 
 *
 */
public class SolutionDay08_476 {
	
	public int findComplement(int num) {
		
		String ss = Integer.toString(num,2);
		
		int ans = 0;
		for (int i = 0; i < ss.length(); i++){
			int tmp = 1;
			if (ss.charAt(i) == '1'){
				tmp = 0;
			}
			ans <<= 1;
			ans = ans | tmp ;
		}
		
		return ans;
    }
	
	public static void main(String[] args) {
		SolutionDay08_476 day = new SolutionDay08_476();
		day.findComplement(5);
	}

}
