package com.daimens.algorithm.may;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         1862.Stripies
 * 
 *         Description
 * 
 *         Our chemical biologists have invented a new very useful form of life
 *         called stripies (in fact, they were first called in Russian -
 *         polosatiki, but the scientists had to invent an English name to apply
 *         for an international patent). The stripies are transparent amorphous
 *         amebiform creatures that live in flat colonies in a jelly-like
 *         nutrient medium. Most of the time the stripies are moving. When two
 *         of them collide a new stripie appears instead of them. Long
 *         observations made by our scientists enabled them to establish that
 *         the weight of the new stripie isn't equal to the sum of weights of
 *         two disappeared stripies that collided; nevertheless, they soon
 *         learned that when two stripies of weights m1 and m2 collide the
 *         weight of resulting stripie equals to 2*sqrt(m1*m2). Our chemical
 *         biologists are very anxious to know to what limits can decrease the
 *         total weight of a given colony of stripies. You are to write a
 *         program that will help them to answer this question. You may assume
 *         that 3 or more stipies never collide together. Input
 * 
 *         The first line of the input contains one integer N (1 <= N <= 100) -
 *         the number of stripies in a colony. Each of next N lines contains one
 *         integer ranging from 1 to 10000 - the weight of the corresponding
 *         stripie. Output
 * 
 *         The output must contain one line with the minimal possible total
 *         weight of colony with the accuracy of three decimal digits after the
 *         point. Sample Input
 * 
 *         3 72 30 50 Sample Output
 * 
 *         120.000
 *
 */
public class SolutionDay24_P1862 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[] stripe = new int[N];
		for (int i = 0; i < N; i++){
			stripe[i] = in.nextInt();
		}
		System.out.println(solve(stripe));
		in.close();
	}
	
	private static double solve(int[] stripe){
		PriorityQueue<Double> queue = new PriorityQueue<>(new Comparator<Double>() {
			@Override
			public int compare(Double o1, Double o2) {
				return (o2-o1) > 0 ? 1 : -1;
			}
		});
		
		for (int i = 0; i < stripe.length; i++){
			queue.offer((double)stripe[i]);
		}
		
		while (queue.size() != 1){
			double m1 = queue.poll();
			double m2 = queue.poll();
			queue.offer(collide(m1, m2));
		}
		
		return queue.peek();
	}
	
	private static double collide(double m1, double m2){
		return 2 * Math.sqrt(m1 * m2);
	}
}
