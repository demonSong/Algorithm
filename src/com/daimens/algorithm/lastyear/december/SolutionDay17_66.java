package com.daimens.algorithm.december;

/**
 * 
 * @author Demon Song
 * 66.Plus One
 * Given a non-negative number represented as an array of digits,plus one to the number.
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 */
public class SolutionDay17_66 {
	
	/**
	 * 
	 * @param digits
	 * @return
	 * 比如说9999就是[9,9,9,9]当对这个数加一的时候，将这个数用数组的形式返回
	 */
	public int[] plusOne(int[] digits) {
		for (int i = digits.length-1; i>=0; i--){
			if (digits[i] ==9){
				digits[i] =0;
			}else{
				++ digits[i]; // 后一位相加后 直接返回就可以了
				return digits; //遍历完所有的数值后，这种情况就是所有的digits都为9
			}
		}
		//把 1 加入digits[0]中
		int[] results = new int[digits.length+1];
		results[0] =1;
		for (int i = 1; i<results.length;i++){
			results[i] = digits[i-1];
		}
		return results;
    }
	
	public static void main(String[] args) {
		SolutionDay17_66 day17_66 = new SolutionDay17_66();
		int[] digits = {1,2,3,4,5};
		day17_66.plusOne(digits);
	}
	
}
