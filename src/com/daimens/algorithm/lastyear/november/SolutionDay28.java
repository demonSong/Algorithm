package com.daimens.algorithm.november;

/**
 * 
 * @author Demon Song 
 * 405.Convert a Number to Hexadecimal 
 * Example 1:
 * Input: 26
 * Output: "1a" 
 *      
 * Example 2:
 * Input: -1
 * Output: "ffffffff"
 */
public class SolutionDay28 {
	public String toHex(int num) {  
	    if(num<10 && num>=0) return Integer.toString(num);  
	    char[] hex = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};  
	    StringBuilder sb = new StringBuilder();  
	    for(int i=0;i<8 && num!=0;i++) {  //为什么操作八次就行之有效 int 总共就32个bit 所以操作八次即可
	        sb.insert(0,hex[num & 15]); 
	        num = num>>4;  
	    }  
	    return sb.toString();  
	}  
	
	public static void main(String[] args) {
		SolutionDay28 day28 = new SolutionDay28();
		day28.toHex(256);
	}
}
