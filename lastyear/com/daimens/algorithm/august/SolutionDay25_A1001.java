package com.daimens.algorithm.august;

import java.util.LinkedList;
import java.util.Queue;

public class SolutionDay25_A1001 {

	public static int findCircleNum(int[][] M) {
		if (M.length == 0)
			return 0;
		int len = M.length;
		int count = 0;
		for (int i = 0; i < len; i++) {
			if (M[i][i] != 0) {
				count++;
				bfs(M, i);
			}
		}
		return count;
	}

	private static void bfs(int[][] M, int row) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(row);
		while (!queue.isEmpty()) {
			int h = queue.poll();
			for (int col = 0; col < M.length; col++) {
				if (M[h][col] != 0) {
					queue.offer(col);
					M[h][col] = 0;
				}
			}
		}
	}

}
