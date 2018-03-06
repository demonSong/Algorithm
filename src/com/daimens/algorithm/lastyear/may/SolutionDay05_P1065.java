package com.daimens.algorithm.may;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         1065.Wooden Sticks
 * 
 *         Description
 * 
 *         There is a pile of n wooden sticks. The length and weight of each
 *         stick are known in advance. The sticks are to be processed by a
 *         woodworking machine in one by one fashion. It needs some time, called
 *         setup time, for the machine to prepare processing a stick. The setup
 *         times are associated with cleaning operations and changing tools and
 *         shapes in the machine. The setup times of the woodworking machine are
 *         given as follows: (a) The setup time for the first wooden stick is 1
 *         minute. (b) Right after processing a stick of length l and weight w ,
 *         the machine will need no setup time for a stick of length l' and
 *         weight w' if l <= l' and w <= w'. Otherwise, it will need 1 minute
 *         for setup. You are to find the minimum setup time to process a given
 *         pile of n wooden sticks. For example, if you have five sticks whose
 *         pairs of length and weight are ( 9 , 4 ) , ( 2 , 5 ) , ( 1 , 2 ) , (
 *         5 , 3 ) , and ( 4 , 1 ) , then the minimum setup time should be 2
 *         minutes since there is a sequence of pairs ( 4 , 1 ) , ( 5 , 3 ) , (
 *         9 , 4 ) , ( 1 , 2 ) , ( 2 , 5 ) . Input
 * 
 *         The input consists of T test cases. The number of test cases (T) is
 *         given in the first line of the input file. Each test case consists of
 *         two lines: The first line has an integer n , 1 <= n <= 5000 , that
 *         represents the number of wooden sticks in the test case, and the
 *         second line contains 2n positive integers l1 , w1 , l2 , w2 ,..., ln
 *         , wn , each of magnitude at most 10000 , where li and wi are the
 *         length and weight of the i th wooden stick, respectively. The 2n
 *         integers are delimited by one or more spaces. Output
 * 
 *         The output should contain the minimum setup time in minutes, one per
 *         line. Sample Input
 * 
 *         3 5 4 9 5 2 2 1 3 5 1 4 3 2 2 1 1 2 2 3 1 3 2 2 3 1 Sample Output
 * 
 *         2 1 3
 *
 */
public class SolutionDay05_P1065 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int i = 0; i < T; i++){
			int n = in.nextInt();
			int[] l = new int[n];
			int[] w = new int[n];
			for (int j = 0; j< n; j++){
				l[j] = in.nextInt(); 
				w[j] = in.nextInt();
			}
			System.out.println(solve(l, w));
		}
		in.close();
	}

//	public static int solve(int[] l, int[] w){
//		int n = l.length;
//		Pair[] pairs = new Pair[n];
//		for (int i = 0; i < n; i++){
//			pairs[i] = new Pair(l[i],w[i]);
//		}
//		
//		Arrays.sort(pairs, new Comparator<Pair>() {
//			@Override
//			public int compare(Pair o1, Pair o2) {
//				return o1.start != o2.start ? o1.start-o2.start : o1.end - o2.end;
//			}
//		});
//		
//		int[] dp = new int[n];
//		int max = 0;
//		for (int i = 0; i < n; i++){
//			dp[i] = 1;
//			for (int j = 0; j < i; ++j){
//				if (pairs[i].end < pairs[j].end){
//					dp[i] = Math.max(dp[i], dp[j]+1);
//				}
//				max = Math.max(max, dp[i]);
//			}
//		}
//		
//		return max;
//	}
	
	public static int solve(int[] l, int[] w){
		int n = l.length;
		Pair[] pairs = new Pair[n];
		for (int i = 0; i < n; i++){
			pairs[i] = new Pair(l[i],w[i]);
		}
		
		Arrays.sort(pairs, new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				return o1.start != o2.start ? o1.start-o2.start : o1.end - o2.end;
			}
		});
		
		int[] dp = new int[n];
		int len = 0;
		for (int i = 0; i < n; i++){
			int index = Arrays.binarySearch(dp, 0,len,-pairs[i].end);
			if (index < 0)
				index = -(index + 1);
			dp[index] = -pairs[i].end;
			if (index == len)
				len ++;
		}
		
		return len;
	}
}

class Pair{
	int start;
	int end;
	Pair(int start, int end){
		this.start = start;
		this.end = end;
	}
	
	@Override
	public String toString() {
		return "["+start+","+end+"]";
	}
}
