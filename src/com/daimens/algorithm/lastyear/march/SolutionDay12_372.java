package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 372.Super Pow
 * Your task is to calculate a^b mod 1337 where a is a positive integer and b is an extremely large positive integer
 * given in the form of an array.
 * Example 1:
 * a = 2
 * b = [3]
 * 
 * Result: 8
 * Example 2:
 * a = 2
 * b = [1,0]
 * 
 * Result: 1024
 *
 */
public class SolutionDay12_372 {
//	public int superPow(int a, int[] b) {
//		if(b.length == 0) return 0;
//		
//		int sum = b[0];
//		for (int i = 1; i < b.length; i++){
//			sum = sum*10 + b[i];  //也有可能溢出
//		}
//		
//		int res = (int) Math.pow(a % 1337, sum); // 会有溢出问题！
//		
//		
//        return res % 1337;
//    }
	
	
	//discucess 的算法思想还是需要明确表达一下
	
	public int superPow(int a, int[] b) {  
	    int res = 1;  
	    for (int i = 0; i < b.length; i++) {  
	        res = pow(res, 10) * pow(a, b[i]) % 1337;  
	    }  
	    return res;  
	}  
	  
	public int pow(int a, int b) {  
	    if (b == 0) return 1;  
	    if (b == 1) return a % 1337;  
	    return pow(a % 1337, b / 2) * pow(a % 1337, b - b / 2) % 1337;  
	}  
}
