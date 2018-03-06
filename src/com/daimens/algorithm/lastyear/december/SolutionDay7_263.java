package com.daimens.algorithm.december;

/**
 * 
 * @author Demon Song
 * 263.Ugly Number
 * Write a program to check whether a given number is an ugly number.
 * Ugly numbers are positive numbers whose prime factors only include
 * 2,3,5.For example,6,8 are ugly while 14 is not ugly since it includes
 * another prime factor 7.
 * Note that 1 is typically treated as an ugly number.
 *
 */
public class SolutionDay7_263 {
	public boolean isUgly(int num){
		if (num < 1) return false;
		//不断的除以2，3，5，直到三个数都不能被除尽为止
		int[] factors = {2,3,5};
		while (true){
			int count =0;
			for(int i = 0; i < factors.length;i++){
				count ++;
				if (num % factors[i] ==0){
					count =0;
					num = num / factors[i];
					break;
				}
				if (num ==1){
					return true;
				}
				if (count ==3 && num !=1){
					return false;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		SolutionDay7_263 day7_263 = new SolutionDay7_263();
		System.out.println(day7_263.isUgly(7));
	}
}
