package com.daimens.algorithm.february;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Demon Song
 * 386.Lexicographical Numbers
 * Given an integer n,return 1-n in lexicographical order.
 * For example,given 13,return :[1,10,11,12,13,2,3,4,5,6,7,8,9].
 * Please optimize your algorithm to use less time and space. The input size 
 * may be as large as 5000,000.
 *
 */
public class SolutionDay19_386 {
	//1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 
	public List<Integer> lexicalOrder(int n) {
		List<Integer> list = new ArrayList<>(n);
		int curr = 1;
		for (int i = 1; i <= n; i++) {
			list.add(curr);
			if(curr * 10 < n){
				curr *= 10;
			}else if (curr % 10 !=9 && curr + 1 <= n){
				curr ++;
			}else{
				while ((curr /10 ) % 10 == 9){
					curr /= 10;
				}
				curr = curr / 10 + 1;
			}
		}
		return list;   
    }
	
	public static void main(String[] args) {
		SolutionDay19_386 day = new SolutionDay19_386();
		day.lexicalOrder(23);
	}
}
