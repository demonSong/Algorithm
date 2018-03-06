package com.daimens.algorithm.june;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         3421.X-factor Chains
 * 
 *         Description
 * 
 *         Given a positive integer X, an X-factor chain of length m is a
 *         sequence of integers,
 * 
 *         1 = X0, X1, X2, …, Xm = X
 * 
 *         satisfying
 * 
 *         Xi < Xi+1 and Xi | Xi+1 where a | b means a perfectly divides into b.
 * 
 *         Now we are interested in the maximum length of X-factor chains and
 *         the number of chains of such length.
 * 
 *         Input
 * 
 *         The input consists of several test cases. Each contains a positive
 *         integer X (X ≤ 220).
 * 
 *         Output
 * 
 *         For each test case, output the maximum length and the number of such
 *         X-factors chains.
 * 
 *         Sample Input
 * 
 *         2 3 4 10 100 Sample Output
 * 
 *         1 1 1 1 2 1 2 2 4 6
 *
 */
public class SolutionDay21_P3421 {
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()){
			int num = in.nextInt();
			System.out.println(solve(num));
		}
		in.close();
	}
	
	public static String solve(int num){
		Map<Integer, Integer> factors = primeFactor(num);
		long max = 0;
		for (int key : factors.keySet()){
			max += factors.get(key);
		}
		long cnt = 1;
		for (int key : factors.keySet()){
			cnt *= factor(factors.get(key));
		}
		cnt = factor(max) / cnt;
		return max + " " + cnt;
	}
	
	public static long factor(long n){
		long res = 1;
		for (long i = 1; i <= n; ++i){
			res *= i;
		}
		return res;
	}
	
	public static Map<Integer,Integer> primeFactor(int num){
		Map<Integer, Integer> factors = new HashMap<Integer, Integer>();
		for (int i = 2; i * i <= num; ++i){
			while (num % i == 0){
				if (!factors.containsKey(i)) factors.put(i, 0);
				factors.put(i, factors.get(i) + 1);
				num = num / i;
			}
		}
		if (num != 1){
			factors.put(num, 1);
		}
		return factors;
	}
}
