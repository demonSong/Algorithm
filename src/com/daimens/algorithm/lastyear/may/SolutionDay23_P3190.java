package com.daimens.algorithm.may;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         3190.Stall Reservations
 * 
 *         Description
 * 
 *         Oh those picky N (1 <= N <= 50,000) cows! They are so picky that each
 *         one will only be milked over some precise time interval A..B (1 <= A
 *         <= B <= 1,000,000), which includes both times A and B. Obviously, FJ
 *         must create a reservation system to determine which stall each cow
 *         can be assigned for her milking time. Of course, no cow will share
 *         such a private moment with other cows.
 * 
 *         Help FJ by determining: The minimum number of stalls required in the
 *         barn so that each cow can have her private milking period An
 *         assignment of cows to these stalls over time Many answers are correct
 *         for each test dataset; a program will grade your answer. Input
 * 
 *         Line 1: A single integer, N
 * 
 *         Lines 2..N+1: Line i+1 describes cow i's milking interval with two
 *         space-separated integers. Output
 * 
 *         Line 1: The minimum number of stalls the barn must have.
 * 
 *         Lines 2..N+1: Line i+1 describes the stall to which cow i will be
 *         assigned for her milking period. Sample Input
 * 
 *         5 1 10 2 4 3 6 5 8 4 7 Sample Output
 * 
 *         4 1 2 3 2 4
 *
 */
public class SolutionDay23_P3190 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[][] cows = new int[n][2];
		for (int i = 0; i < n; i++){
			cows[i][0] = in.nextInt();
			cows[i][1] = in.nextInt();
		}
		solve(cows, n);
		in.close();
	}
	
	private static class MilkTime{
		int start;
		int end;
		int id;
		public MilkTime(int start, int end,int id) {
			this.start = start;
			this.end = end;
			this.id = id;
		}
	}
	
	private static class Stall{
		int end;
		int id;
		public Stall(int end, int id){
			this.end = end;
			this.id = id;
		}
		
		@Override
		public String toString() {
			return "[end: "+end+" id: "+id+"]";
		}
	}
	
	private static void solve(int[][] cows, int n){
		MilkTime[] milks = new MilkTime[n];
		for (int i = 0; i < n; i++){
			milks[i] = new MilkTime(cows[i][0], cows[i][1], i);
		}
		
		Arrays.sort(milks,new Comparator<MilkTime>() {
			@Override
			public int compare(MilkTime o1, MilkTime o2) {
				return o1.start -  o2.start;
			}
		});
		
		PriorityQueue<Stall> queue = new PriorityQueue<>(new Comparator<Stall>() {
			@Override
			public int compare(Stall o1, Stall o2) {
				return o1.end - o2.end;
			}
		});
		
		int[] result = new int[n];
		for (int i = 0; i < n; i++){
			if (i == 0){
				put(queue, milks[i], true, result);
				continue;
			}
			if (milks[i].start <= queue.peek().end){
				put(queue, milks[i], true, result);
			}else{
				put(queue, milks[i], false, result);
			}
		}
		System.out.println(queue.size());
		for (int i = 0; i < n; i++){
			System.out.println(result[i]);
		}
	}
	
	private static void put(PriorityQueue<Stall> queue, MilkTime cow, boolean isNew, int[] result){
		if(isNew){
			int id = queue.size() + 1;
			result[cow.id] = id;
			Stall stall = new Stall(cow.end, id);
			queue.offer(stall);
		}else{
			Stall stall = queue.poll();
			stall.end = cow.end;
			result[cow.id] = stall.id;
			queue.offer(stall);
		}
	}
}
