package com.daimens.algorithm.december;

/**
 * 
 * @author Demon Song
 * 345.Reverse Vowels of a String
 * Write a function that takes a string as input and reverse only the vowels of a string
 * Example1:
 * Given s = "hello",return "holle".
 * Example2:
 * Given s = "leetcode",return "leotcede"
 *
 */
public class SolutionDay13_345 {
	//交换怎么交换？ 遇到奇数和偶数的情况，交换是相同的？如何证明
	public String reverseVowels(String s){
		//"a e i o u"
		String vowels = "aeiouAEIOU";
		char[] a = s.toCharArray();
		int i = 0;
		int j = a.length-i-1;
		while (i<j){
			while( i < j && !vowels.contains(a[i]+"")){
				i++;
			}
			while( i < j && !vowels.contains(a[j]+"")){
				j--;
			}
			if(i<j){
				char c =a[i];
				a[i++] = a[j];
				a[j--] = c;
			}
		}
		return new String(a);
	}
}
