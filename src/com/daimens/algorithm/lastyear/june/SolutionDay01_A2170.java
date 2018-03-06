package com.daimens.algorithm.june;

import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         2170. Marked Ancestor
 * 
 *         Problem F: Marked Ancestor You are given a tree T that consists of N
 *         nodes. Each node is numbered from 1 to N, and node 1 is always the
 *         root node of T. Consider the following two operations on T:
 * 
 *         M v: (Mark) Mark node v. Q v: (Query) Print the index of the nearest
 *         marked ancestor of node v which is nearest to it. Initially, only the
 *         root node is marked. Your job is to write a program that performs a
 *         sequence of these operations on a given tree and calculates the value
 *         that each Q operation will print. To avoid too large output file,
 *         your program is requested to print the sum of the outputs of all
 *         query operations. Note that the judges confirmed that it is possible
 *         to calculate every output of query operations in a given sequence.
 * 
 *         Input The input consists of multiple datasets. Each dataset has the
 *         following format:
 * 
 *         The first line of the input contains two integers N and Q, which
 *         denotes the number of nodes in the tree T and the number of
 *         operations, respectively. These numbers meet the following
 *         conditions: 1 ≤ N ≤ 100000 and 1 ≤ Q ≤ 100000.
 * 
 *         The following N - 1 lines describe the configuration of the tree T.
 *         Each line contains a single integer pi (i = 2, ... , N), which
 *         represents the index of the parent of i-th node.
 * 
 *         The next Q lines contain operations in order. Each operation is
 *         formatted as "M v" or "Q v", where v is the index of a node.
 * 
 *         The last dataset is followed by a line containing two zeros. This
 *         line is not a part of any dataset and should not be processed.
 * 
 *         Output For each dataset, print the sum of the outputs of all query
 *         operations in one line.
 * 
 *         Sample Input 6 3 1 1 2 3 3 Q 5 M 3 Q 5 0 0 Output for the Sample
 *         Input 4
 *
 */
public class SolutionDay01_A2170 {
	
	private static class Union{
		int[] parent;
		int[] height;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		while (true){
			String[] line = in.nextLine().trim().split(" ");
			int N = Integer.parseInt(line[0]);
			int Q = Integer.parseInt(line[1]);
			int[] union = new int[N];
			if (N == 0 && Q == 0) break;
			union[0] = 0;
			for (int i = 1; i <N; i++){
				union[i] = Integer.parseInt(in.nextLine())-1;
			}
			String[][] ops = new String[Q][2];
			for (int i = 0; i < Q; i++){
				line = in.nextLine().trim().split(" ");
				ops[i][0] = line[0];
				ops[i][1] = line[1];
			}
			System.out.println(solve(union, ops));
			
		}
		in.close();
	}
	
	private static int solve(int[] union, String[][] ops){
		
		int res = 0;
		for (int i = 0; i < ops.length; i++){
			if (ops[i][0].equals("M")){
				int pid = Integer.parseInt(ops[i][1])-1;
				mark(union, pid);
			}
			
			if (ops[i][0].equals("Q")){
				int id = Integer.parseInt(ops[i][1])-1;
				res += find(union, id)+1;
			}
		}
		return res;
	}
	
	private static void mark(int[] union, int pid){
		for (int i = 0; i < union.length; i++){
			if (union[i] == pid){
				union[i] = -pid;
			}
		}
	}
	
	private static int find(int[] union, int id){
		int next = union[id];
		while (next < 0){
			next = union[-next];
		}
		return next;
	}
}
