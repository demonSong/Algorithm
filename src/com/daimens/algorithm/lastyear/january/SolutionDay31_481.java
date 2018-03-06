package com.daimens.algorithm.january;

/**
 * 
 * @author Demon Song
 * 481.Magical String
 * A magical string S consists of only '1' and '2' and obeys the following rules:
 * The string S is magical because concatenating the number of contiguous occurrences 
 * of characters '1' and '2' generates the string S itself.
 * The first few elements of string S is the following: S = "1221121221221121122……"
 * If we group the consecutive '1's and '2's in S, it will be:
 * 1 22 11 2 1 22 1 22 11 2 11 22 ......
 * and the occurrences of '1's or '2's in each group are:
 * 1 2	2 1 1 2 1 2 2 1 2 2 ......
 * You can see that the occurrence sequence above is the S itself.
 * Given an integer N as input, return the number of '1's in the first N number in the magical string S.
 * Note: N will not exceed 100,000.
 * Example 1:
 * Input: 6
 * Output: 3
 * Explanation: The first 6 elements of magical string S is "12211" and it contains three 1's, so return 3.
 *
 */
public class SolutionDay31_481 {
	public int magicalString(int n) {
		if(n <= 0) return 0;
		if(n <= 3) return 1;
		int[] list = new int[n+1];
		list[0] = 1;list[1] = 2;list[2]=2;
		int start = 2,end = 3;
		while(end < n){
			if(list[start]==1) {  
	            list[end]= list[end-1]==1?2:1;  
	            end += 1;
	        }  
	        else{  
	            list[end]= list[end-1]==1?2:1;  
	            list[end+1]= list[end-1]==1?2:1;
	            end += 2;
	        }  
	        start++;  
		}
		
		int count = 0;
		for(int temp : list){
			if (temp == 1){
				count ++;
			}
		}
		if(end != n){
			count = list[end-1] == 1 ? count-1:count;
		}
		return count;
		
    }
	
//	public int magicalString(int n){
//		String s = "122";
//		int index = 2,count=0;
//		while(s.length() < n){
//			char[] cs = s.toCharArray();
//			char lastCharacter = cs[s.length()-1];
//			if(cs[index] == '1'){
//				//遇1变
//				s += (lastCharacter == '1' ? '2':'1');
//				index ++;
//			}else{
//				//遇2的情况稍微复杂一点
//				
//				s += (lastCharacter == '1') ? "22":"11";
//				index ++;
//			}
//		}
//		
//		for(char temp : s.toCharArray()){
//			if (temp == '1'){
//				count ++;
//			}
//		}
//		
//		if(s.length() != n){
//			count = s.toCharArray()[s.length()-1] == '1'? count-1:count;
//		}
//		return count;
//	}
	
	public static void main(String[] args) {
		SolutionDay31_481 day31_481 = new SolutionDay31_481();
		System.out.println(day31_481.magicalString(4));
	}
}
