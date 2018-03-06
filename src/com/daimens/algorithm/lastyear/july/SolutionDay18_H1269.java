package com.daimens.algorithm.july;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class SolutionDay18_H1269 {
	
	static class Edge{
		int from;
		int to;
		public Edge(int from, int to){
			this.from = from;
			this.to = to;
		}
	}
	
	static List<Edge>[] g;
	static int[] low;
	static int[] dfn;
	static int[] belong;
	static int index;
	static Stack<Integer> stack;
	static boolean[] instack;
	static int sum;
	
	public static void tarjan(int s){
		int j;
		dfn[s] = low[s] = ++index;
		instack[s] = true;
		stack.push(s);
		for (Edge e : g[s]){
			j = e.to;
			if (dfn[j] == 0){
				tarjan(j);
				if (low[j] < low[s]) low[s] = low[j];
			}
			else if (instack[j] && dfn[j] < low[s]){ //无环情况下，走不到这，有环会进入该循环。
				low[s] = dfn[j]; //找到了虫洞，dfn在之前已经被访问过
			}
		}
		
		if (dfn[s] == low[s]){
			sum ++;
			do{
				j = stack.pop();
				instack[j] = false;
				belong[j] = sum;
			}
			while (j != s);
		}
	}
	
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()){
			int N = in.nextInt();
			int M = in.nextInt();
			if (N == 0 && M == 0) continue;
			
			g = new ArrayList[N];
			
			for (int i = 0; i < N; ++i) g[i] = new ArrayList<>();
			for (int i = 0; i < M; ++i){
				int from = in.nextInt();
				int to = in.nextInt();
				from --;
				to --;
				g[from].add(new Edge(from, to));
			}
			
			low = new int[N];
			dfn = new int[N];
			belong = new int[N];
			index = 0;
			stack = new Stack<>();
			instack = new boolean[N];
			sum = 0;
			
			for (int i = 0; i < N; ++i){
				if (dfn[i] == 0){
					tarjan(i);
				}
			}
			if (sum == 1) System.out.println("Yes");
			else System.out.println("No");
		}
		in.close();
	}
}