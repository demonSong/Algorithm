package com.daimens.algorithm.december;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Demon Song
 * Write an algorithm to determine if a number is "happy".
 * A happy number is a number defined by the following process:
 * Starting with any positive integer, replace the number by the sum of the squares of its digits, 
 * and repeat the process until the number equals 1 (where it will stay),
 * or it loops endlessly in a cycle which does not include 1. 
 * Those numbers for which this process ends in 1 are happy numbers.
 * Example: 19 is a happy number
 * 1^2 + 9^2 = 82
 * 8^2+2^2 = 68
 * 6^2+8^2 = 100
 * 1^2+0^2+0^2 = 1
 */
public class Solution202_2 {
	
	//如何在递归的情况下，记录一个全局变量
	public boolean isHappy(int n) {
		if (n <= 0) return false;
		
		List<Integer> number = new ArrayList<Integer>();
		number.add(n);
		int num = n;
		//为何要用递归的方式去循环呢？直接以while实现
		while (true){
			int sum =0;
			for (char temp : Integer.toString(num).toCharArray()){
				int a = Integer.valueOf(temp-'0');
				sum += a*a;
			}
			if (sum ==1){ 
				return true;
			}else if (number.contains(sum)){
				return false;
			}else {
				number.add(sum);
				num =sum;
			}
		}
		
		
//		for (Character temp : num.toCharArray()){
//			int a = Integer.valueOf(temp-'0');
//			sum += a*a;
//		}
//        if(sum ==1) return true;
//        //程序终止条件是什么？
//        else if (number.contains(sum)){
//        	return false;
//        }
//        else{
//        	return isHappy(sum);
//        }
    }
	
	public static void main(String[] args) {
		Solution202_2 s202 = new Solution202_2();
		s202.isHappy(1);
	}
}
