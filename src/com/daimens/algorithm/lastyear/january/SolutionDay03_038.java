package com.daimens.algorithm.january;

import com.sun.glass.ui.Cursor;

/**
 * 
 * @author Demon Song
 * 38. Count and Say
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.
 * Note: The sequence of integers will be represented as a string.
 *
 */
public class SolutionDay03_038 {
	public String countAndSay(int n) {
		if(n ==1){
			return "1";
		}
		else{
			String output = countAndSay(n-1);
			String result = "";
			int index =0;
			while(index < output.length()){
				char current = output.toCharArray()[index];
				int cursor = index;
				int count = 0;
				while(cursor<output.length() && output.toCharArray()[cursor] == current){
					cursor ++;
					count ++;
				}
				char number = (char) (count +'0');
				result +=number;
				result +=current;
				index +=count;
			}
			return result;
		}
    }
	
	public static void main(String[] args) {
		SolutionDay03_038 day03_038 = new SolutionDay03_038();
		day03_038.countAndSay(2);
	}
}
