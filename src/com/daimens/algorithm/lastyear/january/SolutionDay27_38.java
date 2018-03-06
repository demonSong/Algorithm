package com.daimens.algorithm.january;

/**
 * 
 * @author Demon Song
 * 338.Counting Bits
 * Given a non negative integer number num. For every numbers i in the range  0 ≤ i ≤ num 
 * calculate the number of 1's in their binary representation and return them as an array.
 * Example:
 * For num = 5 you should return [0,1,1,2,1,2]
 * Follow up:
 * 1. It is very easy to come up with a solution with run time O(n*sizeof(integer)).But you can 
 * do it in linear time O(n)/possibly in a single pass?
 * 2. Space complexity should be O(n).
 * 3. Can you do it like a boss? Do it without using any builtin function __builtion_procount in C++ 
 * or in any other language.
 */
public class SolutionDay27_38 {
	
	public int[] countBits(int num){
		int[] ret = new int[num+1];
		
		for (int i = 0; i <= num; i++){
			if(i == 0){
				ret[i] = 0;
			}
			if(i == 1){
				ret[i] = 1;
			}
			
			if(isPowerOfTwo(i)){
				ret[i] = 1;
			}
			else if(i !=0){
				int j = (int) (Math.log(i) / Math.log(2));
				ret[i] = 1 + ret[i-(int) Math.pow(2, j)];
			}
		}
		return ret;
	}
	
	public boolean isPowerOfTwo(int n){
        int count =0;
        while (n >0){
	        count += (n&(0x01));
	        n >>=1;
        }
        return count ==1;
    }
}
