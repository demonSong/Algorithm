package com.daimens.algorithm.september;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SolutionDay13_P0001 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			int m = in.nextInt();
			int[][] pre = new int[m][2];
			for (int i = 0; i < m; ++i) {
				pre[i] = new int[] {in.nextInt(),in.nextInt()};
			}
			
			int[] ans = findOrder(n, pre);
			if (ans.length == 0) System.out.println("None");
			else {
				for (int i = 0; i < ans.length; ++i) {
					System.out.print(ans[i] + (i + 1 == ans.length ? "\n" : ","));
				}
			}
		}
		in.close();
	}
	

	public static int[] findOrder(int num, int[][] prerequisites) {
		int[] ans = new int[num];
		int[] indegree = new int[num];
		int[][] matrix = new int[num][num];

		for (int[] pre : prerequisites) {
			int prepr = pre[1];
			int ready = pre[0];
			indegree[ready]++;
			matrix[prepr][ready] = 1;
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < num; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);
			}
		}

		int count = 0;
		while (!queue.isEmpty()) {
			int v = queue.poll();
			ans[count++] = v;
			for (int i = 0; i < num; i++) {
				if (matrix[v][i] == 1) {
					indegree[i]--;
					if (indegree[i] == 0)
						queue.offer(i);
				}
			}
		}
		return count == num ? ans : new int[0];
	}
}
