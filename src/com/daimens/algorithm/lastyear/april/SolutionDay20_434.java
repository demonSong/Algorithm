package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong 
 * 
 * 434.Number of Segments in a string
 * 
 *         Count the number of segments in a string, where a segment is defined
 *         to be a contiguous sequence of non-space characters.
 * 
 *         Please note that the string does not contain any non-printable
 *         characters.
 * 
 *         Example:
 * 
 *         Input: "Hello, my name is John" Output: 5
 *
 */
public class SolutionDay20_434 {
//	public int countSegments(String s) {
//		if(s.length() == 0) return 0;
//		
//		char[] ss = s.toCharArray();
//		
//		int count = 0;
//		for (int i = 0; i < s.length(); i++){
//			if (ss[i] != ' '){
//				dfs(ss,i);
//				count++;
//			}
//		}
//		
//		return count;
//    }
//	
//	private void dfs(char[] ss,int start){
//		if(start == ss.length || ss[start] == ' ') return;
//		ss[start] = ' ';
//		dfs(ss, start+1);
//	}
	
	public int countSegments(String s) {
	    int res=0;
	    for(int i=0; i<s.length(); i++)
	        if(s.charAt(i)!=' ' && (i==0 || s.charAt(i-1)==' '))
	            res++;        
	    return res;
	}
	
	public static void main(String[] args) {
		SolutionDay20_434 day =new SolutionDay20_434();
		day.countSegments(", , , ,        a, eaefa");
	}
}
