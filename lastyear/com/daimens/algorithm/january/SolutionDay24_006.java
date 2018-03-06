package com.daimens.algorithm.january;


/**
 * 
 * @author Demon Song
 * 6.ZigZag Conversion
 * The String "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * string convert(string text,int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 *
 */
public class SolutionDay24_006 {
	public String Convert(String s, int numRows){
		int len = s.length();
		if(len == 0 || numRows < 2) return s;
		
		
		//>= 2 的情况
		String ret ="";
		int lag = 2*numRows -2;
		for(int i=0; i < numRows; i++){
			for(int j=i; j < len; j+= lag){
				ret += s.charAt(j);
				
				if(i > 0 && i < numRows -1){
					int t = j + lag - 2*i;
					if( t < len){
						ret += s.charAt(t);
					}
				}
			}
		}
		return ret;
	}
}
